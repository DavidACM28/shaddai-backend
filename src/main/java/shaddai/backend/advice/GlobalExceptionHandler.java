package shaddai.backend.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shaddai.backend.dtos.ErrorResponseDTO;
import shaddai.backend.exceptions.CategoryAlreadyExistsException;
import shaddai.backend.exceptions.UserNameAlreadyExistsException;
import shaddai.backend.exceptions.UserNotFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest()
                .body(new ErrorResponseDTO("VALIDATION_ERROR", "Validation failed", errors));
    }

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNameAlreadyExists(UserNameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDTO("USERNAME_CONFLICT", ex.getMessage(), null));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO("USER_NOT_FOUND", ex.getMessage(), null));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCategoryAlreadyExists(CategoryAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDTO("CATEGORY_NAME_CONFLICT", ex.getMessage(), null));
    }
}
