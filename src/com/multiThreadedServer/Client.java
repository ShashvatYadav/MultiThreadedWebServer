package com.multiThreadedServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public void run() {
        int port = 8080;

        try (Socket socket = new Socket(InetAddress.getByName("localhost"), port);
             DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
             DataInputStream fromServer = new DataInputStream(socket.getInputStream())) {

            System.out.println("Connected to server...");

            // Send message first
            String message = "Hello from Client!";
            toServer.writeUTF(message);
            toServer.flush();
            System.out.println("Sent to server: " + message);

            // Then read server response
            String serverResponse = fromServer.readUTF();
            System.out.println("Received from server: " + serverResponse);

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Client().run();
    }
}