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
        friendshipRepository.save(new Friendship(userId, friendId, false));
        return true;
    }

    @Transactional
    public boolean acceptFriendshipRequest(Long userId, Long friendId) {
        friendshipRepository.updateFriendshipStatus(userId, friendId, true);
        return true;
    }

    @Transactional
    public boolean rejectFriendRequest(Long userId, Long friendId) {
        friendshipRepository.deleteFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, false);
        return true;
    }

    @Transactional
    public boolean discardFriendship(Long userId, Long friendId) {
        friendshipRepository.deleteFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, true);
        return true;
    }

    @Transactional
    public List<Long> getAllFriends(Long userId) {
        return friendshipRepository.findAllByUserId(userId, true);
    }

    @Transactional
    public List<Long> getAllFriendshipRequests(Long userId) {
        return friendshipRepository.findAllByUserId(userId, false);
    }

    @Transactional
    public boolean existsFriendshipByUserIdAndFriendId(Long userId, Long friendId) {
        return friendshipRepository.existsFriendshipByUserIdAndFriendId(userId, friendId) || friendshipRepository.existsFriendshipByUserIdAndFriendId(friendId, userId);
    }

    @Transactional
    public boolean existsFriendshipByUserIdAndFriendIdAndStatus(Long userId, Long friendId, boolean status) {
        return friendshipRepository.existsFriendshipByUserIdAndFriendIdAndStatus(userId, friendId, status) || friendshipRepository.existsFriendshipByUserIdAndFriendIdAndStatus(friendId, userId, status);
    }

    @Transactional
    public boolean existsFriendshipByUserIdAndStatus(Long userId, boolean status) {
        return friendshipRepository.existsFriendshipByUserIdAndStatus(userId, status) || friendshipRepository.existsFriendshipByUserIdAndStatus(userId, status);
    }
}
