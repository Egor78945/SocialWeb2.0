package org.example.userdatabaseservice.repository;

import org.example.userdatabaseservice.model.UserProfileRequestModel;
import org.example.userdatabaseservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.password from User u where u.email=?1")
    String findUserPasswordByEmail(String email);
    @Query("SELECT u.id from User u where u.email=?1")
    Long findUserIdByEmail(String email);
    Boolean existsUserByEmail(String email);
    @Query("SELECT new org.example.userdatabaseservice.model.UserProfileRequestModel(u.id, u.name, u.surname, u.age, u.city, u.friendCount, u.registerDate, u.status) from User u where u.email=?1")
    UserProfileRequestModel findUserProfileInformationByEmail(String email);
    @Query("SELECT new org.example.userdatabaseservice.model.UserProfileRequestModel(u.id, u.name, u.surname, u.age, u.city, u.friendCount, u.registerDate, u.status) from User u where u.id=?1")
    UserProfileRequestModel findUserProfileInformationById(Long id);
    @Query("update User u set u.name=?2 where u.id=?1")
    @Modifying
    void updateUserNameById(Long id, String name);
}
