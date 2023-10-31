package com.grapplesoft.meil_backend.models.request.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grapplesoft.meil_backend.models.entities.Employee;
import com.grapplesoft.meil_backend.models.entities.Project;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @param projectId            {@link Project} - Project to allocate
 * @param projectName
 * @param projectCoordinatorId {@link Employee} - HSE Coordinator ID
 * @param siteManagerId
 * @param courierPcode
 * @param courierPmobile
 * @param addressId
 * @param latitude
 * @param longitude
 * @param createDate
 * @param createUserId
 * @param editDate
 * @param editUserId
 * @param isDeleted
 * @param remarks
 */
public record AllotProjectSiteRequestDto(
        @JsonProperty("projectId")
        Long projectId,
        @JsonProperty("projectName")
        String projectName,
        @JsonProperty("projectCoordinatorId")
        Long projectCoordinatorId,
        @JsonProperty("siteManagerId")
        Long siteManagerId,
        @JsonProperty("courierPcode")
        Long courierPcode,
        @JsonProperty("courierPmobile")
        String courierPmobile,
        @JsonProperty("addressId")
        Long addressId,
        @JsonProperty("latitude")
        BigDecimal latitude,
        @JsonProperty("longitude")
        BigDecimal longitude,
        @JsonProperty("createDate")
        LocalDate createDate,
        @JsonProperty("createUserId")
        Long createUserId,
        @JsonProperty("editDate")
        LocalDate editDate,
        @JsonProperty("editUserId")
        Long editUserId,
        @JsonProperty("isDeleted")
        boolean isDeleted,

        @JsonProperty("remarks")
        String remarks
) {
}
