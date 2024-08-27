package org.example.authenticationservice.service;

import com.example.grpc.user.UserDatabaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.enumeration.redis.RedisKey;
import org.example.authenticationservice.model.authentication.UserDetailsImplementation;
import org.example.authenticationservice.model.response.UserProfile;
import org.example.authenticationservice.service.grpc.UserGrpcService;
import org.example.authenticationservice.service.redis.RedisService;
import org.example.authenticationservice.util.converter.UserConverter;
import org.example.authenticationservice.util.validator.UserValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserGrpcService userGrpcService;
    private final RedisService redisService;
    private final JsonMapper jsonMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDatabaseService.DetailsResponse response = userGrpcService.getDetailsRequest(username);
        return new UserDetailsImplementation(username, response.getPassword(), UserConverter.convertStringToUserRole(response.getRolesList()));
    }

    public UserProfile getUserProfile(String email) {
        return new UserProfile(userGrpcService.getProfileInformation(email));
    }

    public UserProfile getUserProfile() throws JsonProcessingException {
        return jsonMapper.readValue(redisService.getObject(RedisKey.CURRENT_KEY.name()), UserProfile.class);
    }

    public UserProfile getUserProfile(Long id) {
        return new UserProfile(userGrpcService.getProfileInformation(id));
    }

    public void changeName(String name) throws JsonProcessingException {
        if (!name.isEmpty() && !name.isBlank() && name.length() <= 30) {
            if (userGrpcService.changeName(getUserProfile().getId(), name)) {
                reloadUserToRedis();
            }
        } else throw new IllegalArgumentException("Illegal name format.");
    }

    public void changeSurname(String surname) throws JsonProcessingException {
        if (!surname.isEmpty() && !surname.isBlank() && surname.length() <= 50) {
            if (userGrpcService.changeSurname(getUserProfile().getId(), surname)) {
                reloadUserToRedis();
            }
        } else throw new IllegalArgumentException("Illegal surname format.");
    }

    public void changeAge(Integer age) throws JsonProcessingException {
        if (age >= 14 && age <= 111) {
            if (userGrpcService.changeAge(getUserProfile().getId(), age)) {
                reloadUserToRedis();
            }
        } else throw new IllegalArgumentException("Age is too low or high.");
    }

    public void changeStatus(String status) throws JsonProcessingException {
        if (!status.isEmpty() && !status.isBlank() && status.length() <= 100) {
            if (userGrpcService.changeStatus(getUserProfile().getId(), status)) {
                reloadUserToRedis();
            }
        } else throw new IllegalArgumentException("Status is empty or too long.");
    }

    public void changeCity(String city) throws JsonProcessingException {
        if (!city.isEmpty() && !city.isBlank() && city.length() <= 50) {
            if (userGrpcService.changeCity(getUserProfile().getId(), city)) {
                reloadUserToRedis();
            }
        } else throw new IllegalArgumentException("City is empty or too long.");
    }

    public void changeEmail(String email) throws JsonProcessingException {
        if (UserValidator.checkEmail(email) && userGrpcService.getEmailUniqueRequest(email).getBoolean()) {
            userGrpcService.changeEmail(getUserProfile().getId(), email);
        } else throw new IllegalArgumentException("Illegal email format or email is busy.");
    }

    public void changePassword(String password) throws JsonProcessingException {
        if (UserValidator.checkPassword(password)) {
            userGrpcService.changePassword(getUserProfile().getId(), passwordEncoder.encode(password));
        } else throw new IllegalArgumentException("Illegal password format.");
    }

    public boolean incrementFriendCount(Long userId) throws JsonProcessingException {
        boolean result = userGrpcService.incrementFriendCount(userId);
        reloadUserToRedis();
        return result;
    }

    public boolean decrementFriendCount(Long userId) throws JsonProcessingException {
        boolean result = userGrpcService.decrementFriendCount(userId);
        reloadUserToRedis();
        return result;
    }

    public void reloadUserToRedis() throws JsonProcessingException {
        redisService.saveObject(RedisKey.CURRENT_KEY.name(), getUserProfile(getUserProfile().getId()));
    }
}
