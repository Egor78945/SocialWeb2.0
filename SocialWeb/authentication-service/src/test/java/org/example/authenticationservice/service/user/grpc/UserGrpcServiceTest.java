package org.example.authenticationservice.service.user.grpc;

import com.example.grpc.user.UserDatabaseService;
import com.example.grpc.user.UserServiceGrpc;
import org.example.authenticationservice.enumeration.role.user.UserRole;
import org.example.authenticationservice.model.request.AuthenticationRequestModel;
import org.example.authenticationservice.model.request.RegisterRequestModel;
import org.example.authenticationservice.util.builder.user.UserDatabaseServiceBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class UserGrpcServiceTest {
    @InjectMocks
    private UserGrpcService userGrpcService;
    @Mock
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    private final String EMAIL;
    private final Long ID;

    {
        EMAIL = "Egor78396@gmail.com";
        ID = 1L;
    }

    @Test
    public void getDetailsRequest() {
        Mockito.when(userServiceBlockingStub.getUserDetails(UserDatabaseServiceBuilder.build(EMAIL))).thenReturn(null);

        Assertions.assertNull(userGrpcService.getDetailsRequest(EMAIL));
    }

    @Test
    public void getEmailUniqueRequest() {
        Mockito.when(userServiceBlockingStub.getEmailUnique(UserDatabaseServiceBuilder.build(EMAIL))).thenReturn(null);

        Assertions.assertNull(userGrpcService.getEmailUniqueRequest(EMAIL));
    }

    @Test
    public void registerUser() {
        RegisterRequestModel requestModel = new RegisterRequestModel();
        requestModel.setEmail(EMAIL);
        requestModel.setName("");
        requestModel.setCity("");
        requestModel.setAge(0);
        requestModel.setPassword("");
        requestModel.setSurname("");

        UserDatabaseService.RegisterUserRequest request = UserDatabaseService.RegisterUserRequest
                .newBuilder()
                .addRole(UserRole.USER_ROLE.name())
                .setEmail(EMAIL)
                .build();

        Mockito.when(userServiceBlockingStub.registerUser(request)).thenReturn(null);

        Assertions.assertNull(userGrpcService.registerUser(requestModel));
    }

    @Test
    public void getProfileInformation_byEmail() {
        Mockito.when(userServiceBlockingStub.getProfileInformationByEmail(UserDatabaseServiceBuilder.build(EMAIL))).thenReturn(null);

        Assertions.assertNull(userGrpcService.getProfileInformation(EMAIL));
    }

    @Test
    public void getProfileInformation_byId() {
        Mockito.when(userServiceBlockingStub.getProfileInformationById(UserDatabaseServiceBuilder.build(ID))).thenReturn(null);

        Assertions.assertNull(userGrpcService.getProfileInformation(ID));
    }

    @Test
    public void changeName() {
        Mockito.when(userServiceBlockingStub.changeName(UserDatabaseServiceBuilder.build(ID, ""))).thenReturn(UserDatabaseService.BooleanResponse.newBuilder().setBoolean(true).build());

        Assertions.assertTrue(userGrpcService.changeName(ID, ""));
    }

    @Test
    public void changeSurname() {
        Mockito.when(userServiceBlockingStub.changeSurname(UserDatabaseServiceBuilder.build(ID, ""))).thenReturn(UserDatabaseService.BooleanResponse.newBuilder().setBoolean(true).build());

        Assertions.assertTrue(userGrpcService.changeSurname(ID, ""));
    }

    @Test
    public void changeAge() {
        Mockito.when(userServiceBlockingStub.changeAge(UserDatabaseServiceBuilder.build(ID, 0))).thenReturn(UserDatabaseService.BooleanResponse.newBuilder().setBoolean(true).build());

        Assertions.assertTrue(userGrpcService.changeAge(ID, 0));
    }

    @Test
    public void changeStatus() {
        Mockito.when(userServiceBlockingStub.changeStatus(UserDatabaseServiceBuilder.build(ID, ""))).thenReturn(UserDatabaseService.BooleanResponse.newBuilder().setBoolean(true).build());

        Assertions.assertTrue(userGrpcService.changeStatus(ID, ""));
    }

    @Test
    public void changeCity() {
        Mockito.when(userServiceBlockingStub.changeCity(UserDatabaseServiceBuilder.build(ID, ""))).thenReturn(UserDatabaseService.BooleanResponse.newBuilder().setBoolean(true).build());

        Assertions.assertTrue(userGrpcService.changeCity(ID, ""));
    }

    @Test
    public void changeEmail() {
        Mockito.when(userServiceBlockingStub.changeEmail(UserDatabaseServiceBuilder.build(ID, ""))).thenReturn(UserDatabaseService.BooleanResponse.newBuilder().setBoolean(true).build());

        Assertions.assertTrue(userGrpcService.changeEmail(ID, ""));
    }

    @Test
    public void changePassword() {
        Mockito.when(userServiceBlockingStub.changePassword(UserDatabaseServiceBuilder.build(ID, ""))).thenReturn(UserDatabaseService.BooleanResponse.newBuilder().setBoolean(true).build());

        Assertions.assertTrue(userGrpcService.changePassword(ID, ""));
    }

    @Test
    public void getProfileInformationListByListId() {
        Mockito.when(userServiceBlockingStub.getProfileInformationByListId(UserDatabaseServiceBuilder.build(new ArrayList<>()))).thenReturn(null);

        Assertions.assertNull(userGrpcService.getProfileInformationListByListId(new ArrayList<>()));
    }

    @Test
    public void incrementFriendCount() {
        Mockito.when(userServiceBlockingStub.incrementFriendCount(UserDatabaseServiceBuilder.build(ID))).thenReturn(UserDatabaseService.BooleanResponse.newBuilder().setBoolean(true).build());

        Assertions.assertTrue(userGrpcService.incrementFriendCount(ID));
    }

    @Test
    public void decrementFriendCount() {
        Mockito.when(userServiceBlockingStub.decrementFriendCount(UserDatabaseServiceBuilder.build(ID))).thenReturn(UserDatabaseService.BooleanResponse.newBuilder().setBoolean(true).build());

        Assertions.assertTrue(userGrpcService.decrementFriendCount(ID));
    }
}