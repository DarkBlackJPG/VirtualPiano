package piano;

import javax.sound.midi.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

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



public class Player {
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
        channel.programChange(instrument);
    }

    /**
     * Plays the given note. Sets the noteOn method of the MidiChannel object (press key)
     * @param note Note to be played
     */
    public void play(final int note){
        channel.noteOn(note, 50);
    }

    /**
     * Plays the given note. Sets the noteOff method of the MidiChannel object (release key)
     * @param note Integer value which represents the Midi code for note
     */
    public void release (final int note) {
        channel.noteOff(note, 50);
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
     * TODO
     *
     * TU DOBIJE NEKI RAK I ZABODE NA PRVIH NEKOLIKO NOTA JEBENIH!!!!! AAAAAA
     *
     */


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

            for (MusicSymbol ms :
                    compositionNotes) {

                ArrayList<Integer> allNotes = ms.getNotes();
                Duration noteDuration = ms.getSymbDuration();

                try {
                    if (ms instanceof Note) {
                        play(allNotes.get(0), (noteDuration.getDurationValue() == 4 ? 250 : 125));
                    } else if (ms instanceof Pause) {
                        Thread.sleep((noteDuration.getDurationValue() == 4 ? 250 : 125));
                    } else if (ms instanceof Chord) {
                        playChord(allNotes, (noteDuration.getDurationValue() == 4 ? 250 : 125));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Player player = new Player(24);
        Composition newComposition = new Composition();
        newComposition.readFromFile("./resource/input/fur_elise.txt");
        player.setMyComposition(newComposition);
        player.playComposition();

    }
}












/*

 */