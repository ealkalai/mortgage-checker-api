package com.mortgages.mortgage_checker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mortgages.mortgage_checker.models.exceptions.MaturityNotSupportedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handles a specific custom exception
    @ExceptionHandler(MaturityNotSupportedException.class)
    public ResponseEntity<String> handleMaturityNotSupportedException(MaturityNotSupportedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handles all other unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}