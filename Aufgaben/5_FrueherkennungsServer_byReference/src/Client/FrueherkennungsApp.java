package Client;

import Entity.Bericht;
import Entity.Roentgenbild;
import Entity.RoentgenbildIF;
import Server.FrueerkennungIF;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class FrueherkennungsApp {
    public static void main(String[] args) throws RemoteException, NotBoundException {

        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
        FrueerkennungIF server = (FrueerkennungIF)reg.lookup("FrueherkennungsService");

        byte[] array = {0,1,0};
        RoentgenbildIF roentgenb1 = new Roentgenbild(new Date(), "Marc Orel", array);
        RoentgenbildIF roentgenb1stub = (RoentgenbildIF) UnicastRemoteObject.exportObject(roentgenb1,0);
        Bericht bericht = server.analysieren(roentgenb1stub);

        System.out.println(bericht.getDiagnose() + " => Diagnose | vom: " + bericht.getDatum() + " | Weiteres Vorgehen: " + bericht.getWeiteresVorgehen());
    }
}
