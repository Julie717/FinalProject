package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.Workout;

import java.util.List;
import java.util.Map;

public interface WorkoutService {
    List<Workout> findAllWorkouts() throws ServiceException;

    List<Workout> findByLevel(Workout.Level level) throws ServiceException;
    Map<Integer, String> findNameWorkouts() throws ServiceException;
}