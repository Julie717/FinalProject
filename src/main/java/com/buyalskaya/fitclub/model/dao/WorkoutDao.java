package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.entity.Workout;

import java.util.List;
import java.util.Map;

public interface WorkoutDao extends CommonDao<Workout> {
    List<Workout> findByLevel(Workout.Level level) throws DaoException;

    Map<Integer, String> findNameWorkouts() throws DaoException;

    boolean isWorkoutExist(int idWorkout) throws DaoException;
}