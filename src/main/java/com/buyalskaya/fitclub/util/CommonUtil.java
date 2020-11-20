package com.buyalskaya.fitclub.util;

import com.buyalskaya.fitclub.model.entity.Schedule;
import com.buyalskaya.fitclub.model.entity.UserRole;
import com.buyalskaya.fitclub.model.entity.UserStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * The type Common util.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class CommonUtil {
    private static final String DASH = "-";
    private static final String SPACE = " ";
    private static final String EMPTY = "";

    private CommonUtil() {
    }

    /**
     * Transform phone string.
     * Is used to change format of phone number, that was transferred from client,
     * to the appropriate format that uses to store phone number in database
     *
     * @param phone the phone
     * @return the string
     */
    public static String transformPhone(String phone) {
        if (phone != null && !phone.isEmpty()) {
            phone = phone.replaceAll(DASH, EMPTY);
            phone = phone.replaceAll(SPACE, EMPTY);
        }
        return phone;
    }


    /**
     * Find name halls set.
     * Is used to distinguish unique hall's names from workouts in schedule
     *
     * @param schedules the schedules
     * @return the set
     */
    public static Set<String> findNameHalls(List<? extends Schedule> schedules) {
        Set<String> nameHalls = new TreeSet<>();
        schedules.forEach(s -> nameHalls.add(s.getNameHall()));
        return nameHalls;
    }

    /**
     * Find dates in one hall set.
     * Is used to distinguish unique dates of training in one hall
     *
     * @param schedules the schedules
     * @param nameHall  the name of a hall
     * @return the set
     */
    private static Set<LocalDate> findDatesInOneHall(List<? extends Schedule> schedules, String nameHall) {
        Set<LocalDate> possibleDate = new TreeSet<>();
        schedules.stream()
                .filter(s -> s.getNameHall().equals(nameHall))
                .forEach(s -> possibleDate.add(s.getStartDate()));
        return possibleDate;
    }

    /**
     * Find times in one hall set.
     * Is used to distinguish unique time of training in one hall
     *
     * @param schedules the schedules
     * @param nameHall  the name of a hall
     * @return the set
     */
    private static Set<LocalTime> findTimesInOneHall(List<? extends Schedule> schedules, String nameHall) {
        Set<LocalTime> possibleTime = new TreeSet<>();
        schedules.stream()
                .filter(s -> s.getNameHall().equals(nameHall))
                .forEach(s -> possibleTime.add(s.getStartTime()));
        return possibleTime;
    }

    /**
     * Find times in each hall map.
     * Is used to receive a correspondence between hall's names and
     * time of workouts beginning in each of halls
     *
     * @param schedules the schedules
     * @return the map
     */
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

    /**
     * Find dates in each hall map.
     * Is used to receive a correspondence between hall's names and
     * dates of workouts beginning in each of halls
     *
     * @param schedules the schedules
     * @return the map
     */
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

    /**
     * Find role user role.
     * Is used to receive a user's role by role's id
     *
     * @param idRole the id role
     * @return the user role
     */
    public static UserRole findRole(int idRole) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.getUserRoleId() == idRole)
                .findFirst()
                .orElse(UserRole.CLIENT);
    }

    /**
     * Find status user status.
     * Is used to receive a user's status by status's id
     *
     * @param idStatus the id status
     * @return the user status
     */
    public static UserStatus findStatus(int idStatus) {
        return Arrays.stream(UserStatus.values())
                .filter(r -> r.getUserStatusId() == idStatus)
                .findFirst()
                .orElse(UserStatus.UNCONFIRMED);
    }
}