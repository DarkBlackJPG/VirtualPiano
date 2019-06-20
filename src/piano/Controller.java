package piano;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;

public class Controller {
    public Button E5;
    public Button C2;
    public Button D2;
    public Button E2;
    public Button F2;
    public Button G2;
    public Button A2;
    public Button B2;
    public Button CS2;
    public Button DS2;
    public Button FS2;
    public Button GS2;
    public Button AS2;
    public Button C3;
    public Button D3;
    public Button E3;
    public Button F3;
    public Button G3;
    public Button A3;
    public Button B3;
    public Button CS3;
    public Button DS3;
    public Button FS3;
    public Button GS3;
    public Button AS3;
    public Button C4;
    public Button D4;
    public Button E4;
    public Button F4;
    public Button G4;
    public Button AS6;
    public Button GS6;
    public Button FS6;
    public Button DS6;
    public Button CS6;
    public Button B6;
    public Button A6;
    public Button G6;
    public Button F6;
    public Button E6;
    public Button D6;
    public Button C6;
    public Button AS5;
    public Button GS5;
    public Button FS5;
    public Button DS5;
    public Button CS5;
    public Button B5;
    public Button A5;
    public Button G5;
    public Button F5;
    public Button D5;
    public Button C5;
    public Button AS4;
    public Button GS4;
    public Button FS4;
    public Button DS4;
    public Button CS4;
    public Button B4;
    public Button A4;


    public Button Record;
    public Button Play;
    public Button Stop;
    public Button Pause;
    public Button AutoPlay;
    public Canvas MyCanvas;

    public ArrayList<Button> keyboardReferences;
    private Player player;


    {
        try {
            player = new Player(1);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        Composition newComposition = new Composition();
        newComposition.readFromFile("./resource/input/fur_elise.txt");
        player.setMyComposition(newComposition);

        keyboardReferences = new ArrayList<>();
    }
    @FXML
    public void initialize() {
        keyboardReferences.add(C2);
        keyboardReferences.add(C3);
        keyboardReferences.add(C4);
        keyboardReferences.add(C5);
        keyboardReferences.add(C6);
        keyboardReferences.add(CS2);
        keyboardReferences.add(CS3);
        keyboardReferences.add(CS4);
        keyboardReferences.add(CS5);
        keyboardReferences.add(CS6);
        keyboardReferences.add(D2);
        keyboardReferences.add(D3);
        keyboardReferences.add(D4);
        keyboardReferences.add(D5);
        keyboardReferences.add(D6);
        keyboardReferences.add(DS2);
        keyboardReferences.add(DS3);
        keyboardReferences.add(DS4);
        keyboardReferences.add(DS5);
        keyboardReferences.add(DS6);
        keyboardReferences.add(E2);
        keyboardReferences.add(E3);
        keyboardReferences.add(E4);
        keyboardReferences.add(E5);
        keyboardReferences.add(E6);
        keyboardReferences.add(F2);
        keyboardReferences.add(F3);
        keyboardReferences.add(F4);
        keyboardReferences.add(F5);
        keyboardReferences.add(F6);
        keyboardReferences.add(FS2);
        keyboardReferences.add(FS3);
        keyboardReferences.add(FS4);
        keyboardReferences.add(FS5);
        keyboardReferences.add(FS6);
        keyboardReferences.add(G2);
        keyboardReferences.add(G3);
        keyboardReferences.add(G4);
        keyboardReferences.add(G5);
        keyboardReferences.add(G6);
        keyboardReferences.add(GS2);
        keyboardReferences.add(GS3);
        keyboardReferences.add(GS4);
        keyboardReferences.add(GS5);
        keyboardReferences.add(GS6);
        keyboardReferences.add(A2);
        keyboardReferences.add(A3);
        keyboardReferences.add(A4);
        keyboardReferences.add(A5);
        keyboardReferences.add(A6);
        keyboardReferences.add(AS2);
        keyboardReferences.add(AS3);
        keyboardReferences.add(AS4);
        keyboardReferences.add(AS5);
        keyboardReferences.add(AS6);
        keyboardReferences.add(B2);
        keyboardReferences.add(B3);
        keyboardReferences.add(B4);
        keyboardReferences.add(B5);
        keyboardReferences.add(B6);
    }
    public static boolean autoplayOn = false;


    public void play() {
        Thread t = new Thread(player);
        player.setThreadReference(t);
        t.setDaemon(true);
        for (Button b :
                keyboardReferences) {
            b.setDisable(true);
        }
        AutoPlay.setDisable(true);
        Pause.setDisable(false);
        Stop.setDisable(false);
        Play.setDisable(false);
        Record.setDisable(true);
        player.activateKeysWhenFinished(this);
        autoplayOn = true;
        t.start();
    }

    public void playNote() {

    }
    public void pause(ActionEvent actionEvent) {
        if (player.threadReferenceSet())
            player.pausePlay();
    }

    public void stop(ActionEvent actionEvent) {
        if (player.threadReferenceSet())
            player.stopPlay();
    }

    public void resume(ActionEvent actionEvent) {
        if (player.threadReferenceSet())
            player.resumePlay();
    }

    public void playNoteC2(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C2")));
    }

    public void playNoteD2(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D2")));
    }

    public void playNoteE2(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("E2")));
    }

    public void playNoteF2(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F2")));
    }

    public void playNoteG2(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G2")));
    }

    public void playNoteA2(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A2")));
    }

    public void playNoteB2(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("B2")));
    }

    public void playNoteCS2(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C#2")));
    }

    public void playNoteDS2(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D#2")));
    }

    public void playNoteFS2(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F#2")));
    }

    public void playNoteGS2(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G#2")));
    }

    public void playNoteAS2(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A#2")));
    }

    public void playNoteC3(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C3")));
    }

    public void playNoteD3(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D3")));
    }

    public void playNoteE3(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("E3")));
    }

    public void playNoteF3(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F3")));
    }

    public void playNoteG3(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G3")));
    }

    public void playNoteA3(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A3")));
    }

    public void playNoteB3(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("B3")));
    }

    public void playNoteCS3(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C#3")));
    }

    public void playNoteDS3(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D#3")));
    }

    public void playNoteFS3(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F#3")));
    }

    public void playNoteGS3(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G#3")));
    }

    public void playNoteAS3(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A#3")));
    }

    public void playNoteC4(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C4")));
    }

    public void playNoteD4(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D4")));
    }

    public void playNoteE4(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("E4")));
    }

    public void playNoteF4(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F4")));
    }

    public void playNoteG4(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G4")));
    }

    public void playNoteA4(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A4")));
    }

    public void playNoteB4(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("B4")));
    }

    public void playNoteCS4(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C#4")));
    }

    public void playNoteDS4(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D#4")));
    }

    public void playNoteFS4(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F#4")));
    }

    public void playNoteGS4(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G#4")));
    }

    public void playNoteAS4(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A#4")));
    }

    public void playNoteC5(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C5")));
    }

    public void playNoteD5(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D5")));
    }

    public void playNoteE5(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("E5")));
    }

    public void playNoteF5(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F5")));
    }

    public void playNoteG5(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G5")));
    }

    public void playNoteA5(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A5")));
    }

    public void playNoteB5(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("B5")));
    }

    public void playNoteCS5(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C#5")));
    }

    public void playNoteDS5(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D#5")));
    }

    public void playNoteFS5(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F#5")));
    }

    public void playNoteGS5(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G#5")));
    }

    public void playNoteAS5(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A#5")));
    }

    public void playNoteC6(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C6")));
    }

    public void playNoteD6(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D6")));
    }

    public void playNoteE6(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("E6")));
    }

    public void playNoteF6(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F6")));
    }

    public void playNoteG6(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G6")));
    }

    public void playNoteA6(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A6")));
    }

    public void playNoteB6(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("B6")));
    }

    public void playNoteCS6(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C#6")));
    }

    public void playNoteDS6(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D#6")));
    }

    public void playNoteFS6(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F#6")));
    }

    public void playNoteGS6(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G#6")));
    }

    public void playNoteAS6(ActionEvent actionEvent) {
        player.play(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A#6")));
    }


}
