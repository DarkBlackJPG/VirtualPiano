package piano;

public class Pause extends MusicSymbol {







    public Pause(Duration symbDuration){
        super(symbDuration);
    }


    protected String myString(){
        return (symbDuration.getMyDuration() == Duration._Duration.QUARTER) ? " ||P|| " : " |p| ";
    }
}
