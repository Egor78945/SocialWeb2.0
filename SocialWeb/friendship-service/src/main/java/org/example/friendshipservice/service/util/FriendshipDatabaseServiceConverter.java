package org.example.friendshipservice.service.util;

import com.example.grpc.FriendshipDatabaseService;

import java.util.List;

public class FriendshipDatabaseServiceConverter {
    public static FriendshipDatabaseService.BooleanResponse convertTo(boolean bool) {
        return FriendshipDatabaseService.BooleanResponse
                .newBuilder()
                .setBoolean(bool)
                .build();
    }

    public static FriendshipDatabaseService.LongListResponse convertTo(List<Long> list) {
        return FriendshipDatabaseService.LongListResponse
                .newBuilder()
                .addAllList(list)
                .build();
    }
}
