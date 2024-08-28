package org.example.authenticationservice.util.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserValidatorTest {
    @Test
    public void checkEmail_valid(){
        Assertions.assertTrue(UserValidator.checkEmail("Egor78396@gmail.com"));
    }

    @Test
    public void checkEmail_emptyUsername(){
        Assertions.assertFalse(UserValidator.checkEmail("@gmail.com"));
    }

    @Test
    public void checkEmail_blankUsername(){
        Assertions.assertFalse(UserValidator.checkEmail(" @gmail.com"));
    }

    @Test
    public void checkEmail_longUsername(){
        Assertions.assertFalse(UserValidator.checkEmail("aaaaaaaaaaaaaaaaaaaaaafasfasfafafafafsafsafsaaaaaaaaaaaaaaaaaaa7ssssssss777887787a8s8ddddddddddddddddddddddddsdfsdfsdf7s7df87s7df87s887d87sf87s7f87s66fs67d7s77ds7d87f87sd@gmail.com"));
    }

    @Test
    public void checkEmail_doubleDog(){
        Assertions.assertFalse(UserValidator.checkEmail("Egor78396@@gmail.com"));
    }

    @Test
    public void checkEmail_withoutDog(){
        Assertions.assertFalse(UserValidator.checkEmail("Egor78396gmail.com"));
    }

    @Test
    public void checkEmail_withoutDomain(){
        Assertions.assertFalse(UserValidator.checkEmail("Egor78396@"));
    }

    @Test
    public void checkEmail_unknownDomain(){
        Assertions.assertFalse(UserValidator.checkEmail("Egor78396@abc.com"));
    }

    @Test
    public void checkPassword_valid(){
        Assertions.assertTrue(UserValidator.checkPassword("1234abcd"));
    }

    @Test
    public void checkPassword_short(){
        Assertions.assertFalse(UserValidator.checkPassword("12ab"));
    }

    @Test
    public void checkPassword_lowDigitCount(){
        Assertions.assertFalse(UserValidator.checkPassword("abcde"));
    }

    @Test
    public void checkPassword_lowLetterCount(){
        Assertions.assertFalse(UserValidator.checkPassword("12345"));
    }

    @Test
    public void checkPassword_long(){
        Assertions.assertFalse(UserValidator.checkPassword("12absdfsd6f6s767567d56756d56fs56d56767s6767ds6f67s676d6d6dd6d6dd6d6d5fsf"));
    }

    @Test
    public void checkPassword_wrongSymbols(){
        Assertions.assertFalse(UserValidator.checkPassword("123abc./!}{"));
    }

    @Test
    public void checkPassword_spaces(){
        Assertions.assertFalse(UserValidator.checkPassword("123 abc"));
    }
}
