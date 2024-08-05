package org.example.authenticationservice.service.grpc;

import com.example.grpc.UserDatabaseService;
import com.example.grpc.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
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
}
