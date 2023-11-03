package com.grapplesoft.meil_backend.utils.passwordUtility;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

import static org.passay.AllowedCharacterRule.ERROR_CODE;

/**
 * Password Utility
 */
public class PasswordUtility {
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


    /**
     * Generates Random Password
     *
     * @return String - randomly generated password
     * @Author Vishwesh Shukla
     * @see <a href="https://www.passay.org/">Passay Password Policy for Java</a>
     */
    public static String generateRandomPassword() {
        PasswordGenerator generator = new PasswordGenerator();

        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        return generator.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
    }
}
