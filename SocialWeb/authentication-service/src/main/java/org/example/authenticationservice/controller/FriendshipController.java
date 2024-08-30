package org.example.authenticationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.annotation.FriendshipControllerAdvice;
import org.example.authenticationservice.model.response.UserProfile;
import org.example.authenticationservice.service.FriendshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
@FriendshipControllerAdvice
public class FriendshipController {
    private final FriendshipService friendshipService;

    @PostMapping("/{id}")
    public ResponseEntity<String> sendFriendRequest(@PathVariable("id") Long id) throws JsonProcessingException {
        friendshipService.friendRequest(id);
        return ResponseEntity.ok("Request has been sent.");
    }

    @PostMapping("/accept/{id}")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable("id") Long id) throws JsonProcessingException {
        friendshipService.acceptFriendshipRequest(id);
        return ResponseEntity.ok("Request has been accepted.");
    }

    @DeleteMapping("/reject/{id}")
    public ResponseEntity<String> rejectFriendshipRequest(@PathVariable("id") Long id) throws JsonProcessingException {
        friendshipService.rejectFriendshipRequest(id);
        return ResponseEntity.ok("Request has been rejected.");
    }

    @DeleteMapping("/discard/{id}")
    public ResponseEntity<String> discardFriendship(@PathVariable("id") Long id) throws JsonProcessingException {
        friendshipService.discardFriendshipRequest(id);
        return ResponseEntity.ok("The user is not longer your friend.");
    }

    @GetMapping("/request")
    public ResponseEntity<List<UserProfile>> getAllFriendshipRequests() throws JsonProcessingException {
        return ResponseEntity.ok(friendshipService.getAllFriendshipRequests());
    }

    @GetMapping
    public ResponseEntity<List<UserProfile>> getAllFriends() throws JsonProcessingException {
        return ResponseEntity.ok(friendshipService.getAllFriends());
    }
}
