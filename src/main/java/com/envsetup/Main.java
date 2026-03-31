/*
 * Decompiled with CFR 0.152.
 */
package com.envsetup;

import com.envsetup.CLIArgumentParser;
import com.envsetup.ConfigLoader;
import com.envsetup.DeploymentPipeline;
import com.envsetup.DockerDetector;
import com.envsetup.EnvironmentProfileManager;
import com.envsetup.HealthChecker;
import com.envsetup.LoggerUtility;
import com.envsetup.MainArgsHolder;
import com.envsetup.RollbackManager;
import com.envsetup.Validator;
import com.envsetup.VersionManager;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainArgsHolder.setArgs(args);
        Scanner scanner = new Scanner(System.in);
        String string = CLIArgumentParser.getEnvironment(args);
        String string2 = "";
        if (string == null) {
            System.out.println("Select environment:");
            System.out.println("1. DEV");
            System.out.println("2. TEST");
            System.out.println("3. PROD");
            int n = scanner.nextInt();
            if (n == 1) {
                string = "DEV";
            } else if (n == 2) {
                string = "TEST";
            } else if (n == 3) {
                string = "PROD";
            } else {
                System.out.println("Invalid selection");
                return;
            }
        }
        if (string.equals("DEV")) {
            string2 = "config/dev.properties";
        } else if (string.equals("TEST")) {
            string2 = "config/test.properties";
        } else if (string.equals("PROD")) {
            string2 = "config/prod.properties";
        } else {
            System.out.println("Invalid environment");
            return;
        }
        LoggerUtility.log("Environment selected: " + string);
        VersionManager.logDeploymentInfo(string);
        String string3 = EnvironmentProfileManager.detectProfile();
        System.out.println("Execution profile detected: " + string3);
        LoggerUtility.log("Execution profile detected: " + string3);
        if (DockerDetector.isRunningInsideDocker()) {
            System.out.println("Running inside Docker container !!");
            LoggerUtility.log("Execution environment: Docker");
        } else {
            System.out.println("Running in local environment !!");
            LoggerUtility.log("Execution environment: Local machine");
        }
        if (!HealthChecker.check(string2)) {
            RollbackManager.rollback(string);
            return;
        }
        Properties properties = ConfigLoader.load(string2);
        if (!Validator.validate(properties)) {
            RollbackManager.rollback(string);
            return;
        }
        if (!DeploymentPipeline.run(properties)) {
            RollbackManager.rollback(string);
        }
        scanner.close();
    }
}
