package com.buyalskaya.fitclub.model.dao.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.connection.ConnectionPool;
import com.buyalskaya.fitclub.model.dao.AddAttributesFromResultSet;
import com.buyalskaya.fitclub.model.dao.ColumnName;
import com.buyalskaya.fitclub.model.dao.SqlQuery;
import com.buyalskaya.fitclub.model.dao.WorkoutDao;
import com.buyalskaya.fitclub.model.entity.Workout;

import java.sql.*;
import java.util.*;

public class WorkoutDaoImpl implements WorkoutDao {
    @Override
    public boolean add(Workout workout) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Workout> findAll() throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Statement statement = null;
        List<Workout> workouts = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_WORKOUTS);
            Workout workout;
            while (resultSet.next()) {
                workout = new Workout();
                AddAttributesFromResultSet.addWorkoutAttributes(workout, resultSet);
                workouts.add(workout);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(statement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return workouts;
    }

    @Override
    public List<Workout> findByLevel(Workout.Level level) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        List<Workout> workouts = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_WORKOUT_BY_LEVEL);
            preparedStatement.setInt(1, level.getIdLevel());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Workout workout = new Workout();
                AddAttributesFromResultSet.addWorkoutAttributes(workout, resultSet);
                workouts.add(workout);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return workouts;
    }

    @Override
    public Map<Integer, String> findNameWorkouts() throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Statement statement = null;
        Map<Integer, String> workoutNames = new HashMap<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_WORKOUT_NAMES);
            int workoutId;
            String workoutName;
            while (resultSet.next()) {
                workoutId = resultSet.getInt(ColumnName.WORKOUTS_ID);
                workoutName = resultSet.getString(ColumnName.WORKOUTS_NAME);
                workoutNames.put(workoutId, workoutName);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(statement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return workoutNames;
    }

    @Override
    public boolean isWorkoutExist(int idWorkout) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        boolean isExist = false;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.IS_WORKOUT_EXIST);
            preparedStatement.setInt(1, idWorkout);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExist = resultSet.getInt(ColumnName.EXIST_WORKOUT) > 0;
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isExist;
    }
}