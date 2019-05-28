package de.marcorel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerKlasse {
    //Serverklasse

    private static final int port = 8081;
    private static final String domain = "192.168.178.38";

    public static void main(String[] args) {
        try (ServerSocket serverSocket	=	new ServerSocket(port))	{
            while(true) {
                //blockiert bei .accept() bis ein Client verbindet
                try {
                    System.out.println("Server gestartet...");
                    Socket s = serverSocket.accept();

                    InputStream in = s.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));

                    OutputStream out = s.getOutputStream();
                    PrintWriter pw = new PrintWriter(out);

                    pw.println("hallo, hier ist der ServerSocket von Marc");
                    pw.flush();

                    String antwort = br.readLine();
                    System.out.println("Antwort der Gegenseite: " + antwort);

                    s.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }


}
