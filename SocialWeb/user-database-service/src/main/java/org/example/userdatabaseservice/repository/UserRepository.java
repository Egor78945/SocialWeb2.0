package org.example.userdatabaseservice.repository;

import org.example.userdatabaseservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.password from User u where u.email=?1")
    String findUserPasswordByEmail(String email);
    @Query("SELECT u.id from User u where u.email=?1")
    Long findUserIdByEmail(String email);
    Boolean findUserExistsByEmail(String email);
}
