package shaddai.backend.exceptions;

public class InvalidActionException extends RuntimeException {
    public InvalidActionException(String action) {
        super("Action " + action + " is not valid, use: INSERT, UPDATE, DELETE");
    }
}
