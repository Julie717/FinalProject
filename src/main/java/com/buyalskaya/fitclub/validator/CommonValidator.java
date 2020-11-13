package com.buyalskaya.fitclub.validator;

import java.util.regex.Pattern;

public class CommonValidator {
    private static final String CHECK_NUMBER = "\\d+";
    private static final String REGEX_DATE_RU = "((0[1-9])|([1-2][0-9])|(3[0-1]))\\.((0[1-9])|(1[0-2]))\\.((19[6-9][0-9]|20[0-9]{2}))";
    private static final String REGEX_DATE_EN = "((0[1-9])|(1[0-2]))/((0[1-9])|([1-2][0-9])|(3[0-1]))/(19[6-9][0-9]|20[0-9]{2})";
    private static final String REGEX_TIME = "(([0-1][0-9])|(2[0-3])):([0-5][0-9])";

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
}