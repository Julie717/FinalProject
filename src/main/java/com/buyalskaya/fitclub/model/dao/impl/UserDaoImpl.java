package com.buyalskaya.fitclub.model.dao.impl;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.connection.ConnectionPool;
import com.buyalskaya.fitclub.model.dao.ColumnName;
import com.buyalskaya.fitclub.model.dao.UserDao;
import com.buyalskaya.fitclub.model.dao.UserRole;
import com.buyalskaya.fitclub.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String SQL_SELECT_USER_BY_LOGIN_PASSWORD = "SELECT idUser, login, password, " +
            "userRole, name, surname, phoneNumber, email, birthday" +
            "FROM users " +
            "WHERE (login = ? AND password=?)";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT idUser, login, password, " +
            "idRole, name, surname, phoneNumber, email, birthday" +
            "FROM users " +
            "WHERE (login = ?)";
    private static final String SQL_INSERT_USER = "INSERT INTO users (login, password, userRole, " +
            "name, surname, phoneNumber, email, birthday) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public Optional<User> authorization(String login, String password) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getINSTANCE().getConnection();
        Optional<User> user = Optional.empty();
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(createUserFromResetSet(resultSet));
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            ConnectionPool.getINSTANCE().releaseConnection(connection);
            ConnectionPool.getINSTANCE().closeStatement(preparedStatement);
        }
        return user;
    }

    @Override
    public boolean registration(User user) throws DaoException {
        boolean isRegistered = findUserByLogin(user.getLogin()).isEmpty();
        if (isRegistered) {
            Connection connection = ConnectionPool.getINSTANCE().getConnection();
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setInt(3, user.getRole().getIdRole());
                preparedStatement.setString(4, user.getName());
                preparedStatement.setString(5, user.getSurname());
                preparedStatement.setString(6, user.getPhoneNumber());
                preparedStatement.setString(7, user.getEmail());
                preparedStatement.setLong(8, user.getBirthday());
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                throw new DaoException("SQL exception (request or table failed)", ex);
            } finally {
                ConnectionPool.getINSTANCE().releaseConnection(connection);
                ConnectionPool.getINSTANCE().closeStatement(preparedStatement);
            }
        }
        return false;
    }


    public Optional<User> findUserByLogin(String login) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getINSTANCE().getConnection();
        Optional<User> user = Optional.empty();
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(createUserFromResetSet(resultSet));
            }
        } catch (SQLException ex) {
            throw new DaoException("SQL exception (request or table failed)", ex);
        } finally {
            ConnectionPool.getINSTANCE().releaseConnection(connection);
            ConnectionPool.getINSTANCE().closeStatement(preparedStatement);
        }
        return user;
    }

    private User createUserFromResetSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setIdUser(resultSet.getInt(ColumnName.USER_ID));
        user.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
        user.setPassword(resultSet.getString(ColumnName.USER_PASSWORD));
        int idRole = resultSet.getInt(ColumnName.USER_ROLE);
        UserRole userRole = Arrays.stream(UserRole.values())
                .filter(r -> r.getIdRole() == idRole)
                .findFirst()
                .orElse(UserRole.CLIENT);
        user.setRole(userRole);
        user.setName(resultSet.getString(ColumnName.USER_NAME));
        user.setSurname(resultSet.getString(ColumnName.USER_SURNAME));
        user.setPhoneNumber(resultSet.getString(ColumnName.USER_PHONE_NUMBER));
        user.setEmail(resultSet.getString(ColumnName.USER_EMAIL));
        user.setBirthday(resultSet.getLong(ColumnName.USER_BIRTHDAY));
        return user;
    }
}
