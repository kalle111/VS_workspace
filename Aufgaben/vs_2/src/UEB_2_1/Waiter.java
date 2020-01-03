package UEB_2_1;

import java.util.Random;

public class Waiter implements Runnable{
    private KitchenCounter theke;
    private String name;

    public Waiter(KitchenCounter theke, String nm) {
        this.name = nm;
        this.theke = theke;
    }


    @Override
    public void run() {
        while(true) {
            //up to 10 sec waitingTime.
            Random rnd = new Random();
            int zufallWartezeit = rnd.nextInt(1);
            //Sleep waitingTime, then generate new LeberkaasSemmel.
            try {
                Thread.sleep(zufallWartezeit*1000);
                this.theke.put();
            } catch (InterruptedException ire) {
                System.out.println("Interrupted Except. caught: " + ire);
                //further handling in real scenario.
            }
        }
    }
}
