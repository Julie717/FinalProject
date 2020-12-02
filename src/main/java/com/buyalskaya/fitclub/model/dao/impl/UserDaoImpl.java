package com.buyalskaya.fitclub.model.dao.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.connection.ConnectionPool;
import com.buyalskaya.fitclub.model.dao.AddAttributesFromResultSet;
import com.buyalskaya.fitclub.model.entity.*;
import com.buyalskaya.fitclub.model.dao.ColumnName;
import com.buyalskaya.fitclub.model.dao.SqlQuery;
import com.buyalskaya.fitclub.model.dao.UserDao;
import com.buyalskaya.fitclub.util.CommonUtil;
import com.buyalskaya.fitclub.util.DateTimeTransformer;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class UserDaoImpl implements UserDao {

    @Override
    public Optional<String> findPasswordByLogin(String login) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Optional<String> password = Optional.empty();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_PASSWORD_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                password = Optional.of(resultSet.getString(ColumnName.USERS_PASSWORD));
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return password;
    }

    @Override
    public boolean add(User user, String password) throws DaoException {
        boolean isAdded;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, user.getRole().getUserRoleId());
            preparedStatement.setInt(4, user.getStatus().getUserStatusId());
            preparedStatement.setString(5, user.getName());
            preparedStatement.setString(6, user.getSurname());
            preparedStatement.setString(7, user.getPhoneNumber());
            preparedStatement.setString(8, user.getEmail());
            long birthday = DateTimeTransformer.fromLocalDateToLong(user.getBirthday());
            preparedStatement.setLong(9, birthday);
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
    public boolean confirmRegistration(String login) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        boolean isConfirm;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.CHANGE_USER_STATUS);
            preparedStatement.setString(1, login);
            int amountOfRows = preparedStatement.executeUpdate();
            isConfirm = amountOfRows > 0;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isConfirm;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Optional<User> user = Optional.empty();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User resultUser = new User();
                AddAttributesFromResultSet.addUserAttributes(resultUser, resultSet);
                user = Optional.of(resultUser);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public UserStatus findUserStatusByLogin(String login) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        UserStatus userStatus = UserStatus.BLOCKED;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_USER_STATUS_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idStatus = resultSet.getInt(ColumnName.USERS_STATUS);
                userStatus = CommonUtil.findStatus(idStatus);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return userStatus;
    }

    @Override
    public Optional<Staff> findStaffByLogin(String login) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Optional<Staff> staff = Optional.empty();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_STAFF_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Staff resultStaff = new Staff();
                AddAttributesFromResultSet.addStaffAttributes(resultStaff, resultSet);
                staff = Optional.of(resultStaff);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return staff;
    }

    @Override
    public List<User> findAll() throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Statement statement = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_USERS);
            User user;
            while (resultSet.next()) {
                user = new User();
                AddAttributesFromResultSet.addUserAttributes(user, resultSet);
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(statement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return users;
    }

    public List<Staff> findAllStaffByRole(UserRole userRole) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        List<Staff> staffs = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_STAFFS);
            preparedStatement.setInt(1, userRole.getUserRoleId());
            ResultSet resultSet = preparedStatement.executeQuery();
            Staff staff;
            while (resultSet.next()) {
                staff = new Staff();
                AddAttributesFromResultSet.addStaffAttributes(staff, resultSet);
                staffs.add(staff);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return staffs;
    }

    @Override
    public boolean updateUserInfo(User user) throws DaoException {
        boolean isUpdate;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_CLIENT_INFO);
            isUpdate = updateUserInfo(user, preparedStatement);
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isUpdate;
    }

    private boolean updateUserInfo(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getSurname());
        preparedStatement.setString(4, user.getPhoneNumber());
        preparedStatement.setString(5, user.getEmail());
        long birthday = DateTimeTransformer.fromLocalDateToLong(user.getBirthday());
        preparedStatement.setLong(6, birthday);
        preparedStatement.setInt(7, user.getIdUser());
        int amountOfRows = preparedStatement.executeUpdate();
        return amountOfRows > 0;
    }

    private boolean updateStaffInfo(Staff staff, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, staff.getWorkExperience());
        preparedStatement.setString(2, staff.getDescription());
        preparedStatement.setInt(3, staff.getIdUser());
        int amountOfRows = preparedStatement.executeUpdate();
        return amountOfRows > 0;
    }

    @Override
    public boolean updateStaffInfo(Staff staff) throws DaoException {
        boolean isUpdate;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_STAFF_INFO);
            isUpdate = updateStaffInfo(staff, preparedStatement);
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isUpdate;
    }

    @Override
    public boolean updateUserAndStaffInfo(Staff staff) throws DaoException {
        boolean isUpdate;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_CLIENT_INFO);
            isUpdate = updateUserInfo(staff, preparedStatement);
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_STAFF_INFO);
            isUpdate = isUpdate && updateStaffInfo(staff, preparedStatement);
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
    public boolean updatePassword(int idUser, String password) throws DaoException {
        boolean isUpdate;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_PASSWORD);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, idUser);
            int amountOfRows = preparedStatement.executeUpdate();
            isUpdate = amountOfRows > 0;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isUpdate;
    }

    @Override
    public boolean updatePhoto(int idUser, InputStream photo) throws DaoException {
        boolean isUpdate;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_PHOTO);
            preparedStatement.setBlob(1, photo);
            preparedStatement.setInt(2, idUser);
            int amountOfRows = preparedStatement.executeUpdate();
            isUpdate = amountOfRows > 0;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isUpdate;
    }

    @Override
    public List<Client> findSubscribedClients(int idSchedule) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        List<Client> clients = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_SUBSCRIBED_CLIENTS);
            preparedStatement.setInt(1, idSchedule);
            ResultSet resultSet = preparedStatement.executeQuery();
            Client client;
            while (resultSet.next()) {
                client = new Client();
                AddAttributesFromResultSet.addClientAttributesForEditSchedule(client, resultSet);
                clients.add(client);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return clients;
    }

    @Override
    public Map<Integer, String> findNameInstructors() throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Statement statement = null;
        Map<Integer, String> instructorNames = new HashMap<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_INSTRUCTOR_NAMES);
            int idInstructor;
            String instructorName;
            while (resultSet.next()) {
                idInstructor = resultSet.getInt(ColumnName.INSTRUCTOR_ID);
                instructorName = resultSet.getString(ColumnName.INSTRUCTOR_NAME);
                instructorNames.put(idInstructor, instructorName);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(statement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return instructorNames;
    }

    @Override
    public Optional<String> findClientName(int idClient) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        Optional<String> clientName = Optional.empty();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_CLIENT_NAME);
            preparedStatement.setInt(1, idClient);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                clientName = Optional.of(resultSet.getString(ColumnName.CLIENT_NAME));
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return clientName;
    }

    @Override
    public boolean isInstructorExist(int idInstructor) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        boolean isExist = false;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.IS_INSTRUCTOR_EXIST);
            preparedStatement.setInt(1, idInstructor);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExist = resultSet.getInt(ColumnName.EXIST_INSTRUCTOR) > 0;
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
    public Map<Integer, String> findUserRoles() throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Statement statement = null;
        Map<Integer, String> roles = new HashMap<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_ROLES);
            int idRole;
            String roleName;
            while (resultSet.next()) {
                idRole = resultSet.getInt(ColumnName.USER_ROLES_ID);
                roleName = resultSet.getString(ColumnName.USER_ROLES_NAME);
                roles.put(idRole, roleName);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(statement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return roles;
    }

    @Override
    public Map<Integer, String> findUserStatuses() throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Statement statement = null;
        Map<Integer, String> statuses = new HashMap<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_STATUSES);
            int idStatus;
            String statusName;
            while (resultSet.next()) {
                idStatus = resultSet.getInt(ColumnName.STATUSES_ID);
                statusName = resultSet.getString(ColumnName.STATUSES_NAME);
                statuses.put(idStatus, statusName);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(statement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return statuses;
    }

    @Override
    public int countUsersAmount() throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        Statement statement = null;
        int usersAmount = 0;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_USERS_AMOUNT);
            if (resultSet.next()) {
                usersAmount = resultSet.getInt(ColumnName.USERS_AMOUNT);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(statement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return usersAmount;
    }

    @Override
    public List<Staff> findUsersInRange(int start, int end) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        List<Staff> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_USERS_IN_RANGE);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, end);
            ResultSet resultSet = preparedStatement.executeQuery();
            Staff user;
            while (resultSet.next()) {
                user = new Staff();
                AddAttributesFromResultSet.addUserOrStaffAttributes(user, resultSet);
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return users;
    }

    @Override
    public List<Staff> findUserBySurname(String surname) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        List<Staff> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_SURNAME);
            preparedStatement.setString(1, surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            Staff user;
            while (resultSet.next()) {
                user = new Staff();
                AddAttributesFromResultSet.addUserOrStaffAttributes(user, resultSet);
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return users;
    }


    @Override
    public boolean updateRoleFromClientToStaff(Staff user) throws DaoException {
        boolean isUpdate;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_USER_ROLE_AND_STATUS);
            preparedStatement.setInt(1, user.getRole().getUserRoleId());
            preparedStatement.setInt(2, user.getStatus().getUserStatusId());
            preparedStatement.setInt(3, user.getIdUser());
            int amountOfRows = preparedStatement.executeUpdate();
            isUpdate = amountOfRows > 0;
            preparedStatement = connection.prepareStatement(SqlQuery.INSERT_INTO_STAFF);
            preparedStatement.setLong(1,
                    DateTimeTransformer.fromLocalDateToLong(user.getStartWorkingDate()));
            if (user.getEndWorkingDate() == null) {
                preparedStatement.setNull(2, Types.BIGINT);
            } else {
                preparedStatement.setLong(2,
                        DateTimeTransformer.fromLocalDateToLong(user.getEndWorkingDate()));
            }
            preparedStatement.setInt(3, user.getIdUser());
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
    public boolean updateRoleFromStaffToClient(Staff user) throws DaoException {
        boolean isUpdate;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_USER_ROLE_AND_STATUS);
            preparedStatement.setInt(1, user.getRole().getUserRoleId());
            preparedStatement.setInt(2, user.getStatus().getUserStatusId());
            preparedStatement.setInt(3, user.getIdUser());
            int amountOfRows = preparedStatement.executeUpdate();
            isUpdate = amountOfRows > 0;
            preparedStatement = connection.prepareStatement(SqlQuery.DELETE_FROM_STAFF);
            preparedStatement.setInt(1, user.getIdUser());
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
    public boolean updateRoleFromClientToClient(Staff user) throws DaoException {
        boolean isUpdate;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_USER_STATUS);
            preparedStatement.setInt(1, user.getStatus().getUserStatusId());
            preparedStatement.setInt(2, user.getIdUser());
            int amountOfRows = preparedStatement.executeUpdate();
            isUpdate = amountOfRows > 0;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isUpdate;
    }

    @Override
    public boolean updateRoleFromStaffToStaff(Staff user) throws DaoException {
        boolean isUpdate;
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_STAFF_ROLE);
            preparedStatement.setInt(1, user.getRole().getUserRoleId());
            preparedStatement.setInt(2, user.getStatus().getUserStatusId());
            preparedStatement.setLong(3, DateTimeTransformer
                    .fromLocalDateToLong(user.getStartWorkingDate()));
            if (user.getEndWorkingDate() == null) {
                preparedStatement.setNull(4, Types.BIGINT);
            } else {
                preparedStatement.setLong(4,
                        DateTimeTransformer.fromLocalDateToLong(user.getEndWorkingDate()));
            }
            preparedStatement.setInt(5, user.getIdUser());
            int amountOfRows = preparedStatement.executeUpdate();
            isUpdate = amountOfRows > 0;
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return isUpdate;
    }

    @Override
    public Optional<Staff> findUserOrStaffById(int idUser) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement preparedStatement = null;
        Optional<Staff> resultUser = Optional.empty();
        try {
            preparedStatement = connection.prepareStatement(SqlQuery.SELECT_USER_OR_STAFF_BY_ID);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Staff user = new Staff();
                AddAttributesFromResultSet.addUserOrStaffAttributes(user, resultSet);
                resultUser = Optional.of(user);
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            closeStatement(preparedStatement);
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
        return resultUser;
    }

    @Override
    public boolean add(User user) throws DaoException {
        throw new UnsupportedOperationException();
    }
}