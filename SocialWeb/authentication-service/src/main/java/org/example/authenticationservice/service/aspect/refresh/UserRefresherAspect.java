package org.example.authenticationservice.service.aspect.refresh;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.example.authenticationservice.service.UserService;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class UserRefresherAspect {
    private final UserService userService;

    @After("execution(public void org.example.authenticationservice.service.UserService.changeName(java.lang.String))")
    public void changeNameAdvice() throws JsonProcessingException {
        userService.refreshUser();
    }

    @After("execution(public void org.example.authenticationservice.service.UserService.changeSurname(java.lang.String))")
    public void changeSurnameAdvice() throws JsonProcessingException {
        userService.refreshUser();
    }

    @After("execution(public void org.example.authenticationservice.service.UserService.changeAge(java.lang.Integer))")
    public void changeAgeAdvice() throws JsonProcessingException {
        userService.refreshUser();
    }

    @After("execution(public void org.example.authenticationservice.service.UserService.changeStatus(java.lang.String))")
    public void changeStatusAdvice() throws JsonProcessingException {
        userService.refreshUser();
    }

    @After("execution(public void org.example.authenticationservice.service.UserService.changeCity(java.lang.String))")
    public void changeCityAdvice() throws JsonProcessingException {
        userService.refreshUser();
    }

    @After("execution(public void org.example.authenticationservice.service.UserService.changeEmail(java.lang.String))")
    public void changeEmailAdvice() throws JsonProcessingException {
        userService.refreshUser();
    }

    @After("execution(public void org.example.authenticationservice.service.UserService.changePassword(java.lang.String))")
    public void changePasswordAdvice() throws JsonProcessingException {
        userService.refreshUser();
    }

    @AfterReturning("execution(public boolean org.example.authenticationservice.service.UserService.incrementFriendCount())")
    public void incrementFriendCountAdvice() throws JsonProcessingException {
        userService.refreshUser();
    }

    @AfterReturning("execution(public boolean org.example.authenticationservice.service.UserService.decrementFriendCount())")
    public void decrementFriendCountAdvice() throws JsonProcessingException {
        userService.refreshUser();
    }
}
