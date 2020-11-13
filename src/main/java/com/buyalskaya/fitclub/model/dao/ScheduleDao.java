package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleDao extends CommonDao <Schedule>{
    List<Schedule> findSchedule(LocalDate startDate, LocalDate endDate) throws DaoException;

    List<Schedule> findClientSchedule(int idClient) throws DaoException;

    List<Schedule> findInstructorSchedule(int idInstructor) throws DaoException;

    List<Schedule> findAllScheduleForClient(LocalDate startDate, LocalDate endDate, int idClient) throws DaoException;

    boolean subscribeClient(int idClient, int idSchedule) throws DaoException;

    boolean unsubscribeClient(int idClient, int idSchedule) throws DaoException;

    Optional<Schedule> findOneSchedule(int idSchedule) throws DaoException;

    boolean deleteWorkoutFromSchedule(int idSchedule) throws DaoException;

    boolean updateScheduleParameters(Schedule schedule) throws DaoException;

    boolean isFreeTime(int idHall, LocalDate startDate, LocalTime startTime, int idSchedule) throws DaoException;
}