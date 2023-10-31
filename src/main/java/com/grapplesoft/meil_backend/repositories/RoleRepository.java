package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
     Optional<Role> findByRoleName(String roleName);
}
