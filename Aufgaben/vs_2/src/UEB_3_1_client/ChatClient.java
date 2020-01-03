package UEB_3_1_client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class ChatClient {
    public static void main(String[] args) {
        try {

            Socket chatSocket = new Socket("localhost", 3030);
            System.out.println("hallo");
        } catch (Exception e) {
            System.out.println("Exception e: " + e);
        }

    }
}
