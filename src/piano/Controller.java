package piano;

import com.sun.glass.ui.CommonDialogs;
import com.sun.prism.impl.Disposer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import java.util.Queue;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

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
    public Button stopRecordButton;
    public Button Play;
    public Button Stop;
    public Button Pause;
    public Button AutoPlay;
    public Canvas MyCanvas;

    public Label compositionPath;
    public Button compositionChooseButton;
    ArrayList<Button> keyboardReferences;

    public ComboBox instrumentCombobox;
    public RadioButton showNotesRadioButton;
    public RadioButton showKeysRadioButton;

    public Button txtButton;
    public Button midiButton;
    public Button setInstrumentButton;
    public Button rememberChoiceButton;

    private Player player;
    private Composition newComposition = new Composition();

    {
        try {
            player = new Player(1);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        assert player != null;
        player.setMyComposition(newComposition);

        keyboardReferences = new ArrayList<>();
    }
    public final HashMap<Character, Button> charButtonBinding = new HashMap<>();
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

        ObservableList<String> options = FXCollections.observableArrayList(
                "1. Acoustic Grand Piano",
                "2. Bright Acoustic Piano",
                "3. Electric Grand Piano",
                "4. Honky-tonk Piano",
                "5. Electric Piano 1",
                "6. Electric Piano 2",
                "7. Harpsichord",
                "8. Clavi",
                "9. Celesta",
                "10. Glockenspiel",
                "11. Music Box",
                "12. Vibraphone",
                "13. Marimba",
                "14. Xylophone",
                "15. Tubular Bells",
                "16. Dulcimer",
                "17. Drawbar Organ",
                "18. Percussive Organ",
                "19. Rock Organ",
                "20. Church Organ",
                "21. Reed Organ",
                "22. Accordion",
                "23. Harmonica",
                "24. Tango Accordion",
                "25. Acoustic Guitar (nylon)",
                "26. Acoustic Guitar (steel)",
                "27. Electric Guitar (jazz)",
                "28. Electric Guitar (clean)",
                "29. Electric Guitar (muted)",
                "30. Overdriven Guitar",
                "31. Distortion Guitar",
                "32. Guitar harmonics",
                "33. Acoustic Bass",
                "34. Electric Bass (finger)",
                "35. Electric Bass (pick)",
                "36. Fretless Bass",
                "37. Slap Bass 1",
                "38. Slap Bass 2",
                "39. Synth Bass 1",
                "40. Synth Bass 2",
                "41. Violin",
                "42. Viola",
                "43. Cello",
                "44. Contrabass",
                "45. Tremolo Strings",
                "46. Pizzicato Strings",
                "47. Orchestral Harp",
                "48. Timpani",
                "49. String Ensemble 1",
                "50. String Ensemble 2",
                "51. SynthStrings 1",
                "52. SynthStrings 2",
                "53. Choir Aahs",
                "54. Voice Oohs",
                "55. Synth Voice",
                "56. Orchestra Hit",
                "57. Trumpet",
                "58. Trombone",
                "59. Tuba",
                "60. Muted Trumpet",
                "61. French Horn",
                "62. Brass Section",
                "63. SynthBrass 1",
                "64. SynthBrass 2",
                "65. Soprano Sax",
                "66. Alto Sax",
                "67. Tenor Sax",
                "68. Baritone Sax",
                "69. Oboe",
                "70. English Horn",
                "71. Bassoon",
                "72. Clarinet",
                "73. Piccolo",
                "74. Flute",
                "75. Recorder",
                "76. Pan Flute",
                "77. Blown Bottle",
                "78. Shakuhachi",
                "79. Whistle",
                "80. Ocarina",
                "81. Lead 1 (square)",
                "82. Lead 2 (sawtooth)",
                "83. Lead 3 (calliope)",
                "84. Lead 4 (chiff)",
                "85. Lead 5 (charang)",
                "86. Lead 6 (voice)",
                "87. Lead 7 (fifths)",
                "88. Lead 8 (bass + lead)",
                "89. Pad 1 (new age)",
                "90. Pad 2 (warm)",
                "91. Pad 3 (polysynth)",
                "92. Pad 4 (choir)",
                "93. Pad 5 (bowed)",
                "94. Pad 6 (metallic)",
                "95. Pad 7 (halo)",
                "96. Pad 8 (sweep)",
                "97. FX 1 (rain)",
                "98. FX 2 (soundtrack)",
                "99. FX 3 (crystal)",
                "100. FX 4 (atmosphere)",
                "101. FX 5 (brightness)",
                "102. FX 6 (goblins)",
                "103. FX 7 (echoes)",
                "104. FX 8 (sci-fi)",
                "105. Sitar",
                "106. Banjo",
                "107. Shamisen",
                "108. Koto",
                "109. Kalimba",
                "110. Bag pipe",
                "111. Fiddle",
                "112. Shanai",
                "113. Tinkle Bell",
                "114. Agogo",
                "115. Steel Drums",
                "116. Woodblock",
                "117. Taiko Drum",
                "118. Melodic Tom",
                "119. Synth Drum",
                "120. Reverse Cymbal",
                "121. Guitar Fret Noise",
                "122. Breath Noise",
                "123. Seashore",
                "124. Bird Tweet",
                "125. Telephone Ring",
                "126. Helicopter",
                "127. Applause",
                "128. Gunshot"
        );

        instrumentCombobox.setItems(options);
        instrumentCombobox.setValue("Choose an instrument");
        player.setMyCanvas(MyCanvas);

        charButtonBinding.put('1',C2);
        charButtonBinding.put('2',D2);
        charButtonBinding.put('3',E2);
        charButtonBinding.put('4',F2);
        charButtonBinding.put('5',G2);
        charButtonBinding.put('6',A2);
        charButtonBinding.put('7',B2);
        charButtonBinding.put('!',CS2);
        charButtonBinding.put('@',DS2);
        charButtonBinding.put('$',FS2);
        charButtonBinding.put('%',GS2);
        charButtonBinding.put('^',AS2);
        charButtonBinding.put('8',C3);
        charButtonBinding.put('9',D3);
        charButtonBinding.put('0',E3);
        charButtonBinding.put('q',F3);
        charButtonBinding.put('w',G3);
        charButtonBinding.put('e',A3);
        charButtonBinding.put('r',B3);
        charButtonBinding.put('*',CS3);
        charButtonBinding.put('(',DS3);
        charButtonBinding.put('Q',FS3);
        charButtonBinding.put('W',GS3);
        charButtonBinding.put('E',AS3);
        charButtonBinding.put('t',C4);
        charButtonBinding.put('y',D4);
        charButtonBinding.put('u',E4);
        charButtonBinding.put('i',F4);
        charButtonBinding.put('o',G4);
        charButtonBinding.put('p',A4);
        charButtonBinding.put('a',B4);
        charButtonBinding.put('T',CS4);
        charButtonBinding.put('Y',DS4);
        charButtonBinding.put('I',FS4);
        charButtonBinding.put('O',GS4);
        charButtonBinding.put('P',AS4);
        charButtonBinding.put('s',C5);
        charButtonBinding.put('d',D5);
        charButtonBinding.put('f',E5);
        charButtonBinding.put('g',F5);
        charButtonBinding.put('h',G5);
        charButtonBinding.put('j',A5);
        charButtonBinding.put('k',B5);
        charButtonBinding.put('S',CS5);
        charButtonBinding.put('D',DS5);
        charButtonBinding.put('G',FS5);
        charButtonBinding.put('H',GS5);
        charButtonBinding.put('J',AS5);
        charButtonBinding.put('l',C6);
        charButtonBinding.put('z',D6);
        charButtonBinding.put('x',E6);
        charButtonBinding.put('c',F6);
        charButtonBinding.put('v',G6);
        charButtonBinding.put('b',A6);
        charButtonBinding.put('n',B6);
        charButtonBinding.put('L',CS6);
        charButtonBinding.put('Z',DS6);
        charButtonBinding.put('C',FS6);
        charButtonBinding.put('V',GS6);
        charButtonBinding.put('B',AS6);
        charButtonBinding.put('1',C2);
        charButtonBinding.put('2',D2);
        charButtonBinding.put('3',E2);
        charButtonBinding.put('4',F2);
        charButtonBinding.put('5',G2);
        charButtonBinding.put('6',A2);
        charButtonBinding.put('7',B2);
        charButtonBinding.put('!',CS2);
        charButtonBinding.put('@',DS2);
        charButtonBinding.put('$',FS2);
        charButtonBinding.put('%',GS2);
        charButtonBinding.put('^',AS2);
        charButtonBinding.put('8',C3);
        charButtonBinding.put('9',D3);
        charButtonBinding.put('0',E3);
        charButtonBinding.put('q',F3);
        charButtonBinding.put('w',G3);
        charButtonBinding.put('e',A3);
        charButtonBinding.put('r',B3);
        charButtonBinding.put('*',CS3);
        charButtonBinding.put('(',DS3);
        charButtonBinding.put('Q',FS3);
        charButtonBinding.put('W',GS3);
        charButtonBinding.put('E',AS3);
        charButtonBinding.put('t',C4);
        charButtonBinding.put('y',D4);
        charButtonBinding.put('u',E4);
        charButtonBinding.put('i',F4);
        charButtonBinding.put('o',G4);
        charButtonBinding.put('p',A4);
        charButtonBinding.put('a',B4);
        charButtonBinding.put('T',CS4);
        charButtonBinding.put('Y',DS4);
        charButtonBinding.put('I',FS4);
        charButtonBinding.put('O',GS4);
        charButtonBinding.put('P',AS4);
        charButtonBinding.put('s',C5);
        charButtonBinding.put('d',D5);
        charButtonBinding.put('f',E5);
        charButtonBinding.put('g',F5);
        charButtonBinding.put('h',G5);
        charButtonBinding.put('j',A5);
        charButtonBinding.put('k',B5);
        charButtonBinding.put('S',CS5);
        charButtonBinding.put('D',DS5);
        charButtonBinding.put('G',FS5);
        charButtonBinding.put('H',GS5);
        charButtonBinding.put('J',AS5);
        charButtonBinding.put('l',C6);
        charButtonBinding.put('z',D6);
        charButtonBinding.put('x',E6);
        charButtonBinding.put('c',F6);
        charButtonBinding.put('v',G6);
        charButtonBinding.put('b',A6);
        charButtonBinding.put('n',B6);
        charButtonBinding.put('L',CS6);
        charButtonBinding.put('Z',DS6);
        charButtonBinding.put('C',FS6);
        charButtonBinding.put('V',GS6);
        charButtonBinding.put('B',AS6);

    }
    static boolean autoplayOn = false;

    private static Stage primaryStage;

    static void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }

    public void loadComposition() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(primaryStage);
        newComposition = new Composition();
        newComposition.readFromFile(file.getAbsolutePath());
        compositionPath.setText(file.getName());
        player.setMyComposition(newComposition);
        player.print();
    }

    public void changeInstrument() {
        String[] dataArray = instrumentCombobox.getValue().toString().split(". ");
        player.setMyInstrument(Integer.parseInt(dataArray[0]));
    }

    public Player getPlayer(){
        return player;
    }

    public static boolean isNote = true;

    public void changeView(){
        isNote = showNotesRadioButton.isSelected();
        player.print();
    }

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
        compositionChooseButton.setDisable(true);
        player.activateKeysWhenFinished(this);
        autoplayOn = true;
        t.start();
    }


    public void pause() {
        if (player.threadReferenceSet())
            player.pausePlay();
    }

    public void stop() {
        if (player.threadReferenceSet())
            player.stopPlay();
    }

    public void resume() {
        if (player.threadReferenceSet())
            player.resumePlay();
    }


    public void midiExport(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File path =  directoryChooser.showDialog(primaryStage);
        try {
            player.exportToMidi(path.getAbsolutePath());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Export information");
            alert.setContentText("The composition was successfully exported to \n"+path.getAbsolutePath()+"!");

            alert.showAndWait();
        } catch (CompositionNotLoadedException | InvalidMidiDataException | IOException e) {
            e.printStackTrace();
        }
    }

    private Main myModel;

    void setModel(Main myModel){
        this.myModel = myModel;
        myModel.setMyController(this);
    }


    public void txtExport(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File path =  directoryChooser.showDialog(primaryStage);
        try {
            player.exportToTxt(path.getAbsolutePath());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Export information");
            alert.setContentText("The composition was successfully exported to \n"+path.getAbsolutePath()+"!");
            alert.showAndWait();

        } catch (CompositionNotLoadedException  e) {
            e.printStackTrace();
        }
    }

    void playNote(int m) {
        class Sleeper extends Thread{
            @Override
            public void run() {
                player.play(m);
                try {
                    sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                player.release(m);
            }
        }
        new Sleeper().start();

        MusicSymbol temp = player.getCurrentSymbol();
        if(temp != null){
            if (temp instanceof Note){
               if(temp.getNotes().get(0) == m){
                   player.updateCurrentNote();
               }
            }

        }

        if (recording){
            recorder.insertNote(new RecorderData(Composition.integerToCharacterMapping.get(m), System.currentTimeMillis()));
        }
    }

    public void playNoteC2() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C2")));
    }

    public void playNoteD2() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D2")));
    }

    public void playNoteE2() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("E2")));
    }

    public void playNoteF2() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F2")));
    }

    public void playNoteG2() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G2")));
    }

    public void playNoteA2() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A2")));
    }

    public void playNoteB2() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("B2")));
    }

    public void playNoteCS2() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C#2")));
    }

    public void playNoteDS2() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D#2")));
    }

    public void playNoteFS2() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F#2")));
    }

    public void playNoteGS2() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G#2")));
    }

    public void playNoteAS2() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A#2")));
    }

    public void playNoteC3() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C3")));
    }

    public void playNoteD3() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D3")));
    }

    public void playNoteE3() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("E3")));
    }

    public void playNoteF3() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F3")));
    }

    public void playNoteG3() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G3")));
    }

    public void playNoteA3() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A3")));
    }

    public void playNoteB3() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("B3")));
    }

    public void playNoteCS3() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C#3")));
    }

    public void playNoteDS3() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D#3")));
    }

    public void playNoteFS3() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F#3")));
    }

    public void playNoteGS3() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G#3")));
    }

    public void playNoteAS3() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A#3")));
    }

    public void playNoteC4() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C4")));
    }

    public void playNoteD4() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D4")));
    }

    public void playNoteE4() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("E4")));
    }

    public void playNoteF4() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F4")));
    }

    public void playNoteG4() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G4")));
    }

    public void playNoteA4() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A4")));
    }

    public void playNoteB4() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("B4")));
    }

    public void playNoteCS4() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C#4")));
    }

    public void playNoteDS4() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D#4")));
    }

    public void playNoteFS4() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F#4")));
    }

    public void playNoteGS4() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G#4")));
    }

    public void playNoteAS4() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A#4")));
    }

    public void playNoteC5() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C5")));
    }

    public void playNoteD5() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D5")));
    }

    public void playNoteE5() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("E5")));
    }

    public void playNoteF5() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F5")));
    }

    public void playNoteG5() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G5")));
    }

    public void playNoteA5() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A5")));
    }

    public void playNoteB5() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("B5")));
    }

    public void playNoteCS5() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C#5")));
    }

    public void playNoteDS5() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D#5")));
    }

    public void playNoteFS5() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F#5")));
    }

    public void playNoteGS5() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G#5")));
    }

    public void playNoteAS5() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A#5")));
    }

    public void playNoteC6() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C6")));
    }

    public void playNoteD6() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D6")));
    }

    public void playNoteE6() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("E6")));
    }

    public void playNoteF6() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F6")));
    }

    public void playNoteG6() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G6")));
    }

    public void playNoteA6() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A6")));
    }

    public void playNoteB6() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("B6")));
    }

    public void playNoteCS6() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("C#6")));
    }

    public void playNoteDS6() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("D#6")));
    }

    public void playNoteFS6() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("F#6")));
    }

    public void playNoteGS6() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("G#6")));
    }

    public void playNoteAS6() {
        playNote(Composition.charToIntMapping.get(Composition.nameToCharachterHashMapping.get("A#6")));
    }


    private boolean recording = false;
    public boolean isRecording() { return recording;}

    class Recorder {
        ArrayList<RecorderData> recorderData;

        Recorder(ArrayList<RecorderData> recorderComposition) {
            recorderData = recorderComposition;
        }

        void insertNote(RecorderData data){
            recorderData.add(data);
        }


    }

    private Recorder recorder;

    // Za akord, svi zajedno treba da budu manji od 100
    // Za ostalo samo treba da gledas razliku izmedju susedna dva
    private static ArrayList<MusicSymbol> formatRecording(ArrayList<RecorderData> rawRecording){
        class TypeRecord {
            private RecorderData data;
            private char Type;
            private TypeRecord(RecorderData data, char Type){
                this.data = data;
                this.Type = Type;
            }
        }
        final long chordDifference = 100;
        final long quarterTick = 600;
        final long eightTick = 300;
        ArrayList<Long> timeDifference = new ArrayList<>();
        if (rawRecording.size() == 1)
            timeDifference.add(1000L);
        for (int i = 0; i < rawRecording.size() - 1; i++){
            timeDifference.add(rawRecording.get(i+1).timestamp - rawRecording.get(i).timestamp);
        }
        ArrayList<TypeRecord> tr = new ArrayList<>();
        boolean isChordFlag = false;
        for (int i = 0; i < timeDifference.size(); i++){
            if (timeDifference.get(i) <= chordDifference){
                if (!isChordFlag)
                    isChordFlag = true;
                tr.add(new TypeRecord(rawRecording.get(i), 'c'));
            } else if(timeDifference.get(i) > chordDifference && timeDifference.get(i) <= eightTick){
                if (isChordFlag) {
                    isChordFlag = false;
                    tr.add(new TypeRecord(rawRecording.get(i), 'C'));
                } else {
                    tr.add(new TypeRecord(rawRecording.get(i), 'e'));
                }
            } else if(timeDifference.get(i) > eightTick && timeDifference.get(i) <= quarterTick){
                if (isChordFlag) {
                    isChordFlag = false;
                    tr.add(new TypeRecord(rawRecording.get(i), 'C'));
                } else {
                    tr.add(new TypeRecord(rawRecording.get(i), 'q'));
                }
            } else if (timeDifference.get(i) > quarterTick){
                if (isChordFlag) {
                    isChordFlag = false;
                    tr.add(new TypeRecord(rawRecording.get(i), 'C'));
                } else {
                    int numberOfPause = (int)(timeDifference.get(i)/quarterTick);
                    tr.add(new TypeRecord(rawRecording.get(i), 'q'));
                    for (int p = 0; p < numberOfPause; p++)
                        tr.add(new TypeRecord(null, 'p'));
                }
            }
        }
        if(rawRecording.size() > 1) {
            tr.add(new TypeRecord(rawRecording.get(rawRecording.size() - 1), rawRecording.get(rawRecording.size() - 2).symbol));
        } else {
            tr.add(new TypeRecord(rawRecording.get(0), rawRecording.get(0).symbol));
        }
        ArrayList<MusicSymbol> formattedMusic = new ArrayList<>();
        char noteSymbol;
        int midiValue;
        String noteName;
        Duration noteDuration;
        Note note;
        for (int i = 0; i < tr.size(); i++) {
            switch (tr.get(i).Type){
                case 'c':
                    ArrayList<Note> notesToImport = new ArrayList<>();
                    while (i < tr.size()) {
                        noteSymbol = tr.get(i).data.symbol;
                        midiValue = Composition.charToIntMapping.get(noteSymbol);
                        noteName =  Composition.characterNameMapping.get(noteSymbol);
                        noteDuration = new Duration(Duration._Duration.QUARTER);
                        note = new Note(noteDuration,midiValue,noteSymbol,noteName);
                        notesToImport.add(note);
                        if (tr.get(i).Type == 'C')
                            break;
                        i++;
                    }
                    Chord chordToImport = new Chord(notesToImport, new Duration(Duration._Duration.QUARTER));
                    formattedMusic.add(chordToImport);
                    break;
                case 'e':
                    noteSymbol = tr.get(i).data.symbol;
                    midiValue = Composition.charToIntMapping.get(noteSymbol);
                    noteName =  Composition.characterNameMapping.get(noteSymbol);
                    noteDuration = new Duration(Duration._Duration.EIGHT);
                    note = new Note(noteDuration,midiValue,noteSymbol,noteName);
                    formattedMusic.add(note);
                    break;
                case 'q':
                    noteSymbol = tr.get(i).data.symbol;
                    midiValue = Composition.charToIntMapping.get(noteSymbol);
                    noteName =  Composition.characterNameMapping.get(noteSymbol);
                    noteDuration = new Duration(Duration._Duration.QUARTER);
                    note = new Note(noteDuration,midiValue,noteSymbol,noteName);
                    formattedMusic.add(note);
                    break;
                case 'p':
                    formattedMusic.add(new Pause(new Duration(Duration._Duration.QUARTER)));
                    break;
                    default:
                        break;
            }
        }


        return formattedMusic;
    }

    public void stopRecording (){
        instrumentCombobox.setDisable(false);
        midiButton.setDisable(false);
        txtButton.setDisable(false);
        setInstrumentButton.setDisable(false);
        compositionChooseButton.setDisable(false);
        AutoPlay.setDisable(false);
        rememberChoiceButton.setDisable(false);
        showNotesRadioButton.setDisable(false);
        showKeysRadioButton.setDisable(false);
        Record.setDisable(false);
        stopRecordButton.setDisable(true);
        TextInputDialog dialog = new TextInputDialog("Enter name");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter composition name:");
        Optional<String> result = dialog.showAndWait();
        recording = false;
        Composition newCompositon = new Composition();
        result.ifPresent(newCompositon::setMyCompositionName);
        newCompositon.importNotes(formatRecording(recorder.recorderData));
        player.setMyComposition(newCompositon);

        compositionPath.setText( result.get() + ".txt");

    }

    public void record() {
        stopRecordButton.setDisable(false);
        instrumentCombobox.setDisable(true);
        midiButton.setDisable(true);
        txtButton.setDisable(true);
        setInstrumentButton.setDisable(true);
        compositionChooseButton.setDisable(true);
        AutoPlay.setDisable(true);
        rememberChoiceButton.setDisable(true);
        showNotesRadioButton.setDisable(true);
        showKeysRadioButton.setDisable(true);
        Record.setDisable(true);

        player.setMyComposition(new Composition());

        recording = true;
        recorder = new Recorder(new ArrayList<>());
        myModel.setRecorder(recorder);

    }

    public void thisKeyPressedC2(){
        colorButton(C2);
    }

    public void thisKeyReleasedC2(){
        unColorButton(C2);
    }

    public void thisKeyPressedD2(){
        colorButton(D2);
    }

    public void thisKeyReleasedD2(){
        unColorButton(D2);
    }

    public void thisKeyPressedE2(){
        colorButton(E2);
    }

    public void thisKeyReleasedE2(){
        unColorButton(E2);
    }

    public void thisKeyPressedF2(){
        colorButton(F2);
    }

    public void thisKeyReleasedF2(){
        unColorButton(F2);
    }

    public void thisKeyPressedG2(){
        colorButton(G2);
    }

    public void thisKeyReleasedG2(){
        unColorButton(G2);
    }

    public void thisKeyPressedA2(){
        colorButton(A2);
    }

    public void thisKeyReleasedA2(){
        unColorButton(A2);
    }

    public void thisKeyPressedB2(){
        colorButton(B2);
    }

    public void thisKeyReleasedB2(){
        unColorButton(B2);
    }

    public void thisKeyPressedCS2(){
        colorButton(CS2);
    }

    public void thisKeyReleasedCS2(){
        unColorButton(CS2);
    }

    public void thisKeyPressedDS2(){
        colorButton(DS2);
    }

    public void thisKeyReleasedDS2(){
        unColorButton(DS2);
    }

    public void thisKeyPressedFS2(){
        colorButton(FS2);
    }

    public void thisKeyReleasedFS2(){
        unColorButton(FS2);
    }

    public void thisKeyPressedGS2(){
        colorButton(GS2);
    }

    public void thisKeyReleasedGS2(){
        unColorButton(GS2);
    }

    public void thisKeyPressedAS2(){
        colorButton(AS2);
    }

    public void thisKeyReleasedAS2(){
        unColorButton(AS2);
    }

    public void thisKeyPressedC3(){
        colorButton(C3);
    }

    public void thisKeyReleasedC3(){
        unColorButton(C3);
    }

    public void thisKeyPressedD3(){
        colorButton(D3);
    }

    public void thisKeyReleasedD3(){
        unColorButton(D3);
    }

    public void thisKeyPressedE3(){
        colorButton(E3);
    }

    public void thisKeyReleasedE3(){
        unColorButton(E3);
    }

    public void thisKeyPressedF3(){
        colorButton(F3);
    }

    public void thisKeyReleasedF3(){
        unColorButton(F3);
    }

    public void thisKeyPressedG3(){
        colorButton(G3);
    }

    public void thisKeyReleasedG3(){
        unColorButton(G3);
    }

    public void thisKeyPressedA3(){
        colorButton(A3);
    }

    public void thisKeyReleasedA3(){
        unColorButton(A3);
    }

    public void thisKeyPressedB3(){
        colorButton(B3);
    }

    public void thisKeyReleasedB3(){
        unColorButton(B3);
    }

    public void thisKeyPressedCS3(){
        colorButton(CS3);
    }

    public void thisKeyReleasedCS3(){
        unColorButton(CS3);
    }

    public void thisKeyPressedDS3(){
        colorButton(DS3);
    }

    public void thisKeyReleasedDS3(){
        unColorButton(DS3);
    }

    public void thisKeyPressedFS3(){
        colorButton(FS3);
    }

    public void thisKeyReleasedFS3(){
        unColorButton(FS3);
    }

    public void thisKeyPressedGS3(){
        colorButton(GS3);
    }

    public void thisKeyReleasedGS3(){
        unColorButton(GS3);
    }

    public void thisKeyPressedAS3(){
        colorButton(AS3);
    }

    public void thisKeyReleasedAS3(){
        unColorButton(AS3);
    }

    public void thisKeyPressedC4(){
        colorButton(C4);
    }

    public void thisKeyReleasedC4(){
        unColorButton(C4);
    }

    public void thisKeyPressedD4(){
        colorButton(D4);
    }

    public void thisKeyReleasedD4(){
        unColorButton(D4);
    }

    public void thisKeyPressedE4(){
        colorButton(E4);
    }

    public void thisKeyReleasedE4(){
        unColorButton(E4);
    }

    public void thisKeyPressedF4(){
        colorButton(F4);
    }

    public void thisKeyReleasedF4(){
        unColorButton(F4);
    }

    public void thisKeyPressedG4(){
        colorButton(G4);
    }

    public void thisKeyReleasedG4(){
        unColorButton(G4);
    }

    public void thisKeyPressedA4(){
        colorButton(A4);
    }

    public void thisKeyReleasedA4(){
        unColorButton(A4);
    }

    public void thisKeyPressedB4(){
        colorButton(B4);
    }

    public void thisKeyReleasedB4(){
        unColorButton(B4);
    }

    public void thisKeyPressedCS4(){
        colorButton(CS4);
    }

    public void thisKeyReleasedCS4(){
        unColorButton(CS4);
    }

    public void thisKeyPressedDS4(){
        colorButton(DS4);
    }

    public void thisKeyReleasedDS4(){
        unColorButton(DS4);
    }

    public void thisKeyPressedFS4(){
        colorButton(FS4);
    }

    public void thisKeyReleasedFS4(){
        unColorButton(FS4);
    }

    public void thisKeyPressedGS4(){
        colorButton(GS4);
    }

    public void thisKeyReleasedGS4(){
        unColorButton(GS4);
    }

    public void thisKeyPressedAS4(){
        colorButton(AS4);
    }

    public void thisKeyReleasedAS4(){
        unColorButton(AS4);
    }

    public void thisKeyPressedC5(){
        colorButton(C5);
    }

    public void thisKeyReleasedC5(){
        unColorButton(C5);
    }

    public void thisKeyPressedD5(){
        colorButton(D5);
    }

    public void thisKeyReleasedD5(){
        unColorButton(D5);
    }

    public void thisKeyPressedE5(){
        colorButton(E5);
    }

    public void thisKeyReleasedE5(){
        unColorButton(E5);
    }

    public void thisKeyPressedF5(){
        colorButton(F5);
    }

    public void thisKeyReleasedF5(){
        unColorButton(F5);
    }

    public void thisKeyPressedG5(){
        colorButton(G5);
    }

    public void thisKeyReleasedG5(){
        unColorButton(G5);
    }

    public void thisKeyPressedA5(){
        colorButton(A5);
    }

    public void thisKeyReleasedA5(){
        unColorButton(A5);
    }

    public void thisKeyPressedB5(){
        colorButton(B5);
    }

    public void thisKeyReleasedB5(){
        unColorButton(B5);
    }

    public void thisKeyPressedCS5(){
        colorButton(CS5);
    }

    public void thisKeyReleasedCS5(){
        unColorButton(CS5);
    }

    public void thisKeyPressedDS5(){
        colorButton(DS5);
    }

    public void thisKeyReleasedDS5(){
        unColorButton(DS5);
    }

    public void thisKeyPressedFS5(){
        colorButton(FS5);
    }

    public void thisKeyReleasedFS5(){
        unColorButton(FS5);
    }

    public void thisKeyPressedGS5(){
        colorButton(GS5);
    }

    public void thisKeyReleasedGS5(){
        unColorButton(GS5);
    }

    public void thisKeyPressedAS5(){
        colorButton(AS5);
    }

    public void thisKeyReleasedAS5(){
        unColorButton(AS5);
    }

    public void thisKeyPressedC6(){
        colorButton(C6);
    }

    public void thisKeyReleasedC6(){
        unColorButton(C6);
    }

    public void thisKeyPressedD6(){
        colorButton(D6);
    }

    public void thisKeyReleasedD6(){
        unColorButton(D6);
    }

    public void thisKeyPressedE6(){
        colorButton(E6);
    }

    public void thisKeyReleasedE6(){
        unColorButton(E6);
    }

    public void thisKeyPressedF6(){
        colorButton(F6);
    }

    public void thisKeyReleasedF6(){
        unColorButton(F6);
    }

    public void thisKeyPressedG6(){
        colorButton(G6);
    }

    public void thisKeyReleasedG6(){
        unColorButton(G6);
    }

    public void thisKeyPressedA6(){
        colorButton(A6);
    }

    public void thisKeyReleasedA6(){
        unColorButton(A6);
    }

    public void thisKeyPressedB6(){
        colorButton(B6);
    }

    public void thisKeyReleasedB6(){
        unColorButton(B6);
    }

    public void thisKeyPressedCS6(){
        colorButton(CS6);
    }

    public void thisKeyReleasedCS6(){
        unColorButton(CS6);
    }

    public void thisKeyPressedDS6(){
        colorButton(DS6);
    }

    public void thisKeyReleasedDS6(){
        unColorButton(DS6);
    }

    public void thisKeyPressedFS6(){
        colorButton(FS6);
    }

    public void thisKeyReleasedFS6(){
        unColorButton(FS6);
    }

    public void thisKeyPressedGS6(){
        colorButton(GS6);
    }

    public void thisKeyReleasedGS6(){
        unColorButton(GS6);
    }

    public void thisKeyPressedAS6(){
        colorButton(AS6);
    }

    public void thisKeyReleasedAS6(){
        unColorButton(AS6);
    }


    class ContainerClass {
        Button button;
        String previous;

        public ContainerClass(Button button, String previous) {
            this.button = button;
            this.previous = previous;
        }
    }
    private ArrayList<ContainerClass> buttonColors = new ArrayList<>();


    public void colorButton(Button id){
        for (ContainerClass c :
                buttonColors) {
            if (c.button == id) {
                return;
            }

        }
        buttonColors.add(new ContainerClass(id, id.getStyle()));
        id.setStyle("-fx-background-color:#7f7f7f");
    }
    public void unColorButton(Button id){
        ContainerClass cc = null;
        for (int i = 0; i < buttonColors.size(); i++)
            if (buttonColors.get(i).button.equals(id))
            {
                cc = buttonColors.get(i);
                buttonColors.remove(i);
                id.setStyle(cc.previous);
                break;
            }
    }



}