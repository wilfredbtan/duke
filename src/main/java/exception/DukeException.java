package exception;

public class DukeException extends Exception{
    public DukeException(String errorMessage, Throwable err) {
        super(errorMessage + "\n", err);
    }
}
