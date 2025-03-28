package org.example.userdatabaseservice.service.user.grpc;

import com.example.grpc.user.UserDatabaseService;
import com.example.grpc.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.userdatabaseservice.model.UserProfileRequestModel;
import org.example.userdatabaseservice.service.role.RoleService;
import org.example.userdatabaseservice.service.user.UserService;
import org.example.userdatabaseservice.util.user.builder.grpc.UserDatabaseServiceBuilder;
import org.example.userdatabaseservice.util.user.converter.grpc.UserConverter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        responseObserver.onNext(UserDatabaseServiceBuilder.build(!userService.existsUserByEmail(request.getString())));
        responseObserver.onCompleted();
    }

    @Override
    public void existsUserById(UserDatabaseService.LongRequest request, StreamObserver<UserDatabaseService.BooleanResponse> responseObserver) {
        responseObserver.onNext(UserDatabaseServiceBuilder.build(userService.existsUserById(request.getLong())));
        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void registerUser(UserDatabaseService.RegisterUserRequest request, StreamObserver<UserDatabaseService.LongResponse> responseObserver) {
        Long savedUserId = userService.registerUser(UserConverter.convertTo(request));
        roleService.saveRole(savedUserId, request.getRoleList());
        responseObserver.onNext(UserDatabaseServiceBuilder.build(savedUserId));
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
        responseObserver.onNext(UserDatabaseServiceBuilder.build(true));
        responseObserver.onCompleted();
    }

    @Override
    public void changeSurname(UserDatabaseService.LongStringRequest request, StreamObserver<UserDatabaseService.BooleanResponse> responseObserver) {
        userService.changeUserSurnameById(request.getLong(), request.getString());
        responseObserver.onNext(UserDatabaseServiceBuilder.build(true));
        responseObserver.onCompleted();
    }

    @Override
    public void changeAge(UserDatabaseService.LongIntegerRequest request, StreamObserver<UserDatabaseService.BooleanResponse> responseObserver) {
        userService.changeUserAgeById(request.getLong(), request.getInteger());
        responseObserver.onNext(UserDatabaseServiceBuilder.build(true));
        responseObserver.onCompleted();
    }

    @Override
    public void changeStatus(UserDatabaseService.LongStringRequest request, StreamObserver<UserDatabaseService.BooleanResponse> responseObserver) {
        userService.changeUserStatus(request.getLong(), request.getString());
        responseObserver.onNext(UserDatabaseServiceBuilder.build(true));
        responseObserver.onCompleted();
    }

    @Override
    public void changeCity(UserDatabaseService.LongStringRequest request, StreamObserver<UserDatabaseService.BooleanResponse> responseObserver) {
        userService.changeUserCityById(request.getLong(), request.getString());
        responseObserver.onNext(UserDatabaseServiceBuilder.build(true));
        responseObserver.onCompleted();
    }

    @Override
    public void changeEmail(UserDatabaseService.LongStringRequest request, StreamObserver<UserDatabaseService.BooleanResponse> responseObserver) {
        userService.changeUserEmailById(request.getLong(), request.getString());
        responseObserver.onNext(UserDatabaseServiceBuilder.build(true));
        responseObserver.onCompleted();
    }

    @Override
    public void changePassword(UserDatabaseService.LongStringRequest request, StreamObserver<UserDatabaseService.BooleanResponse> responseObserver) {
        userService.changeUserPasswordById(request.getLong(), request.getString());
        responseObserver.onNext(UserDatabaseServiceBuilder.build(true));
        responseObserver.onCompleted();
    }

    @Override
    public void getProfileInformationByListId(UserDatabaseService.LongListRequest request, StreamObserver<UserDatabaseService.ListGetProfileInformationResponse> responseObserver) {
        List<UserDatabaseService.GetProfileInformationResponse> responseList = UserConverter.convertTo(userService.getUserProfileInformation(request.getLongsList()));
        UserDatabaseService.ListGetProfileInformationResponse listGetProfileInformationResponse = UserDatabaseServiceBuilder.build(responseList);
        responseObserver.onNext(listGetProfileInformationResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void incrementFriendCount(UserDatabaseService.LongRequest request, StreamObserver<UserDatabaseService.BooleanResponse> responseObserver) {
        userService.incrementUserFriendCountById(request.getLong());
        responseObserver.onNext(UserDatabaseServiceBuilder.build(true));
        responseObserver.onCompleted();
    }

    @Override
    public void decrementFriendCount(UserDatabaseService.LongRequest request, StreamObserver<UserDatabaseService.BooleanResponse> responseObserver) {
        userService.decrementUserFriendCountById(request.getLong());
        responseObserver.onNext(UserDatabaseServiceBuilder.build(true));
        responseObserver.onCompleted();
    }
}
