

import java.io.*;
import java.net.Socket;

public class ClientClass{

    private static final String domain = "172.16.39.77";
    private static final int port = 1234;

    public static void main(String[] args) {
        try (Socket s = new Socket(domain,port)) {
            System.out.println("Connection found!\n");
            InputStream in = s.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            OutputStream out = s.getOutputStream();
            PrintWriter pw = new PrintWriter(out);

            boolean msgBool = true;
            String inbound = "";
            //inbound = br.readLine();
            //System.out.println("Message: " + inbound);
            inbound = br.readLine();
            System.out.println(inbound);

            //pw.println("Nachricht empfangen: " + incomingMsg);
            //pw.flush();
        } catch (Exception e) {
            System.out.println("exception e");
            e.printStackTrace();
        }
    }
}
