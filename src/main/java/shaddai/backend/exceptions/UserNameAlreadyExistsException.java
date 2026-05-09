package shaddai.backend.exceptions;

public class UserNameAlreadyExistsException extends RuntimeException {

    public UserNameAlreadyExistsException(String username) { super("Username " + username + " already exists"); }
}
