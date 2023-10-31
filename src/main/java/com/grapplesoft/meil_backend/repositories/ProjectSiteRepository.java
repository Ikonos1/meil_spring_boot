package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Projectsite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectSiteRepository extends JpaRepository<Projectsite, Long> {
}
