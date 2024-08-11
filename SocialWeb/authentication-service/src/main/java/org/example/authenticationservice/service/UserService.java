package org.example.authenticationservice.service;

import com.example.grpc.UserDatabaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.enumeration.redis.RedisKey;
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
    private final JsonMapper jsonMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDatabaseService.DetailsResponse response = userGrpcService.getDetailsRequest(username);
        return new UserDetailsImplementation(username, response.getPassword(), UserConverter.convertTo(response.getRolesList()));
    }

    public UserProfile getUserProfileInformationRequest(String email) {
        return new UserProfile(userGrpcService.getProfileInformation(email));
    }

    public UserProfile getUserProfile() throws JsonProcessingException {
        return jsonMapper.readValue(redisService.getObject(RedisKey.CURRENT_KEY.name()), UserProfile.class);
    }

    public UserProfile getUserProfileInformationRequest(Long id) throws JsonProcessingException {
        String userProfileString = redisService.getFromHash(RedisKey.USERS_KEY.name(), id.toString());
        if (userProfileString == null) {
            UserProfile userProfile = new UserProfile(userGrpcService.getProfileInformation(id));
            redisService.saveToHash(RedisKey.USERS_KEY.name(), id.toString(), userProfile);
            return userProfile;
        }
        return jsonMapper.readValue(userProfileString, UserProfile.class);
    }
}
