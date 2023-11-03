package com.grapplesoft.meil_backend.models.request.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record ChangeDepartment(
        @JsonProperty("actionType")
        Long actionType,
        @JsonProperty("actionDate")
        LocalDate actionDate,
        @JsonProperty("employeeId")
        Long employeeId,
        @JsonProperty("deptIdFrom")
        String deptIdFrom,
        @JsonProperty("deptIdTo")
        String deptIdTo,
        @JsonProperty("fromProjectId")
        Long fromProjectId
) {
}
