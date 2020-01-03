package UEB_3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RequestHandler implements Runnable{
    private final Socket acceptedSocket;

    public RequestHandler(Socket acceptedSocket) {
        System.out.println("RequestHandler creating...: " + acceptedSocket.toString());
        this.acceptedSocket = acceptedSocket;
    }

    @Override
    public void run() {
        //Request will be handled once the Runnable is started as a thread.
        //System.out.println(Paths.get("index.html").toAbsolutePath().toString());
        List<String> zeilen = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.acceptedSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(this.acceptedSocket.getOutputStream());

            //get index html on server filesystem
            zeilen	=	Files.readAllLines(Paths.get("index.html"));
            System.out.println(zeilen.size());
            String answer = zeilen.toString();

            //get request
            String req = br.readLine();
            //System.out.println(req); // GET / HTTP/1.1
            String[] reqArray = req.split(" ");
            //System.out.println(reqArray[0]); // sollte GET sein.

            // Info-Ausgabe f. Sever-Adming
            System.out.println("Es wurde folgendes file angefordert: " + reqArray[1]);

            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/html");
            writer.print("Content-Length: ");

            int contentLength = 0;

            for(String z : zeilen) {
                System.out.println("z in Zeilen: " + z);
                contentLength += z.length();
            }
            System.out.println("Content-length:" + contentLength);
            writer.println(contentLength);
            writer.println("");

            for(String zeile : zeilen)  {
                writer.println(zeile);
            }

            writer.flush();
            acceptedSocket.close();


        } catch (IOException ioe) {
            //exception handling
            System.out.println("Exception!");
        }
    }
}
