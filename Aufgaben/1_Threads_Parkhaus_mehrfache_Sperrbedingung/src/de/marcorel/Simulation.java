package de.marcorel;

public class Simulation {

    public static void main(String[] args) {
	// write your code here
        //System.out.println("Hello World");

        //Erstellung Parkhaus
        Parkhaus ph = new Parkhaus(5);

        //Erstellung Autos
        for(int i = 0; i<20; i++){
            Thread t = new Auto("NM-OTHR-" + (i+1), ph);
            t.start();
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
