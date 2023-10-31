package com.grapplesoft.meil_backend.services.employeeService;

import com.grapplesoft.meil_backend.models.EmployeeDto;
import com.grapplesoft.meil_backend.models.EmployeeWithoutPassword;
import com.grapplesoft.meil_backend.models.pagination.PagedListData;
import com.grapplesoft.meil_backend.models.entities.Employee;
import com.grapplesoft.meil_backend.models.request.AddEmployeeRequest;
import com.grapplesoft.meil_backend.models.request.UpdateEmployeeRequest;

import java.util.Optional;

public interface EmployeeService {
    Employee addEmployee(AddEmployeeRequest addEmployeeRequestInput);
    PagedListData<Employee> gellAllEmployees(Long page, Long size);
    EmployeeWithoutPassword updateEmployee(UpdateEmployeeRequest request);
    void deleteEmployee(EmployeeDto deleteEmployeeInput);
    void deleteEmployee(String emailOffice);
    Employee getEmployeeByEmailOffice(String emailOffice);
    Employee getEmployeeById(Long id);
    Employee authenticateEmployee(String emailOffice, String password);
}
