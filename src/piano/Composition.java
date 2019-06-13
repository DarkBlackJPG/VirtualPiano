package piano;

import com.sun.source.tree.CompoundAssignmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Composition {
    /// Static fields
    /**
     * HashMap<Character, Integer> charToIntMapping
     * is used for mapping characters from keyboard (inputs) to ints,
     * to MIDI sound ints
     */
    public static HashMap<Character, Integer> charToIntMapping;
    /**
     * HashMap<Character, String> characterNameMapping;
     * is used for mapping characters from keyboard (inputs) to Strings,
     * for printing it on the screen more easier
     */
    public static HashMap<Character, String> characterNameMapping;
    /**
     *static HashMap<String, Character> nameToCharachterHashMapping;
     * is used for mapping Strings when needed to chars because with chars,
     * you can get ints
     */
    public static HashMap<String, Character> nameToCharachterHashMapping;

    /// Static field initialization!

    /*
     * Initialization of static HashMaps -> they are used for simpler mapping
     * of specific notes, names and midi values
     */
    static {
        characterNameMapping = new HashMap<>();
        charToIntMapping = new HashMap<>();
        nameToCharachterHashMapping = new HashMap<>();


        try (BufferedReader br = Files.newBufferedReader(Paths.get("./resource/map.csv"),
             StandardCharsets.US_ASCII)) {
            String currentLine = br.readLine();
            while (currentLine != null) {
                String[] attributes = currentLine.split(",");
                charToIntMapping.put(attributes[0].charAt(0), Integer.parseInt(attributes[2]));
                characterNameMapping.put(attributes[0].charAt(0), attributes[1]);
                nameToCharachterHashMapping.put(attributes[1], attributes[0].charAt(0));
                currentLine = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /// ==================
    /// ===== Fields =====
    /// ==================

    /**
     *  Contains all notes that the Composition consists of
     */
    private ArrayList<MusicSymbol> myNotes = new ArrayList<>();


    /// ====================
    /// ====== Methods =====
    /// ====================

    /**
     * getMyNotes() returns the reference to the Composition i.e.
     * all notes that are considered a compositon
     *
     * @return returns all musical symbols in the composition
     */
    public ArrayList<MusicSymbol> getMyNotes() {
        return  myNotes;
    }

    /**
     * Returns the note at the specified index
     *
     * Can experience IndexOutOfBounds, that can make
     * the program not work as intended
     *
     *
     * @param index this is the index in composition
     * @return Returns the note at the given index
     */
    public MusicSymbol getNoteAt(int index) {
        if(index > myNotes.size() - 1) {
            return null;
        }
        return myNotes.get(index);
    }

    /**
     * Adds a note at the back of the composition
     * @param symbol Symbol to put into the composition
     * @return Non-terminating method, meaning the return
     * value is this composition
     */
    public Composition addNote(MusicSymbol symbol) {
        myNotes.add(symbol);
        return this;
    }

    /**
     * @return returns the number of MusicSymbols (notes) in compositon
     */
    public int getNumberOfSymbols() {
        return myNotes.size();
    }

    /**
     * This method reads the passed file and
     * fills the composition
     *
     * The file has to be written in specific format or the
     * song won't sound quite right i.e. it wont work how its
     * supposed to.
     *
     * You can get compositions from http://virtualpiano.net/
     *
     * @apiNote This method should be enclosed inside if-else statement
     * because the return value is false if there was an error.
     * If the validity of this method is not checked, there will
     * be problems further down the code, most notably NullPointerException, for example
     *
     * @param filePath File path where the composition is located
     * @return Returns true if operation ended successfully
     */
    public boolean readFromFile(String filePath) {

        final String patternString =
                "([\\w]+)?" +        // This part reads QUARTERS (Quarters should be written outside [...])                         | 1
                "(\\[[^\\]]+\\])?" + // This part reads the contents of [...], should have further parsing                          | 2
                "([ ][|][ ])?" +     //This is for the pauses inside [] -> those pauses are read as QUARTER pauses                  | 3
                "( )?"
                ;

        // This creates the pattern object
        Pattern pattern = Pattern.compile(patternString);
        // This creates a Matcher object which uses Regex algorithms to search for pattern
        Matcher myMatcher;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.US_ASCII)) {
            String currentLine = br.readLine();
            while (currentLine != null) {

                myMatcher = pattern.matcher(currentLine);

                    while (myMatcher.find()){

                        /*
                         * This group matches consecutive notes.
                         * (i.e. atYjskI)
                         * All notes are in QUARTER times
                         *
                         */
                        if(myMatcher.group(1) != null){
                            int charCount = myMatcher.group(1).length();
                            String matcherString = myMatcher.group(1);
                           for (int i = 0; i < charCount; i++){
                              char charachter = matcherString.charAt(i);
                              // We invoke the charachter parser to Note and add the newly created note to the list
                              myNotes.add(
                                      charachterParser(charachter, new Duration(Duration._Duration.QUARTER))
                              );

                           }
                        }
                        /*
                         * This group matches notes in [...] braces.
                         * These notes are played in EIGHT time.
                         * (i.e. [a h w e] or [asdf])
                         *
                         * Note: Keep in mind that you can't have a combination
                         * like [ahg kjl] - this does not mean that you play two
                         * chords one after the other. When you want to play
                         * two chords, you have to have [asg][ghs]. This is correct.
                         *
                         * All chords are played in QUARTER time.
                         *
                         */

                        if(myMatcher.group(2) != null) {
                           String matcherReference = myMatcher.group(2);


                           // Must delete the [ and ] occurrences in matcherReference string
                           matcherReference = matcherReference.replace("[", "");
                           matcherReference = matcherReference.replace("]", "");



                            /* You should check if you are dealing with a quarter chord
                             * or consecutive eight notes!
                             *
                             * If(true) then NOTES
                             *
                             * Else then CHORD
                             *
                             */
                           if(matcherReference.contains(" ")){
                               String [] devidedNotes = matcherReference.split(" ");
                               for (String temp :
                                       devidedNotes) {
                                    char charachter = temp.charAt(0);
                                   myNotes.add(
                                           charachterParser(charachter, new Duration(Duration._Duration.EIGHT))
                                   );
                               }
                           } else {
                               myNotes.add(new Chord(matcherReference, new Duration(Duration._Duration.QUARTER)));
                           }

                        }
                        /*
                         * Quarter pause added to composition
                         */
                        if(myMatcher.group(3) != null) {
                            myNotes.add(new Pause(new Duration(Duration._Duration.QUARTER)));
                        }
                        /*
                         * Eight pause added to composition
                         */
                        if (myMatcher.group(4) != null) {
                            myNotes.add(new Pause(new Duration(Duration._Duration.EIGHT)));
                        }
                    }

                currentLine = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     *This private method is invoked when charachter parsing is needed
     * for the purpose of creating a note.
     * The same code is used throughout the "Composition class"
     * @param charachter This is the charachter we want to parse
     * @param duration This parameter tells the parser the duration of the note
     * @return Note which is created and parsed accordingly
     */
    private Note charachterParser(char charachter, Duration duration){
        int midiVal = charToIntMapping.get(charachter);
        String name = characterNameMapping.get(charachter);
        return new Note(duration, midiVal, charachter, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MusicSymbol ms :
                myNotes) {
            sb.append(ms + " ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Composition cm = new Composition();
        if(cm.readFromFile("./resource/input/jingle_bellss.txt")) {
            System.out.println(cm);
        } else {
            System.out.println("There was en error reading the file");
        }
    }

}
