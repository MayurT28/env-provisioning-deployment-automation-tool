package com.envsetup;

import java.io.File;

public class HealthChecker {

    public static boolean check(String configFilePath) {

        System.out.println("\nRunning environment health check...");
        LoggerUtility.log("Running environment health check");

        File configFile = new File(configFilePath);

        if (!configFile.exists()) {

            System.out.println("Configuration file missing!");
            LoggerUtility.log("Health check failed: configuration file not found");

            return false;
        }

        System.out.println("Configuration file found successfully.");
        LoggerUtility.log("Health check successful");

        return true;
    }
}