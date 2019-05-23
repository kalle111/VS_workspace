package de.marcorel;

import java.util.Random;

public class Autohaus extends Thread {
    private String name;
    private Parkhaus herstellerLager;

    public Autohaus(String name, Parkhaus ph) {
        super(name);
        setDaemon(true);
        this.name = name;
        this.herstellerLager = ph;
    }

    public void run() {
        Random zufallsGen = new Random();

        while(true) {
            int zeitZumKaufen = zufallsGen.nextInt(2);
            try {
                Thread.sleep(zeitZumKaufen*1000);

            } catch (InterruptedException e) {
                System.out.println("Autohaus " + Thread.currentThread().getName() + " wurde beim Kauf unterbrochen => Beende Thread.");
                return;
            }
            this.herstellerLager.anAutohausVerkaufen();
        }

    }
}
