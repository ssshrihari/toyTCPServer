package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void doWork(Socket socket){
        System.out.println("Connection is "+ socket);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {
        ExecutorService executorService = null;
        try {
            ServerSocket tcpServer = new ServerSocket(6666);
            executorService = Executors.newFixedThreadPool(10);
            while (true) {
                Socket socket = tcpServer.accept();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        doWork(socket);
                    }
                });


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }
}