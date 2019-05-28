package de.marcorel;

import java.io.*;
import java.net.Socket;

public class ClientKlasse {

    private static final String domain = "localhost";
    private static final int port = 3033;

    public static void main(String[] args) {
            try {
                Socket s = new Socket(domain,port);

                InputStream in = s.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                OutputStream out = s.getOutputStream();
                PrintWriter pw = new PrintWriter(out);

                String incomingMsg = br.readLine();

                pw.println("Nachricht empfangen: " + incomingMsg);
                pw.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
