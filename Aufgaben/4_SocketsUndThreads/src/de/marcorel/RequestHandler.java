package de.marcorel;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RequestHandler implements Runnable{
    private Socket requestSocket;
    private MessageStore messageStore;

    public RequestHandler(Socket s, MessageStore m) {
        this.messageStore = m;
        this.requestSocket = s;
    }

    @Override
    public void run() {
        // implement Thread-handling with Client-Requests

        try {
            //System.out.println("Waiting for Client-Request...");


            List<String> reqList = new ArrayList<>();
            String temp = "";
            /*do {
                reqList.add(br.readLine());
            } while (!(temp.contains("#Ende#")));*/

            int loopCount = 0;

            while(loopCount < 10) {
                InputStream in = this.requestSocket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                OutputStream out = this.requestSocket.getOutputStream();
                PrintWriter pw = new PrintWriter(out);

                if(loopCount == 0) {
                    pw.println("Welcome to OTH messaging service.");
                    pw.println("You are sending from " + this.requestSocket.getRemoteSocketAddress().toString());
                    pw.println("Commands allowed are:");
                    pw.println("REG###username>");
                    pw.println("SND###from>###<to>###<text>");
                    pw.println("RCV###<username>");
                    pw.println("###Endservice1###");
                    pw.println("Please Enter your response now: ");
                    pw.println();
                    pw.flush();
                    System.out.println("Warte auf User-Eingabe...");
                    reqList.add(br.readLine());
                    for(String s : reqList) {
                        if(!s.isEmpty()) {
                            System.out.println("\t*"+s);
                        }
                    }
                    //pw.close();
                } else {
                    pw.println("Following Commands are allowed: ");
                    pw.println("REG###username>");
                    pw.println("SND###from>###<to>###<text>");
                    pw.println("RCV###<username>");
                    pw.println("###Endservice2###");
                    pw.println("Please Enter your response now: ");
                    pw.println();
                    pw.flush();
                    System.out.println("==> Warte auf User-Eingabe...");
                    reqList.add(br.readLine());
                    for(String s : reqList) {
                        if(!s.isEmpty()) {
                            System.out.println("\t*" + s); //aöjdaöskj
                        //aöksjdfökj
                            System.out.printf("");
                        }
                    }
                }
                loopCount++;

                //handle response.
                String relevantCommand = reqList.get((reqList.size()-1));
                String releventCom, user1, user2, msg;
                releventCom = relevantCommand.split("###")[0].trim();

                //feedback fuer das Anlegen von Usern.
                int registerFeedback = 0;

                switch (releventCom){
                    case "REG":
                        user1 = relevantCommand.split("###")[1];
                        System.out.println("You want to " + releventCom + " user: " + user1);
                        String user = messageStore.addUser(user1);
                        pw.println(">> action performed: "+user1 + "registered!!!");
                        pw.flush();
                        break;
                    case "SND":
                        user1 = relevantCommand.split("###")[1];
                        user2 = relevantCommand.split("###")[2];
                        msg = relevantCommand.split("###")[3];
                        System.out.println("You ["+user1+"], want to send a msg to " + user2);
                        pw.println(">> action performed: "+user1 + " sent: " + msg + ", to: " + user2);
                        pw.flush();
                        break;
                    case "RCV":
                        user1 = relevantCommand.split("###")[1];

                        break;
                    case "Endservice":

                        break;
                    default:
                        System.out.println("defualt");
                }
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

    public String registerUser(String name) {

        return "";
    }
}
