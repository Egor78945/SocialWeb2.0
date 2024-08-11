package org.example.userdatabaseservice.service.grpc;

import com.example.grpc.UserDatabaseService;
import com.example.grpc.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.userdatabaseservice.model.UserProfileRequestModel;
import org.example.userdatabaseservice.service.RoleService;
import org.example.userdatabaseservice.service.UserService;
import org.example.userdatabaseservice.util.converter.UserConverter;
import org.springframework.transaction.annotation.Transactional;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public void getUserDetails(UserDatabaseService.StringRequest request, StreamObserver<UserDatabaseService.DetailsResponse> responseObserver) {
        UserDatabaseService.DetailsResponse response = UserDatabaseService.DetailsResponse
                .newBuilder()
                .setPassword(userService.getPassword(request.getString()))
                .addAllRoles(roleService.getRole(userService.getUserId(request.getString())))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getEmailUnique(UserDatabaseService.StringRequest request, StreamObserver<UserDatabaseService.BooleanResponse> responseObserver) {
        UserDatabaseService.BooleanResponse response = UserDatabaseService.BooleanResponse
                .newBuilder()
                .setBoolean(!userService.getUserExists(request.getString()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void registerUser(UserDatabaseService.RegisterUserRequest request, StreamObserver<UserDatabaseService.LongResponse> responseObserver) {
        Long savedUserId = userService.registerUser(UserConverter.convertTo(request));
        roleService.saveRole(savedUserId, request.getRoleList());
        UserDatabaseService.LongResponse response = UserDatabaseService.LongResponse
                .newBuilder()
                .setLong(savedUserId)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getProfileInformationByEmail(UserDatabaseService.StringRequest request, StreamObserver<UserDatabaseService.GetProfileInformationResponse> responseObserver) {
        UserProfileRequestModel requestModel = userService.getUserProfileInformation(request.getString());
        UserDatabaseService.GetProfileInformationResponse response = UserConverter.convertTo(requestModel);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void getProfileInformationById(UserDatabaseService.LongRequest request, StreamObserver<UserDatabaseService.GetProfileInformationResponse> responseObserver) {
        UserProfileRequestModel requestModel = userService.getUserProfileInformation(request.getLong());
        UserDatabaseService.GetProfileInformationResponse response = UserConverter.convertTo(requestModel);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changeName(UserDatabaseService.LongStringRequest request, StreamObserver<UserDatabaseService.BooleanResponse> responseObserver) {
        userService.changeUserNameById(request.getLong(), request.getString());
        UserDatabaseService.BooleanResponse response = UserDatabaseService.BooleanResponse
                .newBuilder()
                .setBoolean(true)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
