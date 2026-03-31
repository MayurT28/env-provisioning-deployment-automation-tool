package com.envsetup;

public class MainArgsHolder {

    private static String[] arguments;

    public static void setArgs(String[] args) {
        arguments = args;
    }

    public static String[] getArgs() {
        return arguments;
    }
}