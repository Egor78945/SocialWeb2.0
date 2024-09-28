package org.example.userdatabaseservice.util.user.converter.grpc;

import com.example.grpc.user.UserDatabaseService;
import org.example.userdatabaseservice.model.UserProfileRequestModel;
import org.example.userdatabaseservice.model.entity.User;

import java.util.List;

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

    public static UserDatabaseService.GetProfileInformationResponse convertTo(UserProfileRequestModel requestModel) {
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

    public static List<UserDatabaseService.GetProfileInformationResponse> convertTo(List<UserProfileRequestModel> requestModel) {
        return requestModel
                .stream()
                .map(r -> convertTo(r))
                .toList();
    }
}
