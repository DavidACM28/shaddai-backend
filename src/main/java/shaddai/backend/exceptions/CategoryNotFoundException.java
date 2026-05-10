package shaddai.backend.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("Category id " + id + " not found");
    }
}
