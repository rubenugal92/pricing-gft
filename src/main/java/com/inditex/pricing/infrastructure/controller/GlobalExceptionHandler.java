package com.inditex.pricing.infrastructure.controller;

import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorResponse> managePriceNotFound(PriceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse()
                .message(ex.getMessage())
                .status(404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> manageMissingParameters(MissingServletRequestParameterException ex) {
        ErrorResponse error = new ErrorResponse()
                .message("Missing parameter: " + ex.getParameterName())
                .status(400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}