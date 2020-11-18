package com.buyalskaya.fitclub.util;

import com.buyalskaya.fitclub.model.entity.Schedule;
import com.buyalskaya.fitclub.model.entity.UserRole;
import com.buyalskaya.fitclub.model.entity.UserStatus;
import com.buyalskaya.fitclub.model.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CommonUtil {
    private static final String DASH = "-";
    private static final String SPACE = " ";
    private static final String EMPTY = "";

    private CommonUtil() {
    }

    public static String transformPhone(String phone) {
        if (phone != null && !phone.isEmpty()) {
            phone = phone.replaceAll(DASH, EMPTY);
            phone = phone.replaceAll(SPACE, EMPTY);
        }
        return phone;
    }


    public static Set<String> findNameHalls(List<? extends Schedule> schedules) {
        Set<String> nameHalls = new TreeSet<>();
        schedules.stream().forEach(s -> nameHalls.add(s.getNameHall()));
        return nameHalls;
    }

    private static Set<LocalDate> findDatesInOneHall(List<? extends Schedule> schedules, String nameHall) {
        Set<LocalDate> possibleDate = new TreeSet<>();
        schedules.stream()
                .filter(s -> s.getNameHall().equals(nameHall))
                .forEach(s -> possibleDate.add(s.getStartDate()));
        return possibleDate;
    }

    private static Set<LocalTime> findTimesInOneHall(List<? extends Schedule> schedules, String nameHall) {
        Set<LocalTime> possibleTime = new TreeSet<>();
        schedules.stream()
                .filter(s -> s.getNameHall().equals(nameHall))
                .forEach(s -> possibleTime.add(s.getStartTime()));
        return possibleTime;
    }

    public static Map<String, Set<LocalTime>> findTimesInEachHall(List<? extends Schedule> schedules) {
        Set<String> nameHalls = CommonUtil.findNameHalls(schedules);
        Map<String, Set<LocalTime>> hallsSchedule = new HashMap<>();
        Set<LocalTime> possibleTime;
        for (String hall : nameHalls) {
            possibleTime = findTimesInOneHall(schedules, hall);
            hallsSchedule.put(hall, possibleTime);
        }
        return hallsSchedule;
    }

    public static Map<String, Set<LocalDate>> findDatesInEachHall(List<? extends Schedule> schedules) {
        Set<String> nameHalls = CommonUtil.findNameHalls(schedules);
        Map<String, Set<LocalDate>> hallsSchedule = new HashMap<>();
        Set<LocalDate> possibleDate;
        for (String hall : nameHalls) {
            possibleDate = findDatesInOneHall(schedules, hall);
            hallsSchedule.put(hall, possibleDate);
        }
        return hallsSchedule;
    }

    public static UserRole findRole(int idRole) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.getUserRoleId() == idRole)
                .findFirst()
                .orElse(UserRole.CLIENT);
    }

    public static UserStatus findStatus(int idStatus) {
        return Arrays.stream(UserStatus.values())
                .filter(r -> r.getUserStatusId() == idStatus)
                .findFirst()
                .orElse(UserStatus.UNCONFIRMED);
    }
}