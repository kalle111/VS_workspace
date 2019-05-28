package de.marcorel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {
    //Serverklasse

    private static int port;
    private static final String domain = "192.168.178.38";
    private final int threadPoolSize = 5;
    private final ExecutorService threadpool;

    public WebServer(int i) {
        this.port = i;
        this.threadpool = Executors.newFixedThreadPool(threadPoolSize);
        startWebserver();
    }
    public static void main(String[] args) {
        new WebServer(80);
    }

    public void startWebserver() {
        //
        try {
            ServerSocket servSock = new ServerSocket(this.port);
            System.out.println("Server wird gestartet...");
            while(true) {
                Socket clientRequest = servSock.accept();
                System.out.println("Neuer Clientrequest => InetAddress: " + clientRequest.getInetAddress() + ", Hostaddress: " + clientRequest.getInetAddress().getHostAddress());

                WebRunnable webrun = new WebRunnable(clientRequest);
                this.threadpool.execute(webrun);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("IOException at creating servSock-Variable");
        }
    }




}
