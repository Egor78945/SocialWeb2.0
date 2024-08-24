package org.example.authenticationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.service.FriendshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
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

    @PostMapping("/reject/{id}")
    public ResponseEntity<String> rejectFriendshipRequest(@PathVariable("id") Long id) throws JsonProcessingException {
        friendshipService.rejectFriendshipRequest(id);
        return ResponseEntity.ok("Request has been rejected.");
    }

    @PostMapping("/discard/{id}")
    public ResponseEntity<String> discardFriendship(@PathVariable("id") Long id) throws JsonProcessingException {
        friendshipService.discardFriendshipRequest(id);
        return ResponseEntity.ok("The user is not longer your friend.");
    }
}
