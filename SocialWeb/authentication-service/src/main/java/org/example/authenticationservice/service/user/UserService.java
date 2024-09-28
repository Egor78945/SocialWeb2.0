package org.example.authenticationservice.service.user;

import com.example.grpc.user.UserDatabaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.enumeration.redis.RedisKey;
import org.example.authenticationservice.model.authentication.UserDetailsImplementation;
import org.example.authenticationservice.model.response.UserProfile;
import org.example.authenticationservice.service.user.grpc.UserGrpcService;
import org.example.authenticationservice.service.redis.RedisService;
import org.example.authenticationservice.util.user.converter.UserConverter;
import org.example.authenticationservice.util.user.validator.UserValidator;
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
    private final ObjectMapper jsonMapper;
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
        return redisService.readValueAs(redisService.getObject(RedisKey.CURRENT_KEY.name()), UserProfile.class);
    }

    public UserProfile getUserProfile(Long id) {
        return new UserProfile(userGrpcService.getProfileInformation(id));
    }

    public void refreshUser() throws JsonProcessingException {
        redisService.saveObject(RedisKey.CURRENT_KEY.name(), getUserProfile(getUserProfile().getId()));
    }

    public void changeName(String name) throws JsonProcessingException {
        if (!name.isEmpty() && !name.isBlank() && name.length() <= 30) {
            userGrpcService.changeName(getUserProfile().getId(), name);
        } else throw new IllegalArgumentException("Illegal name format.");
    }

    public void changeSurname(String surname) throws JsonProcessingException {
        if (!surname.isEmpty() && !surname.isBlank() && surname.length() <= 50) {
            userGrpcService.changeSurname(getUserProfile().getId(), surname);
        } else throw new IllegalArgumentException("Illegal surname format.");
    }

    public void changeAge(Integer age) throws JsonProcessingException {
        if (age >= 14 && age <= 111) {
            userGrpcService.changeAge(getUserProfile().getId(), age);
        } else throw new IllegalArgumentException("Age is too low or high.");
    }

    public void changeStatus(String status) throws JsonProcessingException {
        if (!status.isEmpty() && !status.isBlank() && status.length() <= 100) {
            userGrpcService.changeStatus(getUserProfile().getId(), status);
        } else throw new IllegalArgumentException("Status is empty or too long.");
    }

    public void changeCity(String city) throws JsonProcessingException {
        if (!city.isEmpty() && !city.isBlank() && city.length() <= 50) {
            userGrpcService.changeCity(getUserProfile().getId(), city);
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

    public boolean incrementFriendCount(Long userId) {
        return userGrpcService.incrementFriendCount(userId);
    }

    public boolean decrementFriendCount(Long userId) {
        return userGrpcService.decrementFriendCount(userId);
    }
}
