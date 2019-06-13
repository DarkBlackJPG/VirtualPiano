package piano;

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
    ){
        super(symbDuration);
        this.midiValue = midiValue;
        this.keyboardChar = keyboardChar;
        this.name = name;
    }



    protected String myString(){
        return name;
    };
}
