package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.RolePlain;
import com.grapplesoft.meil_backend.models.entities.Role;

public class RoleBuilder {
    public static RolePlain rolePlain(Role role) {
        return RolePlain.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .build();
    }
}
