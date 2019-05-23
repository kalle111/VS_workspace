package de.marcorel;

import java.util.Deque;
import java.util.LinkedList;

public class Parkhaus {
    //Passive Klasse Parkhaus
    private Deque<Auto> slots = new LinkedList<>();
    private int slots_used;
    private final Object schranke = new Object();

    // Konstruktor natürlich
    public Parkhaus() {
        this.slots_used = 0;
    }

    //Methoden für aktive Klassen
    public void produzierteAutosAbstellen(Auto auto) {
        //Beim Einfahren entsteht ein Engpass, da viele Threads (= 1 Auto pro Thread) möglicherweise einparken möchten UND Parkhaus möglicherweise voll belegt ist.

        synchronized (schranke) {
            //Einfahrt muss synchronisiert werden! => verhindert gleichzeitigen Zugriff!
            //um zu prüfen, ob das Auto abgestellt werden darf => verhindert überparkung des Parkhauses!
            while(slots.size() > 9) {
                //Parkhaus ist belegt.

                try {
                    System.out.println("\n# Kein Platz für produziertes Auto, Parkhaus voll belegt: " + auto.getName() + ", Threadname: " + Thread.currentThread().getName());
                    schranke.wait();
                } catch (InterruptedException e) {
                    //Exception-Handling comes here
                }
            } // Wenn die slots_used < slots Dann
            System.out.println("\n######## Produziertes Auto abgestellt: " + Thread.currentThread().getName() + ", kfzID: " + auto.getName());
            slots.addLast(auto);
            schranke.notifyAll();
            //glaube ist necessary wenn zwei warten
        }
    }

    public void anAutohausVerkaufen() {
        synchronized (schranke) {
            while(slots.size() < 1) {
                try {
                    System.out.println("## Verkauf muss warten, bis ein Auto geliefert wird!!");
                    schranke.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //this.slots_used--;
            System.out.println(">>> Auto wird verkauft: " + slots.getFirst().getName() + ", vom Hersteller: " + slots.getFirst().getHersteller().getName());
            slots.remove();
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

}
