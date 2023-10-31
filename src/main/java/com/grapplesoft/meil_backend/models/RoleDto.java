package com.grapplesoft.meil_backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
//@Getter
//@Setter
public record RoleDto(
        Long roleId,
        @JsonProperty("roleName")
        @NotBlank(message = "Role name missing!")
        String roleName,

        @JsonProperty("description")
        @NotBlank(message = "Description missing!")
        String description,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) implements Serializable{
}
