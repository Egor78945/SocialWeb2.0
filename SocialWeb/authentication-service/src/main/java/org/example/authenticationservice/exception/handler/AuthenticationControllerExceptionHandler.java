package org.example.authenticationservice.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.authenticationservice.annotation.AuthenticationControllerAdvice;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = AuthenticationControllerAdvice.class)
public class AuthenticationControllerExceptionHandler {
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> jsonProcessingExceptionHandler(JsonProcessingException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(505));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> authenticationExceptionHandler(AuthenticationException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
    }
}
