package org.example.friendshipservice.util.friendship.grpc;

import com.example.grpc.friendship.FriendshipDatabaseService;

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
