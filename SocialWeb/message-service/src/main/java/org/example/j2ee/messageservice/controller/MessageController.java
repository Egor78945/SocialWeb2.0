package org.example.j2ee.messageservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/message")
@RestController
@RequiredArgsConstructor
public class MessageController {
    @GetMapping("/hello")
    public ResponseEntity<String> simple(){
        return ResponseEntity.ok("hello");
    }
}
