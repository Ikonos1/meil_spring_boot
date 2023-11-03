package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.EmployeeWithoutPassword;
import com.grapplesoft.meil_backend.models.entities.Employee;
import com.grapplesoft.meil_backend.models.entities.Role;
import com.grapplesoft.meil_backend.models.request.AddEmployeeRequest;

public class EmployeeBuilder {
    public static EmployeeWithoutPassword buildEmployeeWithoutPassword(Employee employee) {
        return new EmployeeWithoutPassword(
                employee.getId(),
                employee.getEmployeeFirstName(),
                employee.getEmployeeLastName(),
                employee.getDesignation(),
                employee.getRole(),
                employee.getDateOfBirth(),
                employee.getDateOfJoining(),
                employee.getDrivingLicense(),
                employee.getDeptCode(),
                employee.getProjCode(),
                employee.getHseLeadership(),
                employee.getHseCompLevel(),
                employee.getHseCompPoints(),
                employee.getEmailOffice(),
                employee.getEmail2(),
                employee.getMobileOffice(),
                employee.getMobile2(),
                employee.getMobile3(),
                employee.getWhatsAppNum(),
                employee.getRemarks(),
                employee.getToken()
        );
    }

    public static Employee fromAddEmployeeRequest(AddEmployeeRequest addEmployeeRequest, Role role, String passwordEncoded, Boolean isPasswordSet) {
        return Employee.builder()
                .employeeFirstName(addEmployeeRequest.firstName())
                .employeeLastName(addEmployeeRequest.lastName())
                .designation(addEmployeeRequest.designation())
                .role(role)
                .dateOfBirth(addEmployeeRequest.dateOfBirth())
                .drivingLicense(addEmployeeRequest.drivingLicense())
                .dateOfJoining(addEmployeeRequest.dateOfJoining())
                .emailOffice(addEmployeeRequest.emailOffice())
                .email2(addEmployeeRequest.email2())
                .mobileOffice(addEmployeeRequest.mobileOffice())
                .mobile2(addEmployeeRequest.mobile2())
                .mobile3(addEmployeeRequest.mobile3())
                .password(passwordEncoded)
                .isPasswordSet(isPasswordSet)
                .build();
    }
}
