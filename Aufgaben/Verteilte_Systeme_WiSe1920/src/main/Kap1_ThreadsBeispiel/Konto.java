package main.Kap1_ThreadsBeispiel;

public class Konto {
    private int kontostand = 0;
    private Object monitor = new Object();


    public void einzahlen (int betrag) {
        synchronized (monitor) {
            this.kontostand = kontostand+betrag;
        }
    }
    public void auszahlen (int betrag) {
        synchronized (monitor) {
            this.kontostand = kontostand-betrag;
        }
    }

    public int getBetrag() {
        return this.kontostand;
    }
}
