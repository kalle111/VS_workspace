package Server;

import Entity.Bericht;
import Entity.BerichtIF;
import Entity.Roentgenbild;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueerkennungIF extends Remote {
    //Remote-Funktionen müssen RemoteExceptions throwen. + Entitätsklassen serialisierbar.
    public BerichtIF analysieren(Roentgenbild r) throws RemoteException;
}
