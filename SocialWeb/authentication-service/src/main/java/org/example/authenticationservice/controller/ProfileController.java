package org.example.authenticationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.model.response.UserProfile;
import org.example.authenticationservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<UserProfile> myProfile() throws JsonProcessingException {
        return ResponseEntity.ok(userService.getUserProfile());
    }
}
