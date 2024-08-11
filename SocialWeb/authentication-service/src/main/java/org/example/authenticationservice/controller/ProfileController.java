package org.example.authenticationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.model.response.UserProfile;
import org.example.authenticationservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<UserProfile> myProfile() throws JsonProcessingException {
        return ResponseEntity.ok(userService.getUserProfile());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> userProfile(@PathVariable("id") Long id) throws JsonProcessingException {
        return ResponseEntity.ok(userService.getUserProfile(id));
    }

    @PatchMapping("/name")
    public ResponseEntity<String> changeName(@RequestParam("newName") String name) throws JsonProcessingException {
        userService.changeName(name);
        return ResponseEntity.ok("Your name has been updated.");
    }
}
