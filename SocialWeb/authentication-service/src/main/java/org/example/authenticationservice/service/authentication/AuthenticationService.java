package org.example.authenticationservice.service.authentication;

import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.configuration.jwt.JWTConfiguration;
import org.example.authenticationservice.model.request.AuthenticationRequestModel;
import org.example.authenticationservice.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTConfiguration jwtConfiguration;
    private final UserService userService;

    public String authenticate(AuthenticationRequestModel authModel) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getEmail(), authModel.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtConfiguration.generateToken(authentication);
    }
}
