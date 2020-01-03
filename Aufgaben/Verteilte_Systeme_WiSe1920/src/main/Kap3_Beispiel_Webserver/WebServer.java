package main.Kap3_Beispiel_Webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {

    public static void main(String [] args) {

        try{
            //ThreadPool
            ExecutorService threadPool;
            threadPool = Executors.newFixedThreadPool(5);
            //ServerSocket + msg
            ServerSocket server = new ServerSocket(3020);
            System.out.println("Server gestartet...");

            while(true) {
                Socket client = server.accept(); //blockierend!!! returned nur solange neuer Client kommt.
                //folgendes wird ausgeführt wenn ein client connected

                Runnable req = new Request(client);
                threadPool.execute(req);
                System.out.println("Thread gestartet...: [ip: " + client.getInetAddress() + " ], [port: " + client.getPort() + " ]END.");
            }
        }catch (IOException e) {

        }
    }


}
