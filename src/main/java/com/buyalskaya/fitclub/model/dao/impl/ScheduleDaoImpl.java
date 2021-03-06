package com.buyalskaya.fitclub.model.dao.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.connection.ConnectionPool;
import com.buyalskaya.fitclub.model.dao.AddAttributesFromResultSet;
import com.buyalskaya.fitclub.model.dao.ColumnName;
import com.buyalskaya.fitclub.model.dao.ScheduleDao;
import com.buyalskaya.fitclub.util.DateTimeTransformer;
import com.buyalskaya.fitclub.model.dao.SqlQuery;
import com.buyalskaya.fitclub.model.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScheduleDaoImpl implements ScheduleDao {

    @Override
    public List<Schedule> findSchedule(LocalDate startDate, LocalDate endDate) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        List<Schedule> schedules = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_SCHEDULE);
            preparedStatement.setLong(1, DateTimeTransformer.fromLocalDateToLong(startDate));
            preparedStatement.setLong(2, DateTimeTransformer.fromLocalDateToLong(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            Schedule schedule;
            while (resultSet.next()) {
                schedule = new Schedule();
                AddAttributesFromResultSet.addAdditionalScheduleAttributes(schedule, resultSet);
                schedules.add(schedule);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return schedules;
    }

    @Override
    public Optional<Schedule> findScheduleById(int idSchedule) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        Optional<Schedule> resultSchedule = Optional.empty();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_SCHEDULE_BY_ID);
            preparedStatement.setInt(1, idSchedule);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                AddAttributesFromResultSet.addScheduleAttributes(schedule, resultSet);
                resultSchedule = Optional.of(schedule);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return resultSchedule;
    }

    @Override
    public List<Schedule> findAllScheduleForClient(LocalDate startDate, LocalDate endDate, int idClient) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        List<Schedule> schedules = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_ALL_SCHEDULE_FOR_CLIENT);
            preparedStatement.setInt(1, idClient);
            preparedStatement.setLong(2, DateTimeTransformer.fromLocalDateToLong(startDate));
            preparedStatement.setLong(3, DateTimeTransformer.fromLocalDateToLong(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            ClientSchedule schedule;
            while (resultSet.next()) {
                schedule = new ClientSchedule();
                AddAttributesFromResultSet.addClientScheduleAttributes(schedule, resultSet);
                schedules.add(schedule);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return schedules;
    }

    @Override
    public List<ClientSchedule> findClientSchedule(int idClient) throws DaoException {
        long today = DateTimeTransformer.fromLocalDateToLong(LocalDate.now());
        long timeNow = DateTimeTransformer.fromLocalTimeToLong(LocalTime.now());
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        List<ClientSchedule> schedules = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_CLIENT_SCHEDULE);
            preparedStatement.setInt(1, idClient);
            preparedStatement.setLong(2, today);
            preparedStatement.setInt(3, idClient);
            preparedStatement.setLong(4, today);
            preparedStatement.setLong(5, timeNow);
            ResultSet resultSet = preparedStatement.executeQuery();
            ClientSchedule schedule;
            while (resultSet.next()) {
                schedule = new ClientSchedule();
                AddAttributesFromResultSet.addAdditionalScheduleAttributes(schedule, resultSet);
                schedule.setSubscribed(true);
                schedules.add(schedule);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return schedules;
    }

    @Override
    public List<ClientSchedule> findInstructorSchedule(int idInstructor) throws DaoException {
        long today = DateTimeTransformer.fromLocalDateToLong(LocalDate.now());
        long timeNow = DateTimeTransformer.fromLocalTimeToLong(LocalTime.now());
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        List<ClientSchedule> schedules = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_INSTRUCTOR_SCHEDULE);
            preparedStatement.setInt(1, idInstructor);
            preparedStatement.setLong(2, today);
            preparedStatement.setInt(3, idInstructor);
            preparedStatement.setLong(4, today);
            preparedStatement.setLong(5, timeNow);
            ResultSet resultSet = preparedStatement.executeQuery();
            ClientSchedule schedule;
            while (resultSet.next()) {
                schedule = new ClientSchedule();
                AddAttributesFromResultSet.addAdditionalScheduleAttributes(schedule, resultSet);
                schedule.setSubscribed(true);
                schedules.add(schedule);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return schedules;
    }

    @Override
    public boolean unsubscribeClient(int idClient, int idSchedule) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isDeleted;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.UNSUBSCRIBE_CLIENT);
            preparedStatement.setInt(1, idClient);
            preparedStatement.setInt(2, idSchedule);
            int amountOfRows = preparedStatement.executeUpdate();
            isDeleted = amountOfRows > 0;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isDeleted;
    }

    @Override
    public boolean subscribeClient(int idClient, int idSchedule) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isAdded;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SUBSCRIBE_CLIENT);
            preparedStatement.setInt(1, idClient);
            preparedStatement.setInt(2, idSchedule);
            int amountOfRows = preparedStatement.executeUpdate();
            isAdded = amountOfRows > 0;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isAdded;
    }

    @Override
    public Optional<Schedule> findOneSchedule(int idSchedule) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        Optional<Schedule> scheduleResult = Optional.empty();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_ONE_SCHEDULE);
            preparedStatement.setInt(1, idSchedule);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                AddAttributesFromResultSet.addAdditionalScheduleAttributes(schedule, resultSet);
                scheduleResult = Optional.of(schedule);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return scheduleResult;
    }

    @Override
    public boolean deleteWorkoutFromSchedule(int idSchedule) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isDeleted;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SqlQuery.DELETE_CLIENT_SCHEDULE);
            preparedStatement.setInt(1, idSchedule);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(SqlQuery.DELETE_SCHEDULE);
            preparedStatement.setInt(1, idSchedule);
            int amountOfRows = preparedStatement.executeUpdate();
            isDeleted = amountOfRows > 0;
            if (isDeleted) {
                connection.commit();
            } else {
                rollbackConnection(connection);
            }
        } catch (SQLException ex) {
            rollbackConnection(connection);
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isDeleted;
    }

    @Override
    public boolean updateScheduleParameters(Schedule schedule) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isUpdated;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_SCHEDULE);
            addScheduleParameterToStatement(schedule, preparedStatement);
            preparedStatement.setInt(8, schedule.getIdSchedule());
            preparedStatement.executeUpdate();
            int amountOfRows = preparedStatement.executeUpdate();
            isUpdated = amountOfRows > 0;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isUpdated;
    }

    @Override
    public boolean isFreeTime(int idHall, LocalDate startDate, LocalTime startTime, LocalTime endTime, int idSchedule) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        boolean isExist = false;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.IS_FREE_TIME);
            preparedStatement.setInt(1, idHall);
            preparedStatement.setLong(2, DateTimeTransformer.fromLocalDateToLong(startDate));
            preparedStatement.setLong(3, DateTimeTransformer.fromLocalTimeToLong(startTime));
            preparedStatement.setLong(4, DateTimeTransformer.fromLocalTimeToLong(startTime));
            preparedStatement.setLong(5, DateTimeTransformer.fromLocalTimeToLong(endTime));
            preparedStatement.setLong(6, DateTimeTransformer.fromLocalTimeToLong(endTime));
            preparedStatement.setInt(7, idSchedule);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExist = resultSet.getInt(ColumnName.EXIST_SCHEDULE) == 0;
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isExist;
    }

    @Override
    public boolean add(Schedule schedule) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isUpdated;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.INSERT_SCHEDULE);
            addScheduleParameterToStatement(schedule, preparedStatement);
            int amountOfRows = preparedStatement.executeUpdate();
            isUpdated = amountOfRows > 0;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isUpdated;
    }


    private void addScheduleParameterToStatement(Schedule schedule, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, DateTimeTransformer.fromLocalDateToLong(schedule.getStartDate()));
        preparedStatement.setLong(2, DateTimeTransformer.fromLocalTimeToLong(schedule.getStartTime()));
        preparedStatement.setInt(3, schedule.getDuration());
        preparedStatement.setInt(4, schedule.getCapacity());
        preparedStatement.setInt(5, schedule.getWorkout().getIdWorkout());
        preparedStatement.setInt(6, schedule.getIdHall());
        preparedStatement.setInt(7, schedule.getIdInstructor());
    }

    @Override
    public int findAmountOfOccupiedPlaces(int idSchedule) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        int occupied_places = 0;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_AMOUNT_OF_OCCUPIED_PLACES);
            preparedStatement.setInt(1, idSchedule);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                occupied_places = resultSet.getInt(ColumnName.OCCUPIED_PLACES);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return occupied_places;
    }

    @Override
    public LocalDate findLastDateInSchedule() throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Statement statement = null;
        LocalDate lastDateInSchedule = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_LAST_DATE_IN_SCHEDULE);
            if (resultSet.next()) {
                long lastDate = resultSet.getLong(ColumnName.SCHEDULE_LAST_DATE);
                lastDateInSchedule = DateTimeTransformer.fromLongToLocalDate(lastDate);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(statement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return lastDateInSchedule;
    }

    @Override
    public boolean copyScheduleToNextWeek(LocalDate startDate, LocalDate endDate) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isAdd;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.COPY_SCHEDULE_OF_LAST_WEEK);
            preparedStatement.setLong(1, DateTimeTransformer.fromLocalDateToLong(startDate));
            preparedStatement.setLong(2, DateTimeTransformer.fromLocalDateToLong(endDate));
            int amountOfRows = preparedStatement.executeUpdate();
            isAdd = amountOfRows > 0;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isAdd;
    }

    @Override
    public List<Schedule> findAll() throws DaoException {
        throw new UnsupportedOperationException();
    }
}
