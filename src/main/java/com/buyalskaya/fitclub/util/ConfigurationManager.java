package com.buyalskaya.fitclub.util;

import java.util.ResourceBundle;

/**
 * The type Configuration manager.
 * Is used to read a path of jsp
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class ConfigurationManager {
    private final static String fileName = "pagenames";
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName);

    private ConfigurationManager() {
    }

    /**
     * Gets property.
     *
     * @param key the key
     * @return the property
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}