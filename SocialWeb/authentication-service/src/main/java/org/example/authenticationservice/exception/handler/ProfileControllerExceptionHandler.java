package org.example.authenticationservice.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.authenticationservice.annotation.ProfileControllerAdvice;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = ProfileControllerAdvice.class)
public class ProfileControllerExceptionHandler {
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> jsonProcessingExceptionHandler(JsonProcessingException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(505));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
    }
}
