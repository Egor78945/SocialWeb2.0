package org.example.userdatabaseservice.repository;

import org.example.userdatabaseservice.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r.role from Role r where r.user_id = ?1")
    List<String> findRoleByUserId(Long id);
}
