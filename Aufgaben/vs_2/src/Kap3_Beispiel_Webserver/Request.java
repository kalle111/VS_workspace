package Kap3_Beispiel_Webserver;

import java.io.*;
import java.net.Socket;

public class Request implements Runnable {
    private Socket socket;

    public Request(Socket client) {
        this.socket = client;
    }

    @Override
    public void run(){
        // Lesen + Schreiben in/aus dem Socket.
        // zB. "GET ..."#
        try {
            InputStream in = this.socket.getInputStream();
            OutputStream out = this.socket.getOutputStream();

            BufferedReader rdr = new BufferedReader(new InputStreamReader(in));
            PrintWriter wrtr = new PrintWriter(out);

            while(rdr.readLine() != null) {
                System.out.println(rdr.readLine());
            }

            String output = "<html><body>Hallo Welt!!!!! (from kalle)</body></html>";

            in.close();
            out.close();
        } catch (IOException io) {
            System.out.println(io);
            //
        }

    }
}
