package org.example.authenticationservice.service.user;

import com.example.grpc.user.UserDatabaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.authenticationservice.enumeration.role.user.UserRole;
import org.example.authenticationservice.model.authentication.UserDetailsImplementation;
import org.example.authenticationservice.model.response.UserProfile;
import org.example.authenticationservice.service.user.grpc.UserGrpcService;
import org.example.authenticationservice.service.redis.RedisService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserGrpcService userGrpcService;
    @Mock
    private RedisService redisService;
    @Mock
    private JsonMapper jsonMapper;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void loadUserByUsername() {
        String username = "egor";

        Mockito
                .when(userGrpcService.getDetailsRequest(username))
                .thenReturn(UserDatabaseService.DetailsResponse.newBuilder().setPassword("123abc").addAllRoles(List.of("USER_ROLE")).build());
        UserDetailsImplementation userDetailsImplementation = new UserDetailsImplementation(username, "123abc", List.of(UserRole.USER_ROLE));

        Assertions.assertEquals(userService.loadUserByUsername(username), userDetailsImplementation);
    }

    @Test
    public void getUserProfileUsingEmail() {
        String email = "abc";
        var response = UserDatabaseService.GetProfileInformationResponse
                .newBuilder()
                .setId(1L)
                .setName("Egor")
                .setSurname("Petrov")
                .setAge(18)
                .setCity("Moscow")
                .setFriendCount(12)
                .setRegisterDate("someDate")
                .setStatus("abc")
                .build();

        Mockito.when(userGrpcService.getProfileInformation(email)).thenReturn(response);
        Assertions.assertEquals(userService.getUserProfile(email).getId(), response.getId());
    }

    @Test
    public void getUserProfile() throws JsonProcessingException {
        Mockito.when(redisService.getObject("CURRENT_KEY")).thenReturn("");
        Mockito.when(jsonMapper.readValue("", UserProfile.class)).thenReturn(new UserProfile());
        Assertions.assertEquals(userService.getUserProfile().getClass(), UserProfile.class);
    }

    @Test
    public void getUserProfileById() {
        Long id = 1L;

        var response = UserDatabaseService.GetProfileInformationResponse
                .newBuilder()
                .setId(1L)
                .setName("Egor")
                .setSurname("Petrov")
                .setAge(18)
                .setCity("Moscow")
                .setFriendCount(12)
                .setRegisterDate("someDate")
                .setStatus("abc")
                .build();

        Mockito.when(userGrpcService.getProfileInformation(id)).thenReturn(response);
        Assertions.assertEquals(userService.getUserProfile(id).getId(), response.getId());
    }

    @Test
    public void changeName_valid() throws JsonProcessingException {
        String name = "Egor";

        var userProfile = new UserProfile();
        userProfile.setId(1L);

        Mockito.when(userService.getUserProfile()).thenReturn(userProfile);
        Mockito.when(userGrpcService.changeName(1L, name)).thenReturn(true);

        userService.changeName(name);

        Assertions.assertNotNull(name);
    }

    @Test
    public void changeName_invalid() {
        String name = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.changeName(name));
    }

    @Test
    public void changeSurname_valid() throws JsonProcessingException {
        String surname = "Petrov";

        var userProfile = new UserProfile();
        userProfile.setId(1L);

        Mockito.when(userService.getUserProfile()).thenReturn(userProfile);
        Mockito.when(userGrpcService.changeSurname(1L, surname)).thenReturn(true);

        userService.changeSurname(surname);

        Assertions.assertNotNull(surname);
    }

    @Test
    public void changeSurname_invalid() {
        String surname = "";

        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.changeSurname(surname));
    }

    @Test
    public void changeAge_valid() throws JsonProcessingException {
        Integer age = 16;

        var userProfile = new UserProfile();
        userProfile.setId(1L);

        Mockito.when(userService.getUserProfile()).thenReturn(userProfile);
        Mockito.when(userGrpcService.changeAge(1L, age)).thenReturn(true);

        userService.changeAge(age);

        Assertions.assertNotNull(age);
    }

    @Test
    public void changeAge_low() {
        Integer age = 12;

        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.changeAge(age));
    }

    @Test
    public void changeAge_high() {
        Integer age = 150;

        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.changeAge(age));
    }

    @Test
    public void changeStatus_valid() throws JsonProcessingException {
        String status = "abc";

        var userProfile = new UserProfile();
        userProfile.setId(1L);

        Mockito.when(userService.getUserProfile()).thenReturn(userProfile);
        Mockito.when(userGrpcService.changeStatus(1L, status)).thenReturn(true);

        userService.changeStatus(status);

        Assertions.assertNotNull(status);
    }

    @Test
    public void changeStatus_low() {
        String status = "";

        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.changeStatus(status));
    }

    @Test
    public void changeStatus_high() {
        String status = "ajfhsaehfrskdhtrkhskjdhrkhskdhrsodolihsuetriusdtrhjsdhfhjtgdjhfgthjkgshdrhshdrhjshjkdrjhsh rrshjhr jkjkdrjs";

        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.changeStatus(status));
    }

    @Test
    public void changeCity_valid() throws JsonProcessingException {
        String city = "some city";

        var userProfile = new UserProfile();
        userProfile.setId(1L);

        Mockito.when(userService.getUserProfile()).thenReturn(userProfile);
        Mockito.when(userGrpcService.changeCity(1L, city)).thenReturn(true);

        userService.changeCity(city);

        Assertions.assertNotNull(city);
    }

    @Test
    public void changeCity_low() {
        String city = "";

        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.changeCity(city));
    }

    @Test
    public void changeCity_high() {
        String city = "sheunhthvsdtdfjhtfcnhjtbbhjdfgthjdxg fhjgdxfdjtgdsdfs";

        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.changeCity(city));
    }

    @Test
    public void changeEmail_valid() throws JsonProcessingException {
        String email = "Egor78396@gmail.com";

        var request = UserDatabaseService.BooleanResponse
                .newBuilder()
                .setBoolean(true)
                .build();

        var userProfile = new UserProfile();
        userProfile.setId(1L);

        Mockito.when(userGrpcService.getEmailUniqueRequest(email)).thenReturn(request);
        Mockito.when(userService.getUserProfile()).thenReturn(userProfile);
        Mockito.when(userGrpcService.changeEmail(1L, email)).thenReturn(true);

        userService.changeEmail(email);

        Assertions.assertNotNull(email);
    }

    @Test
    public void changeEmail_invalid() {
        String email = "@gmail.com";

        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.changeEmail(email));
    }

    @Test
    public void changePassword_valid() throws JsonProcessingException {
        String password = "1234abcd";

        var userProfile = new UserProfile();
        userProfile.setId(1L);

        Mockito.when(userService.getUserProfile()).thenReturn(userProfile);
        Mockito.when(passwordEncoder.encode(password)).thenReturn("encoded");
        Mockito.when(userGrpcService.changePassword(1L, "encoded")).thenReturn(true);

        userService.changePassword(password);

        Assertions.assertNotNull(password);
    }

    @Test
    public void changePassword_invalid() {
        String password = "1234";

        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.changePassword(password));
    }

    @Test
    public void incrementFriendCount(){
        Mockito.when(userGrpcService.incrementFriendCount(1L)).thenReturn(true);

        Assertions.assertTrue(userService.incrementFriendCount(1L));
    }

    @Test
    public void decrementFriendCount(){
        Mockito.when(userGrpcService.decrementFriendCount(1L)).thenReturn(true);

        Assertions.assertTrue(userService.decrementFriendCount(1L));
    }
}
