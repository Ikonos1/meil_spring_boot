package com.grapplesoft.meil_backend.utils.tokenUtility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.grapplesoft.meil_backend.models.PayloadDto;
import org.apache.commons.lang3.StringUtils;

// not in use
public class JwtTokenUtility {
    // umimplemented, unused. not in use
    public static String generateTokenString() {
        return "";
    }
    public static String getTokenFromHeader(String authHeader) {
        return StringUtils.substringAfter(authHeader, "Bearer ");
    }

    public static String getPayloadStringFromToken(String token) {
        String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];
        return new String(java.util.Base64.getDecoder().decode(base64EncodedBody));
    }

    public static PayloadDto getPayloadFromToken(String token) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String payloadString = getPayloadStringFromToken(token);

        try {
            return objectMapper.readValue(payloadString, PayloadDto.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static PayloadDto getPayloadFromAuthHeader(String authHeader) {
        String token = getTokenFromHeader(authHeader);
        return getPayloadFromToken(token);
    }

}
