package org.example.authenticationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.service.grpc.FriendshipGrpcService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final FriendshipGrpcService friendshipGrpcService;
    private final UserService userService;

    public boolean friendRequest(Long friendId) throws JsonProcessingException {
        return friendshipGrpcService.friendshipRequest(userService.getUserProfile().getId(), friendId).getBoolean();
    }

    public boolean acceptFriendshipRequest(Long friendId) throws JsonProcessingException {
        return friendshipGrpcService.acceptFriendshipRequest(userService.getUserProfile().getId(), friendId).getBoolean();
    }

    public boolean rejectFriendshipRequest(Long friendId) throws JsonProcessingException {
        return friendshipGrpcService.rejectFriendshipRequest(userService.getUserProfile().getId(), friendId).getBoolean();
    }

    public boolean discardFriendshipRequest(Long friendId) throws JsonProcessingException {
        return friendshipGrpcService.discardFriendshipRequest(userService.getUserProfile().getId(), friendId).getBoolean();
    }
}