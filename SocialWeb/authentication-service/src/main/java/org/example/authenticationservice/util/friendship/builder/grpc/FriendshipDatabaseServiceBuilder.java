package org.example.authenticationservice.util.friendship.builder.grpc;

import com.example.grpc.friendship.FriendshipDatabaseService;

public class FriendshipDatabaseServiceBuilder {
    public static FriendshipDatabaseService.LongRequest build(Long firstLong) {
        return FriendshipDatabaseService.LongRequest
                .newBuilder()
                .setFirstLong(firstLong)
                .build();
    }

    public static FriendshipDatabaseService.LongLongRequest build(Long firstLong, Long secondLong) {
        return FriendshipDatabaseService.LongLongRequest
                .newBuilder()
                .setFirstLong(firstLong)
                .setSecondLong(secondLong)
                .build();
    }

    public static FriendshipDatabaseService.LongLongBooleanRequest build(Long firstLong, Long secondLong, boolean bool) {
        return FriendshipDatabaseService.LongLongBooleanRequest
                .newBuilder()
                .setFirstLong(firstLong)
                .setSecondLong(secondLong)
                .setBoolean(bool)
                .build();
    }

    public static FriendshipDatabaseService.LongBooleanRequest build(Long firstLong, boolean bool) {
        return FriendshipDatabaseService.LongBooleanRequest
                .newBuilder()
                .setFirstLong(firstLong)
                .setBoolean(bool)
                .build();
    }
}
