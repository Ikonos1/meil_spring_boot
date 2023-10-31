package com.grapplesoft.meil_backend.models.request.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeallotProjectSiteRequest(
        @JsonProperty("projectId")
        Long projectId,
        @JsonProperty("projectSiteId")
        Long projectSiteId,
        @JsonProperty("projectCoordinatorId")
        Long projectCoordinatorId,
        @JsonProperty("remarks")
        String remarks
) {


}
