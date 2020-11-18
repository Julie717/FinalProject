package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.ClientSchedule;
import com.buyalskaya.fitclub.model.entity.Schedule;
import com.buyalskaya.fitclub.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ScheduleService {
    List<Schedule> findScheduleWeek(String numberWeek, User user) throws ServiceException;

    boolean hasScheduleNextWeek(String numberWeek) throws ServiceException;

    List<ClientSchedule> findClientSchedule(int idClient) throws ServiceException;

    List<ClientSchedule> findInstructorSchedule(int idInstructor) throws ServiceException;

    boolean subscribeClient(int idUser, String idSchedule, String subscribe) throws ServiceException;

    Optional<Schedule> findOneSchedule(String idSchedule) throws ServiceException;

    boolean deleteWorkoutFromSchedule(String idSchedule, String locale) throws ServiceException;

    boolean updateScheduleParameters(Map<String, String> scheduleParameters, String locale) throws ServiceException;

    boolean addSchedule(Map<String, String> scheduleParameters, String locale) throws ServiceException;
}