package exception;

public class InvalidFactoryArgumentsException extends Exception {

    public InvalidFactoryArgumentsException(Exception ex) {
        super(ex);
    }
}
