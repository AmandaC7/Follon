package br.com.follon.demo.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = ex.getMostSpecificCause() != null
                ? ex.getMostSpecificCause().getMessage()
                : ex.getMessage();

        if (message == null || message.isBlank()) {
            message = "Data integrity violation";
        }

        String normalized = message
                .replace("org.postgresql.util.PSQLException: ", "")
                .replace("ERROR: ", "")
                .trim();

        if (normalized.toLowerCase(Locale.ROOT).contains("duplicate key")
                || normalized.toLowerCase(Locale.ROOT).contains("duplicate")
                || normalized.toLowerCase(Locale.ROOT).contains("already exists")) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Duplicate record or already existing value"));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("message", "Invalid data for the requested operation"));
    }
}
