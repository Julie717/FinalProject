package com.buyalskaya.fitclub.util;

import org.mindrot.jbcrypt.BCrypt;

public class Encryptor {
    private Encryptor() {
    }

    public static String encryptPassword(String password) {
        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return encryptedPassword;
    }
}