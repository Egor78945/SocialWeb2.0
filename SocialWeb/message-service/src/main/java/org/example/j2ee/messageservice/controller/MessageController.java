package org.example.j2ee.messageservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.j2ee.messageservice.model.dto.message.MessageList;
import org.example.j2ee.messageservice.service.message.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/message")
@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestParam("recipient") Long recipient, @RequestParam("message") String message) throws JsonProcessingException {
        messageService.sendMessage(recipient, message);
        return ResponseEntity.ok(String.format("Message has been sent to user with id %s", recipient));
    }

    @GetMapping
    public ResponseEntity<String> getMessages(@RequestParam("recipient") Long recipientId) throws JsonProcessingException {
        return ResponseEntity.ok(messageService.getMessageAddresses(recipientId).toString());
    }
}