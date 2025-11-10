package com.productionReadyWebServer;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private Logger logger;

    public ClientHandler(Socket clientSocket, Logger logger) {
        this.clientSocket = clientSocket;
        this.logger = logger;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            HttpRequest request = new HttpRequest(in);
            HttpResponse response = new HttpResponse(out);

            logger.info("Handling request: " + request.getMethod() + " " + request.getPath());

            // Simple hardcoded response for demo
            String body = "<html><body><h1>Hello from Multithreaded Server!</h1></body></html>";
            response.send(200, "OK", body);

        } catch (IOException e) {
            logger.error("ClientHandler error: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                logger.error("Error closing client socket: " + e.getMessage());
            }
        }
    }
}