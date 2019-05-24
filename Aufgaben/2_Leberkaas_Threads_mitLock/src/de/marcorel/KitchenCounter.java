package de.marcorel;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KitchenCounter {
    //passive Klasse
    private int maxLeberkaasSemmel;
    private int leberkaasCounter = 0; //weil zu Beginn mit 0 gestartet wird.
    //private final Object schranke = new Object();
    private Lock monitor = new ReentrantLock();
    Condition leberkaasZubereiten = monitor.newCondition();
    Condition leberkaasVerkaufen = monitor.newCondition();

    public KitchenCounter(int inputNumber) {
        this.maxLeberkaasSemmel = inputNumber;
    }

    public void put() {
        monitor.lock();
        while(this.leberkaasCounter >= this.maxLeberkaasSemmel) {
                try {
                    System.out.println("> PUT: Es konnte keine Semmel in die Theke gelegt werden (max erreicht): " + Thread.currentThread().getName());
                    leberkaasZubereiten.await();
                } catch (InterruptedException e) {
                    //errorhandling
                    e.printStackTrace();
                }
        }
            leberkaasCounter++;
            System.out.println("==> Leberkaas put: Es wurd eine Semmel hinzugefügt. Waiter: " + Thread.currentThread().getName());
            leberkaasVerkaufen.signal();

            monitor.unlock();

    }

    public void take() {
        monitor.lock();
            while(this.leberkaasCounter == 0) {
                try {
                    System.out.println("> TAKE: Es konnte keine Semmel genommen werden (Counter = 0): " + Thread.currentThread().getName() + ", Semmelcounter: " + this.leberkaasCounter);
                    leberkaasVerkaufen.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            leberkaasCounter--;
            System.out.println("==> Leberkaas take: Es wurd eine Semmel verkauft. Student: " + Thread.currentThread().getName() + ", Counter: " + leberkaasCounter);
            leberkaasZubereiten.signal();
            monitor.unlock();
    }
}
