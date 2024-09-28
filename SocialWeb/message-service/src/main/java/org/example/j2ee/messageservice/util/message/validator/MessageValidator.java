package org.example.j2ee.messageservice.util.message.validator;

public class MessageValidator {
    public static boolean isValid(String message){
        return !message.isEmpty() && !message.isBlank() && message.length() <= 500;
    }
}
