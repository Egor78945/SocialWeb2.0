package org.example.userdatabaseservice.util.user.builder.grpc;

import com.example.grpc.user.UserDatabaseService;

import java.util.List;

public class UserDatabaseServiceBuilder {
    public static UserDatabaseService.BooleanResponse build(Boolean result) {
        return UserDatabaseService.BooleanResponse
                .newBuilder()
                .setBoolean(result)
                .build();
    }

    public static UserDatabaseService.LongResponse build(Long result) {
        return UserDatabaseService.LongResponse
                .newBuilder()
                .setLong(result)
                .build();
    }

    public static UserDatabaseService.ListGetProfileInformationResponse build(List<UserDatabaseService.GetProfileInformationResponse> responseList){
        return UserDatabaseService.ListGetProfileInformationResponse
                .newBuilder()
                .addAllResponseList(responseList)
                .build();
    }
}
