package de.marcorel;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RequestHandler implements Runnable{
    private Socket requestSocket;

    public RequestHandler(Socket s) {
        this.requestSocket = s;
    }

    @Override
    public void run() {
        // implement Thread-handling with Client-Requests

        try {
            //System.out.println("Waiting for Client-Request...");
            InputStream in = this.requestSocket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            OutputStream out = this.requestSocket.getOutputStream();
            PrintWriter pw = new PrintWriter(out);

            List<String> reqList = new ArrayList<>();
            String temp = "";
            /*do {
                reqList.add(br.readLine());
            } while (!(temp.contains("#Ende#")));*/

            int loopCount = 0;

            while(loopCount < 10) {
                if(loopCount == 0) {
                    pw.println("Welcome to OTH messaging service.");
                    pw.println("You are sending from " + this.requestSocket.getRemoteSocketAddress().toString());
                    pw.println("Commands allowed are:");
                    pw.println("REG###username>");
                    pw.println("SND###from>###<to>###<text>");
                    pw.println("RCV###<username>");
                    pw.println("###Endservice###");
                    pw.println("Please Enter your response now: ");
                    pw.println();
                    pw.flush();
                    System.out.println("Warte auf User-Eingabe...");
                    reqList.add(br.readLine());
                    for(String s : reqList) {
                        if(!s.isEmpty()) {
                            System.out.println(s);
                        }
                    }
                    //pw.close();
                } else {
                    pw.println("Following Commands are allowed: ");
                    pw.println("REG###username>");
                    pw.println("SND###from>###<to>###<text>");
                    pw.println("RCV###<username>");
                    pw.println("###Endservice###");
                    pw.flush();
                    System.out.println("Warte auf User-Eingabe...");
                    reqList.add(br.readLine());
                    for(String s : reqList) {
                        if(!s.isEmpty()) {
                            System.out.println(s);
                        }
                    }
                }
                loopCount++;
            }
            System.out.println("außerhalb while loop");
            // handle incoming Stream data
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No InputStream available!!! IOExdeption...");
        } finally {
            try {
                this.requestSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
