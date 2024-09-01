package org.example.authenticationservice.exception.handler.friendship;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.authenticationservice.annotation.FriendshipControllerAdvice;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = FriendshipControllerAdvice.class)
public class FriendshipControllerExceptionHandler {
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> jsonProcessingExceptionHandler(JsonProcessingException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(505));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
    }
}
