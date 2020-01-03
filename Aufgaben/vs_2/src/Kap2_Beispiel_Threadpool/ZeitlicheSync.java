package Kap2_Beispiel_Threadpool;

import java.util.concurrent.CyclicBarrier;

public class ZeitlicheSync {
    public static void main(String [] args) {
        CyclicBarrier schranke = new CyclicBarrier(2);
        for(int i = 0; i<= 10; i++) {
            Skifahrer ski = new Skifahrer(schranke);
            ski.start();

        }
    }
}
