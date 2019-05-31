package Server;

import Entity.Bericht;
import Entity.Roentgenbild;
import Entity.RoentgenbildIF;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;

public class FrueherkennungsServer implements FrueerkennungIF {
    private int berichtsNummer = 1;

    public static void main (String[]args) throws RemoteException, AlreadyBoundException {
        //sth.
        FrueerkennungIF instanz = new FrueherkennungsServer();
        FrueerkennungIF stub = (FrueerkennungIF) UnicastRemoteObject.exportObject(instanz, 0);

        LocateRegistry.createRegistry(1099);
        Registry reg = LocateRegistry.getRegistry("localhost", 1099);
        reg.bind("FrueherkennungsService",stub); //stub unter Namen hochladen.
        System.out.printf("Server gestartet, Registry created && gebindet!\nServer listening...");
    }

    @Override
    public Bericht analysieren(RoentgenbildIF rb) throws RemoteException {
        //so wird die Analyse behandelt
        System.out.println("Röntgenbild vom: " + rb.getAufnahmeDate1() +" wird nun analysiert!");
        Bericht b = new Bericht("Sprunggelenksbruch", "Kühlen");
        return b;
    }

}
