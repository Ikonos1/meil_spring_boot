package com.grapplesoft.meil_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grapplesoft.meil_backend.models.entities.Department;
import com.grapplesoft.meil_backend.models.entities.Project;
import com.grapplesoft.meil_backend.models.entities.Role;
import com.grapplesoft.meil_backend.models.entities.Token;

import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record EmployeeWithoutPassword(

        @JsonProperty("employeeId")
        Long employeeId,
        @JsonProperty("firstName")
        String firstName,

        @JsonProperty("lastName")
        String lastName,

        @JsonProperty("designation")
        String designation,

        @JsonProperty("role")
        Role role,

        @JsonProperty("dateOfBirth")
        LocalDate dateOfBirth,

        @JsonProperty("dateOfJoining")
        LocalDate dateOfJoining,

        @JsonProperty("drivingLicense")
        String drivingLicense,

        @JsonProperty("department")
        Department department,

        @JsonProperty("project")
        Project project,

        @JsonProperty("leadershipCode")
        String leadershipCode,

        @JsonProperty("hseLevel")
        Integer hseLevel,

        @JsonProperty("hseCompPoints")
        Integer hseCompPoints,

        @JsonProperty("emailOffice")
        String emailOffice,

        @JsonProperty("email2")
        String email2,

        @JsonProperty("mobileOffice")
        String mobileOffice,

        @JsonProperty("mobile2")
        String mobile2,

        @JsonProperty("mobile3")
        String mobile3,

        @JsonProperty("whatsAppNum")
        String whatsAppNum,

        @JsonProperty("remarks")
        String remarks,

        @JsonProperty("token")
        Token token
) {
}
