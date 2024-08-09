package org.example.authenticationservice.service;

import com.example.grpc.UserDatabaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.model.authentication.UserDetailsImplementation;
import org.example.authenticationservice.model.response.UserProfile;
import org.example.authenticationservice.service.grpc.UserGrpcService;
import org.example.authenticationservice.service.redis.RedisService;
import org.example.authenticationservice.util.converter.UserConverter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserGrpcService userGrpcService;
    private final RedisService redisService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDatabaseService.GetDetailsResponse response = userGrpcService.getDetailsRequest(username);
        return new UserDetailsImplementation(username, response.getPassword(), UserConverter.convertTo(response.getRolesList()));
    }

    public UserProfile getUserProfileInformationRequest(String email){
        return new UserProfile(userGrpcService.getProfileInformation(email));
    }

    public UserProfile getUserProfile() throws JsonProcessingException {
        return redisService.getObject("current", UserProfile.class);
    }
}
