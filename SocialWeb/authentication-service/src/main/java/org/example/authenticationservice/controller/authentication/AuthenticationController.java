package org.example.authenticationservice.controller.authentication;

import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.model.request.AuthenticationRequestModel;
import org.example.authenticationservice.service.authentication.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<String> login(@RequestBody AuthenticationRequestModel authModel){
        return ResponseEntity.ok(authenticationService.authenticate(authModel));
    }
}
