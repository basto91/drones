package com.musala.drone.drone.service.advice;

import com.musala.drone.drone.util.exceptions.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {FullFleetException.class})
    protected ResponseEntity handledConflict(RuntimeException ex, WebRequest request){
        String bodyOfResponse = "The fleet is full, no more drone accepted";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity notFound(RuntimeException ex, WebRequest request){
        String bodyOfResponse = "Drone not found";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    protected ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc,WebRequest request) {
        String bodyOfResponse = "File not found";
        return handleExceptionInternal(exc, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<?> handleContrainViolation(ConstraintViolationException exc,WebRequest request) {
        return handleExceptionInternal(exc, exc.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(LowBatteryException.class)
    protected ResponseEntity<?> lowBattery(LowBatteryException exc,WebRequest request) {
        return handleExceptionInternal(exc, "Drone with low battery",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ToHeavyLoadException.class)
    protected ResponseEntity<?> lowBattery(ToHeavyLoadException exc,WebRequest request) {
        return handleExceptionInternal(exc, "the load is to heavy",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
