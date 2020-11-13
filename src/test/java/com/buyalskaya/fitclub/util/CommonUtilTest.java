package com.buyalskaya.fitclub.util;

import com.buyalskaya.fitclub.model.entity.Schedule;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.testng.Assert.assertEquals;

public class CommonUtilTest {
    @Test
    public void transformPhoneTest() {
        String phone = "+375-29-987-25-21";
        String expected = "+375299872521";
        String actual = CommonUtil.transformPhone(phone);
        assertEquals(actual, expected);
    }

    @Test
    public void findNameHallsTest() {
        List<Schedule> schedules = new ArrayList<>();
        Schedule schedule = new Schedule();
        schedule.setNameHall("Big");
        schedule.setIdSchedule(1);
        schedules.add(schedule);
        schedule = new Schedule();
        schedule.setNameHall("Small");
        schedule.setIdSchedule(2);
        schedules.add(schedule);
        schedule = new Schedule();
        schedule.setNameHall("Big");
        schedule.setIdSchedule(3);
        schedules.add(schedule);
        Set<String> expected = new TreeSet<>();
        expected.add("Big");
        expected.add("Small");
        Set<String> actual = CommonUtil.findNameHalls(schedules);
        assertEquals(actual, expected);
    }

    @Test
    public void findDatesInEachHall() {
        List<Schedule> schedules = new ArrayList<>();
        Schedule schedule = new Schedule();
        schedule.setStartDate(LocalDate.of(2020,11,12));
        schedule.setIdSchedule(1);
        schedule.setNameHall("Big");
        schedules.add(schedule);
        schedule = new Schedule();
        schedule.setStartDate(LocalDate.of(2020,11,14));
        schedule.setIdSchedule(2);
        schedule.setNameHall("Big");
        schedules.add(schedule);
        schedule = new Schedule();
        schedule.setStartDate(LocalDate.of(2020,11,12));
        schedule.setIdSchedule(3);
        schedule.setNameHall("Big");
        schedule = new Schedule();
        schedule.setStartDate(LocalDate.of(2020,11,25));
        schedule.setIdSchedule(3);
        schedule.setNameHall("Small");
        schedules.add(schedule);
        Set<LocalDate> bigHall =new TreeSet<>();
        bigHall.add(LocalDate.of(2020,11,12));
        bigHall.add(LocalDate.of(2020,11,14));
        Set<LocalDate> smallHall =new TreeSet<>();
        smallHall.add(LocalDate.of(2020,11,25));
        Map<String, Set<LocalDate>> expected = new HashMap<>();
        expected.put("Big",bigHall);
        expected.put("Small",smallHall);
        Map<String, Set<LocalDate>> actual = CommonUtil.findDatesInEachHall(schedules);
        assertEquals(actual, expected);
    }

    @Test
    public void findTimesInEachHall() {
        List<Schedule> schedules = new ArrayList<>();
        Schedule schedule = new Schedule();
        schedule.setStartTime(LocalTime.of(18,0));
        schedule.setIdSchedule(1);
        schedule.setNameHall("Big");
        schedules.add(schedule);
        schedule = new Schedule();
        schedule.setStartTime(LocalTime.of(9,0));
        schedule.setIdSchedule(2);
        schedule.setNameHall("Big");
        schedules.add(schedule);
        schedule = new Schedule();
        schedule.setStartTime(LocalTime.of(18,0));
        schedule.setIdSchedule(3);
        schedule.setNameHall("Big");
        schedule = new Schedule();
        schedule.setStartTime(LocalTime.of(20,0));
        schedule.setIdSchedule(3);
        schedule.setNameHall("Small");
        schedules.add(schedule);
        Set<LocalTime> bigHall =new TreeSet<>();
        bigHall.add(LocalTime.of(9,0));
        bigHall.add(LocalTime.of(18,0));
        Set<LocalTime> smallHall =new TreeSet<>();
        smallHall.add(LocalTime.of(20,0));
        Map<String, Set<LocalTime>> expected = new HashMap<>();
        expected.put("Big",bigHall);
        expected.put("Small",smallHall);
        Map<String, Set<LocalTime>> actual = CommonUtil.findTimesInEachHall(schedules);
        assertEquals(actual, expected);
    }
}