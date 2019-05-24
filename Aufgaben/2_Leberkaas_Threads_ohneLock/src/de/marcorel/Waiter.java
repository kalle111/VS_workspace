package de.marcorel;

public class Waiter extends Thread{
    private KitchenCounter waiterTheke;
    private String waiterName;

    public Waiter(KitchenCounter theke, String name) {
        super(name);
        setDaemon(true);
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
        }
    }

}
