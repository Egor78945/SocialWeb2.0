package org.example.authenticationservice.service.grpc;

import com.example.grpc.friendship.FriendshipDatabaseService;
import com.example.grpc.friendship.FriendshipServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.authenticationservice.util.builder.FriendshipDatabaseServiceBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendshipGrpcService {
    @GrpcClient("friendship-database-service")
    private FriendshipServiceGrpc.FriendshipServiceBlockingStub blockingStub;

    public FriendshipDatabaseService.BooleanResponse friendshipRequest(Long userId, Long friendId) {
        return blockingStub.friendshipRequest(FriendshipDatabaseServiceBuilder.build(userId, friendId));
    }

    public FriendshipDatabaseService.BooleanResponse acceptFriendshipRequest(Long userId, Long friendId) {
        return blockingStub.acceptFriendshipRequest(FriendshipDatabaseServiceBuilder.build(userId, friendId));
    }

    public FriendshipDatabaseService.BooleanResponse rejectFriendshipRequest(Long userId, Long friendId) {
        return blockingStub.rejectFriendshipRequest(FriendshipDatabaseServiceBuilder.build(userId, friendId));
    }

    public FriendshipDatabaseService.BooleanResponse discardFriendshipRequest(Long userId, Long friendId) {
        return blockingStub.discardFriendship(FriendshipDatabaseServiceBuilder.build(userId, friendId));
    }

    public FriendshipDatabaseService.LongListResponse getFriendsRequest(Long userId) {
        return blockingStub.getAllFriends(FriendshipDatabaseServiceBuilder.build(userId));
    }

    public FriendshipDatabaseService.LongListResponse getFriendshipRequestsRequest(Long userId) {
        return blockingStub.getAllFriendshipRequests(FriendshipDatabaseServiceBuilder.build(userId));
    }
}