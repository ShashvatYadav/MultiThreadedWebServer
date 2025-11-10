package com.productionReadyWebServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private boolean running;
    private ThreadPool threadPool;
    private Logger logger;

    public Server(int port, int threadCount) {
        this.port = port;
        this.threadPool = new ThreadPool(threadCount);
        this.logger = new Logger();
    }

    public void start() {
        running = true;
        logger.info("Server starting on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (running) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Accepted new connection from " + clientSocket.getInetAddress());
                threadPool.execute(new ClientHandler(clientSocket, logger));
            }
        } catch (IOException e) {
            logger.error("Server error: " + e.getMessage());
        }
    }
}