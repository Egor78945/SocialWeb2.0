package org.example.friendshipservice.service;

import lombok.RequiredArgsConstructor;
import org.example.friendshipservice.exception.RequestRejectedException;
import org.example.friendshipservice.model.entity.Friendship;
import org.example.friendshipservice.repository.FriendshipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;

    @Transactional
    public boolean sendFriendshipRequest(Long userId, Long friendId) {
        boolean exists = friendshipRepository.existsFriendshipByUserIdAndFriendId(userId, friendId) || friendshipRepository.existsFriendshipByUserIdAndFriendId(friendId, userId);
        if (!exists)
            friendshipRepository.save(new Friendship(userId, friendId, false));
        else
            throw new RequestRejectedException("Request already has been sent or this user is already your friend.");
        return false;
    }

    @Transactional
    public boolean acceptFriendshipRequest(Long userId, Long friendId) {
        boolean exists = friendshipRepository.existsFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, false) || friendshipRepository.existsFriendshipByUserIdAndFriendIdAndStatus(friendId, userId, false);
        if (exists)
            friendshipRepository.updateFriendshipStatus(userId, friendId, true);
        else
            throw new RequestRejectedException("User already is your friend or the user didn't sent a friend request to you.");
        return true;
    }

    @Transactional
    public boolean rejectFriendRequest(Long userId, Long friendId) {
        boolean exists = friendshipRepository.existsFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, false) || friendshipRepository.existsFriendshipByUserIdAndFriendIdAndStatus(friendId, userId, false);
        if (exists)
            friendshipRepository.deleteFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, false);
        else
            throw new RequestRejectedException("User didn't sent a friend request to you");
        return true;
    }

    @Transactional
    public boolean discardFriendship(Long userId, Long friendId) {
        boolean exists = friendshipRepository.existsFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, true) || friendshipRepository.existsFriendshipByUserIdAndFriendIdAndStatus(friendId, userId, true);
        if (exists)
            friendshipRepository.deleteFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, true);
        else
            throw new RequestRejectedException("User never been your friend.");
        return true;
    }

    @Transactional
    public List<Long> getAllFriends(Long userId) {
        boolean exists = friendshipRepository.existsFriendshipByUserIdAndStatus(userId, true) || friendshipRepository.existsFriendshipByUserIdAndStatus(userId, true);
        if (exists)
            return friendshipRepository.findAllByUserId(userId, true);
        else
            throw new RequestRejectedException("You haven't friends.");
    }

    @Transactional
    public List<Long> getAllFriendshipRequests(Long userId) {
        boolean exists = friendshipRepository.existsFriendshipByUserIdAndStatus(userId, false) || friendshipRepository.existsFriendshipByUserIdAndStatus(userId, false);
        if (exists)
            return friendshipRepository.findAllByUserId(userId, false);
        else
            throw new RequestRejectedException("You haven't friendship requests.");
    }

    public boolean existsFriendshipByUserIdAndFriendId(Long userId, Long friendId){
        return friendshipRepository.existsFriendshipByUserIdAndFriendId(userId, friendId) || friendshipRepository.existsFriendshipByUserIdAndFriendId(friendId, userId);
    }

    public boolean existsFriendshipByUserIdAndFriendIdAndStatus(Long userId, Long friendId, boolean status){
        return friendshipRepository.existsFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, status) || friendshipRepository.existsFriendshipByUserIdAndFriendIdAndStatus(friendId, userId, status);
    }

    public boolean existsFriendshipByUserIdAndStatus(Long userId, boolean status){
        return friendshipRepository.existsFriendshipByUserIdAndStatus(userId, status) || friendshipRepository.existsFriendshipByUserIdAndStatus(userId, status);
    }
}
