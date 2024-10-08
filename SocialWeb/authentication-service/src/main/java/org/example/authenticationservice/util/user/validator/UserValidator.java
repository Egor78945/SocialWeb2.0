package org.example.authenticationservice.util.user.validator;

import org.example.authenticationservice.model.request.RegisterRequestModel;

public class UserValidator {
    public static boolean isValid(RegisterRequestModel requestModel) {
        return (!requestModel.getName().isEmpty() && requestModel.getName().length() <= 30)
                && (!requestModel.getSurname().isEmpty() && requestModel.getSurname().length() <= 50)
                && (requestModel.getAge() >= 14 && requestModel.getAge() <= 111)
                && (!requestModel.getCity().isEmpty() && requestModel.getCity().length() <= 50)
                && checkPassword(requestModel.getPassword())
                && checkEmail(requestModel.getEmail());
    }

    public static boolean checkEmail(String email) {
        String[] emailArray = email.split("@");
        return emailArray.length == 2
                && (emailArray[1].equals("gmail.com") || emailArray[1].equals("mail.ru"))
                && !emailArray[0].isBlank()
                && !emailArray[0].isEmpty()
                && emailArray[0].length() <= 100;
    }

    public static boolean checkPassword(String password) {
        if (password.length() >= 5 && password.length() <= 50) {
            int digitCount = 0;
            int letterCount = 0;
            for (char c : password.toCharArray()) {
                if (Character.isDigit(c))
                    digitCount++;
                else if (Character.isLetter(c))
                    letterCount++;
                else
                    return false;
            }
            return digitCount >= 2 && letterCount >= 2;
        }
        return false;
    }
}
