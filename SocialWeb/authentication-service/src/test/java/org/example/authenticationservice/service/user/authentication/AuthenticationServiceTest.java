package org.example.authenticationservice.service.user.authentication;

import com.example.grpc.user.UserDatabaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.authenticationservice.configuration.jwt.JWTConfiguration;
import org.example.authenticationservice.enumeration.redis.RedisKey;
import org.example.authenticationservice.model.request.AuthenticationRequestModel;
import org.example.authenticationservice.model.request.RegisterRequestModel;
import org.example.authenticationservice.model.response.UserProfile;
import org.example.authenticationservice.service.redis.RedisService;
import org.example.authenticationservice.service.user.UserService;
import org.example.authenticationservice.service.user.grpc.UserGrpcService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationService authenticationService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserGrpcService userGrpcService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JWTConfiguration jwtConfiguration;
    @Mock
    private RedisService redisService;
    @Mock
    private UserService userService;

    private final String EMAIL;
    private final String PASSWORD;
    private final RegisterRequestModel requestModel;

    {
        EMAIL = "Egor78396@gmail.com";
        PASSWORD = "12345abcde";

        requestModel = new RegisterRequestModel();
        requestModel.setName("Egor");
        requestModel.setCity("Moscow");
        requestModel.setAge(17);
        requestModel.setPassword("12345abcd");
        requestModel.setSurname("Petrov");
    }

    @Test
    public void authenticate() throws JsonProcessingException {
        AuthenticationRequestModel requestModel = new AuthenticationRequestModel(EMAIL, PASSWORD);
        UserProfile userProfile = new UserProfile();

        Mockito.when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getEmail(), requestModel.getPassword()))).thenReturn(null);
        Mockito.when(userService.getUserProfile(requestModel.getEmail())).thenReturn(userProfile);
        Mockito.doNothing().when(redisService).saveObject(RedisKey.CURRENT_KEY.name(), userProfile);
        Mockito.when(jwtConfiguration.generateToken(null)).thenReturn(null);

        Assertions.assertNull(authenticationService.authenticate(requestModel));
    }

    @Test
    public void register_valid(){
        requestModel.setEmail(EMAIL);

        Mockito.when(userGrpcService.getEmailUniqueRequest(requestModel.getEmail())).thenReturn(UserDatabaseService.BooleanResponse.newBuilder().setBoolean(true).build());
        Mockito.when(passwordEncoder.encode(requestModel.getPassword())).thenReturn("");
        Mockito.when(userGrpcService.registerUser(requestModel)).thenReturn(UserDatabaseService.LongResponse.newBuilder().setLong(1L).build());

        Assertions.assertEquals(authenticationService.register(requestModel), 1L);
    }

    @Test
    public void register_invalidEmail(){
        requestModel.setEmail("@gmail.com");

        Assertions.assertThrows(IllegalArgumentException.class, () -> authenticationService.register(requestModel));
    }

    @Test
    public void register_invalidPassword(){
        requestModel.setEmail(EMAIL);
        requestModel.setPassword("12");

        Assertions.assertThrows(IllegalArgumentException.class, () -> authenticationService.register(requestModel));
    }
}
