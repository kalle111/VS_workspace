package de.marcorel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {

    private static int port = 8081;
    private static String domain = "192.168.178.38";
    public static void main(String[] args) {
	// write your code here
        runServer();
    }

    public static String[] runServer() {
        System.out.println("Server-Main will be started");

        try (ServerSocket servSo = new ServerSocket(port)){
            System.out.println("Socket erstellt - warte nun auf .accept()");
            while(true) {
                Socket request = servSo.accept();
                System.out.println("Conneciton Partner found!\n\n");
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
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
