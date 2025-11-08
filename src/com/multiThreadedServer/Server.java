package com.multiThreadedServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void start() {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                System.out.println("Waiting for client connection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getRemoteSocketAddress());

                // handle client in a new thread
                new Thread(() -> handleClient(clientSocket)).start();
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket) {
        try (DataInputStream fromClient = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream toClient = new DataOutputStream(clientSocket.getOutputStream())) {

            System.out.println("Reading data from client...");
            String clientData = fromClient.readUTF();
            System.out.println("Received from client: " + clientData);

            String response = "Hello from Multi Threaded Server!";
            toClient.writeUTF(response);
            toClient.flush(); // ensure data is sent

            System.out.println("Sent to client: " + response);

        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ignored) {}
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }
}