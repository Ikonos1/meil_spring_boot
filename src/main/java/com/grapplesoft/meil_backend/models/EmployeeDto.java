package com.grapplesoft.meil_backend.models;

import com.grapplesoft.meil_backend.models.entities.Employee;
import com.grapplesoft.meil_backend.models.entities.Role;

import java.time.LocalDate;

/**
 * EmployeeDto
 * {@link Employee}
 */
public record EmployeeDto(
        Integer id,
        String employeeName,
        String employeeFirstName,
        String employeeLastName,
        Integer empStatusId,
        Integer deptCode,
        Integer projectCode,
        String mobileOffice,
        String mobile2,
        String mobile3,
        String emailOffice,
        Integer hseFunctionId,
        String designation,
        LocalDate dateOfJoining,
        LocalDate dateOfBirth,
        String drivingLicense,
        Integer hseCompLevel,
        Integer hseCompPoints,
        String hseLeadership,
        String remarks,
        LocalDate createDate,
        Employee createUserId,
        LocalDate editDate,
        Employee editUserId,
        Boolean isDeleted,
        Role role,
        String roleString,
        String password,
        String email2) {
}
