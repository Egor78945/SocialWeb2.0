package org.example.authenticationservice.service.friendship;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.model.response.UserProfile;
import org.example.authenticationservice.service.friendship.grpc.FriendshipGrpcService;
import org.example.authenticationservice.service.user.grpc.UserGrpcService;
import org.example.authenticationservice.service.user.UserService;
import org.example.authenticationservice.util.builder.user.UserDatabaseServiceBuilder;
import org.example.authenticationservice.util.converter.user.UserConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final FriendshipGrpcService friendshipGrpcService;
    private final UserGrpcService userGrpcService;
    private final UserService userService;

    public boolean friendRequest(Long friendId) throws JsonProcessingException {
        Long userId = userService.getUserProfile().getId();

        if (userId.equals(friendId)) {
            throw new IllegalArgumentException("You can't send requests to yourself.");
        }
        if (friendshipGrpcService.existsFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, true).getBoolean() || friendshipGrpcService.existsFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, false).getBoolean()) {
            throw new IllegalArgumentException("The user is already your friend or you actually sent friendship request to this user.");
        }
        if (!userGrpcService.existsUserById(UserDatabaseServiceBuilder.build(friendId)).getBoolean()) {
            throw new IllegalArgumentException(String.format("User with id %s is not found.", friendId));
        }

        return friendshipGrpcService.friendshipRequest(userId, friendId).getBoolean();
    }

    public boolean acceptFriendshipRequest(Long friendId) throws JsonProcessingException {
        Long userId = userService.getUserProfile().getId();

        if (!friendshipGrpcService.existsFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, false).getBoolean()) {
            throw new IllegalArgumentException("User didn't send friendship request to you.");
        }

        return friendshipGrpcService.acceptFriendshipRequest(userId, friendId).getBoolean() && userService.incrementFriendCount(userId) && userService.incrementFriendCount(friendId);
    }

    public boolean rejectFriendshipRequest(Long friendId) throws JsonProcessingException {
        Long userId = userService.getUserProfile().getId();

        if (!friendshipGrpcService.existsFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, false).getBoolean()) {
            throw new IllegalArgumentException("User didn't send friendship request to you.");
        }

        return friendshipGrpcService.rejectFriendshipRequest(userId, friendId).getBoolean();
    }

    public boolean discardFriendshipRequest(Long friendId) throws JsonProcessingException {
        Long userId = userService.getUserProfile().getId();

        if (!friendshipGrpcService.existsFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, true).getBoolean()) {
            throw new IllegalArgumentException("The user is not your friend.");
        }

        return friendshipGrpcService.discardFriendshipRequest(userId, friendId).getBoolean() && userService.decrementFriendCount(userId) && userService.decrementFriendCount(friendId);
    }

    public List<UserProfile> getAllFriendshipRequests() throws JsonProcessingException {
        List<Long> idList = friendshipGrpcService.getFriendshipRequests(userService.getUserProfile().getId()).getListList();

        if (idList.isEmpty()) {
            throw new IllegalArgumentException("You don't have friendship requests.");
        }

        return UserConverter.convertGetProfileInformationResponseToUserProfile(userGrpcService.getProfileInformationListByListId(idList).getResponseListList());
    }

    public List<UserProfile> getAllFriends() throws JsonProcessingException {
        List<Long> idList = friendshipGrpcService.getFriendsRequest(userService.getUserProfile().getId()).getListList();

        if (idList.isEmpty()) {
            throw new IllegalArgumentException("You don't have friends.");
        }

        return UserConverter.convertGetProfileInformationResponseToUserProfile(userGrpcService.getProfileInformationListByListId(idList).getResponseListList());
    }
}