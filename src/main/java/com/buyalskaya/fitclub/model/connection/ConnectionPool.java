package com.buyalskaya.fitclub.model.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final String FILE_DATABASE_CONFIG = "config.database";
    private static final String URL = "url";
    private static final String USER = "user";
    private static final String PASS = "password";
    private static final String AUTO_RECONNECT = "autoReconnect";
    private static final String CHARACTER_ENCODING = "characterEncoding";
    private static final String SERVER_TIME_ZONE = "serverTimezone";
    private static final String USE_UNICODE = "useUnicode";
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenConnections;
    private static final int DEFAULT_POOL_SIZE = 10;

    private ConnectionPool() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_DATABASE_CONFIG);
            String url = resourceBundle.getString(URL);
            Properties properties = createConnectionProperties(resourceBundle);
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            freeConnections = new LinkedBlockingDeque<>();
            givenConnections = new ArrayDeque<>();
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

    private Properties createConnectionProperties(ResourceBundle resourceBundle) {
        String user = resourceBundle.getString(USER);
        String pass = resourceBundle.getString(PASS);
        String autoReconnect = resourceBundle.getString(AUTO_RECONNECT);
        String characterEncoding = resourceBundle.getString(CHARACTER_ENCODING);
        String serverTimeZone = resourceBundle.getString(SERVER_TIME_ZONE);
        String useUnicode = resourceBundle.getString(USE_UNICODE);
        Properties properties = new Properties();
        properties.put(USER, user);
        properties.put(PASS, pass);
        properties.put(CHARACTER_ENCODING, characterEncoding);
        properties.put(AUTO_RECONNECT, autoReconnect);
        properties.put(SERVER_TIME_ZONE, serverTimeZone);
        properties.put(USE_UNICODE, useUnicode);
        return properties;
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
                freeConnections.offer((ProxyConnection) connection);
            }
        } else {
            logger.log(Level.WARN, "Incorrect connection for releasing");
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

    public void closeStatement(Statement statement) {//TODO Can we add this method in ConnectionPool?
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.log(Level.WARN, "Statement isn't closed");
            }
        }
    }

    public static ConnectionPool getINSTANCE() {
        return INSTANCE;
    }
}