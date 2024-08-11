package org.example.userdatabaseservice.service;

import lombok.RequiredArgsConstructor;
import org.example.userdatabaseservice.model.UserProfileRequestModel;
import org.example.userdatabaseservice.model.entity.User;
import org.example.userdatabaseservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public boolean getUserExists(String email) {
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
    public void changeUserNameById(Long id, String name){
        userRepository.updateUserNameById(id, name);
    }
}
