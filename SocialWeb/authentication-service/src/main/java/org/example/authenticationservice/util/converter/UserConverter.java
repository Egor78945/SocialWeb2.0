package org.example.authenticationservice.util.converter;

import org.example.authenticationservice.enumeration.role.user.UserRole;

import java.util.List;
import java.util.Map;

public class UserConverter {
    private static final Map<String, UserRole> map = Map.of(UserRole.USER_ROLE.name(), UserRole.USER_ROLE,
            UserRole.ADMIN_ROLE.name(), UserRole.ADMIN_ROLE);

    public static UserRole convertRole(String role) {
        if (map.containsKey(role)) {
            return map.get(role);
        }
        throw new IllegalArgumentException("Unknown role.");
    }

    public static List<UserRole> convertRole(List<String> roles) {
        return roles
                .stream()
                .map(UserConverter::convertRole)
                .toList();
    }
}
