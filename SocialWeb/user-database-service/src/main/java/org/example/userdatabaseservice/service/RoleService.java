package org.example.userdatabaseservice.service;

import lombok.RequiredArgsConstructor;
import org.example.userdatabaseservice.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<String> getRole(Long userId) {
        return roleRepository.findRoleByUserId(userId);
    }
}
