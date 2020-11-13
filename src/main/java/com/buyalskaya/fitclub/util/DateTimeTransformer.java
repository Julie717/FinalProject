package com.buyalskaya.fitclub.util;

import com.buyalskaya.fitclub.validator.CommonValidator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.regex.Pattern;

public class DateTimeTransformer {
    public final static LocalDate START_COUNTING = LocalDate.of(1899, Month.DECEMBER, 30);
    private final static int TIME_DIVIDER = 100;
    private final static String DATE_PATTERN_RU = "dd.MM.yyyy";
    private final static String DATE_PATTERN_EN = "MM/dd/yyyy";
    private final static String LOCALE_EN = "en_US";
    private static final int DAYS_IN_WEEK = 7;

    private DateTimeTransformer() {
    }

    public static LocalDate fromLongToLocalDate(long date) {
        return START_COUNTING.plusDays(date);
    }

    public static long fromLocalDateToLong(LocalDate date) {
        return date.toEpochDay() - START_COUNTING.toEpochDay();
    }

    public static LocalTime fromLongToLocalTime(long time) {
        return LocalTime.of((int) time / TIME_DIVIDER, (int) time % TIME_DIVIDER);
    }

    public static long fromLocalTimeToLong(LocalTime time) {
        return time.getHour() * 100 + time.getMinute();
    }

    public static LocalDate fromStringToLocalDate(String date, String locale) {
        LocalDate resultDate = null;
        if (date != null && !date.isEmpty()) {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(locale.equals(LOCALE_EN) ? DATE_PATTERN_EN : DATE_PATTERN_RU);
            resultDate = LocalDate.parse(date, formatter);
        }
        return resultDate;
    }

    public static LocalDate findMonday(int numberWeek) {
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        if (numberWeek != 0) {
            monday = monday.plusDays(numberWeek * DAYS_IN_WEEK);
        }
        return monday;
    }

    public static LocalDate findSunday(int numberWeek) {
        LocalDate today = LocalDate.now();
        LocalDate sunday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        if (numberWeek != 0) {
            sunday = sunday.plusDays(numberWeek * DAYS_IN_WEEK);
        }
        return sunday;
    }

    public static int fromStringToIntNumberWeek(String numberWeek) {
        int week = 0;
        if (CommonValidator.isPositiveInteger(numberWeek)) {
            week = Integer.parseInt(numberWeek);
        }
        return week;
    }
}