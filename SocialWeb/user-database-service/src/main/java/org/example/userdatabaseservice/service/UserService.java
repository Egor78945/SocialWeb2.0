package org.example.userdatabaseservice.service;

import lombok.RequiredArgsConstructor;
import org.example.userdatabaseservice.model.entity.User;
import org.example.userdatabaseservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String getPassword(String email){
        return userRepository.findUserPasswordByEmail(email);
    }

    public Long getUserId(String email){
        return userRepository.findUserIdByEmail(email);
    }

    public boolean getUserExists(String email){
        return userRepository.existsUserByEmail(email);
    }

    public Long registerUser(User user){
        return userRepository.save(user).getId();
    }
}
