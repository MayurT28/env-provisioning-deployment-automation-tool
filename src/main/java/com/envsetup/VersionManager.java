package com.envsetup;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VersionManager {

    private static final String VERSION = "v1.0";

    public static void logDeploymentInfo(String environment) {

        String executionMode = DockerDetector.isRunningInsideDocker() ? "CONTAINER" : "LOCAL";

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println("\nDeployment Version: " + VERSION);
        System.out.println("Environment: " + environment);
        System.out.println("Execution Mode: " + executionMode);
        System.out.println("Timestamp: " + timestamp);

        LoggerUtility.log("Deployment Version: " + VERSION);
        LoggerUtility.log("Environment: " + environment);
        LoggerUtility.log("Execution Mode: " + executionMode);
        LoggerUtility.log("Timestamp: " + timestamp);
    }
}