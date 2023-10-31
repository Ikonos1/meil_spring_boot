package com.grapplesoft.meil_backend.services.roleService;

import com.grapplesoft.meil_backend.models.RoleDto;
import com.grapplesoft.meil_backend.models.entities.Role;

public interface RoleService {

    Role insertRole(RoleDto roleDto);

    Role getRoleByRoleName(String roleName);

    Role getRoleByRoleId(Long roleId);

}
