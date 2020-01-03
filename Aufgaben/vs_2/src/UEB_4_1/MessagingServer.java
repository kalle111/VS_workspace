package UEB_4_1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessagingServer {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(6);
        System.out.println("Server startet on port 3030.");

        try(ServerSocket server = new ServerSocket(3030)) {
            while (true) {
                System.out.println("> waiting for connections...");
                //wait till client connects.
                Socket clientSocket = server.accept(); //blockierend.

                //create ClientRequest
                Runnable ClientRequest = new ClientRequest(clientSocket);
                //Add to thread-pool.
                threadPool.execute(ClientRequest);
            }


        } catch (IOException ioe) {
            System.out.println("IOException");
        }
    }
}
