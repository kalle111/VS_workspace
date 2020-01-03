package UEB_3_2;

public class ServerController {
    public static void main(String[] args) {
        System.out.println("ServerController-Class running & creating ServerSocket...");

        WebServerSocket wss = new WebServerSocket(80, 5);
        wss.startServer();
    }
}
