package org.example.friendshipservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "friendships")
@Data
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "friend_id")
    private Long friendId;
    @Column(name = "status")
    private Boolean status;

    public Friendship(Long userId, Long friendId, Boolean status) {
        this.userId = userId;
        this.friendId = friendId;
        this.status = status;
    }

    public Friendship() {
    }
}
