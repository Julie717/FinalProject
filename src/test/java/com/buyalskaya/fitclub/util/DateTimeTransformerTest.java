package com.buyalskaya.fitclub.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.testng.Assert.assertEquals;

public class DateTimeTransformerTest {
    @DataProvider(name = "dataForFromLongToLocalDate")
    public Object[][] dataForFromLongToLocalDate() {
        return new Object[][]{
                {44147, LocalDate.of(2020, 11, 12)},
                {33089, LocalDate.of(1990, 8, 4)},
                {0, LocalDate.of(1899, 12, 30)},
                {-5, LocalDate.of(1899, 12, 25)}
        };
    }

    @Test(dataProvider = "dataForFromLongToLocalDate")
    public void fromLongToLocalDateTest(long date, LocalDate expected) {
        LocalDate actual = DateTimeTransformer.fromLongToLocalDate(date);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFromLocalDateToLong")
    public Object[][] dataFromForLocalDateToLong() {
        return new Object[][]{
                {LocalDate.of(2020, 11, 12), 44147},
                {LocalDate.of(1990, 8, 4), 33089},
                {LocalDate.of(1899, 12, 30), 0},
                {LocalDate.of(1899, 12, 25), -5}
        };
    }

    @Test(dataProvider = "dataForFromLocalDateToLong")
    public void fromLocalDateToLongTest(LocalDate date, long expected) {
        long actual = DateTimeTransformer.fromLocalDateToLong(date);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFromLongToLocalTime")
    public Object[][] dataForFromLongToLocalTime() {
        return new Object[][]{
                {1800, LocalTime.of(18, 0)},
                {900, LocalTime.of(9, 0)},
                {10, LocalTime.of(0, 10)},
                {0, LocalTime.of(0, 0)}
        };
    }

    @Test(dataProvider = "dataForFromLongToLocalTime")
    public void fromLongToLocalTimeTest(long time, LocalTime expected) {
        LocalTime actual = DateTimeTransformer.fromLongToLocalTime(time);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFromLocalTimeToLong")
    public Object[][] dataForFromLocalTimeToLong() {
        return new Object[][]{
                {LocalTime.of(18, 0), 1800},
                {LocalTime.of(9, 0), 900},
                {LocalTime.of(0, 10), 10},
                {LocalTime.of(0, 0), 0}
        };
    }

    @Test(dataProvider = "dataForFromLocalTimeToLong")
    public void fromLocalTimeToLongTest(LocalTime time, long expected) {
        long actual = DateTimeTransformer.fromLocalTimeToLong(time);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFromStringToLocalDateRu")
    public Object[][] dataForFromStringToLocalDateRu() {
        return new Object[][]{
                {"12.11.2020", LocalDate.of(2020, 11, 12)},
                {"04.08.1990", LocalDate.of(1990, 8, 4)},
                {"30.12.1899", LocalDate.of(1899, 12, 30)},
                {"25.12.1899", LocalDate.of(1899, 12, 25)}
        };
    }

    @Test(dataProvider = "dataForFromStringToLocalDateRu")
    public void fromStringToLocalDateRuTest(String date, LocalDate expected) {
        LocalDate actual = DateTimeTransformer.fromStringToLocalDate(date, "ru_RU");
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFromStringToLocalDateEn")
    public Object[][] dataForFromStringToLocalDateEn() {
        return new Object[][]{
                {"11/12/2020", LocalDate.of(2020, 11, 12)},
                {"1/2/2020", LocalDate.of(2020, 1, 2)},
                {"5/4/87", LocalDate.of(1987, 5, 4)},
                {"08/04/1990", LocalDate.of(1990, 8, 4)},
                {"12/30/1899", LocalDate.of(1899, 12, 30)},
                {"12/25/1899", LocalDate.of(1899, 12, 25)}
        };
    }

    @Test(dataProvider = "dataForFromStringToLocalDateEn")
    public void fromStringToLocalDateEnTest(String date, LocalDate expected) {
        LocalDate actual = DateTimeTransformer.fromStringToLocalDate(date, "en_US");
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFindMonday")
    public Object[][] dataForFindMonday() {
        return new Object[][]{
                {0, LocalDate.of(2020, 11, 9)},
                {1, LocalDate.of(2020, 11, 16)},
                {2, LocalDate.of(2020, 11, 23)},
                {-1, LocalDate.of(2020, 11, 2)}
        };
    }

    @Test(dataProvider = "dataForFindMonday")
    public void findMondayTest(int numberWeek, LocalDate expected) {
        LocalDate actual = DateTimeTransformer.findMonday(numberWeek);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFindSunday")
    public Object[][] dataForFindSunday() {
        return new Object[][]{
                {0, LocalDate.of(2020, 11, 15)},
                {1, LocalDate.of(2020, 11, 22)},
                {2, LocalDate.of(2020, 11, 29)},
                {-1, LocalDate.of(2020, 11, 8)}
        };
    }

    @Test(dataProvider = "dataForFindSunday")
    public void findSundayTest(int numberWeek, LocalDate expected) {
        LocalDate actual = DateTimeTransformer.findSunday(numberWeek);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFromStringToIntNumberWeek")
    public Object[][] dataForFromStringToIntNumberWeek() {
        return new Object[][]{
                {"2", 2},
                {"0", 0},
                {"-1", 0},
                {null, 0}
        };
    }

    @Test(dataProvider = "dataForFromStringToIntNumberWeek")
    public void fromStringToIntNumberWeekTest(String numberWeek, int expected) {
        int actual = DateTimeTransformer.fromStringToIntNumberWeek(numberWeek);
        assertEquals(actual, expected);
    }
}