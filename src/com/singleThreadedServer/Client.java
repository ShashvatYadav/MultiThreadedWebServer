package com.singleThreadedServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public void run() throws IOException {
        // Client implementation goes here
        int port = 8080;
        InetAddress ip = InetAddress.getByName("localhost");
        Socket socket = new Socket(ip, port);
        DataInputStream fromServer = new DataInputStream(socket.getInputStream());
        String serverData = fromServer.readUTF();
        System.out.println("Received from server: " + serverData);
        DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
        String message = "Hello from Client!";
        toServer.writeUTF(message);
        System.out.println("Sent to server: " + message);

        fromServer.close();
        toServer.close();
        socket.close();
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
