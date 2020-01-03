package UEB_1_3;


public class Parkhaus {
    private int parkplatzKapazität;
    private int belegt = 0;
    private final Object schranke = new Object();


    public Parkhaus(int a) {
        this.parkplatzKapazität = a;
    }

    public void einparken(Auto auto) {
        //einparken logik

        synchronized (schranke) {
            while(belegt >= parkplatzKapazität) {
                try {
                    System.out.println("Alle Parkplätze belegt. Kennzeichen: " + auto.getKennzeichen() + " >>>> wartet an Schranke.");
                    schranke.wait();
                } catch (InterruptedException ioe) {
                    System.out.println("Interupted Exception: " + ioe);
                }
            }
            //while nicht mehr voll belegt
            this.belegt++;
            System.out.println(auto.getKennzeichen() + " >> eingefahren.");
            schranke.notifyAll();
            // sowohl an der Schranke könnten Autos warten, als auch im Parkhaus auf ein/ausfahrt.
        }
    }

    public void ausparken(Auto auto) {
        //ausparken logik
        synchronized (schranke) {
            while(belegt <= 5) {
                try {
                    System.out.println("****************Es müssen min. 2 Parkplätze belegt sein. Auto: " + auto.getKennzeichen() + " #### wartet in Parkhaus.");
                    schranke.wait();
                } catch (InterruptedException ire) {
                    System.out.println("InterruptedException: " + ire);
                }
            }
            this.belegt--;
            System.out.println(auto.getKennzeichen() + " ## ausgefahren.");
            schranke.notifyAll();
        }
    }
}
