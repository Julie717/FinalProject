package com.buyalskaya.fitclub.util;

import com.buyalskaya.fitclub.validator.CommonValidator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * The type Date time transformer.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class DateTimeTransformer {
    /**
     * The constant START_COUNTING.
     */
    public final static LocalDate START_COUNTING = LocalDate.of(1899, Month.DECEMBER, 30);
    private final static int TIME_DIVIDER = 100;
    private final static String DATE_PATTERN_RU = "dd.MM.yyyy";
    private final static String DATE_PATTERN_EN = "MM/dd/yyyy";
    private final static String LOCALE_EN = "en_US";
    private static final int DAYS_IN_WEEK = 7;

    private DateTimeTransformer() {
    }

    /**
     * From long to local date local date.
     * Is used to change date format from long (as date stores in database)
     * to LocalDate(as it uses in current project)
     *
     * @param date the date
     * @return the local date
     */
    public static LocalDate fromLongToLocalDate(long date) {
        return START_COUNTING.plusDays(date);
    }

    /**
     * From local date to long long.
     * Is used to change date format from LocalDate(as it uses in current project)
     * to long (as date stores in database)
     *
     * @param date the date
     * @return the long
     */
    public static long fromLocalDateToLong(LocalDate date) {
        return date.toEpochDay() - START_COUNTING.toEpochDay();
    }

    /**
     * From long to local time local time.
     * Is used to change time format from long (as time stores in database)
     * to LocalTime(as it uses in current project)
     *
     * @param time the time
     * @return the local time
     */
    public static LocalTime fromLongToLocalTime(long time) {
        return LocalTime.of((int) time / TIME_DIVIDER, (int) time % TIME_DIVIDER);
    }

    /**
     * From local time to long long.
     * Is used to change time format from LocalTime(as it uses in current project)
     * to long (as time stores in database)
     *
     * @param time the time
     * @return the long
     */
    public static long fromLocalTimeToLong(LocalTime time) {
        return time.getHour() * 100 + time.getMinute();
    }

    /**
     * From string to local date local date.
     * Is used to change date format from string (as it come from client)
     * to LocalDate(as it uses in current project)
     *
     * @param date   the date
     * @param locale the locale
     * @return the local date
     */
    public static LocalDate fromStringToLocalDate(String date, String locale) {
        LocalDate resultDate = null;
        if (date != null && !date.isEmpty()) {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(locale.equals(LOCALE_EN) ? DATE_PATTERN_EN : DATE_PATTERN_RU);
            resultDate = LocalDate.parse(date, formatter);
        }
        return resultDate;
    }

    /**
     * Find monday local date.
     * Is used to find monday on the number of week (input parameter).
     * If numberWeek=0 than this is a current week,
     * if numberWeek=1 than this is a next week and so on
     *
     * @param numberWeek the number week
     * @return the local date
     */
    public static LocalDate findMonday(int numberWeek) {
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        if (numberWeek != 0) {
            monday = monday.plusDays(numberWeek * DAYS_IN_WEEK);
        }
        return monday;
    }

    /**
     * Find sunday local date.
     * Is used to find sunday on the number of week (input parameter).
     * If numberWeek=0 than this is a current week,
     * if numberWeek=1 than this is a next week and so on
     *
     * @param numberWeek the number week
     * @return the local date
     */
    public static LocalDate findSunday(int numberWeek) {
        LocalDate today = LocalDate.now();
        LocalDate sunday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        if (numberWeek != 0) {
            sunday = sunday.plusDays(numberWeek * DAYS_IN_WEEK);
        }
        return sunday;
    }

    /**
     * From string to int number week int.
     * Is used to change number of week format from String to int
     *
     * @param numberWeek the number week
     * @return the int
     */
    public static int fromStringToIntNumberWeek(String numberWeek) {
        int week = 0;
        if (CommonValidator.isPositiveInteger(numberWeek)) {
            week = Integer.parseInt(numberWeek);
        }
        return week;
    }
}