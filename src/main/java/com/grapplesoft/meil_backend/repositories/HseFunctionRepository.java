package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Hsefunction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HseFunctionRepository extends JpaRepository<Hsefunction, String> {
    Hsefunction findByHsefunccodeAndIsdeleted(String hseFunctionCode,boolean isDeleted);

}