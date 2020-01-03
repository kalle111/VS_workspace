package Kap2_Beispiel_Threadpool;

public class Printer implements Runnable{
    private static int counter = 0;

    @Override
    public void run() {
        int myNr = counter++;

        System.out.println("Begin, nr: " + myNr);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        System.out.println("End, nr: " + myNr);
    }
}
