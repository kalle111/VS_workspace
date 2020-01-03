package UEB_2_1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KitchenCounter {
    private int leberkaasKapazität;
    private int currentLeberkaas = 0;
    //private Object schranke = new Object();
    private Lock monitor = new ReentrantLock();
    private Condition voll, leer;


    public KitchenCounter(int i) {
        leberkaasKapazität = i;
        //necessary for concurrency framework.
        monitor = new ReentrantLock();
        voll = monitor.newCondition();
        leer = monitor.newCondition();
    }

    public void put() {
        //System.out.println("PUT: NOW LOCKING");
        monitor.lock();
        while(currentLeberkaas == leberkaasKapazität) {
            try {
                //System.out.println("PUT: LEER awaiting");
                System.out.println("##### CANT PUT, THEKE IS FULL");
                leer.await();
            } catch(InterruptedException ire) {
                System.out.println("Ire: "+ ire);
            }
        }

        currentLeberkaas++;
        System.out.println(">> put(): jetzt wieder " + currentLeberkaas + " Semmeln vorhanden.");
        voll.signal();
        monitor.unlock();
    }

    public void take() {
        //System.out.println("TAKE: locking monitor");
        monitor.lock();
        while(currentLeberkaas == 0) {
            try {
                System.out.println("##### CANT TAKE, THEKE IS EMPTY");
                voll.await();
            } catch( InterruptedException ire) {
                System.out.println("IRE: " + ire);
                // further handling...
            }

        }
        currentLeberkaas--;
        System.out.println("## take(): jetzt nur noch " + currentLeberkaas + " Semmeln in der Theke!");
        leer.signal();
        monitor.unlock();
    }
}
