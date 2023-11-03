package com.grapplesoft.meil_backend.services.departmentService;

import com.grapplesoft.meil_backend.models.entities.Department;
import com.grapplesoft.meil_backend.models.request.DepartmentRequest;

import java.util.List;

public interface DepartmentService {
    // Method signature for inserting a Department into the system
    Department insert(DepartmentRequest departmentRequest);

    // Method signature for editing a Department into the system
    Department edit(DepartmentRequest departmentRequest);

    // Method signature for retrieving all Departments
    List<Department> getall();

    // Method signature for deleting a Department by its ID
    boolean delete(String id);
}
