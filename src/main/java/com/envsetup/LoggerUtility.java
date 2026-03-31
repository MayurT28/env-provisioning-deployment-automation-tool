package com.envsetup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtility {

    private static final String LOG_FILE_PATH = "logs/deployment.log";

    public static void log(String message) {

        ensureLogDirectoryExists();

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String formattedMessage = "[" + timestamp + "] " + message;

        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {

            writer.write(formattedMessage + System.lineSeparator());

        } catch (IOException exception) {

            System.err.println("Logging failed: " + exception.getMessage());
        }
    }

    private static void ensureLogDirectoryExists() {

        File logDirectory = new File("logs");

        if (!logDirectory.exists()) {
            logDirectory.mkdirs();
        }
    }
}