package Kap1_ThreadsBeispiel;

import java.util.Random;

public class Geldautomat implements Runnable {
    private Konto konto;
    private String standort;

    public Geldautomat(Konto kto, String standort) {
        this.konto = kto;
        this.standort = standort;
    }

    @Override
    public void run() {
        for(int i = 1; i<= 100; i++) {
            int betrag = new Random().nextInt(1000);
            //System.out.println("Betrag = " + betrag);
            konto.einzahlen(betrag);
            //betrag = new Random().nextInt(999);
            konto.auszahlen(betrag);
            System.out.println("Standort: " + this.standort + " -- KontoStand: " + this.konto.getBetrag());
            try {
                //Thread.sleep(betrag/100);
            }catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
