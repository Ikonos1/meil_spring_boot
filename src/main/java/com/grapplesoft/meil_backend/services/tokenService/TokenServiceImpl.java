package com.grapplesoft.meil_backend.services.tokenService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grapplesoft.meil_backend.configuration.JwtProperties;
import com.grapplesoft.meil_backend.models.entities.Employee;
import com.grapplesoft.meil_backend.models.entities.Token;
import com.grapplesoft.meil_backend.repositories.TokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {
    private final JwtProperties jwtProperties;
    private final TokenRepository tokenRespository;
    private final SecretKey key;

    @Autowired
    public TokenServiceImpl(@Qualifier("jwtProperties") JwtProperties jwtProperties,
                            @Qualifier("tokenRepository") TokenRepository tokenRespository) {
        this.jwtProperties = jwtProperties;
        this.tokenRespository = tokenRespository;
        this.key = Keys.hmacShaKeyFor(this.jwtProperties.getSecret().getBytes());
    }


    /**
     * @param employee
     * @return
     */
    @Override
    public Token getTokenForEmployeeOrCreateNew(Employee employee) {


        return null;
    }


    /**
     * @param emailOffice
     * @return
     */
    @Override
    public Token getTokenForEmployeeOrCreateNew(String emailOffice) {

        return null;
    }

    /**
     * @param employee
     */
    @Override
    public void deleteTokenForEmployee(Employee employee) {

    }

    /**
     * @param token
     * @return
     */
    @Override
    public Token updateTokenLastUsed(Token token) {
        return null;
    }

    /**
     * @param employee
     * @return
     */
    @Override
    public Token createTokenForEmployee(Employee employee) {
        Token token = Token.builder()
                .employee(employee)
                .tokenString(generateJwtTokenString(employee))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return this.tokenRespository.save(token);
    }

    private String generateJwtTokenString(Employee employee) {
        Map<String, String> payload = new HashMap<String, String>();
        payload.put("employeeId", employee.getId().toString());
        payload.put("emailOffice", employee.getEmailOffice());
        payload.put("roleId", employee.getRole().getId().toString());
        payload.put("role", employee.getRole().getRoleName());
        payload.put("issuer", jwtProperties.getIssuer());
        payload.put("iat", LocalDateTime.now().toString());

        try {
            // TODO: finish up json processing
            return Jwts.builder().content(new ObjectMapper().writeValueAsString(payload))
                    .signWith(this.key)
                    .compact();
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
