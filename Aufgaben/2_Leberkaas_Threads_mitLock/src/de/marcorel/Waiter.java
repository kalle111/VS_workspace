package de.marcorel;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Waiter extends Thread{
    private KitchenCounter waiterTheke;
    private String waiterName;

    public Waiter(KitchenCounter theke, String name) {
        super(name);
        //setDaemon(true);
        this.waiterName = name;
        this.waiterTheke = theke;
    }

    public KitchenCounter getWaiterTheke() {
        return waiterTheke;
    }

    public void setWaiterTheke(KitchenCounter waiterTheke) {
        this.waiterTheke = waiterTheke;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    @Override
    public void run() {
        while(true) {
            this.waiterTheke.put();
            System.out.println("  " + Thread.currentThread().getName() + " neue Semmel abgelegt!");
            try {
                TimeUnit.SECONDS.sleep( new Random().nextInt(2) );
            } catch (InterruptedException e) {
            }
        }

    }

}
