package shaddai.backend.exceptions;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String date) {
        super("Date " + date + " not valid, use format YYYY-MM-DD");
    }
}
