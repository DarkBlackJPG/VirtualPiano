package piano;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import javax.sound.midi.MidiUnavailableException;

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
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("OK Wtf");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

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
                if (keyEvent.getCode() == KeyCode.SHIFT) {
                    shiftPressed = true;
                }
                if (keyEvent.getCode() != KeyCode.SHIFT && shiftPressed) {
                    char c = keyEvent.getCode().getName().toLowerCase().charAt(0);
                    if(tryParse(keyEvent.getCode().getName())){
                        int temp = Integer.parseInt(keyEvent.getCode().getName());
                        c = map[temp];
                    }

                    player.play(Composition.charToIntMapping.get(c));

                } else if (keyEvent.getCode() != KeyCode.SHIFT) {
                    char c = keyEvent.getCode().getName().toLowerCase().charAt(0);
                    player.play(Composition.charToIntMapping.get(c));
                }
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
