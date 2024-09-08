package org.example.j2ee.messageservice.controller;

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
    public ResponseEntity<String> sendMessage(@RequestParam("sender") Long sender, @RequestParam("recipient") Long recipient, @RequestParam("message") String message){
        messageService.sendMessage(sender, recipient, message);
        return ResponseEntity.ok("hello");
    }
}
