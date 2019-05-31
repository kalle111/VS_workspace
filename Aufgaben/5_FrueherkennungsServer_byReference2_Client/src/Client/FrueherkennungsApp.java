package Client;

import Entity.Bericht;
import Entity.BerichtIF;
import Entity.Roentgenbild;
import Server.FrueerkennungIF;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

public class FrueherkennungsApp {
    public static void main(String[] args) throws RemoteException, NotBoundException {

        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
        FrueerkennungIF stub = (FrueerkennungIF)reg.lookup("FrueherkennungsService");

        byte[] array = {0,1,0};
        Roentgenbild roentgenb1 = new Roentgenbild(new Date(), "Marc Orel", array);

        BerichtIF b1 = stub.analysieren(roentgenb1);
        System.out.println("Bericht b1 vom " + b1.getDatum() + " liefert folgende Diagnose: " + b1.getDiagnose() + ". Behandelt wir dies wie folgt: " + b1.getWeiteresVorgehen());
    }
}
