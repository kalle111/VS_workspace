package de.marcorel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hersteller extends Thread {
    private String herstellerName;
    private Parkhaus parkhaus;
    private List<Auto> produzierteUnits = new ArrayList<>();

    public Hersteller(String name, Parkhaus ph) {
        super(name);
        setDaemon(true);
        this.herstellerName = name;
        this.parkhaus = ph;
    }

    @Override
    public void run() {
        // autos herstellen

        Random zufallsGenerator = new Random();
        int autoZaehler = 1;
        while(true) {
            int herstellDauer = zufallsGenerator.nextInt(1);

            try {
                Thread.sleep(herstellDauer*1000);
                //produziert Auto und möchte es im Parkhaus abstellen

            } catch (InterruptedException e) {
                System.out.printf("Herstellprozess wurde unterbrochen!...beende Herstellung");
                return;
            }
            this.parkhaus.produzierteAutosAbstellen(produziereAuto(("Hersteller: + " + this.herstellerName + ", kfzID: #"+ autoZaehler++)));
        }

    }

    public Auto produziereAuto(String s){
        Auto temp = new Auto(this, s);
        produzierteUnits.add(temp);
        System.out.printf("-- Auto hergestellt: " + temp.getName());
        return temp;
    }

    public void verkauftesAuto(Auto a) {
        produzierteUnits.remove(a);
    }
}
