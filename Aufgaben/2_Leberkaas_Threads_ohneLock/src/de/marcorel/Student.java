package de.marcorel;

public class Student extends Thread {
    private KitchenCounter studentTheke;
    private String studentName;

    public Student(KitchenCounter theke, String name) {
        super(name);
        setDaemon(true);
        this.studentTheke = theke;
        this.studentName = name;
    }

    @Override
    public void run() {
        while(true) {
            this.studentTheke.take();
        }
    }
}
