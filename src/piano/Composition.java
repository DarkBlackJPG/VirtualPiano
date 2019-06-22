package piano;

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

    public static HashMap<Integer, Character> integerToCharacterMapping;
    /// Static field initialization!

    /*
     * Initialization of static HashMaps -> they are used for simpler mapping
     * of specific notes, names and midi values
     */
    static {
        characterNameMapping = new HashMap<>();
        charToIntMapping = new HashMap<>();
        nameToCharachterHashMapping = new HashMap<>();
        integerToCharacterMapping = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get("./resource/map.csv"),
             StandardCharsets.US_ASCII)) {
            String currentLine = br.readLine();
            while (currentLine != null) {
                String[] attributes = currentLine.split(",");
                charToIntMapping.put(attributes[0].charAt(0), Integer.parseInt(attributes[2]));
                characterNameMapping.put(attributes[0].charAt(0), attributes[1]);
                nameToCharachterHashMapping.put(attributes[1], attributes[0].charAt(0));
                integerToCharacterMapping.put(Integer.parseInt(attributes[2]), attributes[0].charAt(0));
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

    private String myCompositionTxtFile = null;

    /// ====================
    /// ====== Methods =====
    /// ====================

    public void importNotes(ArrayList<MusicSymbol> music){
        myNotes = music;
    }

    public static String extractFilename(String pathToFile){
        final String patternString = "\\w+(?:\\.\\w+)";

        Pattern pattern = Pattern.compile(patternString);
        Matcher myMatcher = pattern.matcher(pathToFile);

        if (myMatcher.find()){
            String intermediate = myMatcher.group(0);
            intermediate = intermediate.replace(".txt", "");
            return intermediate;
        }

        return null;

    }

    /**
     *
     * @return returns the fullPath of composition
     */
    public String getMyCompositionTxtFile() {
        return myCompositionTxtFile;
    }

    /**
     * Set the new file for the composition. This will call the readFromFile(..) method implicitly
     * @param myCompositionTxtFile The parameter has two types of parsing the location:
     *                             1) You can add the full, absolute path OR the relative path, but you
     *                                then have to know the relative path precisely, you have to indicate
     *                                that you are giving te full path with the @ at the beginning, otherwise, it
     *                                result in an unhandled exception (i.e. @../relative/resource/temp.txt).
     *
     *                             2) You can add only the file name, the extension must exist. Relative path
     *                                assumed. The location where the file should be is ./resource/input/
     */
    public void setMyCompositionTxtFile(String myCompositionTxtFile) {
        if(myCompositionTxtFile.charAt(0) == '@'){
            myCompositionTxtFile.replace("@", "");
            this.myCompositionTxtFile = myCompositionTxtFile;
        } else {
            this.myCompositionTxtFile = "./resource/input/"+(myCompositionTxtFile.contains(".txt") ? myCompositionTxtFile : myCompositionTxtFile + ".txt");
        }
        readFromFile(this.myCompositionTxtFile);
    }
    public void setMyCompositionName(String myCompositionTxtFile){
        if (myCompositionTxtFile == "") {
            myCompositionTxtFile = "DefaultName";
        }
            this.myCompositionTxtFile = "./resource/input/" + (myCompositionTxtFile.contains(".txt") ? myCompositionTxtFile : myCompositionTxtFile + ".txt");

    }
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

        myCompositionTxtFile = filePath;

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

    /**
     * This function is used to convert the Composition object to a String type
     * @return Returns the String object containing ONLY the file name, <b>not</b> the entire file path!
     */
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
