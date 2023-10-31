package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {


}
