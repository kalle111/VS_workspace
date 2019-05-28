package de.marcorel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class WebRunnable implements Runnable{

    //private ServerSocket sock;
    private final Socket clientRequest;

    public WebRunnable(Socket s) {
        this.clientRequest = s;
    }

    @Override
    public void run() {
        PrintWriter pw = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(clientRequest.getInputStream()));
            pw = new PrintWriter(clientRequest.getOutputStream());

            String request = br.readLine();
            String[] reqParts = request.split(" ");
            String path = reqParts[1].replace("/", "");

            if(path.isEmpty()) {
                path = "\\index.html";
            }
            System.out.println("Request = " + request);
            System.out.println("Path = " + path);
            System.out.println("akt. Pfad: " + Paths.get(".").toAbsolutePath().toString());
            List<String> linesInFile = null;
            try {
                linesInFile = Files.readAllLines(Paths.get("./res" + path));
            } catch (NoSuchFileException e) {
                linesInFile = Files.readAllLines(Paths.get("./res" + "/main.html"));
            }
            // Alle Zeilen plus Header-Zeilen in Socket schreiben:
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type: text/html");
            pw.print("Content-Length: ");
            pw.println("" + linesInFile.stream().mapToInt( line -> line.length()+1 ).sum());
            pw.println("");


            for(String zeile : linesInFile ) {
                pw.println(zeile);
            }

            pw.flush();
            clientRequest.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
