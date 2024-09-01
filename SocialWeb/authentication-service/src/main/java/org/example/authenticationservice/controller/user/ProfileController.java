package org.example.authenticationservice.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.annotation.ProfileControllerAdvice;
import org.example.authenticationservice.model.response.UserProfile;
import org.example.authenticationservice.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@ProfileControllerAdvice
public class ProfileController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<UserProfile> myProfile() throws JsonProcessingException {
        return ResponseEntity.ok(userService.getUserProfile());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> userProfile(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserProfile(id));
    }

    @PatchMapping("/name")
    public ResponseEntity<String> changeName(@RequestParam("newName") String name) throws JsonProcessingException {
        userService.changeName(name);
        return ResponseEntity.ok("Your name has been updated.");
    }

    @PatchMapping("/surname")
    public ResponseEntity<String> changeSurname(@RequestParam("newSurname") String surname) throws JsonProcessingException {
        userService.changeSurname(surname);
        return ResponseEntity.ok("Your surname has been updated.");
    }

    @PatchMapping("/age")
    public ResponseEntity<String> changeAge(@RequestParam("newAge") Integer age) throws JsonProcessingException {
        userService.changeAge(age);
        return ResponseEntity.ok("Your age has been updated.");
    }

    @PatchMapping("/status")
    public ResponseEntity<String> changeStatus(@RequestParam("newStatus") String status) throws JsonProcessingException {
        userService.changeStatus(status);
        return ResponseEntity.ok("Your status has been updated.");
    }

    @PatchMapping("/city")
    public ResponseEntity<String> changeCity(@RequestParam("newCity") String city) throws JsonProcessingException {
        userService.changeCity(city);
        return ResponseEntity.ok("Your city has been updated.");
    }

    @PatchMapping("/email")
    public ResponseEntity<String> changeEmail(@RequestParam("newEmail") String email) throws JsonProcessingException {
        userService.changeEmail(email);
        return ResponseEntity.ok("Email successfully changed.");
    }

    @PatchMapping("/password")
    public ResponseEntity<String> changePassword(@RequestParam("newPassword") String password) throws JsonProcessingException {
        userService.changePassword(password);
        return ResponseEntity.ok("Password successfully changed.");
    }
}
