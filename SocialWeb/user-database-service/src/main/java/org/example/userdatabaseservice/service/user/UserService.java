package org.example.userdatabaseservice.service.user;

import lombok.RequiredArgsConstructor;
import org.example.userdatabaseservice.model.UserProfileRequestModel;
import org.example.userdatabaseservice.model.entity.User;
import org.example.userdatabaseservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public String getPassword(String email) {
        return userRepository.findUserPasswordByEmail(email);
    }

    public Long getUserId(String email) {
        return userRepository.findUserIdByEmail(email);
    }

    @Transactional
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Transactional
    public Long registerUser(User user) {
        return userRepository.save(user).getId();
    }

    @Transactional
    public UserProfileRequestModel getUserProfileInformation(String email) {
        return userRepository.findUserProfileInformationByEmail(email);
    }

    @Transactional
    public UserProfileRequestModel getUserProfileInformation(Long id) {
        return userRepository.findUserProfileInformationById(id);
    }

    @Transactional
    public void changeUserNameById(Long id, String name) {
        userRepository.updateUserNameById(id, name);
    }

    @Transactional
    public void changeUserSurnameById(Long id, String surname) {
        userRepository.updateUserSurnameById(id, surname);
    }

    @Transactional
    public void changeUserAgeById(Long id, Integer age) {
        userRepository.updateUserAgeById(id, age);
    }

    @Transactional
    public void changeUserStatus(Long id, String status) {
        userRepository.updateUserStatusById(id, status);
    }

    @Transactional
    public void changeUserCityById(Long id, String city) {
        userRepository.updateUserCityById(id, city);
    }

    @Transactional
    public void changeUserEmailById(Long id, String email) {
        userRepository.updateUserEmailById(id, email);
    }

    @Transactional
    public void changeUserPasswordById(Long id, String password) {
        userRepository.updateUserPasswordById(id, password);
    }

    @Transactional
    public List<UserProfileRequestModel> getUserProfileInformation(List<Long> idList) {
        return idList
                .stream()
                .map(id -> getUserProfileInformation(id))
                .toList();
    }

    @Transactional
    public void incrementUserFriendCountById(Long userId){
        userRepository.incrementUserFriendCountById(userId);
    }

    @Transactional
    public void decrementUserFriendCountById(Long userId){
        if(userRepository.getUserFriendCountById(userId) > 0) {
            userRepository.decrementUserFriendCountById(userId);
        }
    }

    @Transactional
    public boolean existsUserById(Long id){
        return userRepository.existsById(id);
    }
}
