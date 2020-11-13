package com.buyalskaya.fitclub.model.dao;

import com.buyalskaya.fitclub.exception.DaoException;
import com.buyalskaya.fitclub.model.entity.CommonEntity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface CommonDao<T extends CommonEntity> {
    Logger logger = LogManager.getLogger();

    boolean add(T entity) throws DaoException;

    List<T> findAll() throws DaoException;

    default void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Statement isn't closed");
            }
        }
    }

    default void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error during transaction rollback", ex);
        }
    }
}