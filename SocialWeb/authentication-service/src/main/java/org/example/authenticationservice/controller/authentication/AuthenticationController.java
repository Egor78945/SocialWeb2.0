package org.example.authenticationservice.controller.authentication;

import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.model.request.AuthenticationRequestModel;
import org.example.authenticationservice.model.request.RegisterRequestModel;
import org.example.authenticationservice.service.authentication.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<String> login(@RequestBody AuthenticationRequestModel authModel) {
        return ResponseEntity.ok(authenticationService.authenticate(authModel));
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterRequestModel requestModel) {
        return ResponseEntity.ok(String.format("You have successfully registered by id %s", authenticationService.register(requestModel)));
    }
}
