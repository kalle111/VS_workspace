package Client;

import Entity.Bericht;
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
        FrueerkennungIF stub = (FrueerkennungIF)reg.lookup("FrueherkennungsServerController");

        byte[] array = {0,1,0};
        Roentgenbild roentgenb1 = new Roentgenbild(new Date(), "Marc Orel", array);

        Bericht b1 = stub.analysieren(roentgenb1);
    }
}
