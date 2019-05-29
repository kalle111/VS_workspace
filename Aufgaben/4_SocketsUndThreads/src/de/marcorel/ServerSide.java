package de.marcorel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSide {

    private static int port = 1234;
    private static String domain = "172.16.39.77";
    private static final int threadpoolSize = 20;
    private static MessageStore database = new MessageStore();
    public static void main(String[] args) {
	// write your code here
        runServer();
    }

    public static void runServer() {
        System.out.println("Server-Main will be started");
        ExecutorService threadpool = Executors.newFixedThreadPool(threadpoolSize);
        try (ServerSocket servSo = new ServerSocket(port)){
            System.out.println("Socket erstellt - warte nun auf .accept()");
            while(true) {
                //Incoming Requests will be accepted + handled per Runnable/Thread
                Socket request = servSo.accept();
                System.out.println("Client-Request found: " + request.getInetAddress());
                //Client-Requests handled as Threads via Threadpool.
                Runnable requestHandler = new RequestHandler(request,database);
                threadpool.execute(requestHandler);

                /*System.out.println("Conneciton Partner found!\n\n");
                InputStream in = request.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                PrintWriter pw = new PrintWriter(request.getOutputStream());

                pw.println("Willkommen zum Messenger-Dienst.");
                pw.println("Waehlen Sie nun den gewunschten Dienst, durch Eingabe der folgenden Befehle: ");
                pw.println("==>   REG###USERNAME");
                pw.println("==>   SEND###FROMUSER###TOUSER###MSG#");
                pw.println("==>   READ###USERNAME###PASSWORD#");
                pw.println("#Ende#");
                pw.flush();
                //request.close();

                String requestString = "";

                requestString = br.readLine();*/

                /*if(requestString.isEmpty()) {
                    System.out.println("String empty!");
                }else {
                    System.out.println(requestString);
                }*/

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        threadpool.shutdown();
    }
}
