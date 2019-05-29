

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientClass{

    private static final String domain = "132.199.58.7";
    private static final int port = 1234;

    public static void main(String[] args) {
        chatServerCon();
    }

    public static void chatServerCon() {
        String response = "";
        Scanner sc = new Scanner(System.in);
        int loopCounter = 0;
        try (Socket s = new Socket(domain,port)) {
            System.out.println("<<<internal>>>Connection found!\n");

            InputStream in = s.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            OutputStream out = s.getOutputStream();
            PrintWriter pw = new PrintWriter(out);
            while (!response.contains("###Ende###")) {

                response = "";
                //Print was ausgegeben wird vom Server bis .isEmpty()
                    String tempResponse = "a";
                    //wichtig, dass der Sender noch eine leere Zeile hinzufügt.
                    while(!tempResponse.isEmpty()) {
                        tempResponse = br.readLine();
                        response += (tempResponse.concat("\n"));
                        //System.out.println(tempResponse);
                    }
                    System.out.println(response);

                    //Eingabe machen
                    String input = sc.nextLine();
                    pw.println(input);
                    pw.flush();
                    //iteration erhöhen
                //make sure to not interrupt the loop.
                if (loopCounter == 0) {
                    response = "";
                }
                loopCounter++;
            }
            //System.out.println("durch endservice beendet°!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } catch(Exception e){
            System.out.println("exception e");
            e.printStackTrace();
        }
    }
}
