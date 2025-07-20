package com.weylandyutani.starter.exceptions;

import com.weylandyutani.starter.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommandValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(CommandValidationException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(QueueOverflowException.class)
    public ResponseEntity<ErrorResponse> handleQueueOverflow(QueueOverflowException e) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ErrorResponse(e.getMessage()));
    }
}
