package com.buyalskaya.fitclub.model.service.impl;

import com.buyalskaya.fitclub.controller.ParameterName;
import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.creator.ScheduleCreator;
import com.buyalskaya.fitclub.model.dao.*;
import com.buyalskaya.fitclub.model.entity.*;
import com.buyalskaya.fitclub.model.service.ScheduleService;
import com.buyalskaya.fitclub.util.DateTimeTransformer;
import com.buyalskaya.fitclub.util.MailCreator;
import com.buyalskaya.fitclub.validator.CommonValidator;
import com.buyalskaya.fitclub.validator.ScheduleValidator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ScheduleServiceImpl implements ScheduleService {
    private static final String TRUE = "true";
    private static final int IMPOSSIBLE_SCHEDULE_ID = -1;

    @Override
    public List<Schedule> findScheduleWeek(String numberWeek, User user) throws ServiceException {
        List<Schedule> schedules;
        int week = DateTimeTransformer.fromStringToIntNumberWeek(numberWeek);
        LocalDate startDate = LocalDate.now();
        if (week != 0) {
            startDate = DateTimeTransformer.findMonday(week);
        }
        LocalDate sunday = DateTimeTransformer.findSunday(week);
        try {
            ScheduleDao scheduleDao = DaoFactory.getInstance().getScheduleDao();
            if (user != null && user.getRole() == UserRole.CLIENT) {
                schedules = scheduleDao.findAllScheduleForClient(startDate, sunday, user.getIdUser());
            } else {
                schedules = scheduleDao.findSchedule(startDate, sunday);
            }
            schedules.sort(Comparator.comparing(Schedule::getNameHall)
                    .thenComparing(Schedule::getStartDate)
                    .thenComparing(Schedule::getStartTime));
        } catch (DaoException ex) {
            throw new ServiceException("Error during find week schedule", ex);
        }
        return schedules;
    }

    public boolean hasScheduleNextWeek(String numberWeek) throws ServiceException {
        boolean hasSchedule = false;
        int currentWeek = DateTimeTransformer.fromStringToIntNumberWeek(numberWeek);
        int nextWeek = currentWeek + 1;
        LocalDate monday = DateTimeTransformer.findMonday(nextWeek);
        LocalDate sunday = DateTimeTransformer.findSunday(nextWeek);
        try {
            ScheduleDao scheduleDao = DaoFactory.getInstance().getScheduleDao();
            List<Schedule> schedules = scheduleDao.findSchedule(monday, sunday);
            if (!schedules.isEmpty()) {
                hasSchedule = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Error during find week schedule", ex);
        }
        return hasSchedule;
    }

    @Override
    public List<ClientSchedule> findClientSchedule(int idClient) throws ServiceException {
        List<ClientSchedule> schedules;
        try {
            ScheduleDao scheduleDao = DaoFactory.getInstance().getScheduleDao();
            schedules = scheduleDao.findClientSchedule(idClient);
            schedules.sort(Comparator.comparing(Schedule::getNameHall)
                    .thenComparing(Schedule::getStartDate)
                    .thenComparing(Schedule::getStartTime));
        } catch (DaoException ex) {
            throw new ServiceException("Error during find client schedule", ex);
        }
        return schedules;
    }

    @Override
    public List<ClientSchedule> findInstructorSchedule(int idInstructor) throws ServiceException {
        List<ClientSchedule> schedules;
        try {
            ScheduleDao scheduleDao = DaoFactory.getInstance().getScheduleDao();
            schedules = scheduleDao.findInstructorSchedule(idInstructor);
            schedules.sort(Comparator.comparing(Schedule::getNameHall)
                    .thenComparing(Schedule::getStartDate)
                    .thenComparing(Schedule::getStartTime));
        } catch (DaoException ex) {
            throw new ServiceException("Error during find instructor schedule", ex);
        }
        return schedules;
    }

    @Override
    public boolean subscribeClient(int idUser, String idSchedule, String subscribe) throws ServiceException {
        boolean isChanged = false;
        if (CommonValidator.isIdValid(idSchedule)) {
            try {
                int correctIdSchedule = Integer.parseInt(idSchedule);
                ScheduleDao scheduleDao = DaoFactory.getInstance().getScheduleDao();
                if (subscribe.equalsIgnoreCase(TRUE)) {
                    isChanged = scheduleDao.unsubscribeClient(idUser, correctIdSchedule);
                } else {
                    isChanged = scheduleDao.subscribeClient(idUser, correctIdSchedule);
                }
            } catch (DaoException ex) {
                throw new ServiceException("Error during unsubscribing", ex);
            }
        }
        return isChanged;
    }

    @Override
    public Optional<Schedule> findOneSchedule(String idSchedule) throws ServiceException {
        Optional<Schedule> schedule = Optional.empty();
        if (CommonValidator.isIdValid(idSchedule)) {
            int idScheduleInt = Integer.parseInt(idSchedule);
            try {
                ScheduleDao scheduleDao = DaoFactory.getInstance().getScheduleDao();
                schedule = scheduleDao.findOneSchedule(idScheduleInt);
            } catch (DaoException ex) {
                throw new ServiceException("Error during find one schedule", ex);
            }
        }
        return schedule;
    }

    @Override
    public boolean deleteWorkoutFromSchedule(String idSchedule, String locale) throws ServiceException {
        boolean isDeleted = false;
        if (CommonValidator.isIdValid(idSchedule)) {
            int idScheduleInt = Integer.parseInt(idSchedule);
            try {
                UserDao userDao = DaoFactory.getInstance().getUserDao();
                ScheduleDao scheduleDao = DaoFactory.getInstance().getScheduleDao();
                List<Client> subscribedClients = userDao.findSubscribedClients(idScheduleInt);
                Optional<Schedule> schedule = scheduleDao.findOneSchedule(idScheduleInt);
                if (schedule.isPresent()) {
                    isDeleted = scheduleDao.deleteWorkoutFromSchedule(idScheduleInt);
                    if (isDeleted && !subscribedClients.isEmpty()) {
                        subscribedClients.forEach(c -> MailCreator.sendMailToCancelWorkout(locale, c.getEmail(),
                                c.getName(), schedule.get()));
                    }
                }
            } catch (DaoException ex) {
                throw new ServiceException("Error during delete workout in schedule", ex);
            }
        }
        return isDeleted;
    }

    @Override
    public boolean updateScheduleParameters(Map<String, String> scheduleParameters, String locale) throws ServiceException {
        boolean isUpdated = false;
        if (ScheduleValidator.isScheduleParametersValid(scheduleParameters, locale) &&
                CommonValidator.isIdValid(scheduleParameters.get(ParameterName.SCHEDULE_ID))) {
            Schedule schedule = ScheduleCreator.createSchedule(scheduleParameters, locale);
            try {
                ScheduleDao scheduleDao = DaoFactory.getInstance().getScheduleDao();
                Optional<Schedule> currentScheduleOpt = scheduleDao.findScheduleById(schedule.getIdSchedule());
                if (currentScheduleOpt.isPresent()) {
                    Schedule currentSchedule = currentScheduleOpt.get();
                    if (isCorrectParametersForUpdateSchedule(schedule, currentSchedule)) {
                        int duration = schedule.getDuration();
                        LocalTime endTime = schedule.getStartTime().plusMinutes(duration);
                        boolean isFree = scheduleDao.isFreeTime(schedule.getIdHall(), schedule.getStartDate(),
                                schedule.getStartTime(), endTime, schedule.getIdSchedule());
                        if (isFree) {
                            isUpdated = scheduleDao.updateScheduleParameters(schedule);
                            if (isUpdated && (!schedule.getStartDate().equals(currentSchedule.getStartDate()) ||
                                    !schedule.getStartTime().equals(currentSchedule.getStartTime()))) {
                                UserDao userDao = DaoFactory.getInstance().getUserDao();
                                List<Client> subscribedClients = userDao.findSubscribedClients(schedule.getIdSchedule());
                                if (!subscribedClients.isEmpty()) {
                                    subscribedClients.forEach(c -> MailCreator.sendMailToUpdateWorkout(locale,
                                            c.getEmail(), c.getName(), schedule, currentSchedule));
                                }
                            }
                        }
                    }
                }
            } catch (
                    DaoException ex) {
                throw new ServiceException("Error during update schedule parameters", ex);
            }
        }
        return isUpdated;
    }

    private boolean isCorrectParametersForUpdateSchedule(Schedule schedule, Schedule currentSchedule)
            throws DaoException {
        ScheduleDao scheduleDao = DaoFactory.getInstance().getScheduleDao();
        boolean isCorrect = !schedule.equals(currentSchedule);
        int occupiedPlaces = scheduleDao.findAmountOfOccupiedPlaces(schedule.getIdSchedule());
        isCorrect = isCorrect && (occupiedPlaces <= schedule.getCapacity());
        WorkoutDao workoutDao = DaoFactory.getInstance().getWorkoutDao();
        isCorrect = isCorrect && workoutDao.isWorkoutExist(schedule.getWorkout().getIdWorkout());
        HallDao hallDao = DaoFactory.getInstance().getHallDao();
        isCorrect = isCorrect && hallDao.isHallExist(schedule.getIdHall());
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        isCorrect = isCorrect && userDao.isInstructorExist(schedule.getIdInstructor());
        return isCorrect;
    }

    @Override
    public boolean addSchedule(Map<String, String> scheduleParameters, String locale) throws ServiceException {
        boolean isAdded = false;
        if (ScheduleValidator.isScheduleParametersValid(scheduleParameters, locale)) {
            Schedule schedule = ScheduleCreator.createSchedule(scheduleParameters, locale);
            try {
                WorkoutDao workoutDao = DaoFactory.getInstance().getWorkoutDao();
                HallDao hallDao = DaoFactory.getInstance().getHallDao();
                UserDao userDao = DaoFactory.getInstance().getUserDao();
                ScheduleDao scheduleDao = DaoFactory.getInstance().getScheduleDao();
                boolean isWorkoutExist = workoutDao.isWorkoutExist(schedule.getWorkout().getIdWorkout());
                boolean isHallExist = hallDao.isHallExist(schedule.getIdHall());
                boolean isInstructorExist = userDao.isInstructorExist(schedule.getIdInstructor());
                if (isWorkoutExist && isHallExist && isInstructorExist) {
                    int duration = schedule.getDuration();
                    LocalTime endTime = schedule.getStartTime().plusMinutes(duration);
                    boolean isFree = scheduleDao.isFreeTime(schedule.getIdHall(), schedule.getStartDate(),
                            schedule.getStartTime(), endTime, IMPOSSIBLE_SCHEDULE_ID);
                    if (isFree) {
                        isAdded = DaoFactory.getInstance().getScheduleDao()
                                .add(schedule);
                    }
                }
            } catch (DaoException ex) {
                throw new ServiceException("Error during add schedule", ex);
            }
        }
        return isAdded;
    }

    @Override
    public boolean copyScheduleOfLastWeek() throws ServiceException {
        boolean isAdded = false;
        try {
            ScheduleDao scheduleDao = DaoFactory.getInstance().getScheduleDao();
            LocalDate lastDate = scheduleDao.findLastDateInSchedule();
            if (lastDate != null) {
                LocalDate monday = lastDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                LocalDate sunday = lastDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                isAdded = scheduleDao.copyScheduleToNextWeek(monday, sunday);
            }
        } catch (DaoException ex) {
            throw new ServiceException("Error during copy schedule", ex);
        }
        return isAdded;
    }
}