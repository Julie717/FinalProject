package com.buyalskaya.fitclub.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The type Encryptor.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class Encryptor {
    private Encryptor() {
    }

    /**
     * Encrypt password string.
     * Is used to encrypt password.
     * Bcrypt is a password-hashing function designed by Niels Provos and David Mazières,
     * based on the Blowfish cipher and presented at USENIX in 1999. Besides incorporating
     * a salt to protect against rainbow table attacks, bcrypt is an adaptive function:
     * over time, the iteration count can be increased to make it slower, so it remains
     * resistant to brute-force search attacks even with increasing computation power.
     *
     * @param password the password
     * @return the string
     */
    public static String encryptPassword(String password) {
        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return encryptedPassword;
    }

    /**
     * Is equal passwords boolean.
     * Is used to compare two passwords one of them is encrypted
     *
     * @param password        the password
     * @param encryptPassword the encrypt password
     * @return the boolean
     */
    public static boolean isEqualPasswords(String password, String encryptPassword) {
        return BCrypt.checkpw(password, encryptPassword);
    }
}