package UEB_3_1_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {

    public static void main(String[] args) {
        ExecutorService threadPool;
        threadPool = Executors.newFixedThreadPool(10);

        // info msg
        System.out.println("Server startet on Port 3030");

        while(true) {
            try(ServerSocket server = new ServerSocket(3030)) {
                System.out.println("...waiting for clients to connect...");
                Socket chatclient = server.accept();

                Runnable newRunnable = new ChatServerUtil(chatclient);
                threadPool.execute(newRunnable);
                //bis hier hin blockiert weil .accept();



            } catch (IOException ioe) {
                System.out.println("IO Exception + " + ioe);
                //handle.
            }
        }

    }
}
