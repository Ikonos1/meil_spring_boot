package com.grapplesoft.meil_backend.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record RolePlain(
        Long id,
        String roleName,
        String description
) {
}
