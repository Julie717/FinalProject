package com.buyalskaya.fitclub.model.creator;

import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.model.entity.*;
import com.buyalskaya.fitclub.util.DateTimeTransformer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

/**
 * The type Schedule creator.
 * Is used in services for creating schedules that provide transfer of
 * parameters to dao with correct types
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class ScheduleCreator {
    private ScheduleCreator() {
    }

    /**
     * Create schedule schedule.
     *
     * @param scheduleParameters the schedule parameters
     * @param locale             the locale
     * @return the schedule
     */
    public static Schedule createSchedule(Map<String, String> scheduleParameters, String locale) {
        Schedule schedule = new Schedule();
        String idSchedule = scheduleParameters.get(ParameterName.SCHEDULE_ID);
        if (idSchedule != null) {
            schedule.setIdSchedule(Integer.parseInt(scheduleParameters.get(ParameterName.SCHEDULE_ID)));
        }
        int idWorkout = Integer.parseInt(scheduleParameters.get(ParameterName.SCHEDULE_WORKOUT_ID));
        schedule.setWorkout(new Workout(idWorkout));
        int idHall = Integer.parseInt(scheduleParameters.get(ParameterName.SCHEDULE_HALL_ID));
        schedule.setIdHall(idHall);
        int idInstructor = Integer.parseInt(scheduleParameters.get(ParameterName.SCHEDULE_INSTRUCTOR_ID));
        schedule.setIdInstructor(idInstructor);
        LocalDate startDate = DateTimeTransformer.fromStringToLocalDate(
                scheduleParameters.get(ParameterName.SCHEDULE_START_DATE), locale);
        schedule.setStartDate(startDate);
        LocalTime startTime = LocalTime.parse(scheduleParameters.get(ParameterName.SCHEDULE_START_TIME));
        schedule.setStartTime(startTime);
        int duration = Integer.parseInt(scheduleParameters.get(ParameterName.SCHEDULE_DURATION));
        schedule.setDuration(duration);
        int capacity = Integer.parseInt(scheduleParameters.get(ParameterName.SCHEDULE_CAPACITY));
        schedule.setCapacity(capacity);
        return schedule;
    }
}