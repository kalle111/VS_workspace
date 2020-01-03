package UEB_1_3;

import java.util.Random;

public class Fahrzeughersteller extends Thread {
    private String herstellerName;
    private AutoLager al;
    private int autoNummer = 1;

    public Fahrzeughersteller(String name, AutoLager al) {
        this.herstellerName = name;
        this.al = al;
    }

    @Override
    public void run() {
        while(true) {
            Random rnd = new Random();
            int warteProd = rnd.nextInt(3);

            try {
                Thread.sleep(warteProd*1000);
                al.autoHerstellen(new Car("R-OTH-" + autoNummer));
                incrementId();
            } catch (InterruptedException ire) {
                //
            }

        }
    }

    public String getHerstellerName() {
        return herstellerName;
    }

    public void setHerstellerName(String herstellerName) {
        this.herstellerName = herstellerName;
    }

    public void incrementId() {
        this.autoNummer++;
    }
}
