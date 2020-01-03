package UEB_1_3;

import java.util.Random;

public class Autohaus extends Thread {
    //aktiv
    private String name;
    private AutoLager al;

    public Autohaus(String ahname, AutoLager al) {
        this.name = ahname;
        this.al = al;
    }

    @Override
    public void run() {
        while(true) {
            Random rnd = new Random();
            int warteKauf = rnd.nextInt(15);

            try {
                Thread.sleep(warteKauf*1000);
                this.al.autoKaufen(this);
            } catch (InterruptedException ire) {

            }
        }
    }

    public String getNameAutohaus() {
        return name;
    }
    public void setNameAutohaus(String name) {
        this.name = name;
    }
}
