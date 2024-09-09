package org.example.j2ee.messageservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.service.message.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/message")
@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestParam("recipient") Long recipient, @RequestParam("message") String message) throws JsonProcessingException {
        messageService.sendMessage(recipient, message);
        return ResponseEntity.ok("hello");
    }
}
