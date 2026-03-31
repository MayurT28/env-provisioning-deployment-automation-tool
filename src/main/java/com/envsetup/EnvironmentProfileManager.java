package com.envsetup;

public class EnvironmentProfileManager {

    public static String detectProfile() {

        // Step 1: Check if running inside Docker
        if (DockerDetector.isRunningInsideDocker()) {

            LoggerUtility.log("Execution profile detected: DOCKER");

            return "DOCKER";
        }

        // Step 2: Check if cloud environment variable is set
        String cloudEnvironmentFlag = System.getenv("CLOUD_ENV");

        if ("true".equalsIgnoreCase(cloudEnvironmentFlag)) {

            LoggerUtility.log("Execution profile detected: CLOUD");

            return "CLOUD";
        }

        // Step 3: Default fallback
        LoggerUtility.log("Execution profile detected: LOCAL");

        return "LOCAL";
    }
}