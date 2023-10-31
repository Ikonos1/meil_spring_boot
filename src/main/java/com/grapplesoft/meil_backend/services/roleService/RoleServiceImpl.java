package com.grapplesoft.meil_backend.services.roleService;

import com.grapplesoft.meil_backend.models.RoleDto;
import com.grapplesoft.meil_backend.models.entities.Role;
import com.grapplesoft.meil_backend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(@Qualifier("roleRepository") RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * @param roleDto : RoleDto
     * @return roleDto: RoleDto object of inserted role
     */
    @Override
    public Role insertRole(RoleDto roleDto) {
        Role role = new Role(
                roleDto.roleName(),
                roleDto.description(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return this.roleRepository.save(role);
    }

    /**
     * @param roleName
     * @return
     */
    @Override
    public Role getRoleByRoleName(String roleName) {
        Optional<Role> res = this.roleRepository.findByRoleName(roleName);
        return res.orElse(null);
    }

    /**
     * @param roleId
     * @return
     */
    @Override
    public Role getRoleByRoleId(Long roleId) {
        return this.roleRepository.findById(roleId).orElse(null);
    }
}
