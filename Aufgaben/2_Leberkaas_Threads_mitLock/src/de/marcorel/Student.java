package de.marcorel;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Student extends Thread {
    private KitchenCounter studentTheke;
    private String studentName;

    public Student(KitchenCounter theke, String name) {
        super(name);
        //setDaemon(true);
        this.studentTheke = theke;
        this.studentName = name;
    }

    @Override
    public void run() {
        while(true) {
            this.studentTheke.take();
            System.out.println("  " + Thread.currentThread().getName() + " isst!");
            try {
                TimeUnit.SECONDS.sleep( new Random().nextInt(10) );
            } catch (InterruptedException e) {
            }


        }
    }
}
