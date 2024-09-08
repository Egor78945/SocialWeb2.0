package org.example.j2ee.messageservice.service.validator.message;

public class MessageValidator {
    public static boolean isValid(String message){
        return !message.isEmpty() && !message.isBlank() && message.length() <= 500;
    }
}
