package com.envsetup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    public static Properties load(String configFilePath) {

        Properties properties = new Properties();

        try (FileInputStream inputStream = new FileInputStream(configFilePath)) {

            properties.load(inputStream);

            LoggerUtility.log("Configuration loaded successfully from: " + configFilePath);

        } catch (IOException exception) {

            LoggerUtility.log("Failed to load configuration file: " + configFilePath);

            exception.printStackTrace();
        }

        return properties;
    }
}