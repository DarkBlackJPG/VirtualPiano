package piano;

import java.util.ArrayList;

public class Note extends MusicSymbol {

    private int midiValue;
    private char keyboardChar;
    private String name;


    public Note(char noteChar, Duration symbolDuration) {
        super(symbolDuration);

        keyboardChar = noteChar;
        midiValue = Composition.charToIntMapping.get(keyboardChar);
        name = Composition.characterNameMapping.get(keyboardChar);
    }

    public Note(

            Duration symbDuration,
            int midiValue,
            char keyboardChar,
            String name
    ) {
        super(symbDuration);
        this.midiValue = midiValue;
        this.keyboardChar = keyboardChar;
        this.name = name;
    }

    public int getMidiValue() {
        return midiValue;
    }

    public void setMidiValue(int midiValue) {
        this.midiValue = midiValue;
    }

    public char getKeyboardChar() {
        return keyboardChar;
    }

    public void setKeyboardChar(char keyboardChar) {
        this.keyboardChar = keyboardChar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Overriden myString from MusicSymbol.class
     *
     * @return TextFormat for Note
     */
    protected String myString() {
        return name;
    }


    /**
     * @return Returns the integer which represents the MIDI note code
     */
    public ArrayList<Integer> getNotes() {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(midiValue);
        return temp;
    }
}
