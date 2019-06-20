package piano;

import javafx.scene.control.Button;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TODO
 *
 * Iskomentarisi sve i popravi.
 * Napravi API za koriscenje plejera jer ces imati nit
 * koja ce da trci po notama
 *
 *
 * Kul bi bilo kada bi stavio i da se menjaju instrumenti sa nekom listom
 * metodom koja menja instrument. Izmeni malo da ne bude bas sve static
 * i tako to
 *
 *
 * Buduci ja -> odoh ja da sad ucim OS da ne bi sjebao ispit sad u sredu...
 * Nek nam je bog na pomoci.....
 *
 *
 */


class CompositionNotLoadedException extends Exception {
    CompositionNotLoadedException() {
        super("The composition is not loaded, add composition and try again!");
    }
}

/*

All midi instruments

1. 	Acoustic Grand Piano
2. 	Bright Acoustic Piano
3. 	Electric Grand Piano
4. 	Honky-tonk Piano
5. 	Electric Piano 1
6. 	Electric Piano 2
7. 	Harpsichord
8. 	Clavi
9. 	Celesta
10. Glockenspiel
11. 	Music Box
12. 	Vibraphone
13. 	Marimba
14. 	Xylophone
15. 	Tubular Bells
16. 	Dulcimer
17. 	Drawbar Organ
18. 	Percussive Organ
19. 	Rock Organ
20. 	Church Organ
21. 	Reed Organ
22. 	Accordion
23. 	Harmonica
24. 	Tango Accordion
25. 	Acoustic Guitar (nylon)
26. 	Acoustic Guitar (steel)
27. 	Electric Guitar (jazz)
28. 	Electric Guitar (clean)
29. 	Electric Guitar (muted)
30. 	Overdriven Guitar
31. 	Distortion Guitar
32. 	Guitar harmonics
33. 	Acoustic Bass
34. 	Electric Bass (finger)
35. 	Electric Bass (pick)
36. 	Fretless Bass
37. 	Slap Bass 1
38. 	Slap Bass 2
39. 	Synth Bass 1
40. 	Synth Bass 2
41. 	Violin
42. 	Viola
43. 	Cello
44. 	Contrabass
45. 	Tremolo Strings
46. 	Pizzicato Strings
47. 	Orchestral Harp
48. 	Timpani
49. 	String Ensemble 1
50. 	String Ensemble 2
51. 	SynthStrings 1
52. 	SynthStrings 2
53. 	Choir Aahs
54. 	Voice Oohs
55. 	Synth Voice
56. 	Orchestra Hit
57. 	Trumpet
58. 	Trombone
59. 	Tuba
60. 	Muted Trumpet
61. 	French Horn
62. 	Brass Section
63. 	SynthBrass 1
64. 	SynthBrass 2
65. 	Soprano Sax
66. 	Alto Sax
67. 	Tenor Sax
68. 	Baritone Sax
69. 	Oboe
70. 	English Horn
71. 	Bassoon
72. 	Clarinet
73. 	Piccolo
74. 	Flute
75. 	Recorder
76. 	Pan Flute
77. 	Blown Bottle
78. 	Shakuhachi
79. 	Whistle
80. 	Ocarina
81. 	Lead 1 (square)
82. 	Lead 2 (sawtooth)
83. 	Lead 3 (calliope)
84. 	Lead 4 (chiff)
85. 	Lead 5 (charang)
86. 	Lead 6 (voice)
87. 	Lead 7 (fifths)
88. 	Lead 8 (bass + lead)
89. 	Pad 1 (new age)
90. 	Pad 2 (warm)
91. 	Pad 3 (polysynth)
92. 	Pad 4 (choir)
93. 	Pad 5 (bowed)
94. 	Pad 6 (metallic)
95. 	Pad 7 (halo)
96. 	Pad 8 (sweep)
97. 	FX 1 (rain)
98. 	FX 2 (soundtrack)
99. 	FX 3 (crystal)
100. 	FX 4 (atmosphere)
101. 	FX 5 (brightness)
102. 	FX 6 (goblins)
103. 	FX 7 (echoes)
104. 	FX 8 (sci-fi)
105. 	Sitar
106. 	Banjo
107. 	Shamisen
108. 	Koto
109. 	Kalimba
110. 	Bag pipe
111. 	Fiddle
112. 	Shanai
113. 	Tinkle Bell
114. 	Agogo
115. 	Steel Drums
116. 	Woodblock
117. 	Taiko Drum
118. 	Melodic Tom
119. 	Synth Drum
120. 	Reverse Cymbal
121. 	Guitar Fret Noise
122. 	Breath Noise
123. 	Seashore
124. 	Bird Tweet
125. 	Telephone Ring
126. 	Helicopter
127. 	Applause
128. 	Gunshot
 */

public class Player extends Thread{
    /**
     * Static field which is used as the first instrument
     * from 127 instruments that can be used in java
     */
    public static final int DEFAULT_INSTRUMENT = 0;

    private Composition myComposition;

    private int myInstrument;
    private MidiChannel channel;

    /**
     * This method is used to get the reference to the composition
     * that is being referenved inside this player object
     *
     * @return Composition reference
     */
    public Composition getMyComposition() {
        return myComposition;
    }

    /**
     * This method is used to set the Composition reference.
     * @param composition Composition reference
     */
    public void setMyComposition(Composition composition){
        myComposition = composition;
    }


    /**
     * Get Player's instrument
     * @return Integer value that is used to indicate the type of instrument
     * used in the current Player object
     */
    public int getMyInstrument() {
        return myInstrument;
    }

    /**
     * Used to set a new instrument for the current channel. There's a shit ton of
     * instruments which you can look up on the internet
     *
     * @param newInstrument Integer value which represents the instrument
     *                      instrument value for the channel
     */
    public void setMyInstrument (int newInstrument){
        myInstrument = newInstrument;
        channel.programChange(myInstrument);
    }

    /**
     * Default constructor for the player object. It sets
     * the current instrument to 0 = Grand Piano
     *
     * @throws MidiUnavailableException This is thrown
     *                                  if the instrument doesn't exist
     */
    public Player() throws MidiUnavailableException {
        this(DEFAULT_INSTRUMENT);
    }

    /**
     * Constructor which sets the current instrument to the passed argument.
     * Also, this constructor sets the channel which is used for the
     * midi song.
     *
     * @param instrument The instrument we wish to use
     * @throws MidiUnavailableException This is thrown
     *                                  if the instrument doesn't exist
     */
    public  Player(int instrument) throws MidiUnavailableException {
        channel = getChannel(1);
        myInstrument = instrument;
        myComposition = null;
        channel.setOmni(true);
        channel.programChange(instrument);
    }

    /**
     * Plays the given note. Sets the noteOn method of the MidiChannel object (press key)
     * @param note Note to be played
     */
    public void play(final int note){
        channel.noteOn(note, 60);
    }

    /**
     * Plays the given note. Sets the noteOff method of the MidiChannel object (release key)
     * @param note Integer value which represents the Midi code for note
     */
    public void release (final int note) {
        channel.noteOff(note, 60);
    }

    /**
     * Plays the given note for the given length of time
     * @param note Integer value which represents the note
     * @param length Long value which represents the note for which the note must be played
     * @throws InterruptedException This method uses sleep(..) which throws the InterruptedException
     */
    public void play(final int note, final long length) throws InterruptedException {
        play(note);
        Thread.sleep(length);
        release(note);
    }

    /**
     * This method plays the chord. The chord is comprised of an array of integers, all notes
     * should be started immediately, and when the length runs out, all notes are released.
     * @param chordNotes ArrayList<Integer> Object contains all notes inside the chord
     * @param length wait interval between noteOn and noteOff in ms.
     * @throws InterruptedException Contains the static method sleep which throws {@link InterruptedException}
     */
    public void playChord(final ArrayList<Integer> chordNotes, final long length) throws InterruptedException{
        for (Integer note:
             chordNotes) {
            play(note);
        }
        Thread.sleep(length);
        channel.allNotesOff();
    }

    /**
     * Private method to get the channel with the given parameter
     * @param channel Channel number
     * @return Returns the MidiChannel Object
     * @throws MidiUnavailableException Thrown if channel unavailable, 16 max channels (1..16)
     */
    private static MidiChannel getChannel(int channel) throws MidiUnavailableException{
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        return synthesizer.getChannels()[channel];
    }



    /**
     * This method is invoked when you want to play the Composition object.
     *
     * Possible errors include when the myComposition is not initialized. This should be
     * avoided by using the {@code setMyComposition(Composition newComposition)} method
     * inside the Player object
     */
    public void playComposition() {
        if(myComposition != null){
            ArrayList<MusicSymbol> compositionNotes = myComposition.getMyNotes();

            final int quarterTick = 300;
            final int eightTick = 150;


            for (MusicSymbol ms :
                    compositionNotes) {

                ArrayList<Integer> allNotes = ms.getNotes();
                Duration noteDuration = ms.getSymbDuration();

                try {
                    if (ms instanceof Note) {
                        play(allNotes.get(0), (noteDuration.getDurationValue() == 4 ? quarterTick : eightTick));
                    } else if (ms instanceof Pause) {
                        Thread.sleep((noteDuration.getDurationValue() == 4 ? quarterTick : eightTick));
                    } else if (ms instanceof Chord) {
                        playChord(allNotes, (noteDuration.getDurationValue() == 4 ? quarterTick : eightTick));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     *  This method is called when you need to export to MIDI file. The output filename will
     *  be set to the Filename of the .txt notes file. If you export your recording, you will be
     *  asked to name your session
     *
     *  This method MUST set the meta data (messages) to the midi format like Sysex, Track name, tempo
     *  etc.
     *
     *  {@link MidiEvent}'s second constructor parameter is used to tell the exporter the tick value
     *
     *
     * @throws CompositionNotLoadedException Composition myst be loaded to player! the exporter will throw this
     *                                       exception when the composition is not set or if there was an error with
     *                                       the file
     * @throws InvalidMidiDataException {@link InvalidMidiDataException}
     * @throws IOException {@link IOException}
     */
    public void exportToMidi()

            throws CompositionNotLoadedException,
                    InvalidMidiDataException,
                    IOException {
        if(myComposition == null || myComposition.getMyNotes().size() <= 0){
            throw new CompositionNotLoadedException();
        }

        Sequence myMIDISequence = new Sequence(Sequence.PPQ, 24);
        Track myTrack = myMIDISequence.createTrack();

        /*
         * This code creates the General MIDI Sysex data and adds it to the track
         *
         */
        byte[] b = {(byte)0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte)0xF7};
        SysexMessage sm = new SysexMessage();
        sm.setMessage(b, 6);
        MidiEvent midiEvent = new MidiEvent(sm,(long)0);
        myTrack.add(midiEvent);

        /*
         * This code sets the tempo with a meta message
         */
        MetaMessage metaMessage = new MetaMessage();
        byte[] bt = {0x02, (byte)0x00, 0x00};
        metaMessage.setMessage(0x51 ,bt, 3);
        midiEvent = new MidiEvent(metaMessage,(long)0);
        myTrack.add(midiEvent);

        /*
         * This code sets the track name
         */

        metaMessage = new MetaMessage();
        String TrackName = Composition.extractFilename(myComposition.getMyCompositionTxtFile());
        metaMessage.setMessage(0x03 ,TrackName.getBytes(), TrackName.length());
        midiEvent = new MidiEvent(metaMessage,(long)0);
        myTrack.add(midiEvent);

        /*
          * This code sets omni mode -> this refers to the idea
          * that the midi file will respond to any instrument
          * on any channel
         */
        ShortMessage message = new ShortMessage();
        message.setMessage(0xB0, 0x7D,0x00);
        midiEvent = new MidiEvent(message,(long)0);
        myTrack.add(midiEvent);


        /*
         * This code sets the instrument
         */

        message = new ShortMessage();
        message.setMessage(0xC0, myInstrument, 0x00);
        midiEvent = new MidiEvent(message,(long)0);
        myTrack.add(midiEvent);


        ArrayList<MusicSymbol> compositionSymbols = myComposition.getMyNotes();
        int currentTick = 1;
        for (MusicSymbol ms :
                compositionSymbols) {

            if (ms instanceof Pause){
                currentTick += ( ms.getSymbDuration().getDurationValue() == 4 ? 30 : 15);
                continue;
            }


            if(ms instanceof Note) {
                /*
                 * Adds the noteOn Event to the track
                 */
                message = new ShortMessage();
                message.setMessage(0x90, ((Note) ms).getMidiValue(), 0x60);
                midiEvent = new MidiEvent(message, (long) currentTick);
                myTrack.add(midiEvent);


                currentTick += ( ms.getSymbDuration().getDurationValue() == 4 ? 30 : 15);


                /*
                 * Adds the noteOff to the track
                 */
                message = new ShortMessage();
                message.setMessage(0x80, ((Note) ms).getMidiValue(), 0x60);
                midiEvent = new MidiEvent(message, (long) currentTick);
                myTrack.add(midiEvent);
            } else if (ms instanceof Chord) {
                /*
                 * Adds the noteOn Event to the track
                 */
                ArrayList<Integer> chordNotes  = ms.getNotes();
                for (Integer note:
                     chordNotes) {
                    message = new ShortMessage();
                    message.setMessage(0x90, note, 0x60);
                    midiEvent = new MidiEvent(message, (long) currentTick);
                    myTrack.add(midiEvent);
                }



                currentTick += ( ms.getSymbDuration().getDurationValue() == 4 ? 30 : 15);


                /*
                 * Adds the noteOff to all notes that are pressed
                 */
                for (Integer note:
                        chordNotes) {
                    message = new ShortMessage();
                    message.setMessage(0x80, note, 0x60);
                    midiEvent = new MidiEvent(message, (long) currentTick);
                    myTrack.add(midiEvent);
                }
            }



        }


        //****  set end of track (meta event) 19 ticks later  ****
        metaMessage = new MetaMessage();
        byte[] bet = {}; // empty array
        metaMessage.setMessage(0x2F,bet,0);
        midiEvent = new MidiEvent(metaMessage, (long)140);
        myTrack.add(midiEvent);

        //****  write the MIDI sequence to a MIDI file  ****
        File f = new File(TrackName+".mid");
        MidiSystem.write(myMIDISequence,1,f);


    }

    public static void main(String[] args) throws Exception {
        Player player = new Player(1);
        Composition newComposition = new Composition();
        newComposition.readFromFile("./resource/input/ariana_grande.txt");
        player.setMyComposition(newComposition);

       //player.exportToMidi();
        player.playComposition();
    }

    private Controller ctrl;
    public void activateKeysWhenFinished(Controller ctrl){
            this.ctrl = ctrl;
    }

    private boolean isPaused = false;
    private Thread myThread = null;
    public void setThreadReference(Thread myThread){
        this.myThread = myThread;
    }

    private boolean stop = false;
    public synchronized void pausePlay(){
        if (!isPaused)
                isPaused = true;
    }
    public synchronized void resumePlay(){
        if(isPaused)
            isPaused = false;
        notify();
    }
    public void stopPlay(){
        stop = true;
    }

    public boolean threadReferenceSet(){
        return myThread != null;

    }

    @Override
    public void run() {
        if (myComposition != null)
        {
            ArrayList<MusicSymbol> compositionNotes = myComposition.getMyNotes();

            final int quarterTick = 300;
            final int eightTick = 150;

            try {
                while (!interrupted()){

                    for (MusicSymbol ms :
                            compositionNotes) {
                        synchronized (this){
                            while (isPaused){
                                wait();
                            }
                        }
                        ArrayList<Integer> allNotes = ms.getNotes();
                        Duration noteDuration = ms.getSymbDuration();
                        try {
                            if (ms instanceof Note) {
                                play(allNotes.get(0), (noteDuration.getDurationValue() == 4 ? quarterTick : eightTick));
                            } else if (ms instanceof Pause) {
                                sleep((noteDuration.getDurationValue() == 4 ? quarterTick : eightTick));
                            } else if (ms instanceof Chord) {
                                playChord(allNotes, (noteDuration.getDurationValue() == 4 ? quarterTick : eightTick));
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (stop)
                            break;
                    }
                    break;
                }
            } catch (InterruptedException ex){

            }
            stop = false;
            ArrayList<Button> buttons = ctrl.keyboardReferences;
            for (javafx.scene.control.Button b :
                    buttons) {
                b.setDisable(false);
            }
            Controller.autoplayOn = false;
            ctrl.Pause.setDisable(true);
            ctrl.Stop.setDisable(true);
            ctrl.Play.setDisable(true);
            ctrl.AutoPlay.setDisable(false);
            ctrl.Record.setDisable(false);
        }
    }
}












/*

 */