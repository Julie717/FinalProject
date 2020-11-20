package com.buyalskaya.fitclub.model.service.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.dao.DaoFactory;
import com.buyalskaya.fitclub.model.dao.WorkoutDao;
import com.buyalskaya.fitclub.model.entity.Workout;
import com.buyalskaya.fitclub.model.service.WorkoutService;

import java.util.List;
import java.util.Map;

public class WorkoutServiceImpl implements WorkoutService {

    @Override
    public List<Workout> findByLevel(Workout.Level level) throws ServiceException {
        List<Workout> workouts;
        try {
            WorkoutDao workoutDao = DaoFactory.getInstance().getWorkoutDao();
            workouts = workoutDao.findByLevel(level);
        } catch (DaoException ex) {
            throw new ServiceException("Error during find workouts", ex);
        }
        return workouts;
    }

    @Override
    public Map<Integer, String> findNameWorkouts() throws ServiceException {
        Map<Integer, String> workouts;
        try {
            WorkoutDao workoutDao = DaoFactory.getInstance().getWorkoutDao();
            workouts = workoutDao.findNameWorkouts();
        } catch (DaoException ex) {
            throw new ServiceException("Error during find instructors", ex);
        }
        return workouts;
    }

    @Override
    public List<Workout> findAllWorkouts() throws ServiceException {
        List<Workout> workouts;
        try {
            WorkoutDao workoutDao = DaoFactory.getInstance().getWorkoutDao();
            workouts = workoutDao.findAll();
        } catch (DaoException ex) {
            throw new ServiceException("Error during find workouts", ex);
        }
        return workouts;
    }
}