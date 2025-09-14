package com.mortgages.mortgage_checker.models.exceptions;

public class MaturityNotSupportedException extends RuntimeException {
    public MaturityNotSupportedException(String message) {
        super(message);
    }
}