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
    protected abstract String myString();
}
