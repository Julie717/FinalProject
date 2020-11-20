package com.buyalskaya.fitclub.model.service;

import com.buyalskaya.fitclub.exception.ServiceException;
import com.buyalskaya.fitclub.model.entity.Workout;

import java.util.List;
import java.util.Map;

/**
 * The interface Workout service.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public interface WorkoutService {
    /**
     * Find by level list.
     * Is used to find workouts of a current level
     *
     * @param level the level
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Workout> findByLevel(Workout.Level level) throws ServiceException;

    /**
     * Find name workouts map.
     * Is used to find name's workouts. It returns a map with ids and names of workouts
     *
     * @return the map
     * @throws ServiceException the service exception
     */
    Map<Integer, String> findNameWorkouts() throws ServiceException;

    /**
     * Find all workouts list.
     * Is used to find all workouts
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Workout> findAllWorkouts() throws ServiceException;
}