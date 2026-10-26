package utb.fai;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        int port = 12345;
        int max_threads = 10;

        // Implement input parameter processing
        port = Integer.parseInt(args[0]);
        max_threads = Integer.parseInt(args[1]);

        // Implementation of the main server loop
        ExecutorService executor = Executors.newFixedThreadPool(max_threads);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.execute(new ClientThread(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
