package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    Department findByDeptcodeAndIsdeleted(String departmentCode, boolean isDeleted);

}