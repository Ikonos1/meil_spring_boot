package com.grapplesoft.meil_backend.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record DeleteEmployeeRequest (
        @JsonProperty("emailOffice")
        @NotBlank(message = "Email cannot be empty")
        String emailOffice
) {
}
