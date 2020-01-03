package Kap2_Beispiel_Threadpool;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Skifahrer extends Thread{
    private CyclicBarrier schranke;
    public Skifahrer(CyclicBarrier schranke) {
        this.schranke = schranke;
    };
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (Exception e){
            // abc
        }
        System.out.println("Anstellen");
    //aufeinander syncen
        try {
            schranke.await();
        } catch (InterruptedException ex) {
            // apres ski
        } catch (BrokenBarrierException e) {
            //apres ski
        }
        System.out.println("Lift fahren");
}

}
