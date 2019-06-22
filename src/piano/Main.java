package piano;

import com.sun.prism.impl.Disposer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import javax.management.timer.Timer;
import javax.sound.midi.MidiUnavailableException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Main extends Application {

    private static boolean shiftPressed = false;

    private Player player;
    {
        try {
            player = new Player(1);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    private boolean tryParse(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }
    private Controller myController;
    public void setMyController(Controller myController){
        this.myController = myController;
    }


    private Controller.Recorder recorder;

    public void setRecorder(Controller.Recorder recorder)
    {
        this.recorder = recorder;
    }

    private ArrayList<Character> allowedKeys = new ArrayList<>(){{
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

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("virtualpiano.net");
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

            if (!Controller.autoplayOn) {
                if (new KeyCodeCombination(KeyCode.getKeyCode(keyEvent.getCode().getName()), KeyCombination.SHIFT_DOWN).match(keyEvent)) {
                    if(tryParse(keyEvent.getCode().getName())){
                        char c = keyEvent.getCode().getName().toLowerCase().charAt(0);
                        if (allowedKeys.contains(c)){
                            Integer a = Composition.charToIntMapping.get(map[Integer.parseInt(keyEvent.getCode().getName())]);
                            if(a != null)
                                controller.playNote(a);
                        }


                    } else {
                        if (keyEvent.getCode() != KeyCode.SHIFT) {
                            Character c = keyEvent.getCode().getName().toUpperCase().charAt(0);
                            if (allowedKeys.contains(c)) {

                                controller.playNote(Composition.charToIntMapping.get(c));

                            }

                        }
                    }
                } else if (keyEvent.getCode() != KeyCode.SHIFT) {
                    char c = keyEvent.getCode().getName().toLowerCase().charAt(0);
                    if (allowedKeys.contains(c))
                        controller.playNote(Composition.charToIntMapping.get(c));
                }

            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
