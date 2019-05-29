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
            System.out.println("Im Client-Request");
            InputStream in = this.requestSocket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            OutputStream out = this.requestSocket.getOutputStream();
            PrintWriter pw = new PrintWriter(out);

            List<String> reqList = new ArrayList<>();
            String temp = "";
            /*do {
                reqList.add(br.readLine());
            } while (!(temp.contains("#Ende#")));*/
            pw.println("Willkommen zum Server");
            System.out.printf(">>> geschrieben: Willkommen zum Server -- Chat-Service:\n");
            pw.println("")
            pw.flush();
            reqList.add(br.readLine());

            for(String s : reqList) {
                if(s != null || s != "") {
                    System.out.println(s);
                }

            }
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
