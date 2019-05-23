package de.marcorel;

import java.util.Random;

public class Auto extends Thread {
    //Auto-Klasse
    //private String Kennzeichen;
    private Parkhaus parkhaus;

    public Auto(String kennzeichen, Parkhaus ph) {
        super(kennzeichen); // Weitergabe an Konstruktor der Klasse Thread => deswegen extends Thread
        this.parkhaus = ph;
        setDaemon(true); // Hindert die JVM nicht am beenden des Thread! => wichtig für Aufgabe (d)
    }

    @Override
    public void run() {
        // Starte Thread für 1 Auto

        //ZufallsZahlenGenerator erstellen
        Random ZufallsZahl = new Random();

        while(true) {
            //Auto soll ständig ein und aus fahren!

            //Auto soll aber randomisierte Zeit warten, vor der nächsten Aktion.
            int fahrzeit = ZufallsZahl.nextInt(5);

            //Thread soll <<fahrzeit>> lang schlafen => Falls interrupted während dem Schlaf, wird eine Exception gecatched.
            try {
                Thread.sleep((fahrzeit*1000));
            } catch (InterruptedException e) {
                System.out.println("Thread wurde während dem Fahren aufgeweckt. Beende Thread.");
                return; //beendet den Thread
            }
            //Nachdem zufällig lang gefahren wurde wird nun der Einparkvorgange eingeleitet
            this.parkhaus.einfahren();

            // Erstellung der Parkzeit, wenn eingeparkt wurde (try/catch)
            int parkzeit = ZufallsZahl.nextInt(2);
            try {
                Thread.sleep(parkzeit*1000);
            } catch (InterruptedException e) {
                System.out.println("Thread wurde während dem Parken aufgeweckt => wird beendet.");
                return;
            }
            this.parkhaus.ausfahren();
        }


    }
}
