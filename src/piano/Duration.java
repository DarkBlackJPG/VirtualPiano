package piano;

/**
 * Duration class used as an abstract type for
 * duration measuring
 *
 * @fields _Duration myDuration
 */
public class Duration {
    private _Duration myDuration;

    public Duration(_Duration duration) {
        myDuration = duration;
    }

    /**
     * Get the duration
     *
     * @return myDuration
     */
    public _Duration getMyDuration() {
        return myDuration;
    }

    /**
     * Set duration
     *
     * @param newDuration
     */
    public void setMyDuration(_Duration newDuration) {
        myDuration = newDuration;
    }

    /**
     * This mehod converts the duration enumeration
     * to its value
     */
    public int getDurationValue() {
        return ((myDuration == _Duration.QUARTER) ? 4 : 8);
    }

    public enum _Duration {
        QUARTER,
        EIGHT
    }
}
