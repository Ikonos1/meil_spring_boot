package com.grapplesoft.meil_backend.models.request.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record EmployeeTransfer(
        @JsonProperty("projectId")
        Long Actiontype,
        @JsonProperty("actiondate")
        LocalDate actiondate,
        @JsonProperty("employeeid")
        Long employeeid,
        @JsonProperty("fromProjectId")
        Long fromprojectid,
        @JsonProperty("toprojectid")
        Long toprojectid,
        @JsonProperty("date1")
        LocalDate date1,
        @JsonProperty("date2")
        LocalDate date2,
        @JsonProperty("function1")
        String function1,
        @JsonProperty("function2")
        String function2
) {
}
