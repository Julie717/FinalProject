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

/**
 * The interface Common dao.
 * Consists of the common methods for all dao
 *
 * @param <T> the type parameter
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public interface CommonDao<T extends CommonEntity> {
    /**
     * The constant logger.
     */
    Logger logger = LogManager.getLogger();

    /**
     * Add boolean.
     *
     * @param entity the entity
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(T entity) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<T> findAll() throws DaoException;

    /**
     * Close statement.
     *
     * @param statement the statement
     */
    default void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Statement isn't closed");
            }
        }
    }

    /**
     * Rollback connection.
     *
     * @param connection the connection
     */
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