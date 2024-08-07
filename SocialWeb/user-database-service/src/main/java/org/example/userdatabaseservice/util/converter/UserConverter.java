package org.example.userdatabaseservice.util.converter;

import com.example.grpc.UserDatabaseService;
import org.example.userdatabaseservice.model.entity.User;

public class UserConverter {
    public static User convertTo(UserDatabaseService.RegisterUserRequest request) {
        return User.newBuilder()
                .setName(request.getName())
                .setSurname(request.getSurname())
                .setAge(request.getAge())
                .setCity(request.getCity())
                .setEmail(request.getEmail())
                .setPassword(request.getPassword())
                .setRegisterDate()
                .setFriendCount(0)
                .setStatus("")
                .build();
    }
}
