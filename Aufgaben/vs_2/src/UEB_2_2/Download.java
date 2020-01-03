package UEB_2_2;



import javax.swing.JProgressBar;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// aktive Klasse
public class Download extends Thread {

    private final CountDownLatch start, stop;
    private final JProgressBar balken;

    public Download(CountDownLatch start, CountDownLatch stop, JProgressBar balken) {
        this.start = start;
        this.stop = stop;
        this.balken = balken;
        this.start(); //Thread nach Konstruktion starten!
    }

    @Override
    public void run() {
        int timePerKb = new Random().nextInt(50);
        System.out.println(Thread.currentThread().getName() + " run");
        try {
            start.await();
            System.out.println(Thread.currentThread().getName() + " start nach await");
            for(int i=0; i<=balken.getMaximum(); i++) {
                TimeUnit.MILLISECONDS.sleep(timePerKb);
                balken.setValue(i);
            }
            stop.countDown();
        } catch (InterruptedException e) {
        }
    }
}
