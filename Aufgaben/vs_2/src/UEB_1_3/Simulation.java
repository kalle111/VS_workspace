package UEB_1_3;

public class Simulation {
    public static void main(String [] args) throws InterruptedException {
        System.out.println("Starting Simulation...");
        //Aufgabe 1_a+b
        /*Parkhaus prkhs = new Parkhaus(10);

        for(int i = 1; i <= 25; i++) {
            Thread t = new Auto("R-OTH-" + i, prkhs);
            t.setDaemon(true);
            t.start();
        }

        //thread sleep

        Thread.sleep(60000);*/
        //Aufgabe 1_c
        AutoLager autoLager = new AutoLager(10);

        Fahrzeughersteller fh = new Fahrzeughersteller("Hersteller 1", autoLager);
        fh.setDaemon(true);
        fh.start();

        for(int i = 1; i<= 5; i++) {
            Autohaus ah = new Autohaus("Autohaus Nr." + i, autoLager);
            ah.setDaemon(true);
            ah.start();
        }


        Thread.sleep(15000);
        System.out.println("Simulation beendet");
    }
}
