package com.grapplesoft.meil_backend.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record EmployeeLoginRequest(
        @NotBlank(message = "Email cannot be empty")
        @JsonProperty("emailOffice")
        String emailOffice,
        @NotBlank(message = "Email cannot be empty")
        @JsonProperty("password")
        String password
) {
}
