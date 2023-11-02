package com.grapplesoft.meil_backend.services.departmentService;

import com.grapplesoft.meil_backend.models.entities.Department;
import com.grapplesoft.meil_backend.models.request.DepartmentRequest;
import com.grapplesoft.meil_backend.repositories.DepartmentRepository;
import com.grapplesoft.meil_backend.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    DepartmentServiceImpl(
            @Qualifier("departmentRepository") DepartmentRepository departmentRepository,
            @Qualifier("employeeRepository") EmployeeRepository employeeRepository
    ) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Department insert(DepartmentRequest departmentRequest) {
        Department department = new Department();

        department.setDeptcode(departmentRequest.deptcode());
        department.setDeptname(departmentRequest.deptname());
        department.setCreatedate(LocalDate.now());
        if (departmentRequest.createuserid() != null) {
            department.setCreateuserid(employeeRepository.findById(departmentRequest.createuserid()).orElse(null));
        }
        return departmentRepository.save(department);
    }

    @Override
    public Department edit(DepartmentRequest departmentRequest) {
        Department department = departmentRepository.findById(departmentRequest.deptcode()).orElse(null);
        if (department != null) {
            department.setDeptname(departmentRequest.deptname());
            department.setEditdate(LocalDate.now());
            if (departmentRequest.edituserid() != null) {
                department.setEdituserid(employeeRepository.findById(departmentRequest.edituserid()).orElse(null));
            }
            return departmentRepository.save(department);

        } else {

            return null;
        }
    }

    @Override
    public List<Department> getall() {
        // Retrieving all departments from the repository
        return departmentRepository.findAll();
    }

    @Override
    public boolean delete(String id) {
        // Check if the department exists
        Department department = departmentRepository.findById(id).orElse(null);


        if (department != null) {
            if (departmentRepository.findByDeptcodeAndIsdeleted(id, true) == null) {
                department.setIsdeleted(true);
                department.setEditdate(LocalDate.now());
                departmentRepository.save(department);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
