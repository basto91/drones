package com.musala.drone.drone.service.advice;

import com.musala.drone.drone.util.exceptions.FullFleetException;
import com.musala.drone.drone.util.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(value = {FullFleetException.class})
    protected ResponseEntity handledConflict(RuntimeException ex, WebRequest request){
        String bodyOfResponse = "The fleet is full, no more drone accepted";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity notFound(RuntimeException ex, WebRequest request){
        String bodyOfResponse = "Drone not found";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
