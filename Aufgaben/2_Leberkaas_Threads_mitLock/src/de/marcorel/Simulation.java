package de.marcorel;

public class Simulation {
    //Simulationsklasse
    public static void main(String[] args) {
        // write your code here
        KitchenCounter theke1 = new KitchenCounter(4);

        Thread t1 = new Waiter(theke1, "Kellner-1");
        t1.start();
        Thread t2 = new Waiter(theke1, "Kellner-2");
        t2.start();
        for(int i = 1; i<=8;i++){
             Thread temp = new Student(theke1, "Student #" + i);
             temp.start();
        }

        System.out.println("hello");
    }
}
