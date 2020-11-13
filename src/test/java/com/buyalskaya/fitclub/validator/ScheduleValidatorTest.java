package com.buyalskaya.fitclub.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class ScheduleValidatorTest {
    @Test
    public void isUserParametersPositiveTest() {
        Map<String, String> scheduleParameters = new HashMap<>();
        scheduleParameters.put("idWorkout", "17");
        scheduleParameters.put("idHall", "25");
        scheduleParameters.put("idInstructor", "4");
        scheduleParameters.put("startDate", "04.05.2020");
        scheduleParameters.put("startTime", "18:00");
        scheduleParameters.put("duration", "55");
        scheduleParameters.put("capacity", "15");
        boolean actual = ScheduleValidator.isScheduleParametersValid(scheduleParameters);
        assertEquals(actual, true);
    }

    @Test
    public void isUserUserParametersNegativeTest() {
        Map<String, String> scheduleParameters = new HashMap<>();
        scheduleParameters.put("idWorkout", "17");
        scheduleParameters.put("idHall", "25");
        scheduleParameters.put("idInstructor", "4");
        scheduleParameters.put("startDate", "04.05.2020");
        scheduleParameters.put("capacity", "15");
        boolean actual = ScheduleValidator.isScheduleParametersValid(scheduleParameters);
        assertEquals(actual, false);
    }

    @DataProvider(name = "dataForIsCapacityValid")
    public Object[][] dataForIsCapacityValid() {
        return new Object[][]{
                {"25", true},
                {"15", true},
                {"33", false},
                {"0", false},
                {"-7", false},
                {"-1.1", false},
                {"1.1", false},
                {"1o", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsCapacityValid")
    public void isCapacityValidTest(String capacity, boolean expected) {
        boolean actual = ScheduleValidator.isCapacityValid(capacity);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsDurationValid")
    public Object[][] dataForIsDurationValid() {
        return new Object[][]{
                {"55", true},
                {"85", true},
                {"0", false},
                {"-7", false},
                {"1o", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsDurationValid")
    public void isDurationValidTest(String duration, boolean expected) {
        boolean actual = ScheduleValidator.isDurationValid(duration);
        assertEquals(actual, expected);
    }
}
