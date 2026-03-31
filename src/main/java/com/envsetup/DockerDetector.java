package com.envsetup;

import java.io.File;

public class DockerDetector {

    public static boolean isRunningInsideDocker() {

        // Standard Docker runtime indicator file
        File dockerEnvironmentFile = new File("/.dockerenv");

        if (dockerEnvironmentFile.exists()) {

            LoggerUtility.log("Docker environment detected via /.dockerenv");

            return true;
        }

        LoggerUtility.log("Docker environment not detected");

        return false;
    }
}