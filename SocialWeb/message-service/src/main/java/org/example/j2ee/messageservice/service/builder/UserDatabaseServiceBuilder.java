package org.example.j2ee.messageservice.service.builder;

import com.example.grpc.user.UserDatabaseService;

public class UserDatabaseServiceBuilder {
    public static UserDatabaseService.LongRequest buildTo(Long value) {
        return UserDatabaseService.LongRequest
                .newBuilder()
                .setLong(value)
                .build();
    }
}
