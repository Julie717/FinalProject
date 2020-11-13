package com.buyalskaya.fitclub.model.connection;

import com.mysql.cj.jdbc.Driver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


public enum ConnectionPool {
    INSTANCE;

    private final Logger logger = LogManager.getLogger();
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> givenConnections;
    private static final int DEFAULT_POOL_SIZE = 16;

    ConnectionPool() {
        try {
            String url = DbPropertyReader.receiveUrl();
            Properties properties = DbPropertyReader.receiveProperties();
            DriverManager.registerDriver(new Driver());
            freeConnections = new LinkedBlockingDeque<>();
            givenConnections = new LinkedBlockingDeque<>();
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, properties);
                freeConnections.offer(new ProxyConnection(connection));
            }
        } catch (MissingResourceException ex) {
            logger.log(Level.FATAL, "Properties file with database configuration isn't found");
            throw new RuntimeException("Properties file with database configuration isn't found", ex);
        } catch (SQLException ex) {
            logger.log(Level.FATAL, "Database connection wasn't established");
            throw new RuntimeException("Database connection wasn't established", ex);
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException ex) {
            logger.log(Level.ERROR, "Error during receiving connection");
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            if (givenConnections.remove(connection)) {
                try {
                    if(!connection.getAutoCommit()){
                        connection.setAutoCommit(true);
                    }
                } catch (SQLException ex) {
                    logger.log(Level.ERROR, "Connection can't establish autocommit true");
                }
                freeConnections.offer((ProxyConnection) connection);
            } else {
                logger.log(Level.ERROR, "Connection isn't remove");
            }
        } else {
            logger.log(Level.ERROR, "Incorrect connection for releasing");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (InterruptedException ex) {
                logger.log(Level.ERROR, "Destroying pool isn't successful");
            }
        }
        deregisterDriver();
    }

    private void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException ex) {
                logger.log(Level.ERROR, "Deregister drivers aren't successful");
            }
        });
    }
}