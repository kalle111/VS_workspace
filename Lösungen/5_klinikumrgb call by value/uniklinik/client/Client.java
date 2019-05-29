package de.uniklinik.client;

import de.lmu.Bericht;
import de.lmu.FrueherkennungIF;
import de.lmu.Roentgenbild;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

public class Client {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            FrueherkennungIF stub = (FrueherkennungIF) registry.lookup("LMU-Frueherkennungs-Service");

            Roentgenbild roentgenbild = new Roentgenbild("Max Muster", new Date());

            System.out.println("Röntgenbild wird jetzt an Server geschickt...");

            Bericht bericht = stub.analysieren(roentgenbild);

            System.out.println("...Arztbericht erhalten: " + bericht.toString());

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
