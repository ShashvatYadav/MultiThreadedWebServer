package com.productionReadyWebServer;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;

        try (Socket socket = new Socket(host, port)) {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send a simple HTTP GET request
            out.write("GET / HTTP/1.1\r\n");
            out.write("Host: localhost\r\n");
            out.write("Connection: close\r\n");
            out.write("\r\n");
            out.flush();

            // Read and print response
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
