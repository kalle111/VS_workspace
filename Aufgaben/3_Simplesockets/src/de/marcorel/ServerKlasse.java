package de.marcorel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerKlasse {
    //Serverklasse

    private final int port = 3033;
    private final String domain = "localhost";

    public static void main(String[] args) {
        try (ServerSocket serverSocket	=	new ServerSocket(3033))	{
            while(true) {
                //blockiert bei .accept() bis ein Client verbindet
                try {
                    Socket s = serverSocket.accept();

                    InputStream in = s.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));

                    OutputStream out = s.getOutputStream();
                    PrintWriter pw = new PrintWriter(out);

                    pw.println("hallo, hier ist der ServerSocket von Marc");
                    pw.flush();

                    String antwort = br.readLine();
                    System.out.println("Antwort der Gegenseite: " + antwort);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }


}
