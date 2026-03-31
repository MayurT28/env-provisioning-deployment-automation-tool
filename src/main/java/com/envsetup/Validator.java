package com.envsetup;

import java.util.Properties;

public class Validator {

    public static boolean validate(Properties properties) {

        System.out.println("\nValidating configuration...");
        LoggerUtility.log("Configuration validation started");

        String databaseUrl = properties.getProperty("db.url");
        String loggingLevel = properties.getProperty("logging.level");

        // Validate database URL
        if (databaseUrl == null || !databaseUrl.startsWith("jdbc")) {

            System.out.println("Invalid database URL configuration!");
            LoggerUtility.log("Validation failed: Invalid database URL");

            return false;
        }

        // Validate logging level
        if (!isValidLoggingLevel(loggingLevel)) {

            System.out.println("Invalid logging level configuration!");
            LoggerUtility.log("Validation failed: Invalid logging level");

            return false;
        }

        System.out.println("Configuration validation successful.");
        LoggerUtility.log("Configuration validation successful");

        return true;
    }

    private static boolean isValidLoggingLevel(String loggingLevel) {

        if (loggingLevel == null) {
            return false;
        }

        return loggingLevel.equalsIgnoreCase("DEBUG")
                || loggingLevel.equalsIgnoreCase("WARN")
                || loggingLevel.equalsIgnoreCase("ERROR");
    }
}