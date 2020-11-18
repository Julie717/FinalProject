package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.entity.Workout;

import java.util.List;
import java.util.Map;

/**
 * The interface Workout dao.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public interface WorkoutDao extends CommonDao<Workout> {
    /**
     * Find by level list.
     * Is used to find all workouts of a current level
     *
     * @param level the level
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Workout> findByLevel(Workout.Level level) throws DaoException;

    /**
     * Find name workouts map.
     * Is used to find all workout's names.
     * Returns a map with workout's ids and names
     *
     * @return the map
     * @throws DaoException the dao exception
     */
    Map<Integer, String> findNameWorkouts() throws DaoException;

    /**
     * Is workout exist boolean.
     * Is used to check existence of a workout with current id.
     * If workout exists it returns true, else - false
     *
     * @param idWorkout the id workout
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isWorkoutExist(int idWorkout) throws DaoException;
}