package com.productionReadyWebServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    private ExecutorService executor;

    public ThreadPool(int threadCount) {
        executor = Executors.newFixedThreadPool(threadCount);
    }

    public void execute(Runnable task) {
        executor.execute(task);
    }

    public void shutdown() {
        executor.shutdown();
    }
}