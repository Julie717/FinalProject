package com.buyalskaya.fitclub.model.connection;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * The type Db property reader.
 * Uses for reading properties for creating connection with database.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
class DbPropertyReader {
    private static final String FILE_DATABASE_CONFIG = "config/database";
    private static final String URL = "url";
    private static final String USER = "user";
    private static final String PASS = "password";
    private static final String AUTO_RECONNECT = "autoReconnect";
    private static final String CHARACTER_ENCODING = "characterEncoding";
    private static final String SERVER_TIME_ZONE = "serverTimezone";
    private static final String USE_UNICODE = "useUnicode";
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_DATABASE_CONFIG);

    private DbPropertyReader() {
    }

    /**
     * Receive properties properties.
     *
     * @return the properties
     */
    static Properties receiveProperties() {
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

    /**
     * Receive url string.
     *
     * @return the string
     */
    static String receiveUrl() {
        return resourceBundle.getString(URL);
    }
}