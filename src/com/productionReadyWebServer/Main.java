package com.productionReadyWebServer;

public class Main {
    public static void main(String[] args) {
        int port = 8080;
        Server server = new Server(port, 10); // 10 threads in pool
        server.start();
    }
}