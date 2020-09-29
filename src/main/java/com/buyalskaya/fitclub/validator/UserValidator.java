package com.buyalskaya.fitclub.validator;

import java.util.Map;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String LOGIN_REGEX = "\\p{Alpha}[\\w\\-.]{4,20}";
    private static final String PASSWORD_REGEX = "\\pL{8,20}";

    public boolean isLoginValid(String login) {
        boolean isValid = false;
        if (login != null && !login.isEmpty()) {
            isValid = Pattern.matches(LOGIN_REGEX,login);
        }
        return isValid;
    }

    public boolean isPasswordValid(String password) {
        boolean isValid = false;
        if (password != null && !password.isEmpty()) {
            isValid = Pattern.matches(PASSWORD_REGEX,password);
        }
        return isValid;
    }

    public boolean isUserParametersValid(Map<String,String> parameters) {
        boolean isValid = false;
        return isValid;
    }
}
