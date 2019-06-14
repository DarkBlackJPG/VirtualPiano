package piano;

import javax.sound.midi.*;
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

    private static final int DEFAULT_INSTRUMETN = 0;
    private MidiChannel channel;

    public Player() throws MidiUnavailableException {
        this(DEFAULT_INSTRUMETN);
    }
    public  Player(int instrument) throws MidiUnavailableException{
        channel = getChannel(1);
        channel.programChange(instrument);
    }

    public void play(final int note){
        channel.noteOn(note, 50);
    }
    public void release (final int note) {
        channel.noteOff(note, 50);
    }

    public void play(final int note, final long length) throws InterruptedException {
        play(note);
        Thread.sleep(length);
        release(note);
    }

    private static MidiChannel getChannel(int channel) throws MidiUnavailableException{
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        return synthesizer.getChannels()[channel];
    }

    public static void main(String[] args) throws Exception {
        Player player = new Player();
        Scanner scanner = new Scanner(System.in);
        int note;

        for(int i = 1; i <= 127; i++){
            player.play(i, 200);
        }

//        while (!Thread.currentThread().isInterrupted()){
//            System.out.println("Note [1..127] : ");
//            note = scanner.nextInt();
//            player.play(note, 200);
//        }
        scanner.close();


    }


}
