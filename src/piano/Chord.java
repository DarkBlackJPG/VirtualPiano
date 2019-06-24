package piano;

import java.util.ArrayList;

public class Chord extends MusicSymbol {

    private ArrayList<Note> chordNotes;

    public Chord(String notes, Duration symbDuration) {
        super(symbDuration);
        chordNotes = new ArrayList<>();
        for (int i = 0; i < notes.length(); i++) {
            chordNotes.add(new Note(notes.charAt(i), symbDuration));
        }
    }

    public Chord(ArrayList<Note> notes, Duration symbDuration) {
        super(symbDuration);
        chordNotes = notes;

    }

    /**
     * getChordNotes() getter for all notes in the chord
     *
     * @return Return type of ArrayList<Note>
     */
    public ArrayList<Note> getChordNotes() {
        return chordNotes;
    }

    /**
     * Setter for chorNotes (all notes in chord), sets the chordNotes reference to new ArrayList
     *
     * @param newNotes The new Note list
     */
    public void setChordNotes(ArrayList<Note> newNotes) {
        chordNotes = newNotes;
    }

    /**
     * Individually add notes to chord
     *
     * @param newNote Note to be added
     * @return returns the reference to this chord, so this method is non-terminal
     */
    public Chord addNote(Note newNote) {
        chordNotes.add(newNote);
        return this;
    }

    /**
     * Overriden method from MysicSymbol.class
     *
     * @return Text format for chord
     */
    protected String myString() {
        StringBuilder sb = new StringBuilder("[");
        for (Note n :
                chordNotes) {
            sb.append(n);
        }
        sb.append("]");
        return sb.toString();
    }


    public ArrayList<Integer> getNotes() {
        ArrayList<Integer> temp = new ArrayList<>();
        for (Note midi :
                chordNotes) {
            temp.add(midi.getMidiValue());
        }

        return temp;
    }
}
