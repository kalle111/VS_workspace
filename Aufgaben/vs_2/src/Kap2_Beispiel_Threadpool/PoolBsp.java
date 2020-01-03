package Kap2_Beispiel_Threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolBsp {
    public static void main(String [] args) {
        System.out.println("Hello World from Pool Beispiel");
        ExecutorService es;
        es = Executors.newFixedThreadPool(10); // 10 threads gleichzeitig.

        for(int i = 1; i <= 100; i++) {

            Printer a = new Printer();
            //new Thread(a).start(); => FALSCH!
            es.execute(a);

        }
        return;
    }
}
