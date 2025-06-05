package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpEchoServer {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        try (ServerSocket serverSocket = new ServerSocket(7)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}