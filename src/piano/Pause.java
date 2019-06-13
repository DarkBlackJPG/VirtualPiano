package piano;

public class Pause extends MusicSymbol {







    public Pause(Duration symbDuration){
        super(symbDuration);
    }

    /**
     * Overriden myString from MusicSymbol.class
     * @return TextFormat for Pause
     */
    protected String myString(){
        return (symbDuration.getMyDuration() == Duration._Duration.QUARTER) ? " ||P|| " : " |p| ";
    }
}
