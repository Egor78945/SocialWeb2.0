package org.example.friendshipservice.repository;

import org.example.friendshipservice.model.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    @Query("SELECT CASE WHEN EXISTS (SELECT id FROM Friendship WHERE userId=?1 AND friendId=?2 OR userId=?2 AND friendId=?1) THEN TRUE ELSE FALSE END")
    boolean existsFriendshipByUserIdAndFriendId(Long userId, Long friendId);

    @Query("SELECT CASE WHEN EXISTS (SELECT id FROM Friendship WHERE (userId=?1 OR friendId=?1) AND status=?2) THEN TRUE ELSE FALSE END")
    boolean existsFriendshipByUserIdAndStatus(Long userId, Boolean status);

    @Query("SELECT CASE WHEN EXISTS (SELECT id FROM Friendship WHERE (userId=?1 AND friendId=?2 OR userId=?2 AND friendId=?1) AND status=?3) THEN TRUE ELSE FALSE END")
    boolean existsFriendshipByUserIdAndFriendIdAndStatus(Long userId, Long friendId, Boolean status);

    @Query("UPDATE Friendship SET status=?3 WHERE userId = ?1 AND friendId=?2 OR userId = ?2 AND friendId=?1")
    @Modifying
    @Transactional
    void updateFriendshipStatus(Long userId, Long friendId, Boolean status);

    @Query("DELETE FROM Friendship WHERE (userId=?1 AND friendId=?2 OR userId=?2 AND friendId=?1) AND status=?3")
    @Modifying
    @Transactional
    void deleteFriendshipByUserIdAndFriendIdAndStatus(Long userId, Long friendId, Boolean status);

    @Query("SELECT friendId FROM Friendship WHERE userId=?1 AND status=?2 UNION SELECT userId FROM Friendship WHERE friendId=?1 AND status=?2")
    @Transactional
    List<Long> findAllByUserIdAndStatus(Long id, Boolean status);
}
