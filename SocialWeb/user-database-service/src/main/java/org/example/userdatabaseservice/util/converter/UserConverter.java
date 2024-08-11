package org.example.userdatabaseservice.util.converter;

import com.example.grpc.UserDatabaseService;
import org.example.userdatabaseservice.model.UserProfileRequestModel;
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

    public static UserDatabaseService.GetProfileInformationResponse convertTo(UserProfileRequestModel requestModel){
        return UserDatabaseService.GetProfileInformationResponse
                .newBuilder()
                .setId(requestModel.getId())
                .setName(requestModel.getName())
                .setSurname(requestModel.getSurname())
                .setAge(requestModel.getAge())
                .setCity(requestModel.getCity())
                .setFriendCount(requestModel.getFriendCount())
                .setRegisterDate(requestModel.getRegisterDate().toString())
                .setStatus(requestModel.getStatus())
                .build();
    }
}
