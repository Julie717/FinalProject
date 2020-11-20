package com.buyalskaya.fitclub.validator;

import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.model.entity.UserRole;
import com.buyalskaya.fitclub.model.entity.UserStatus;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * The type User validator.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
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

    /**
     * Is user parameters valid boolean.
     * Is used to check the correctness of user's parameters to add into users table
     *
     * @param userParameters the user parameters
     * @return the boolean
     */
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

    /**
     * Is user update parameters valid boolean.
     * Is used to check the correctness of user's parameters to update users table
     *
     * @param userParameters the user parameters
     * @return the boolean
     */
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

    /**
     * Is staff update parameters valid boolean.
     * Is used to check the correctness of staff's parameters to update staffs table
     *
     * @param staffParameters the staff parameters
     * @return the boolean
     */
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

    /**
     * Is user parameters for change role or status valid boolean.
     * Is used to check the correctness of user's parameters to change a user's role or status
     *
     * @param userParameters the user parameters
     * @return the boolean
     */
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

    /**
     * Is work experience valid boolean.
     * Is used to check the correctness of staff's work experience
     *
     * @param workExperience the work experience
     * @return the boolean
     */
    public static boolean isWorkExperienceValid(String workExperience) {
        boolean isValid = CommonValidator.isPositiveInteger(workExperience);
        if (isValid) {
            isValid = Integer.parseInt(workExperience) <= MAX_WORK_EXPERIENCE;
        }
        return isValid;
    }

    /**
     * Is description valid boolean.
     * Is used to check the correctness of staff's description
     *
     * @param description the description
     * @return the boolean
     */
    public static boolean isDescriptionValid(String description) {
        boolean isValid = false;
        if (description != null) {
            isValid = Pattern.matches(DESCRIPTION_REGEX, description);
        }
        return isValid;
    }

    /**
     * Is login valid boolean.
     * Is used to check the correctness of user's login
     *
     * @param login the login
     * @return the boolean
     */
    public static boolean isLoginValid(String login) {
        boolean isValid = false;
        if (login != null && !login.isEmpty()) {
            isValid = Pattern.matches(LOGIN_REGEX, login);
        }
        return isValid;
    }

    /**
     * Is password valid boolean.
     * Is used to check the correctness of user's password
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean isPasswordValid(String password) {
        boolean isValid = false;
        if (password != null && !password.isEmpty()) {
            isValid = Pattern.matches(PASSWORD_REGEX, password);
        }
        return isValid;
    }

    /**
     * Is name valid boolean.
     * Is used to check the correctness of user's name
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean isNameValid(String name) {
        boolean isValid = false;
        if (name != null && !name.isEmpty()) {
            isValid = Pattern.matches(NAME_REGEX, name);
        }
        return isValid;
    }

    /**
     * Is surname valid boolean.
     * Is used to check the correctness of user's surname
     *
     * @param surname the surname
     * @return the boolean
     */
    public static boolean isSurnameValid(String surname) {
        boolean isValid = false;
        if (surname != null && !surname.isEmpty()) {
            isValid = Pattern.matches(SURNAME_REGEX, surname);
        }
        return isValid;
    }

    /**
     * Is phone valid boolean.
     * Is used to check the correctness of user's phone
     *
     * @param phone the phone
     * @return the boolean
     */
    public static boolean isPhoneValid(String phone) {
        boolean isValid = false;
        if (phone != null && !phone.isEmpty()) {
            isValid = Pattern.matches(PHONE_REGEX, phone);
        }
        return isValid;
    }

    /**
     * Is email valid boolean.
     * Is used to check the correctness of user's email
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        if (email != null && !email.isEmpty()) {
            isValid = Pattern.matches(EMAIL_REGEX, email);
        }
        return isValid;
    }

    /**
     * Is photo extension valid boolean.
     * Is used to check the correctness of user's photo extension
     *
     * @param photo the photo
     * @return the boolean
     */
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

    /**
     * Is id role valid boolean.
     * Is used to check the correctness of user's role's id
     *
     * @param idRole the id role
     * @return the boolean
     */
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

    /**
     * Is id status valid boolean.
     * Is used to check the correctness of user's status's id
     *
     * @param idStatus the id status
     * @return the boolean
     */
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