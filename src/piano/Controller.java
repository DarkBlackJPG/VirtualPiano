package piano;

import com.sun.glass.ui.CommonDialogs;
import com.sun.prism.impl.Disposer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.shape.QuadCurve;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Queue;

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
        tr.add(new TypeRecord(rawRecording.get(rawRecording.size()-1),rawRecording.get(rawRecording.size()-2).symbol));

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


        for (RecorderData d :
                recorder.recorderData) {
            System.out.println(d.symbol + "\t" + d.timestamp);
        }

        TextInputDialog dialog = new TextInputDialog("Enter name");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter composition name:");
        Optional<String> result = dialog.showAndWait();
        recording = false;
        Composition newCompositon = new Composition();
        result.ifPresent(newCompositon::setMyCompositionName);
        newCompositon.importNotes(formatRecording(recorder.recorderData));
        player.setMyComposition(newCompositon);


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
}