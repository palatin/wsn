package exception;

public class InvalidRandomRangeException extends Exception {

    public InvalidRandomRangeException() {
        super("Max - min should be >= count");
    }
}
