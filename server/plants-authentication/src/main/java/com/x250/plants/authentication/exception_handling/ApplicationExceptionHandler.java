package com.x250.plants.authentication.exception_handling;

import com.x250.plants.authentication.exception.BadRequestException;
import com.x250.plants.authentication.exception.CaptchaVerificationException;
import com.x250.plants.authentication.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    private static final String MAP_KEY = "message";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(CaptchaVerificationException.class)
    public Map<String, String> handleCaptchaVerificationExceptions(
            CaptchaVerificationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(MAP_KEY, ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String, String> handleResourceNotFoundExceptions(
            ResourceNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(MAP_KEY, ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Map<String, String> handleBadRequestExceptions(
            BadRequestException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(MAP_KEY, ex.getMessage());
        return errors;
    }

}
