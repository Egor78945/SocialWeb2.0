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

    public UserDatabaseService.GetDetailsResponse getDetailsRequest(String email) {
        UserDatabaseService.GetDetailsRequest request = UserDatabaseService.GetDetailsRequest
                .newBuilder()
                .setEmail(email)
                .build();
        return userServiceBlockingStub.getUserDetails(request);
    }

    public UserDatabaseService.GetEmailUniqueResponse getEmailUniqueRequest(String email) {
        UserDatabaseService.GetEmailUniqueRequest request = UserDatabaseService.GetEmailUniqueRequest
                .newBuilder()
                .setEmail(email)
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

    public UserDatabaseService.GetProfileInformationResponse getProfileInformation(String email){
        UserDatabaseService.GetDetailsRequest request = UserDatabaseService.GetDetailsRequest
                .newBuilder()
                .setEmail(email)
                .build();
        return userServiceBlockingStub.getProfileInformation(request);
    }
    public UserDatabaseService.GetProfileInformationResponse getProfileInformation(Long id){
        UserDatabaseService.GetDetailsRequest request = UserDatabaseService.GetDetailsRequest
                .newBuilder()
                .setId(id)
                .build();
        return userServiceBlockingStub.getProfileInformation(request);
    }
}
