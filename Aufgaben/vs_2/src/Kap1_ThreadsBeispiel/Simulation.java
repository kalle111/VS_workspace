package Kap1_ThreadsBeispiel;

public class Simulation {

    public static void main (String [] args) {
        Konto k = new Konto();
        Runnable g1 = new Geldautomat(k, "Uni");
        Geldautomat g2 = new Geldautomat(k, "Oth");

        Thread t1 = new Thread(g1);
        Thread t2 = new Thread(g2);
        t1.start();
        t2.start();
    }
}
