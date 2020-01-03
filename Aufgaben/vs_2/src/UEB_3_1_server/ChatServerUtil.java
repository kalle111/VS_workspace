package UEB_3_1_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerUtil implements Runnable {
    private Socket chatClientSocket;

    public ChatServerUtil(Socket acceptedSocket) {
        this.chatClientSocket = acceptedSocket;
    }

    @Override
    public void run() {
        // do something
        String chatClientIp = chatClientSocket.getInetAddress().toString();
        System.out.println("In ChatServerUtil: ");
        System.out.println(chatClientIp);


        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.chatClientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(this.chatClientSocket.getOutputStream());

            String initialInput = br.readLine();
            System.out.println("chatClient initialMessage: "+initialInput);
            String responseMsg = "Sent from ChatServer: "+initialInput;
            writer.println(responseMsg);
            writer.flush();
            System.out.println("server responseMessage: " +initialInput);



        } catch (IOException ioe) {
            //handle ioe
        }
        System.out.println("ChatServerUtil Ende.");
    }
}
