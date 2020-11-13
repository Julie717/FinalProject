package com.buyalskaya.fitclub.validator;

import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.model.entity.UserRole;
import com.buyalskaya.fitclub.model.entity.UserStatus;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN_REGEX = "\\p{Alpha}[\\w\\-.]{4,20}";
    private static final String PASSWORD_REGEX = "(?=.*\\d)(?=.*\\p{Upper})[\\w\\p{Punct}]{8,}";
    private static final String NAME_REGEX = "\\pL{2,20}";
    private static final String SURNAME_REGEX = "[\\pL-]{2,50}";
    private static final String PHONE_REGEX = "\\+375\\d{9}";
    private static final String EMAIL_REGEX = "([\\p{Lower}\\d_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String DESCRIPTION_REGEX = "([^<>]){0,2000}";
    private static final int MAX_WORK_EXPERIENCE = 60;
    private static final String PHOTO_FORMAT = "image/jpeg";

    private UserValidator() {
    }

    public static boolean isUserParametersValid(Map<String, String> userParameters) {
        boolean isValid = true;
        if (!isLoginValid(userParameters.get(ParameterName.USER_LOGIN))) {
            isValid = false;
            userParameters.remove(ParameterName.USER_LOGIN);
        }
        String password = userParameters.get(ParameterName.USER_PASSWORD);
        if (!isPasswordValid(password)) {
            isValid = false;
            userParameters.remove(ParameterName.USER_PASSWORD);
        }
        String repeatedPassword = userParameters.get(ParameterName.REPEATED_PASSWORD);
        if (!password.equals(repeatedPassword)) {
            isValid = false;
            userParameters.remove(ParameterName.REPEATED_PASSWORD);
        }
        if (!isNameValid(userParameters.get(ParameterName.USER_NAME))) {
            isValid = false;
            userParameters.remove(ParameterName.USER_NAME);
        }
        if (!isSurnameValid(userParameters.get(ParameterName.USER_SURNAME))) {
            isValid = false;
            userParameters.remove(ParameterName.USER_SURNAME);
        }
        if (!isPhoneValid(userParameters.get(ParameterName.USER_PHONE))) {
            isValid = false;
            userParameters.remove(ParameterName.USER_PHONE);
        }
        if (!isEmailValid(userParameters.get(ParameterName.USER_EMAIL))) {
            isValid = false;
            userParameters.remove(ParameterName.USER_EMAIL);
        }
        if (!CommonValidator.isDateValid(userParameters.get(ParameterName.USER_BIRTHDAY))) {
            isValid = false;
            userParameters.remove(ParameterName.USER_BIRTHDAY);
        }
        return isValid;
    }

    public static boolean isUserUpdateParametersValid(Map<String, String> userParameters) {
        boolean isValid = true;
        if (userParameters.containsKey(ParameterName.USER_LOGIN)) {
            isValid = isLoginValid(userParameters.get(ParameterName.USER_LOGIN));
        }
        if (userParameters.containsKey(ParameterName.USER_NAME)) {
            isValid = isNameValid(userParameters.get(ParameterName.USER_NAME)) && isValid;
        }
        if (userParameters.containsKey(ParameterName.USER_SURNAME)) {
            isValid = isSurnameValid(userParameters.get(ParameterName.USER_SURNAME)) && isValid;
        }
        if (userParameters.containsKey(ParameterName.USER_PHONE)) {
            isValid = isPhoneValid(userParameters.get(ParameterName.USER_PHONE)) && isValid;
        }
        if (userParameters.containsKey(ParameterName.USER_EMAIL)) {
            isValid = isEmailValid(userParameters.get(ParameterName.USER_EMAIL)) && isValid;
        }
        if (userParameters.containsKey(ParameterName.USER_BIRTHDAY)) {
            isValid = CommonValidator.isDateValid(userParameters.get(ParameterName.USER_BIRTHDAY)) && isValid;
        }
        return isValid;
    }

    public static boolean isStaffUpdateParametersValid(Map<String, String> staffParameters) {
        boolean isValid = true;
        if (staffParameters.containsKey(ParameterName.WORK_EXPERIENCE)) {
            isValid = isWorkExperienceValid(staffParameters.get(ParameterName.WORK_EXPERIENCE));
        }
        if (staffParameters.containsKey(ParameterName.STAFF_DESCRIPTION)) {
            isValid = isDescriptionValid(staffParameters.get(ParameterName.STAFF_DESCRIPTION)) && isValid;
        }
        return isValid;
    }

    public static boolean isUserParametersForChangeRoleOrStatusValid(Map<String, String> userParameters) {
        boolean isValid = UserValidator.isIdStatusValid(userParameters.get(ParameterName.USER_STATUS)) &&
                UserValidator.isIdRoleValid(userParameters.get(ParameterName.USER_ROLE)) &&
                UserValidator.isIdStatusValid(userParameters.get(ParameterName.USER_STATUS));
        if (isValid) {
            int idRole = Integer.parseInt(userParameters.get(ParameterName.USER_ROLE));
            if (idRole != UserRole.CLIENT.getUserRoleId()) {
                isValid = CommonValidator.isDateValid(userParameters.get(ParameterName.STAFF_START_WORKING_DATE));
                String endWorkingDate = userParameters.get(ParameterName.STAFF_END_WORKING_DATE);
                if (endWorkingDate != null && !endWorkingDate.isEmpty()) {
                    isValid = CommonValidator.isDateValid(endWorkingDate);
                }
            }
        }
        return isValid;
    }

    public static boolean isWorkExperienceValid(String workExperience) {
        boolean isValid = CommonValidator.isPositiveInteger(workExperience);
        if (isValid) {
            isValid = Integer.parseInt(workExperience) <= MAX_WORK_EXPERIENCE;
        }
        return isValid;
    }

    public static boolean isDescriptionValid(String description) {
        boolean isValid = false;
        if (description != null) {
            isValid = Pattern.matches(DESCRIPTION_REGEX, description);
        }
        return isValid;
    }

    public static boolean isLoginValid(String login) {
        boolean isValid = false;
        if (login != null && !login.isEmpty()) {
            isValid = Pattern.matches(LOGIN_REGEX, login);
        }
        return isValid;
    }

    public static boolean isPasswordValid(String password) {
        boolean isValid = false;
        if (password != null && !password.isEmpty()) {
            isValid = Pattern.matches(PASSWORD_REGEX, password);
        }
        return isValid;
    }

    public static boolean isNameValid(String name) {
        boolean isValid = false;
        if (name != null && !name.isEmpty()) {
            isValid = Pattern.matches(NAME_REGEX, name);
        }
        return isValid;
    }

    public static boolean isSurnameValid(String surname) {
        boolean isValid = false;
        if (surname != null && !surname.isEmpty()) {
            isValid = Pattern.matches(SURNAME_REGEX, surname);
        }
        return isValid;
    }

    public static boolean isPhoneValid(String phone) {
        boolean isValid = false;
        if (phone != null && !phone.isEmpty()) {
            isValid = Pattern.matches(PHONE_REGEX, phone);
        }
        return isValid;
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        if (email != null && !email.isEmpty()) {
            isValid = Pattern.matches(EMAIL_REGEX, email);
        }
        return isValid;
    }

    public static boolean isPhotoExtensionValid(InputStream photo) {
        boolean isValid = false;
        try {
            String mimeType = URLConnection.guessContentTypeFromStream(photo);
            isValid = PHOTO_FORMAT.equals(mimeType);
        } catch (IOException ex) {
            logger.log(Level.WARN, "Impossible to find file's extension", ex);
        }
        return isValid;
    }

    public static boolean isIdRoleValid(String idRole) {
        boolean isValid = false;
        if (idRole != null && !idRole.isEmpty() && CommonValidator.isPositiveInteger(idRole)) {
            int idRoleInt = Integer.parseInt(idRole);
            isValid = Arrays.stream(UserRole.values())
                    .mapToInt(r -> r.getUserRoleId())
                    .anyMatch(r -> r == idRoleInt);
        }
        return isValid;
    }

    public static boolean isIdStatusValid(String idStatus) {
        boolean isValid = false;
        if (idStatus != null && !idStatus.isEmpty() && CommonValidator.isPositiveInteger(idStatus)) {
            int idStatusInt = Integer.parseInt(idStatus);
            isValid = Arrays.stream(UserStatus.values())
                    .mapToInt(r -> r.getUserStatusId())
                    .anyMatch(r -> r == idStatusInt);
        }
        return isValid;
    }
}