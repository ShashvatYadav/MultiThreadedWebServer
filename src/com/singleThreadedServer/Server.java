package com.singleThreadedServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
        while (true) {
            try {
                System.out.println("Waiting for client connection...");
                Socket server = serverSocket.accept();
                System.out.println("Client connected: " + server.getRemoteSocketAddress());

                DataInputStream fromClient = new DataInputStream(server.getInputStream());
                System.out.println("Reading data from client...");
                String clientData = fromClient.readUTF();
                System.out.println("Received from client: " + clientData);

                DataOutputStream toClient = new DataOutputStream(server.getOutputStream());
                String response = "Hello from Single Threaded Server!";
                toClient.writeUTF(response);
                System.out.println("Sent to client: " + response);

                fromClient.close();
                toClient.close();
                server.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
