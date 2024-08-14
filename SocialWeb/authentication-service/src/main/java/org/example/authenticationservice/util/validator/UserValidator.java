package org.example.authenticationservice.util.validator;

import org.example.authenticationservice.model.request.RegisterRequestModel;

import java.util.function.Predicate;

public class UserValidator {
    public static Predicate<RegisterRequestModel> isValidRegisterModel = requestModel ->
            (!requestModel.getName().isEmpty() && requestModel.getName().length() <= 30)
                    && (!requestModel.getSurname().isEmpty() && requestModel.getSurname().length() <= 50)
                    && (requestModel.getAge() >= 14 && requestModel.getAge() <= 111)
                    && (!requestModel.getCity().isEmpty() && requestModel.getCity().length() <= 50)
                    && UserValidator.isValidPassword.test(requestModel.getPassword())
                    && UserValidator.isValidEmail.test(requestModel.getEmail());

    public static Predicate<String> isValidEmail = email -> {
        String[] emailArray = email.split("@");
        return emailArray.length == 2
                && (emailArray[1].equals("gmail.com") || emailArray[1].equals("mail.ru"))
                && emailArray[0].length() >= 1
                && emailArray[0].length() <= 100;
    };

    public static Predicate<String> isValidPassword = p -> {
        if (p.length() >= 10 && p.length() <= 50) {
            int digitCount = 0;
            int letterCount = 0;
            for (char c : p.toCharArray()) {
                if (Character.isDigit(c))
                    digitCount++;
                else if (Character.isLetter(c))
                    letterCount++;
                else
                    return false;
            }
            return digitCount >= 10 && letterCount >= 10;
        }
        return false;
    };
}
