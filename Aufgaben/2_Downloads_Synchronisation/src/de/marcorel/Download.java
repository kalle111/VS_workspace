package de.marcorel;

import javax.swing.JProgressBar;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// aktive Klasse
public class Download extends Thread
{

    private final JProgressBar balken;
    private final CountDownLatch start, stop;
    // weitere Attribute zur Synchronisation hier definieren

    public Download(CountDownLatch start,CountDownLatch stop, JProgressBar balken) {
        this.balken = balken;
        this.start = start;
        this.stop = stop;
        start();
    }

    @Override
    public void run() {
        int rndmInt = new Random().nextInt(60);
        System.out.println(Thread.currentThread().getName() + " run: " + rndmInt + " Seconds.");
        try {
            start.await();
            System.out.println(Thread.currentThread().getName() + " start nach await.");
            for(int i = 0; i<=balken.getMaximum();i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(rndmInt);
                    balken.setValue(i);
                } catch (InterruptedException e){}
                stop.countDown();
            }
        } catch (InterruptedException e1) {}
    }
    /*  hier die Methode definieren, die jeweils den Balken weiterschiebt
     *  Mit balken.getMaximum() bekommt man den Wert fuer 100 % gefuellt
     *  Mit balken.setValue(...) kann man den Balken einstellen (wieviel gefuellt) dargestellt wird
     *  Setzen Sie den value jeweils und legen Sie die Methode dann für eine zufaellige Zeit schlafen
     */


}
