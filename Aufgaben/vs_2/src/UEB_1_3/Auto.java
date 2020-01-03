package UEB_1_3;

import java.util.Random;

public class Auto extends Thread {
    private String kennzeichen = "";
    private String status = "fährt herum.";
    private Parkhaus parkhaus;
    //Constructor
    public Auto(String kennz, Parkhaus parkhaus) {
        this.kennzeichen = kennz;
        this.parkhaus = parkhaus;
    }


    //run
    @Override
    public void run() {
        //herumfahren
        Random zufallsZahl = new Random();

        while(true) {
            int fahrzeit = zufallsZahl.nextInt(10);

            try {
                Thread.sleep(fahrzeit*1000);
                //einparken
            } catch (InterruptedException ire) {
                System.out.println("Interrupted Exception: "+ire);
                return;
            }

            //einfahrt
            parkhaus.einparken(this);
            int parkzeit = zufallsZahl.nextInt(10);

            try {
                Thread.sleep(parkzeit*500);
            } catch (InterruptedException ire) {
                System.out.println("Interrupted while parking"+ire);
            }
            this.parkhaus.ausparken(this);
        }


    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getKennzeichen() {
        return kennzeichen;
    }
    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }
}
