package piano;

public class RecorderData {
    public long timestamp;
    public char symbol;
    RecorderData(char symbol, long timestamp){
        this.symbol = symbol;
        this.timestamp = timestamp;
    }
}
