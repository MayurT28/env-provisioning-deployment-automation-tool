/*
 * Decompiled with CFR 0.152.
 */
package com.envsetup;

import com.envsetup.LoggerUtility;

public class RollbackManager {
    public static void rollback(String string) {
        System.out.println("\nDeployment failed !!");
        System.out.println("Rolling back environment changes for: " + string);
        LoggerUtility.log("Deployment failed");
        LoggerUtility.log("Rollback started for environment: " + string);
        RollbackManager.simulateStep("Reverting configuration changes...");
        RollbackManager.simulateStep("Restoring previous database state...");
        RollbackManager.simulateStep("Stopping partially started services...");
        System.out.println("Rollback completed successfully !!");
        LoggerUtility.log("Rollback completed successfully");
    }

    private static void simulateStep(String string) {
        System.out.println(string);
        LoggerUtility.log(string);
        try {
            Thread.sleep(800L);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
