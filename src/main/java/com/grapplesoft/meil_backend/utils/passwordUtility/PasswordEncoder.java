package com.grapplesoft.meil_backend.utils.passwordUtility;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * This class is used to create a BCryptPasswordEncoder object with the required config attributes
 */
public class PasswordEncoder {
    // not used currently, as these config attributes are for Pbkdf2PasswordEncoder
    private static final String SECRET = "my-ulta-strong-secret-for-meil";
    private static final int SALT_LENGTH = 2;
    private static final int ITERATIONS = 10;

    // config attributes for BCryptPasswordEncoder
    private static final int STRENGTH = 15;

    public static BCryptPasswordEncoder getPasswordEncoder() {
        // adding SecureRandom if in the future the project requires strong seeding implemented
        SecureRandom secureRandom = new SecureRandom();

        // using $2Y version for same functionality as $2B but with better compatibility
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y, STRENGTH, secureRandom);
    }
}
