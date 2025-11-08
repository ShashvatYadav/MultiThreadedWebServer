package com.TheradPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    // Create a thread pool with fixed number of threads
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public void start() {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("🚀 Server started on port " + port);

            while (true) {
                System.out.println("Waiting for client connection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("✅ Client connected: " + clientSocket.getRemoteSocketAddress());

                // Submit client handling task to the thread pool
                executor.submit(() -> handleClient(clientSocket));
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        } finally {
            shutdownThreadPool();
        }
    }

    private void handleClient(Socket clientSocket) {
        try (DataInputStream fromClient = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream toClient = new DataOutputStream(clientSocket.getOutputStream())) {

            System.out.println("👂 Reading data from client...");
            String clientData = fromClient.readUTF();
            System.out.println("📥 Received from client: " + clientData);

            String response = "Hello from Thread Pool Server!";
            toClient.writeUTF(response);
            toClient.flush();
            System.out.println("📤 Sent to client: " + response);

        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ignored) {}
        }
    }

    private void shutdownThreadPool() {
        executor.shutdown();
        System.out.println("Thread pool shut down.");
    }

    public static void main(String[] args) {
        new Server().start();
    }
}