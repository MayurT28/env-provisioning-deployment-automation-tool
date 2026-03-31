package com.envsetup;

public class CLIArgumentParser {

    public static String getEnvironment(String[] args) {

        if (args == null) {
            return null;
        }

        for (String argument : args) {

            if (argument.startsWith("--env=")) {

                return argument.substring(6).toUpperCase();
            }
        }

        return null;
    }

    public static boolean skipDatabaseStep(String[] args) {

        if (args == null) {
            return false;
        }

        for (String argument : args) {

            if (argument.equalsIgnoreCase("--skip-db")) {

                return true;
            }
        }

        return false;
    }

    public static boolean failDatabaseStep(String[] args) {

        if (args == null) {
            return false;
        }

        for (String argument : args) {

            if (argument.equalsIgnoreCase("--fail-db")) {

                return true;
            }
        }

        return false;
    }
}