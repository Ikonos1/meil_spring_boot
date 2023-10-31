package com.grapplesoft.meil_backend.controllers;


import com.grapplesoft.meil_backend.builders.ApiResponseBuilder;
import com.grapplesoft.meil_backend.builders.EmployeeBuilder;
import com.grapplesoft.meil_backend.models.EmployeeWithoutPassword;
import com.grapplesoft.meil_backend.models.PayloadDto;
import com.grapplesoft.meil_backend.models.entities.Employee;
import com.grapplesoft.meil_backend.models.pagination.PagedListData;
import com.grapplesoft.meil_backend.models.request.AddEmployeeRequest;
import com.grapplesoft.meil_backend.models.request.DeleteEmployeeRequest;
import com.grapplesoft.meil_backend.models.request.EmployeeLoginRequest;
import com.grapplesoft.meil_backend.models.request.UpdateEmployeeRequest;
import com.grapplesoft.meil_backend.models.response.ApiResponse;
import com.grapplesoft.meil_backend.services.employeeService.EmployeeService;
import com.grapplesoft.meil_backend.utils.PaginationUtility;
import com.grapplesoft.meil_backend.utils.StringStore;
import com.grapplesoft.meil_backend.utils.tokenUtility.JwtTokenUtility;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController extends BaseController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(@Qualifier("employeeServiceImpl") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<EmployeeWithoutPassword>> postEmployee(
            @Valid @RequestBody AddEmployeeRequest request
    ) {
        Employee employee = this.employeeService.addEmployee(request);

        if (employee == null) {
            return ResponseEntity.badRequest().body(
                    ApiResponseBuilder.badRequest("Employee already exists!")
            );
        }

        EmployeeWithoutPassword response = EmployeeBuilder.buildEmployeeWithoutPassword(employee);
        ApiResponse<EmployeeWithoutPassword> apiResponse = ApiResponseBuilder.success(HttpStatus.CREATED, response, "Employee added successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<EmployeeWithoutPassword>> updateEmployee(
            @RequestBody UpdateEmployeeRequest request
    ) {
        EmployeeWithoutPassword employee = this.employeeService.updateEmployee(request);
        if (employee != null) {
            return ResponseEntity.ok(ApiResponseBuilder.success(employee, "Employee updated successfully"));
        } else {
            return ResponseEntity.badRequest().body(
                    ApiResponseBuilder.badRequest("Employee not found!")
            );
        }
    }


    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<EmployeeWithoutPassword>> loginEmployee(
            @Valid @RequestBody EmployeeLoginRequest request
    ) {
        Employee employee = this.employeeService.authenticateEmployee(request.emailOffice(), request.password());
        EmployeeWithoutPassword response = EmployeeBuilder.buildEmployeeWithoutPassword(employee);
        ApiResponse<EmployeeWithoutPassword> apiResponse = ApiResponseBuilder.success(HttpStatus.CREATED, response, "Employee logged in successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<ApiResponse<EmployeeWithoutPassword>> getEmployee(
            @RequestHeader(value = StringStore.TOKEN_HEADER_KEY) String authHeader
    ) {
        PayloadDto payload = JwtTokenUtility.getPayloadFromAuthHeader(authHeader);

        if (payload != null && payload.emailOffice() != null) {
            Employee employee = this.employeeService.getEmployeeByEmailOffice(payload.emailOffice());
            EmployeeWithoutPassword res = EmployeeBuilder.buildEmployeeWithoutPassword(employee);

            ApiResponse<EmployeeWithoutPassword> apiRes = ApiResponseBuilder.success(res, "Employee fetched successfully");
            return ResponseEntity.ok(apiRes);
        } else {
            return ResponseEntity.badRequest().body(
                    ApiResponseBuilder.badRequest("Invalid Token!")
            );
        }
    }

//    @DeleteMapping(value = "/delete", produces = "application/json")
//    public ResponseEntity<ApiResponse<String>> deleteEmployee(
//            @RequestHeader(value = StringStore.TOKEN_HEADER_KEY) String authHeader
//    ) {
//        PayloadDto payload = JwtTokenUtility.getPayloadFromAuthHeader(authHeader);
//
//        if (payload != null && payload.emailOffice() != null) {
//            this.employeeService.deleteEmployee(payload.emailOffice());
//            return ResponseEntity.ok(ApiResponseBuilder.success(HttpStatus.NO_CONTENT,
//                    null, "Employee deleted successfully"));
//        } else {
//            return ResponseEntity.badRequest().body(
//                    ApiResponseBuilder.badRequest("Invalid Token!")
//            );
//        }
//    }

    @DeleteMapping(value = "/delete", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(
            @Valid @RequestBody @NotBlank(message = "Email cannot be empty") DeleteEmployeeRequest request
    ) {
        Employee employee = employeeService.getEmployeeByEmailOffice(request.emailOffice());
        if (employee.getEmailOffice() != null) {
            this.employeeService.deleteEmployee(request.emailOffice());
            return ResponseEntity.ok(ApiResponseBuilder.success(HttpStatus.NO_CONTENT,
                    null, "Employee deleted successfully"));
        } else {
            return ResponseEntity.badRequest().body(
                    ApiResponseBuilder.badRequest("User not found!")
            );
        }
    }


    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<ApiResponse<PagedListData<EmployeeWithoutPassword>>> getAllEmployees() {
        PagedListData<Employee> employees = this.employeeService.gellAllEmployees(null, null);
        List<EmployeeWithoutPassword> empWoPass = new ArrayList<>();

        for (var employee : employees.getData()) {
            empWoPass.add(EmployeeBuilder.buildEmployeeWithoutPassword(employee));
        }

        PagedListData<EmployeeWithoutPassword> res = PaginationUtility.buildPagedListData(empWoPass, employees.getTotalElements(), employees.getTotalPages(), employees.getCurrentPage(), employees.getPageSize());


        ApiResponse<PagedListData<EmployeeWithoutPassword>> apiResponse = ApiResponseBuilder.success(res, "Employees fetched successfully");

        return ResponseEntity.ok(apiResponse);
    }
}
