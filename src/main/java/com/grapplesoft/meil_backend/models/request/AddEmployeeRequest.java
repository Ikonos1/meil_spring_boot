package com.grapplesoft.meil_backend.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public record AddEmployeeRequest(

        @JsonProperty("employeeid")
        Long id,

        @JsonProperty("firstName")
        @NotBlank(message = "First name cannot be empty")
        String firstName,

        @JsonProperty("lastName")
        @NotBlank(message = "Last name cannot be empty")
        String lastName,

        @JsonProperty("designation")
        @NotBlank(message = "Designation cannot be empty")
        String designation,

        @JsonProperty("role")
        @NotNull(message = "Role cannot be empty")
        Long role,

        @JsonProperty("dateOfBirth")
        @NotNull(message = "Date of birth cannot be empty")
        @Past
        LocalDate dateOfBirth,

        @JsonProperty("dateOfJoining")
        @NotNull(message = "Driving license cannot be empty")
        LocalDate dateOfJoining,

        @JsonProperty("drivingLicense")
        @NotBlank(message = "Driving license cannot be empty")
        String drivingLicense,

        @JsonProperty("departmentCode")
        @NotNull(message = "Department code cannot be empty")
        Long departmentCode,

        @JsonProperty("projectCode")
        @NotNull(message = "Project code cannot be empty")
        Long projectCode,

        @JsonProperty("leadershipCode")
        @NotBlank(message = "Leadership code cannot be empty")
        String leadershipCode,

        @JsonProperty("hseLevel")
        @NotNull(message = "HSE level cannot be empty. only 1 to 5 allowed")
        Integer hseLevel,

        @JsonProperty("hseCompPoints")
        @NotNull(message = "HSE comp points cannot be empty")
        Integer hseCompPoints,

        @JsonProperty("emailOffice")
        @NotBlank(message = "Email office cannot be empty")
        String emailOffice,

        @JsonProperty("email2")
//        @NotBlank(message = "Email 2 cannot be empty")
        String email2,

        @JsonProperty("mobileOffice")
        @NotBlank(message = "Mobile office cannot be empty")
        String mobileOffice,

        @JsonProperty("mobile2")
//        @NotBlank(message = "Mobile 2 cannot be empty")
        String mobile2,

        @JsonProperty("mobile3")
//        @NotBlank(message = "Mobile 3 cannot be empty")
        String mobile3,

        @JsonProperty("whatsAppNum")
//        @NotBlank(message = "WhatsApp number cannot be empty")
        String whatsAppNum,

        @JsonProperty("remarks")
        @NotBlank(message = "Remarks cannot be empty")
        String remarks,

        @JsonProperty("password")
//        @NotBlank(message = "Password cannot be empty")
        String password
) implements Serializable {
}
