package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Actiontype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActionTypeRepository extends JpaRepository<Actiontype, Long> {
    Optional<Actiontype> findByAction(String action);
}
