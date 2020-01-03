package UEB_3_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServerSocket {
    //Instance variables + constructor
    private static int port;
    private ExecutorService threadpool;

    public WebServerSocket(int port, int poolSize) {
        this.port = port;
        this.threadpool = Executors.newFixedThreadPool(poolSize);
    }
    // ###############################

    public void startServer() {
        try(ServerSocket serverSocket = new ServerSocket(this.port)) {
            while(true) {
                //wird blockiert bab .accept();
                try {
                    //blockierender aufruf!
                    Socket acceptedSocket = serverSocket.accept();
                    Runnable runIt = new RequestHandler(acceptedSocket);
                    this.threadpool.execute(runIt);
                    //System.out.println("Thread gestartet...: [ip: " + acceptedSocket.getInetAddress() + " ], [port: " + acceptedSocket.getPort() + " ]END.");
                    //System.out.println(acceptedSocket.toString());

                } catch (IOException ioe) {
                    System.out.println("IOException at second try{}-Block.");
                }

            }
        } catch (IOException ioe) {
            System.out.println("IOException from first try{}-Block.");
        }
    }
}
