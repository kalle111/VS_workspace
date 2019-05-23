package de.marcorel;

import java.util.Random;

public class Auto {
    public Hersteller getHersteller() {
        return hersteller;
    }

    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    //Auto-Klasse
    //private String Kennzeichen;
    private Hersteller hersteller;
    private String Name;

    public Auto(Hersteller h, String name) {
        //super(kennzeichen); // Weitergabe an Konstruktor der Klasse Thread => deswegen extends Thread
        this.hersteller = h;
        this.Name = name;
        //setDaemon(true); // Hindert die JVM nicht am beenden des Thread! => wichtig für Aufgabe (d)
    }

    /*@Override
    public void run() {
        // Starte Thread für 1 Auto

        //ZufallsZahlenGenerator erstellen
        Random ZufallsZahl = new Random();

        while(true) {
            //Auto soll ständig ein und aus fahren!

            //Auto soll aber randomisierte Zeit warten, vor der nächsten Aktion.
            int fahrzeit = ZufallsZahl.nextInt(10);

            //Thread soll <<fahrzeit>> lang schlafen => Falls interrupted während dem Schlaf, wird eine Exception gecatched.
            try {
                Thread.sleep((fahrzeit*1000));
            } catch (InterruptedException e) {
                System.out.println("Thread wurde während dem Fahren aufgeweckt. Beende Thread.");
                return; //beendet den Thread
            }
            //Nachdem zufällig lang gefahren wurde wird nun der Einparkvorgange eingeleitet
            this.parkhaus.produzierteAutosAbstellen();

            // Erstellung der Parkzeit, wenn eingeparkt wurde (try/catch)
            int parkzeit = ZufallsZahl.nextInt(10);
            try {
                Thread.sleep(parkzeit*1000);
            } catch (InterruptedException e) {
                System.out.println("Thread wurde während dem Parken aufgeweckt => wird beendet.");
                return;
            }
            this.parkhaus.verkaufen();
        }

        */

}
