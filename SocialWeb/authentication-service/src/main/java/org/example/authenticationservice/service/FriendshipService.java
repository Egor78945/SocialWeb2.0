package org.example.authenticationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.authenticationservice.model.response.UserProfile;
import org.example.authenticationservice.service.grpc.FriendshipGrpcService;
import org.example.authenticationservice.service.grpc.UserGrpcService;
import org.example.authenticationservice.util.converter.UserConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final FriendshipGrpcService friendshipGrpcService;
    private final UserGrpcService userGrpcService;
    private final UserService userService;

    public boolean friendRequest(Long friendId) throws JsonProcessingException {
        if(!friendId.equals(userService.getUserProfile().getId())) {
            return friendshipGrpcService.friendshipRequest(userService.getUserProfile().getId(), friendId).getBoolean();
        }
        throw new IllegalArgumentException("You can't send requests to yourself.");
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

    public List<UserProfile> getAllFriendshipRequests() throws JsonProcessingException {
        List<Long> idList = friendshipGrpcService.getFriendshipRequestsRequest(userService.getUserProfile().getId()).getListList();
        return UserConverter.convertGetProfileInformationResponseToUserProfile(userGrpcService.getProfileInformationListByListId(idList).getResponseListList());
    }

    public List<UserProfile> getAllFriends() throws JsonProcessingException {
        List<Long> idList = friendshipGrpcService.getFriendsRequest(userService.getUserProfile().getId()).getListList();
        return UserConverter.convertGetProfileInformationResponseToUserProfile(userGrpcService.getProfileInformationListByListId(idList).getResponseListList());
    }
}