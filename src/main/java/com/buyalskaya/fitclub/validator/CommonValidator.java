package com.buyalskaya.fitclub.validator;

import com.buyalskaya.fitclub.controller.ParameterName;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * The type Common validator.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class CommonValidator {
    private static final String CHECK_NUMBER = "\\d+";
    private final static String LOCALE_EN = "en_US";
    private static final String REGEX_DATE_RU = "((0[1-9])|([1-2][0-9])|(3[0-1]))\\.((0[1-9])|(1[0-2]))\\.((19[6-9][0-9]|20[0-9]{2}))";
    private static final String REGEX_DATE_EN = "((0[1-9])|(1[0-2]))/((0[1-9])|([1-2][0-9])|(3[0-1]))/(19[6-9][0-9]|20[0-9]{2})";
    private static final String REGEX_TIME = "(([0-1][0-9])|(2[0-3])):([0-5][0-9])";
    private static final String MESSAGE_REGEX = "([^<>]){0,500}";
    private static final String NAME_REGEX = "\\pL[\\pL- ]{1,50}";

    private CommonValidator() {
    }

    /**
     * Is positive integer boolean.
     * It makes a  check if number is positive integer
     *
     * @param number the number
     * @return the boolean
     */
    public static boolean isPositiveInteger(String number) {
        boolean isValid = false;
        if (number != null && !number.isEmpty()) {
            isValid = Pattern.matches(CHECK_NUMBER, number);
        }
        return isValid;
    }

    /**
     * Is id valid boolean.
     * Is used to check the correctness of id
     *
     * @param id the id
     * @return the boolean
     */
    public static boolean isIdValid(String id) {
        return CommonValidator.isPositiveInteger(id);
    }

    /**
     * Is date valid boolean.
     * Is used to check the correctness of date
     *
     * @param date the date
     * @return the boolean
     */
    public static boolean isDateValid(String date, String locale) {
        boolean isValid = false;
        if (date != null && !date.isEmpty()) {
            if (locale.equals(LOCALE_EN)) {
                isValid = Pattern.matches(REGEX_DATE_EN, date);
            } else {
                isValid = Pattern.matches(REGEX_DATE_RU, date);
            }
        }
        return isValid;
    }

    /**
     * Is time valid boolean.
     * Is used to check the correctness of time
     *
     * @param time the time
     * @return the boolean
     */
    public static boolean isTimeValid(String time) {
        boolean isValid = false;
        if (time != null && !time.isEmpty()) {
            isValid = Pattern.matches(REGEX_TIME, time);
        }
        return isValid;
    }

    /**
     * Is contact parameters valid boolean.
     * Is used to check the correctness of parameters to send a message from user to administrators
     *
     * @param contactParameters the contact parameters
     * @return the boolean
     */
    public static boolean isContactParametersValid(Map<String, String> contactParameters) {
        boolean isValid = isNameValid(contactParameters.get(ParameterName.CONTACT_US_NAME));
        isValid = isValid & UserValidator.isPhoneValid(contactParameters.get(ParameterName.CONTACT_US_PHONE));
        isValid = isValid & UserValidator.isEmailValid(contactParameters.get(ParameterName.CONTACT_US_EMAIL));
        isValid = isValid & isMessageValid(contactParameters.get(ParameterName.CONTACT_US_MESSAGE));
        return isValid;
    }

    /**
     * Is name valid boolean.
     * Is used to check the correctness of user's name
     *
     * @param name the name
     * @return the boolean
     */
    private static boolean isNameValid(String name) {
        boolean isValid = false;
        if (name != null && !name.isEmpty()) {
            isValid = Pattern.matches(NAME_REGEX, name);
        }
        return isValid;
    }

    /**
     * Is message valid boolean.
     * Is used to check the correctness of a message
     *
     * @param message the message
     * @return the boolean
     */
    private static boolean isMessageValid(String message) {
        boolean isValid = false;
        if (message != null) {
            isValid = Pattern.matches(MESSAGE_REGEX, message);
        }
        return isValid;
    }
}