package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.entity.ClientSchedule;
import com.buyalskaya.fitclub.model.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleDao extends CommonDao<Schedule> {
    Optional<Schedule> findScheduleById(int idSchedule) throws DaoException;

    List<Schedule> findSchedule(LocalDate startDate, LocalDate endDate) throws DaoException;

    List<ClientSchedule> findClientSchedule(int idClient) throws DaoException;

    List<ClientSchedule> findInstructorSchedule(int idInstructor) throws DaoException;

    List<Schedule> findAllScheduleForClient(LocalDate startDate, LocalDate endDate, int idClient) throws DaoException;

    boolean subscribeClient(int idClient, int idSchedule) throws DaoException;

    boolean unsubscribeClient(int idClient, int idSchedule) throws DaoException;

    Optional<Schedule> findOneSchedule(int idSchedule) throws DaoException;

    boolean deleteWorkoutFromSchedule(int idSchedule) throws DaoException;

    boolean updateScheduleParameters(Schedule schedule) throws DaoException;

    boolean isFreeTime(int idHall, LocalDate startDate, LocalTime startTime, LocalTime endTime, int idSchedule) throws DaoException;

    int findAmountOfOccupiedPlaces(int idSchedule) throws DaoException;
}