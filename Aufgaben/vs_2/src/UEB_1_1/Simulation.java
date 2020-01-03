package UEB_1_1;

public class Simulation {
    public static void main(String [] args) throws InterruptedException {
        System.out.println("Starting Simulation...");
        Parkhaus prkhs = new Parkhaus(4);

        for(int i = 1; i <= 20; i++) {
            Thread t = new Auto("R-OTH-" + i, prkhs);
            t.setDaemon(true);
            t.start();
        }

        //thread sleep

        Thread.sleep(15000);
        System.out.println("Simulation beendet");
    }
}
