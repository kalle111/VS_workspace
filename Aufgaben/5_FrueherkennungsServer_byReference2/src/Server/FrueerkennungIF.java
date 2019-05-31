package Server;

import Entity.Bericht;
import Entity.Roentgenbild;
import Entity.RoentgenbildIF;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueerkennungIF extends Remote {
    //Remote-Funktionen müssen RemoteExceptions throwen. + Entitätsklassen serialisierbar.
    public Bericht analysieren(RoentgenbildIF r) throws RemoteException;
    //public void roentgenBildAnlegen(RoentgenbildIF rb)throws RemoteException;
}
