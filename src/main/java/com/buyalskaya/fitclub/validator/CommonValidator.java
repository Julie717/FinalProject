package com.buyalskaya.fitclub.validator;

import com.buyalskaya.fitclub.controller.ParameterName;

import java.util.Map;
import java.util.regex.Pattern;

public class CommonValidator {
    private static final String CHECK_NUMBER = "\\d+";
    private static final String REGEX_DATE_RU = "((0[1-9])|([1-2][0-9])|(3[0-1]))\\.((0[1-9])|(1[0-2]))\\.((19[6-9][0-9]|20[0-9]{2}))";
    private static final String REGEX_DATE_EN = "((0[1-9])|(1[0-2]))/((0[1-9])|([1-2][0-9])|(3[0-1]))/(19[6-9][0-9]|20[0-9]{2})";
    private static final String REGEX_TIME = "(([0-1][0-9])|(2[0-3])):([0-5][0-9])";
    private static final String MESSAGE_REGEX = "([^<>]){0,500}";
    private static final String NAME_REGEX = "\\pL[\\pL- ]{1,50}";

    private CommonValidator() {
    }

    public static boolean isPositiveInteger(String number) {
        boolean isValid = false;
        if (number != null && !number.isEmpty()) {
            isValid = Pattern.matches(CHECK_NUMBER, number);
        }
        return isValid;
    }

    public static boolean isIdValid(String id) {
        return CommonValidator.isPositiveInteger(id);
    }

    public static boolean isDateValid(String date) {
        boolean isValid = false;
        if (date != null && !date.isEmpty()) {
            isValid = Pattern.matches(REGEX_DATE_RU, date) ||
                    Pattern.matches(REGEX_DATE_EN, date);
        }
        return isValid;
    }

    public static boolean isTimeValid(String time) {
        boolean isValid = false;
        if (time != null && !time.isEmpty()) {
            isValid = Pattern.matches(REGEX_TIME, time);
        }
        return isValid;
    }

    public static boolean isContactParametersValid(Map<String, String> contactParameters) {
        boolean isValid = isNameValid(contactParameters.get(ParameterName.CONTACT_US_NAME));
        isValid = isValid & UserValidator.isPhoneValid(contactParameters.get(ParameterName.CONTACT_US_PHONE));
        isValid = isValid & UserValidator.isEmailValid(contactParameters.get(ParameterName.CONTACT_US_EMAIL));
        isValid = isValid & isMessageValid(contactParameters.get(ParameterName.CONTACT_US_MESSAGE));
        return isValid;
    }

    private static boolean isNameValid(String name) {
        boolean isValid = false;
        if (name != null && !name.isEmpty()) {
            isValid = Pattern.matches(NAME_REGEX, name);
        }
        return isValid;
    }

    private static boolean isMessageValid(String message) {
        boolean isValid = false;
        if (message != null) {
            isValid = Pattern.matches(MESSAGE_REGEX, message);
        }
        return isValid;
    }
}