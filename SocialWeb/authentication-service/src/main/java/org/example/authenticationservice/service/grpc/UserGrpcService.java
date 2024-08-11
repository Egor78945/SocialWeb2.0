package org.example.authenticationservice.service.grpc;

import com.example.grpc.UserDatabaseService;
import com.example.grpc.UserServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.authenticationservice.enumeration.role.user.UserRole;
import org.example.authenticationservice.model.request.RegisterRequestModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGrpcService {
    @GrpcClient("user-grpc-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public UserDatabaseService.DetailsResponse getDetailsRequest(String email) {
        UserDatabaseService.StringRequest request = UserDatabaseService.StringRequest
                .newBuilder()
                .setString(email)
                .build();
        return userServiceBlockingStub.getUserDetails(request);
    }

    public UserDatabaseService.BooleanResponse getEmailUniqueRequest(String email) {
        UserDatabaseService.StringRequest request = UserDatabaseService.StringRequest
                .newBuilder()
                .setString(email)
                .build();
        return userServiceBlockingStub.getEmailUnique(request);
    }

    public UserDatabaseService.RegisterUserResponse registerUser(RegisterRequestModel requestModel) {
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
        UserDatabaseService.StringRequest request = UserDatabaseService.StringRequest
                .newBuilder()
                .setString(email)
                .build();
        return userServiceBlockingStub.getProfileInformationByEmail(request);
    }

    public UserDatabaseService.GetProfileInformationResponse getProfileInformation(Long id) {
        UserDatabaseService.LongRequest request = UserDatabaseService.LongRequest
                .newBuilder()
                .setLong(id)
                .build();
        return userServiceBlockingStub.getProfileInformationById(request);
    }
}
