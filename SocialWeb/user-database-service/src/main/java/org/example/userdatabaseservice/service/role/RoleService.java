package org.example.userdatabaseservice.service.role;

import lombok.RequiredArgsConstructor;
import org.example.userdatabaseservice.model.entity.Role;
import org.example.userdatabaseservice.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<String> getRole(Long userId) {
        return roleRepository.findRoleByUserId(userId);
    }

    @Transactional
    public void saveRole(Role role){
        roleRepository.save(role);
    }

    @Transactional
    public void saveRole(Long savedUserId, List<String> roles){
        for(String role: roles){
            saveRole(new Role(savedUserId, role));
        }
    }
}
