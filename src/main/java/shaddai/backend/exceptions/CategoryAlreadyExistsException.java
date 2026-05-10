package shaddai.backend.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String category) { super("Category " + category + " already exists"); }
}
