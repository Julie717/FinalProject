package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.ClientSchedule;
import com.buyalskaya.fitclub.model.entity.Schedule;
import com.buyalskaya.fitclub.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Schedule service.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public interface ScheduleService {
    /**
     * Find schedule week list.
     * Is used to find schedule of the special number of week.
     * If number of week = 0 than it's a current week.
     * If number of week = 1 than it's a next week and so on.
     * If schedule isn't found, it returns an empty list.
     *
     * @param numberWeek the number week
     * @param user       the user
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Schedule> findScheduleWeek(String numberWeek, User user) throws ServiceException;

    /**
     * Has schedule next week boolean.
     * Is used to check existence schedule in the next week in database
     *
     * @param numberWeek the number week
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean hasScheduleNextWeek(String numberWeek) throws ServiceException;

    /**
     * Find client schedule list.
     * Is used to find active client's schedule
     * (those workouts that haven't passed yet)
     *
     * @param idClient the id client
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ClientSchedule> findClientSchedule(int idClient) throws ServiceException;

    /**
     * Find instructor schedule list.
     * Is used to find active instructor's schedule
     * (those workouts that haven't passed yet)
     *
     * @param idInstructor the id instructor
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ClientSchedule> findInstructorSchedule(int idInstructor) throws ServiceException;

    /**
     * Subscribe client boolean.
     * Is used to subscribe client to a workout
     *
     * @param idUser     the id user
     * @param idSchedule the id schedule
     * @param subscribe  the subscribe
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean subscribeClient(int idUser, String idSchedule, String subscribe) throws ServiceException;

    /**
     * Find one schedule optional.
     * Is used to find information about one workout in schedule
     *
     * @param idSchedule the id schedule
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Schedule> findOneSchedule(String idSchedule) throws ServiceException;

    /**
     * Delete workout from schedule boolean.
     * Is used to delete workout from schedule
     *
     * @param idSchedule the id schedule
     * @param locale     the locale
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteWorkoutFromSchedule(String idSchedule, String locale) throws ServiceException;

    /**
     * Update schedule parameters boolean.
     * Is used to change workout's parameters in schedule
     *
     * @param scheduleParameters the schedule parameters
     * @param locale             the locale
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateScheduleParameters(Map<String, String> scheduleParameters, String locale) throws ServiceException;

    /**
     * Add schedule boolean.
     * Is used to add a workout to schedule
     *
     * @param scheduleParameters the schedule parameters
     * @param locale             the locale
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addSchedule(Map<String, String> scheduleParameters, String locale) throws ServiceException;

    /**
     * Copy schedule of last week boolean.
     * Is used to copy all workouts from the last week to the next in the schedule
     *
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean copyScheduleOfLastWeek() throws ServiceException;
}