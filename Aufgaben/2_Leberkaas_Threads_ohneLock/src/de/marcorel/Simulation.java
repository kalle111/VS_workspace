package de.marcorel;

public class Simulation {
    //Simulationsklasse
    public static void main(String[] args) {
        // write your code here
        KitchenCounter theke1 = new KitchenCounter(4);

        new Thread(new Waiter(theke1, "Kellner-1")).start();
        new Thread(new Waiter(theke1, "Kellner-2")).start();
        for(int i = 1; i<=8;i++){
            new Thread(new Student(theke1, "Student #" + i)).start();
        }

        System.out.println("hello");
    }
}
