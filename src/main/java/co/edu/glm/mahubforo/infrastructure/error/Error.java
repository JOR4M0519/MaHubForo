package co.edu.glm.mahubforo.infrastructure.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Error {

    /**
     * 404
    */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handle404() {
        return ResponseEntity.notFound().build();
    }

    /**
     * 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidateErrorData>> handle400(MethodArgumentNotValidException e) {
        List<ValidateErrorData> errores = e.getFieldErrors().stream()
                .map(ValidateErrorData::new)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errores);
    }

    /**
     * Custom Validation
     */
    @ExceptionHandler(Validation.class)
    public ResponseEntity<String> handleValidateError(Validation e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * Incorrect Credentials
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleIncorrectCredentials(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }
    /**
     * Error Validation
     */
    private record ValidateErrorData(String campo, String error) {
        public ValidateErrorData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
