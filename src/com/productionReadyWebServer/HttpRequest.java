package com.productionReadyWebServer;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    private String method;
    private String path;

    public HttpRequest(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line != null && !line.isEmpty()) {
            String[] parts = line.split(" ");
            if (parts.length >= 2) {
                method = parts[0];
                path = parts[1];
            }
        }
        // Skip rest of headers
        while (line != null && !line.isEmpty()) {
            line = reader.readLine();
        }
    }

    public String getMethod() { return method; }
    public String getPath() { return path; }
}