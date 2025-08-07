package com.inditex.pricing.infrastructure.controller;

import com.inditex.pricing.application.exception.PriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<String> managePriceNotFound(PriceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> manageMissingParameters(MissingServletRequestParameterException ex) {
        String message = "Missing parameters: " + ex.getParameterName();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}