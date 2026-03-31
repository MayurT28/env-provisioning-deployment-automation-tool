package com.envsetup;

import java.util.Properties;

public class DatabaseManager {

    public static String resolveDatabaseURL(Properties properties) {

        // Step 1: Check environment variable override
        String environmentDbUrl = System.getenv("DB_URL");

        if (environmentDbUrl != null && !environmentDbUrl.isEmpty()) {

            LoggerUtility.log("Using DB URL from environment variable override");

            return environmentDbUrl;
        }

        // Step 2: Detect execution profile
        String profile = EnvironmentProfileManager.detectProfile();

        if ("DOCKER".equals(profile)) {

            LoggerUtility.log("Using container database configuration");

            return "jdbc:mysql://container-db:3306/container_database";
        }

        if ("CLOUD".equals(profile)) {

            LoggerUtility.log("Using cloud database configuration");

            return "jdbc:mysql://cloud-db-server:3306/cloud_database";
        }

        // Step 3: Default fallback to properties file
        String propertiesDbUrl = properties.getProperty("db.url");

        if (propertiesDbUrl == null || propertiesDbUrl.isEmpty()) {

            LoggerUtility.log("Database URL missing in properties file");

            return null;
        }

        LoggerUtility.log("Using DB URL from properties file");

        return propertiesDbUrl;
    }
}