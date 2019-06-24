package piano;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.sound.midi.MidiUnavailableException;

import java.util.ArrayList;

public class Main extends Application {

    private static boolean shiftPressed = false;

    private Player player;
    private Controller myController;
    private Controller.Recorder recorder;
    private ArrayList<Character> allowedKeys = new ArrayList<>() {{
        add('1');
        add('2');
        add('3');
        add('4');
        add('5');
        add('6');
        add('7');
        add('8');
        add('9');
        add('0');
        add('q');
        add('w');
        add('e');
        add('r');
        add('t');
        add('y');
        add('u');
        add('i');
        add('o');
        add('p');
        add('a');
        add('s');
        add('d');
        add('f');
        add('g');
        add('h');
        add('j');
        add('k');
        add('l');
        add('z');
        add('x');
        add('c');
        add('v');
        add('b');
        add('n');
        add('!');
        add('@');
        add('$');
        add('%');
        add('^');
        add('*');
        add('(');
        add('Q');
        add('W');
        add('E');
        add('T');
        add('Y');
        add('I');
        add('O');
        add('P');
        add('S');
        add('D');
        add('G');
        add('H');
        add('J');
        add('L');
        add('Z');
        add('C');
        add('V');
        add('B');
    }};
    private ArrayList<KeyCode> pressedkeys = new ArrayList<>();

    {
        try {
            player = new Player(1);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private boolean tryParse(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    void setMyController(Controller myController) {
        this.myController = myController;
    }

    void setRecorder(Controller.Recorder recorder) {
        this.recorder = recorder;
    }
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("\uD83D\uDCA9 project No.2");
        primaryStage.getIcons().add(new Image("file:./resource/images/piano.png"));
        Controller controller = loader.getController();
        controller.setModel(this);
        player = controller.getPlayer();
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        Controller.setPrimaryStage(primaryStage);


        root.setOnKeyPressed(keyEvent -> {
            char[] map = new char[]{
                    ')', //0
                    '!', //1
                    '@', //2
                    '#', //3
                    '$', //4
                    '%', //5
                    '^', //6
                    '&', //7
                    '*', //8
                    '(' //9
            };

            if (!Controller.autoplayOn && keyEvent.getCode().getName().length() == 1
                    && !pressedkeys.contains(keyEvent.getCode())
                    && (allowedKeys.contains(keyEvent.getCode().getName().toLowerCase().charAt(0))
                    || allowedKeys.contains(keyEvent.getCode().getName().charAt(0)))) {

                if (keyEvent.getCode() != KeyCode.SHIFT && new KeyCodeCombination(KeyCode.getKeyCode(keyEvent.getCode().getName()), KeyCombination.SHIFT_DOWN).match(keyEvent)) {
                    if (tryParse(keyEvent.getCode().getName())) {
                        char c = keyEvent.getCode().getName().toLowerCase().charAt(0);
                        if (allowedKeys.contains(c)) {
                            Integer a = Composition.charToIntMapping.get(map[Integer.parseInt(keyEvent.getCode().getName())]);
                            if (a != null) {
                                pressedkeys.add(keyEvent.getCode());
                                controller.playNote(a);
                                controller.colorButton(controller.charButtonBinding.get(map[Integer.parseInt(keyEvent.getCode().getName())]));
                            }
                        }


                    } else {
                        Character c = keyEvent.getCode().getName().toUpperCase().charAt(0);
                        if (allowedKeys.contains(c)) {
                            pressedkeys.add(keyEvent.getCode());
                            controller.colorButton(controller.charButtonBinding.get(c));
                            controller.playNote(Composition.charToIntMapping.get(c));

                        }

                    }
                } else if (keyEvent.getCode() != KeyCode.SHIFT) {
                    char c = keyEvent.getCode().getName().toLowerCase().charAt(0);
                    if (allowedKeys.contains(c)) {
                        pressedkeys.add(keyEvent.getCode());
                        controller.colorButton(controller.charButtonBinding.get(c));
                        controller.playNote(Composition.charToIntMapping.get(c));
                    }

                }
            }
        });

        root.setOnKeyReleased(keyEvent -> {
            char[] map = new char[]{
                    ')', //0
                    '!', //1
                    '@', //2
                    '#', //3
                    '$', //4
                    '%', //5
                    '^', //6
                    '&', //7
                    '*', //8
                    '(' //9
            };
            if (!Controller.autoplayOn && keyEvent.getCode().getName().length() == 1 && pressedkeys.contains(keyEvent.getCode()) && (allowedKeys.contains(keyEvent.getCode().getName().toLowerCase().charAt(0)) || allowedKeys.contains(keyEvent.getCode().getName().charAt(0)))) {
                if (keyEvent.getCode() != KeyCode.SHIFT && new KeyCodeCombination(KeyCode.getKeyCode(keyEvent.getCode().getName()), KeyCombination.SHIFT_DOWN).match(keyEvent)) {
                    if (tryParse(keyEvent.getCode().getName())) {
                        char c = keyEvent.getCode().getName().toLowerCase().charAt(0);
                        if (allowedKeys.contains(c)) {
                            Integer a = Composition.charToIntMapping.get(map[Integer.parseInt(keyEvent.getCode().getName())]);
                            if (a != null) {
                                controller.unColorButton(controller.charButtonBinding.get(map[Integer.parseInt(keyEvent.getCode().getName())]));
                                for (int i = 0; i < pressedkeys.size(); i++) {
                                    if (pressedkeys.get(i) == keyEvent.getCode()) {
                                        pressedkeys.remove(i);
                                        break;
                                    }
                                }
                            }
                        }


                    } else {
                        Character c = keyEvent.getCode().getName().toUpperCase().charAt(0);
                        if (allowedKeys.contains(c)) {
                            controller.unColorButton(controller.charButtonBinding.get(c));
                            for (int i = 0; i < pressedkeys.size(); i++) {
                                if (pressedkeys.get(i) == keyEvent.getCode()) {
                                    pressedkeys.remove(i);
                                    break;
                                }
                            }

                        }
                    }
                } else if (keyEvent.getCode() != KeyCode.SHIFT) {
                    char c = keyEvent.getCode().getName().toLowerCase().charAt(0);
                    if (allowedKeys.contains(c)) {
                        controller.unColorButton(controller.charButtonBinding.get(c));
                        for (int i = 0; i < pressedkeys.size(); i++) {
                            if (pressedkeys.get(i) == keyEvent.getCode()) {
                                pressedkeys.remove(i);
                                break;
                            }
                        }
                    }

                }
            }

        });
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    }
}
