package com.grapplesoft.meil_backend.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Past;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Add employee request DTO object
 *
 * @param firstName
 * @param lastName
 * @param designation
 * @param role
 * @param dateOfBirth
 * @param dateOfJoining
 * @param drivingLicense
 * @param departmentCode
 * @param projectCode
 * @param leadershipCode
 * @param hseLevel
 * @param hseCompPoints
 * @param emailOffice
 * @param email2
 * @param mobileOffice
 * @param mobile2
 * @param mobile3
 * @param whatsAppNum
 * @param password
 * @param remarks
 */
public record UpdateEmployeeRequest(

        @JsonProperty("employeeid")
        Long id,

        @JsonProperty("firstName")
        String firstName,

        @JsonProperty("lastName")
        String lastName,

        @JsonProperty("designation")
        String designation,

        @JsonProperty("role")
        Long role,

        @JsonProperty("dateOfBirth")
        @Past
        LocalDate dateOfBirth,

        @JsonProperty("dateOfJoining")
        LocalDate dateOfJoining,

        @JsonProperty("drivingLicense")
        String drivingLicense,

        @JsonProperty("departmentCode")
        Long departmentCode,

        @JsonProperty("projectCode")
        Long projectCode,

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

        @JsonProperty("password")
        String password
) implements Serializable {
}
