package de.marcorel;

public class Parkhaus {
    //Passive Klasse Parkhaus
    private int slots;
    private int slots_used;
    private final Object schranke = new Object();

    // Konstruktor natürlich
    public Parkhaus(int slotNr) {
        this.slots = slotNr;
        this.slots_used = 0;
    }

    //Methoden für aktive Klassen
    public void einfahren() {
        //Beim Einfahren entsteht ein Engpass, da viele Threads (= 1 Auto pro Thread) möglicherweise einparken möchten UND Parkhaus möglicherweise voll belegt ist.

        synchronized (schranke) {
            //Einfahrt muss synchronisiert werden! => verhindert gleichzeitigen Zugriff!
            //um zu prüfen, ob das Auto einfahren darf => verhindert überparkung des Parkhauses!
            while(slots_used >= slots) {
                //Parkhaus ist belegt.

                try {
                    System.out.println("########wartet an der Schranke: " + Thread.currentThread().getName());
                    schranke.wait();
                } catch (InterruptedException e) {
                    //Exception-Handling comes here
                }
            } // Wenn die slots_used < slots Dann
            System.out.println(" >> Einfachrt: " + Thread.currentThread().getName());
            slots_used++;
        }
    }

    public void ausfahren() {
        synchronized (schranke) {

            while(slots_used < 5) {

                try {
                    System.out.println("################## --> wartet auf Ausfahrt.");
                    schranke.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.slots_used--;
            System.out.println(" >> Ausfahrt: " + Thread.currentThread().getName());
            schranke.notifyAll();
        }
    }

    // Getter & Setter
    public int getSlots_used() {
        return slots_used;
    }

    public void setSlots_used(int slots_used) {
        this.slots_used = slots_used;
    }



    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }
}
