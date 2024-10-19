package com.inditex.prices.application.controller;

import com.inditex.prices.application.controller.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleException(NoSuchElementException exception) {
        return buildErrorResponse(exception);
    }

    private static ResponseEntity<ErrorResponse> buildErrorResponse(NoSuchElementException exception) {
        var response = new ErrorResponse();
        response.setType("no such element");
        response.setTitle("No Such Element Exception");
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setDetail(exception.getMessage());
        response.setInstance("/prices");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
