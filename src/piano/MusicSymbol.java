package piano;

//import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

public abstract class MusicSymbol {
    /// Fields
    protected Duration symbDuration;


    /// Methods

    //@Contract(pure = true)
    public MusicSymbol(Duration symbDuration){
        this.symbDuration = symbDuration;
    }
    /**
     * Gets the symbol duration
     * @return Returns the Duration object which tells us what type of note/chord/pause this is
     */
    public Duration getSymbDuration() {
        return  symbDuration;
    }

    /**
     * Changes the duration of MusicSymbol
     * @param newDuration Duration object used to change the duration of this MusicSymbol
     */
    public void setSymbDuration(Duration newDuration) {
        symbDuration = newDuration;
    }

    @Override
    public String toString() {
        return myString();
    }

    /// Abstract methods
//    public abstract int getMidiData();

    /**
     * This abstract overrides in child classes because each child class
     * has its own way of printing itself out
     *
     * @return Returns the textual format of a child class
     */
    protected abstract String myString();

    /**
     * This abstract overrides in child classes because it is used to return the
     * values of its notes
     * @return ArrayList<Integer> Object which is actually all Midi values for
     * notes
     */
    public abstract ArrayList<Integer> getNotes();
}
