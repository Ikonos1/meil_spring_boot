package com.grapplesoft.meil_backend.security;

import com.grapplesoft.meil_backend.configuration.JwtProperties;
import com.grapplesoft.meil_backend.services.employeeService.EmployeeService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final List<String> EXCLUDED_URLS = List.of(
            "/employee/add", "/employee/login"
    );

    private final EmployeeService employeeService;
    private final JwtProperties jwtProperties;
    private final SecretKey key;

    @Autowired
    public JwtTokenAuthenticationFilter(EmployeeService employeeService, JwtProperties jwtProperties) {
        this.employeeService = employeeService;
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(this.jwtProperties.getSecret().getBytes());
    }

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if (isExcluded(request)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String extractedToken = extractTokenFromRequest(request);
//        if (extractedToken != null && validateToken(extractedToken)) {
//
//            System.out.println("Token is valid");
//            return;
//        }

        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    private Boolean validateToken(String token) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.jwtProperties.getSecret().getBytes(),
                signatureAlgorithm.getJcaName());
        SecretKey key = Keys.hmacShaKeyFor(this.jwtProperties.getSecret().getBytes());

//        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(signatureAlgorithm,Keys.hmacShaKeyFor(this.jwtProperties.getSecret().getBytes()));
        String[] chunks = token.split("\\.");
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        String signature = chunks[2];
        String tokenWithoutSignature = chunks[0] + "." + chunks[1];


//        String verifiedPayload = Arrays.toString(Jwts.parser().verifyWith(key).build().parseSignedContent(tokenWithoutSignature).getPayload());

//        String verifiedPayload = Jwts.parser().setSigningKey(key).parseClaimsJws(tokenWithoutSignature).getBody().toString();


        byte[] verifiedPayload = Jwts.parser().decryptWith(key).build().parseEncryptedContent(tokenWithoutSignature).getPayload();

        return new String(verifiedPayload).equals(payload);
    }

    private boolean isExcluded(HttpServletRequest request) {
        String path = request.getRequestURI();
        return EXCLUDED_URLS.contains(path);
    }
}
