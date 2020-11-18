package com.buyalskaya.fitclub.model.dao.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.connection.ConnectionPool;
import com.buyalskaya.fitclub.model.dao.AddAttributesFromResultSet;
import com.buyalskaya.fitclub.model.dao.ColumnName;
import com.buyalskaya.fitclub.model.dao.MembershipDao;
import com.buyalskaya.fitclub.model.dao.SqlQuery;
import com.buyalskaya.fitclub.model.entity.ClientMembership;
import com.buyalskaya.fitclub.model.entity.Membership;
import com.buyalskaya.fitclub.util.DateTimeTransformer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MembershipDaoImpl implements MembershipDao {

    @Override
    public List<ClientMembership> findActiveClientMemberships(int idClient) throws DaoException {
        long today = DateTimeTransformer.fromLocalDateToLong(LocalDate.now());
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        List<ClientMembership> memberships = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_ACTIVE_CLIENT_MEMBERSHIPS);
            preparedStatement.setInt(1, idClient);
            preparedStatement.setLong(2, today);
            ResultSet resultSet = preparedStatement.executeQuery();
            ClientMembership membership;
            while (resultSet.next()) {
                membership = new ClientMembership();
                AddAttributesFromResultSet.addClientMembershipAttributes(membership, resultSet);
                memberships.add(membership);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return memberships;
    }

    @Override
    public boolean addClientMembership(int idMembership, int idClient, LocalDate date, int classesAmount) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isAdded;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.INSERT_CLIENT_MEMBERSHIP);
            preparedStatement.setLong(1, DateTimeTransformer.fromLocalDateToLong(date));
            preparedStatement.setInt(2, classesAmount);
            preparedStatement.setInt(3, idClient);
            preparedStatement.setInt(4, idMembership);
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
    public List<Membership> findAll() throws DaoException {
        long today = DateTimeTransformer.fromLocalDateToLong(LocalDate.now());
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        List<Membership> memberships = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_ALL_MEMBERSHIPS);
            preparedStatement.setLong(1, today);
            ResultSet resultSet = preparedStatement.executeQuery();
            Membership membership;
            while (resultSet.next()) {
                membership = new Membership();
                AddAttributesFromResultSet.addMembershipAttributes(membership, resultSet);
                memberships.add(membership);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return memberships;
    }

    @Override
    public int findClassesAmountInMembership(int idMembership) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        int classesAmount = 0;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_CLASSES_AMOUNT_IN_MEMBERSHIP);
            preparedStatement.setInt(1, idMembership);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                classesAmount = resultSet.getInt(ColumnName.MEMBERSHIPS_CLASSES_AMOUNT);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return classesAmount;
    }

    @Override
    public int findClassesAmountInClientMembership(int idClientMembership) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        int classesAmount = 0;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_CLASSES_AMOUNT_IN_CLIENT_MEMBERSHIP);
            preparedStatement.setInt(1, idClientMembership);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                classesAmount = resultSet.getInt(ColumnName.MEMBERSHIPS_CLASSES_AMOUNT);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return classesAmount;
    }

    @Override
    public int findRemainingClassesInClientMembership(int idClientMembership) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        int remainingClasses = 0;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_REMAINING_CLASSES_IN_CLIENT_MEMBERSHIP);
            preparedStatement.setInt(1, idClientMembership);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                remainingClasses = resultSet.getInt(ColumnName.CLIENT_MEMBERSHIPS_REMAINING_CLASSES);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return remainingClasses;
    }


    @Override
    public boolean minusWorkoutInMembership(int idClientMembership, int idClient, int idSchedule) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isUpdate;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_CLIENT_SCHEDULE);
            preparedStatement.setInt(1, idClientMembership);
            preparedStatement.setInt(2, idClient);
            preparedStatement.setInt(3, idSchedule);
            int amountOfRows = preparedStatement.executeUpdate();
            isUpdate = amountOfRows > 0;
            preparedStatement = connection.prepareStatement(SqlQuery.CLIENT_MEMBERSHIP_MINUS_WORKOUT);
            preparedStatement.setInt(1, idClientMembership);
            amountOfRows = preparedStatement.executeUpdate();
            isUpdate = isUpdate && (amountOfRows > 0);
            if (isUpdate) {
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
        return isUpdate;
    }

    @Override
    public boolean plusWorkoutInMembership(int idClientMembership, int idClient, int idSchedule) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isUpdate;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_CLIENT_SCHEDULE);
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setInt(2, idClient);
            preparedStatement.setInt(3, idSchedule);
            int amountOfRows = preparedStatement.executeUpdate();
            isUpdate = amountOfRows > 0;
            preparedStatement = connection.prepareStatement(SqlQuery.CLIENT_MEMBERSHIP_PLUS_WORKOUT);
            preparedStatement.setInt(1, idClientMembership);
            amountOfRows = preparedStatement.executeUpdate();
            isUpdate = isUpdate && (amountOfRows > 0);
            if (isUpdate) {
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
        return isUpdate;
    }

    @Override
    public boolean add(Membership membership) throws DaoException {
        throw new UnsupportedOperationException();
    }
}