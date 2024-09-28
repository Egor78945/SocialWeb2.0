package org.example.authenticationservice.util.converter.user;

import com.example.grpc.user.UserDatabaseService;
import org.example.authenticationservice.enumeration.role.user.UserRole;
import org.example.authenticationservice.model.response.UserProfile;
import org.example.authenticationservice.util.user.converter.UserConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserConverterTest {
    @Test
    public void convertStringToUserRole_validRole() {
        Assertions.assertEquals(UserConverter.convertStringToUserRole("USER_ROLE"), UserRole.USER_ROLE);
    }

    @Test
    public void convertStringToUserRole_invalidRole() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserConverter.convertStringToUserRole("User"));
    }

    @Test
    public void convertStringToUserRole_listValidRole() {
        Assertions.assertEquals(UserConverter.convertStringToUserRole(List.of("USER_ROLE", "ADMIN_ROLE")), List.of(UserRole.USER_ROLE, UserRole.ADMIN_ROLE));
    }

    @Test
    public void convertStringToUserRole_listInvalidRole() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserConverter.convertStringToUserRole(List.of("USER", "ADMIN")));
    }

    @Test
    public void convertGetProfileInformationResponseToUserProfile_valid(){
        UserDatabaseService.GetProfileInformationResponse testResponse = UserDatabaseService.GetProfileInformationResponse
                .newBuilder()
                .setId(1L)
                .setName("Egor")
                .setSurname("Petrov")
                .setAge(18)
                .setCity("Novocheboksarsk")
                .setFriendCount(12)
                .setRegisterDate("someDate")
                .setStatus("abc")
                .build();
        UserProfile testUserProfile = new UserProfile(1L, "Egor", "Petrov", 18, "Novocheboksarsk", 12, "someDate", "abc");
        Assertions.assertEquals(UserConverter.convertGetProfileInformationResponseToUserProfile(testResponse), testUserProfile);
    }

    public void convertGetProfileInformationResponseToUserProfile_validListConvert(){
        UserDatabaseService.GetProfileInformationResponse testResponse1 = UserDatabaseService.GetProfileInformationResponse
                .newBuilder()
                .setId(1L)
                .setName("Egor")
                .setSurname("Petrov")
                .setAge(18)
                .setCity("Novocheboksarsk")
                .setFriendCount(12)
                .setRegisterDate("someDate")
                .setStatus("abc")
                .build();
        UserProfile testUserProfile1 = new UserProfile(1L, "Egor", "Petrov", 18, "Novocheboksarsk", 12, "someDate", "abc");
        Assertions.assertEquals(UserConverter.convertGetProfileInformationResponseToUserProfile(List.of(testResponse1)), List.of(testUserProfile1));
    }
}
