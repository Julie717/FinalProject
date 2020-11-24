package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.entity.ClientSchedule;
import com.buyalskaya.fitclub.model.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * The interface Schedule dao.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public interface ScheduleDao extends CommonDao<Schedule> {
    /**
     * Find schedule by id optional.
     * Is used to find information about workout in schedule by id schedule
     *
     * @param idSchedule the id schedule
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Schedule> findScheduleById(int idSchedule) throws DaoException;

    /**
     * Find schedule list.
     * Is used to find information about workouts in schedule from startDate to endDate
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Schedule> findSchedule(LocalDate startDate, LocalDate endDate) throws DaoException;

    /**
     * Find client schedule list.
     * Is used to find information about client's workouts in schedule by client's id
     *
     * @param idClient the id client
     * @return the list
     * @throws DaoException the dao exception
     */
    List<ClientSchedule> findClientSchedule(int idClient) throws DaoException;

    /**
     * Find instructor schedule list.
     * Is used to find information about workouts that instructor conducts
     *
     * @param idInstructor the id instructor
     * @return the list
     * @throws DaoException the dao exception
     */
    List<ClientSchedule> findInstructorSchedule(int idInstructor) throws DaoException;

    /**
     * Find all schedule for client list.
     * Is used to find information about client's workouts in schedule from startDate to endDate
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param idClient  the id client
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Schedule> findAllScheduleForClient(LocalDate startDate, LocalDate endDate, int idClient) throws DaoException;

    /**
     * Subscribe client boolean.
     * Is used to subscribe client to the workout in schedule
     *
     * @param idClient   the id client
     * @param idSchedule the id schedule
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean subscribeClient(int idClient, int idSchedule) throws DaoException;

    /**
     * Unsubscribe client boolean.
     * Is used to unsubscribe client from the workout in schedule
     *
     * @param idClient   the id client
     * @param idSchedule the id schedule
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean unsubscribeClient(int idClient, int idSchedule) throws DaoException;

    /**
     * Find one schedule optional.
     * Is used to find information about one workout in schedule
     *
     * @param idSchedule the id schedule
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Schedule> findOneSchedule(int idSchedule) throws DaoException;

    /**
     * Delete workout from schedule boolean.
     * Is used to delete workout from schedule
     *
     * @param idSchedule the id schedule
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteWorkoutFromSchedule(int idSchedule) throws DaoException;

    /**
     * Update schedule parameters boolean.
     * Is used to update workout's parameters in schedule
     *
     * @param schedule the schedule
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateScheduleParameters(Schedule schedule) throws DaoException;

    /**
     * Is free time boolean.
     * Is used to check freedom of the hall in current date and time
     * int idSchedule is a parameter that uses to show that is used to update schedule with current id,
     * if this method is used before add of schedule idSchedule can be any impossible schedule id (for example -1)
     *
     * @param idHall     the id hall
     * @param startDate  the start date
     * @param startTime  the start time
     * @param endTime    the end time
     * @param idSchedule the id schedule
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isFreeTime(int idHall, LocalDate startDate, LocalTime startTime, LocalTime endTime, int idSchedule) throws DaoException;

    /**
     * Find amount of occupied places int.
     * Is used to find amount of occupied places by clients for current workout in schedule
     *
     * @param idSchedule the id schedule
     * @return the int
     * @throws DaoException the dao exception
     */
    int findAmountOfOccupiedPlaces(int idSchedule) throws DaoException;

    /**
     * Find last date in schedule local date.
     * Is used to find last date (stored in database) in schedule
     *
     * @return the local date
     * @throws DaoException the dao exception
     */
    LocalDate findLastDateInSchedule() throws DaoException;

    /**
     * Copy schedule to next week boolean.
     * Is used to copy schedule from the last stored week to the next
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean copyScheduleToNextWeek(LocalDate startDate, LocalDate endDate) throws DaoException;
}