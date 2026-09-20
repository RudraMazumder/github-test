package com.example.demo;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(InvalidSumRequestException.class)
    public ResponseEntity<ApiError> handleInvalidSumRequest(InvalidSumRequestException exception) {
        return ResponseEntity.badRequest().body(new ApiError(exception.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleUnreadableMessage() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError("Request body must contain valid whole numbers for 'a' and 'b'."));
    }

    @Schema(description = "Error returned when the request cannot be processed.")
    public record ApiError(
            @Schema(description = "Human-readable explanation of the error.")
            String error
    ) {
    }
}
