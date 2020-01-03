package UEB_1_3;

import java.util.ArrayDeque;
import java.util.Deque;

public class AutoLager {
    //passive Klasse
    private int autoKapazität;
    private Deque<Car> autoDeque = new ArrayDeque<>();
    private Object schranke = new Object();


    public AutoLager(int i) {
        this.autoKapazität = i;
    }

    public void autoHerstellen(Car car) {
        synchronized (schranke) {
            while(autoDeque.size() >= autoKapazität) {
                try {
                    System.out.println("= Lager ist voll.");
                    schranke.wait();
                }catch (InterruptedException ire) {
                    //
                }
            }
            System.out.println("Hersteller hat folgendes Auto produziert: " + car.getKennzeichen());
            autoDeque.addLast(car);
            schranke.notifyAll();
        }

    }
    public void autoKaufen(Autohaus autohaus) {
        //System.out.println("Autohaus: " + autohaus);
        synchronized (schranke) {
            while(autoDeque.size() <= 0) {
                try {
                    System.out.println("> Kein Auto zum Verkauf im Lager!");
                    schranke.wait();
                } catch (InterruptedException ire) {
                    //
                }
            }
            System.out.println("# Verkauf von Auto: " + autoDeque.getFirst().getKennzeichen() + " an Autohaus: " + autohaus.getNameAutohaus());
            autoDeque.removeFirst();
            schranke.notifyAll();
        }

    }
}
