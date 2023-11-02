package com.grapplesoft.meil_backend.models.request;

public record DepartmentRequest(
        String deptcode,
        String deptname,
        Long createuserid,
        Long edituserid,

        Boolean isdeleted
) {
}
