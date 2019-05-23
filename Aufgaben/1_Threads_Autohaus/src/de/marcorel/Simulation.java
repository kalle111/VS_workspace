package de.marcorel;

public class Simulation {

    public static void main(String[] args) {
	// write your code here
        //System.out.println("Hello World");

        //Erstellung Parkhaus
        Parkhaus ph = new Parkhaus();

        //Erstellung Autos
        /*for(int i = 0; i<20; i++){
            Thread t = new Auto("BMW-" + (i+1), ph);
            t.start();
        }*/

        //Nun wird der Hersteller erstellt
        Hersteller hersteller1 = new Hersteller("BMW", ph);
        hersteller1.start();

        //Nun autohäuser erstllen
        for (int i = 0; i <5 ; i++) {
            Autohaus temp = new Autohaus(("Haus #" + (i+1)),ph);
            temp.run();
        }

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
