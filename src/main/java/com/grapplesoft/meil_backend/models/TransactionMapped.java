package com.grapplesoft.meil_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grapplesoft.meil_backend.models.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record TransactionMapped(
        long id,
        Actiontype actiontypeid,
        LocalDate actiondate,
        EmployeeWithoutPassword employeeid,
        Project fromprojectid,
        Project toprojectid,
        Projectsite projectsite,
        EmployeeWithoutPassword hsecoordid,
        EmployeeWithoutPassword hsemgrid,
        Long hsemgstatusid,
        LocalDate date1,

        LocalDate date2,
        Department dept1,
        Department dept2,
        Hsefunction function1,
        Hsefunction function2,
        Status newstatus,
        String remarks,
        Boolean docavailable,
        String searchtext,
        Boolean isactivated,
        LocalDate createdate,

        EmployeeWithoutPassword createuserid,

        LocalDate editdate,

        EmployeeWithoutPassword edituserid,
        EmployeeWithoutPassword isdeleted,

        EmployeeWithoutPassword isapproved
) {
}
