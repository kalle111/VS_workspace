package UEB_4_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRequest implements Runnable {
    private Socket clientSocket;

    public ClientRequest(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // do something
        System.out.println("> Verbindung mit Client akzeptiert, warten auf Befehlseingabe...");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(this.clientSocket.getOutputStream());

            String clientMessage = br.readLine();
            String messageArray[] = clientMessage.split("###");
            System.out.println(">>> Erhaltene Anweisung: "+clientMessage);





            this.clientSocket.close();
        } catch (IOException e) {
            //handle
            System.out.println("IOException with BufferedReader!");
            e.printStackTrace();
        }
    }
}
