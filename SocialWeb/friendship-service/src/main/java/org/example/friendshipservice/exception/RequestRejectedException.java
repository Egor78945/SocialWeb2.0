package org.example.friendshipservice.exception;

public class RequestRejectedException extends RuntimeException {
    public RequestRejectedException(String message) {
        super(message);
    }
}
