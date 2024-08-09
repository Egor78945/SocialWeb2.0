package org.example.authenticationservice.service.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.configuration.jwt.JWTConfiguration;
import org.example.authenticationservice.model.request.AuthenticationRequestModel;
import org.example.authenticationservice.model.request.RegisterRequestModel;
import org.example.authenticationservice.service.UserService;
import org.example.authenticationservice.service.grpc.UserGrpcService;
import org.example.authenticationservice.service.redis.RedisService;
import org.example.authenticationservice.util.validator.UserValidator;
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
    private final UserGrpcService userGrpcService;
    private final PasswordEncoder passwordEncoder;
    private final JWTConfiguration jwtConfiguration;
    private final RedisService redisService;
    private final UserService userService;

    public String authenticate(AuthenticationRequestModel authModel) throws JsonProcessingException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getEmail(), authModel.getPassword()));
        redisService.saveObject("current", userService.getUserProfileInformationRequest(authModel.getEmail()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtConfiguration.generateToken(authentication);
    }

    public Long register(RegisterRequestModel requestModel) {
        if (UserValidator.isValid(requestModel) && userGrpcService.getEmailUniqueRequest(requestModel.getEmail()).getResult()) {
            requestModel.setPassword(passwordEncoder.encode(requestModel.getPassword()));
            return userGrpcService.registerUser(requestModel).getId();
        } else throw new IllegalArgumentException("Bad registration data or email is busy.");
    }
}
