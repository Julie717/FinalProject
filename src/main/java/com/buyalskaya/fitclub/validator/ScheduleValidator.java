package com.buyalskaya.fitclub.validator;

import com.buyalskaya.fitclub.controller.ParameterName;

import java.util.Map;

public class ScheduleValidator {
    private static final int MIN_CAPACITY = 1;
    private static final int MAX_CAPACITY = 30;
    private static final int MIN_DURATION = 55;
    private static final int MAX_DURATION = 85;

    private ScheduleValidator() {
    }

    public static boolean isScheduleParametersValid(Map<String, String> scheduleParameters) {
        return CommonValidator.isIdValid(scheduleParameters.get(ParameterName.SCHEDULE_WORKOUT_ID)) &&
                CommonValidator.isIdValid(scheduleParameters.get(ParameterName.SCHEDULE_HALL_ID)) &&
                CommonValidator.isIdValid(scheduleParameters.get(ParameterName.SCHEDULE_INSTRUCTOR_ID)) &&
                CommonValidator.isDateValid(scheduleParameters.get(ParameterName.SCHEDULE_START_DATE)) &&
                CommonValidator.isTimeValid(scheduleParameters.get(ParameterName.SCHEDULE_START_TIME)) &&
                CommonValidator.isPositiveInteger(scheduleParameters.get(ParameterName.SCHEDULE_DURATION)) &&
                CommonValidator.isPositiveInteger(scheduleParameters.get(ParameterName.SCHEDULE_CAPACITY));
    }

    public static boolean isCapacityValid(String capacity) {
        boolean isValid = CommonValidator.isPositiveInteger(capacity);
        if (isValid) {
            int capacityInt = Integer.parseInt(capacity);
            isValid = capacityInt >= MIN_CAPACITY && capacityInt <= MAX_CAPACITY;
        }
        return isValid;
    }

    public static boolean isDurationValid(String duration) {
        boolean isValid = CommonValidator.isPositiveInteger(duration);
        if (isValid) {
            int capacityInt = Integer.parseInt(duration);
            isValid = capacityInt >= MIN_DURATION && capacityInt <= MAX_DURATION;
        }
        return isValid;
    }
}