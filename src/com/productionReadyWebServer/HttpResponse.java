package com.productionReadyWebServer;

import java.io.BufferedWriter;
import java.io.IOException;

public class HttpResponse {
    private BufferedWriter writer;

    public HttpResponse(BufferedWriter writer) {
        this.writer = writer;
    }

    public void send(int statusCode, String statusText, String body) throws IOException {
        writer.write("HTTP/1.1 " + statusCode + " " + statusText + "\r\n");
        writer.write("Content-Type: text/html\r\n");
        writer.write("Content-Length: " + body.length() + "\r\n");
        writer.write("Connection: close\r\n");
        writer.write("\r\n");
        writer.write(body);
        writer.flush();
    }
}