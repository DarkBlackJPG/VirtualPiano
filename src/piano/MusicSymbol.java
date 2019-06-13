package piano;

//import org.jetbrains.annotations.Contract;

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
     * @return
     */
    public Duration getSymbDuration() {
        return  symbDuration;
    }

    /**
     * Changes the duration of MusicSymbol
     * @param newDuration
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
}
