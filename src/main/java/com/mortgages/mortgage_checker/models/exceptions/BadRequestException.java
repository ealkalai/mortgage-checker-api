package com.mortgages.mortgage_checker.models.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Bad Request")
 public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
 }