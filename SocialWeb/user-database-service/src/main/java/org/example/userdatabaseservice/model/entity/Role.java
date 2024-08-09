package org.example.userdatabaseservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "user_role")
    private String user_role;

    public Role(Long user_id, String user_role) {
        this.user_id = user_id;
        this.user_role = user_role;
    }

    public Role() {
    }
}
