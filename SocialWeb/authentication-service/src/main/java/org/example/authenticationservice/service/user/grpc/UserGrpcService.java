package org.example.authenticationservice.service.user.grpc;

import com.example.grpc.user.UserDatabaseService;
import com.example.grpc.user.UserServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.authenticationservice.enumeration.role.user.UserRole;
import org.example.authenticationservice.model.request.RegisterRequestModel;
import org.example.authenticationservice.util.builder.user.UserDatabaseServiceBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGrpcService {
    @GrpcClient("user-grpc-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public UserDatabaseService.DetailsResponse getDetailsRequest(String email) {
        return userServiceBlockingStub.getUserDetails(UserDatabaseServiceBuilder.build(email));
    }

    public UserDatabaseService.BooleanResponse getEmailUniqueRequest(String email) {
        return userServiceBlockingStub.getEmailUnique(UserDatabaseServiceBuilder.build(email));
    }

    public UserDatabaseService.LongResponse registerUser(RegisterRequestModel requestModel) {
        UserDatabaseService.RegisterUserRequest request = UserDatabaseService.RegisterUserRequest
                .newBuilder()
                .setName(requestModel.getName())
                .setSurname(requestModel.getSurname())
                .setAge(requestModel.getAge())
                .setEmail(requestModel.getEmail())
                .setCity(requestModel.getCity())
                .setPassword(requestModel.getPassword())
                .addRole(UserRole.USER_ROLE.name())
                .build();
        return userServiceBlockingStub.registerUser(request);
    }

    public UserDatabaseService.GetProfileInformationResponse getProfileInformation(String email) {
        return userServiceBlockingStub.getProfileInformationByEmail(UserDatabaseServiceBuilder.build(email));
    }

    public UserDatabaseService.GetProfileInformationResponse getProfileInformation(Long id) {
        return userServiceBlockingStub.getProfileInformationById(UserDatabaseServiceBuilder.build(id));
    }

    public Boolean changeName(Long id, String name) {
        return userServiceBlockingStub.changeName(UserDatabaseServiceBuilder.build(id, name)).getBoolean();
    }

    public Boolean changeSurname(Long id, String surname) {
        return userServiceBlockingStub.changeSurname(UserDatabaseServiceBuilder.build(id, surname)).getBoolean();
    }

    public Boolean changeAge(Long id, Integer age) {
        return userServiceBlockingStub.changeAge(UserDatabaseServiceBuilder.build(id, age)).getBoolean();
    }

    public Boolean changeStatus(Long id, String status) {
        return userServiceBlockingStub.changeStatus(UserDatabaseServiceBuilder.build(id, status)).getBoolean();
    }

    public Boolean changeCity(Long id, String city) {
        return userServiceBlockingStub.changeCity(UserDatabaseServiceBuilder.build(id, city)).getBoolean();
    }

    public Boolean changeEmail(Long id, String email) {
        return userServiceBlockingStub.changeEmail(UserDatabaseServiceBuilder.build(id, email)).getBoolean();
    }

    public Boolean changePassword(Long id, String password) {
        return userServiceBlockingStub.changePassword(UserDatabaseServiceBuilder.build(id, password)).getBoolean();
    }

    public UserDatabaseService.ListGetProfileInformationResponse getProfileInformationListByListId(List<Long> idList){
        return userServiceBlockingStub.getProfileInformationByListId(UserDatabaseServiceBuilder.build(idList));
    }

    public Boolean incrementFriendCount(Long userId){
        return userServiceBlockingStub.incrementFriendCount(UserDatabaseServiceBuilder.build(userId)).getBoolean();
    }

    public Boolean decrementFriendCount(Long userId){
        return userServiceBlockingStub.decrementFriendCount(UserDatabaseServiceBuilder.build(userId)).getBoolean();
    }
}
