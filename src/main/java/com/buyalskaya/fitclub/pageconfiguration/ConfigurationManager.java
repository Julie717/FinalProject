package com.buyalskaya.fitclub.pageconfiguration;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static String fileName = "pagenames";
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName);

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}