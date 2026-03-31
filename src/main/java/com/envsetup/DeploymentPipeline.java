package com.envsetup;

import java.util.Properties;

public class DeploymentPipeline {

    public static boolean run(Properties properties) {

        System.out.println("\nStarting deployment pipeline...");
        LoggerUtility.log("Pipeline started");

        // Step 1: Initialize environment
        if (isEnabled(properties, "pipeline.init")) {
            if (!executeStep("Initializing environment...")) {
                return false;
            }
        }

        // Step 2: Apply configuration
        if (isEnabled(properties, "pipeline.config")) {
            if (!executeStep("Applying configuration...")) {
                return false;
            }
        }

        // Step 3: Database connection
        if (isEnabled(properties, "pipeline.db")) {

            String dbUrl = DatabaseManager.resolveDatabaseURL(properties);

            if (CLIArgumentParser.failDatabaseStep(MainArgsHolder.getArgs())) {

                System.out.println("Database connection failed (simulated)");
                LoggerUtility.log("Simulated DB failure triggered");

                return false;
            }

            if (!executeStep("Connecting DB: " + dbUrl)) {
                return false;
            }
        }

        // Step 4: Logging configuration
        if (isEnabled(properties, "pipeline.logging")) {

            String loggingLevel = properties.getProperty("logging.level");

            if (!executeStep("Setting logging level: " + loggingLevel)) {
                return false;
            }
        }

        // Step 5: Start services
        if (isEnabled(properties, "pipeline.services")) {
            if (!executeStep("Starting services...")) {
                return false;
            }
        }

        System.out.println("\nDeployment completed successfully !!");
        LoggerUtility.log("Pipeline completed successfully");

        return true;
    }

    private static boolean executeStep(String message) {

        System.out.println(message);
        LoggerUtility.log(message);

        try {
            Thread.sleep(800);

        } catch (InterruptedException exception) {

            LoggerUtility.log("Pipeline step interrupted: " + message);
            exception.printStackTrace();

            return false;
        }

        return true;
    }

    private static boolean isEnabled(Properties properties, String key) {

        String value = properties.getProperty(key);

        return value != null && value.equalsIgnoreCase("true");
    }
}