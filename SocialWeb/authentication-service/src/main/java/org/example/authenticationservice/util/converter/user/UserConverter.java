package org.example.authenticationservice.util.converter.user;

import com.example.grpc.user.UserDatabaseService;
import org.example.authenticationservice.enumeration.role.user.UserRole;
import org.example.authenticationservice.model.response.UserProfile;

import java.util.List;
import java.util.Map;

public class UserConverter {
    private static final Map<String, UserRole> map;

    static {
        map = Map.of(UserRole.USER_ROLE.name(), UserRole.USER_ROLE,
                UserRole.ADMIN_ROLE.name(), UserRole.ADMIN_ROLE);
    }

    public static UserRole convertStringToUserRole(String role) {
        if (map.containsKey(role)) {
            return map.get(role);
        }
        throw new IllegalArgumentException("Unknown role.");
    }

    public static List<UserRole> convertStringToUserRole(List<String> roles) {
        return roles
                .stream()
                .map(UserConverter::convertStringToUserRole)
                .toList();
    }

    public static UserProfile convertGetProfileInformationResponseToUserProfile(UserDatabaseService.GetProfileInformationResponse response) {
        return new UserProfile(response);
    }

    public static List<UserProfile> convertGetProfileInformationResponseToUserProfile(List<UserDatabaseService.GetProfileInformationResponse> response) {
        return response
                .stream()
                .map(UserConverter::convertGetProfileInformationResponseToUserProfile)
                .toList();
    }
}
