package org.example.userdatabaseservice.util.builder;

import com.example.grpc.user.UserDatabaseService;

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
}
