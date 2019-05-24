package de.marcorel;

public class KitchenCounter {
    //passive Klasse
    private int maxLeberkaasSemmel;
    private int leberkaasCounter = 0; //weil zu Beginn mit 0 gestartet wird.
    private final Object schranke = new Object();

    public KitchenCounter(int inputNumber) {
        this.maxLeberkaasSemmel = inputNumber;
    }

    public void put() {
        synchronized (schranke) {
            while(this.leberkaasCounter >= this.maxLeberkaasSemmel) {
                try {
                    System.out.println("> PUT: Es konnte keine Semmel in die Theke gelegt werden (max erreicht): " + Thread.currentThread().getName());
                    schranke.wait();
                } catch (InterruptedException e) {
                    //errorhandling
                    e.printStackTrace();
                }
            }
            leberkaasCounter++;
            System.out.println("==> Leberkaas put: Es wurd eine Semmel hinzugefügt. Waiter: " + Thread.currentThread().getName());
            //notify()? notifyAll()?
            schranke.notifyAll();
        }
    }

    public void take() {
        synchronized (schranke) {
            while(this.leberkaasCounter <= 0) {
                try {
                    System.out.println("> TAKE: Es konnte keine Semmel genommen werden (Counter = 0): " + Thread.currentThread().getName());
                    schranke.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            leberkaasCounter--;
            System.out.println("==> Leberkaas take: Es wurd eine Semmel verkauft. Student: " + Thread.currentThread().getName());
            schranke.notifyAll();
        }
    }
}
