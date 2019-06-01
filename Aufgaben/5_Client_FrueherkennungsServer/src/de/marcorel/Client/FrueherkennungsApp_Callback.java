package de.marcorel.Client;

import Entity.Bericht;
import Entity.Roentgenbild;
import Server.FrueerkennungIF;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

public class FrueherkennungsApp_Callback {
    public static void main(String[] args) throws RemoteException, NotBoundException {

        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
        Server.FrueerkennungIF stub = (Server.FrueerkennungIF)reg.lookup("FrueherkennungsService");

        byte[] array = {0,1,0};
        Entity.Roentgenbild roentgenb1 = new Entity.Roentgenbild(new Date(), "Marc Orel", array);

        Entity.Bericht b1 = stub.analysieren(roentgenb1);
        System.out.println("Diagnose: " + b1.getDiagnose() + ", weiteres Vorgehen: " + b1.getWeiteresVorgehen() + " (Bericht vom " + b1.getDatum() + ")");
    }

    /*public static void setAnalyse(Bericht b) {
        //returned void also nichts

    }*/


}
