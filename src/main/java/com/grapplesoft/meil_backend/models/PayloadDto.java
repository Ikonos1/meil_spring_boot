package com.grapplesoft.meil_backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record PayloadDto(
        @JsonProperty("role")
        String role,
        @JsonProperty("issuer")
        String issuer,
        @JsonProperty("roleId")
        Long roleId,
        @JsonProperty("employeeId")
        Long employeeId,
        @JsonProperty("iat")
        LocalDateTime iat,
        @JsonProperty("emailOffice")
        String emailOffice
) {
}
