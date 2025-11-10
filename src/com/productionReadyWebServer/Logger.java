package com.productionReadyWebServer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void info(String message) {
        System.out.println("[INFO] [" + LocalDateTime.now().format(formatter) + "] " + message);
    }

    public void error(String message) {
        System.err.println("[ERROR] [" + LocalDateTime.now().format(formatter) + "] " + message);
    }
}