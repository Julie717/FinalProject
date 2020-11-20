package com.buyalskaya.fitclub.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class CommonValidatorTest {
    @DataProvider(name = "dataForIsPositiveInteger")
    public Object[][] dataForIsPositiveInteger() {
        return new Object[][]{
                {"19", true},
                {"150", true},
                {"0", true},
                {"-1", false},
                {"-1.1", false},
                {"1.1", false},
                {"1.", false},
                {"1o", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsPositiveInteger")
    public void isPositiveIntegerTest(String number, boolean expected) {
        boolean actual = CommonValidator.isPositiveInteger(number);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataForIsPositiveInteger")
    public void isIdValidTest(String id, boolean expected) {
        boolean actual = CommonValidator.isPositiveInteger(id);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsDateValid")
    public Object[][] dataForIsDateValid() {
        return new Object[][]{
                {"04.10.1985", true},
                {"01/12/2020", true},
                {"01/01/2020", true},
                {"4/5/87", false},
                {"1/1/20", false},
                {"1.1.2020", false},
                {"45780", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsDateValid")
    public void isDateValidTest(String date, boolean expected) {
        boolean actual = CommonValidator.isDateValid(date);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIsTimeValid")
    public Object[][] dataForIsTimeValid() {
        return new Object[][]{
                {"09:00", true},
                {"10:15", true},
                {"22:59", true},
                {"00:00", true},
                {"00:60", false},
                {"24:00", false},
                {"o1:00", false},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "dataForIsTimeValid")
    public void isTimeValidValidTest(String time, boolean expected) {
        boolean actual = CommonValidator.isTimeValid(time);
        assertEquals(actual, expected);
    }

    @Test
    public void isContactParametersValidPositiveTest() {
        Map<String, String> contactParameters = new HashMap<>();
        contactParameters.put("name", "Виктория Иванова");
        contactParameters.put("phone", "+375299876541");
        contactParameters.put("email", "ivanov_vv@gmail.com");
        contactParameters.put("message", "Здравствуйте! Можно ли записать на занятие во вторник в 19:00 в большой зал?");
        assertTrue(CommonValidator.isContactParametersValid(contactParameters));
    }

    @Test
    public void isContactParametersValidNegativeTest() {
        Map<String, String> contactParameters = new HashMap<>();
        contactParameters.put("name", "Виктория Иванова");
        contactParameters.put("email", "ivanov_vv@gmail.com");
        contactParameters.put("message", "Здравствуйте! Можно ли записать на занятие во вторник в 19:00 в большой зал?");
        assertFalse(CommonValidator.isContactParametersValid(contactParameters));
    }
}