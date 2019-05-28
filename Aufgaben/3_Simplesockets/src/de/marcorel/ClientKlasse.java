package de.marcorel;

import java.io.*;
import java.net.Socket;

public class ClientKlasse {

    private static final String domain = "192.168.178.38";
    private static final int port = 8081;

    public static void main(String[] args) {
            try {
                Socket s = new Socket(domain,port);
                System.out.println("Connection found!\n");
                InputStream in = s.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                OutputStream out = s.getOutputStream();
                PrintWriter pw = new PrintWriter(out);

                boolean msgBool = true;
                String inbound = "";
                //inbound = br.readLine();
                //System.out.println("Message: " + inbound);
                while(msgBool) {
                    String temp = br.readLine();

                    if(temp.contains("#Ende#")) {
                        msgBool = false;
                    } else {
                        inbound += temp + "\n";
                    }
                    //System.out.println("loopend");
                }
                System.out.println(inbound);

                //pw.println("Nachricht empfangen: " + incomingMsg);
                //pw.flush();
            } catch (Exception e) {
                System.out.println("exception e");
                e.printStackTrace();
            }
    }
}
