package org.example.authenticationservice.service;

import com.example.grpc.UserDatabaseService;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.model.authentication.UserDetailsImplementation;
import org.example.authenticationservice.service.grpc.UserGrpcService;
import org.example.authenticationservice.util.converter.UserConverter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserGrpcService userGrpcService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDatabaseService.GetDetailsResponse response = userGrpcService.getDetailsRequest(username);
        return new UserDetailsImplementation(username, response.getPassword(), UserConverter.convertTo(response.getRolesList()));
    }
}
