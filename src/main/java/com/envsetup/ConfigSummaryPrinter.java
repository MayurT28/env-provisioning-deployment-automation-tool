package com.envsetup;

import java.util.Properties;

public class ConfigSummaryPrinter {

    public static void printSummary(String environment, Properties properties) {

        String profile = EnvironmentProfileManager.detectProfile();
        String databaseSource = resolveDatabaseSource(profile);

        System.out.println("\n===== EXECUTION SUMMARY =====");

        System.out.println("Environment: " + environment);
        System.out.println("Execution Profile: " + profile);
        System.out.println("Database Source: " + databaseSource);
        System.out.println("Logging Level: " + properties.getProperty("logging.level"));

        printPipelineFlag(properties, "pipeline.init");
        printPipelineFlag(properties, "pipeline.config");
        printPipelineFlag(properties, "pipeline.db");
        printPipelineFlag(properties, "pipeline.logging");
        printPipelineFlag(properties, "pipeline.services");

        System.out.println("=============================\n");

        LoggerUtility.log("Execution summary printed");
    }

    private static String resolveDatabaseSource(String profile) {

        if (System.getenv("DB_URL") != null) {
            return "ENVIRONMENT VARIABLE";
        }

        if ("DOCKER".equals(profile)) {
            return "DOCKER PROFILE";
        }

        if ("CLOUD".equals(profile)) {
            return "CLOUD PROFILE";
        }

        return "PROPERTIES FILE";
    }

    private static void printPipelineFlag(Properties properties, String key) {

        String value = properties.getProperty(key);

        if (value == null) {
            value = "not defined";
        }

        System.out.println(key + ": " + value);
    }
}