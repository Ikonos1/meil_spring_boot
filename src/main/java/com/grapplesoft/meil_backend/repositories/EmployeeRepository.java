package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmailOffice(String emailOffice);
    Long deleteByEmailOffice(String emailOffice);

}
