package com.grapplesoft.meil_backend.services.tokenService;

import com.grapplesoft.meil_backend.models.entities.Employee;
import com.grapplesoft.meil_backend.models.entities.Token;

public interface TokenService {
    Token getTokenForEmployeeOrCreateNew(Employee employee);
    Token getTokenForEmployeeOrCreateNew(String emailOffice);
    void deleteTokenForEmployee(Employee employee);
    Token updateTokenLastUsed(Token token);
    Token createTokenForEmployee(Employee employee);
}
