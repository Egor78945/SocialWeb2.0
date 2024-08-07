package org.example.userdatabaseservice.service.grpc;

import com.example.grpc.UserDatabaseService;
import com.example.grpc.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.userdatabaseservice.model.entity.User;
import org.example.userdatabaseservice.service.RoleService;
import org.example.userdatabaseservice.service.UserService;
import org.example.userdatabaseservice.util.converter.UserConverter;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {
    private final UserService userService;
    private final RoleService roleService;
    @Override
    public void getUserDetails(UserDatabaseService.GetDetailsRequest request, StreamObserver<UserDatabaseService.GetDetailsResponse> responseObserver) {
        UserDatabaseService.GetDetailsResponse response = UserDatabaseService.GetDetailsResponse
                .newBuilder()
                .setPassword(userService.getPassword(request.getEmail()))
                .addAllRoles(roleService.getRole(userService.getUserId(request.getEmail())))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getEmailUnique(UserDatabaseService.GetEmailUniqueRequest request, StreamObserver<UserDatabaseService.GetEmailUniqueResponse> responseObserver) {
        UserDatabaseService.GetEmailUniqueResponse response = UserDatabaseService.GetEmailUniqueResponse
                .newBuilder()
                .setResult(userService.getUserExists(request.getEmail()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void registerUser(UserDatabaseService.RegisterUserRequest request, StreamObserver<UserDatabaseService.RegisterUserResponse> responseObserver) {
        UserDatabaseService.RegisterUserResponse response = UserDatabaseService.RegisterUserResponse
                .newBuilder()
                .setId(userService.registerUser(UserConverter.convertTo(request)))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
