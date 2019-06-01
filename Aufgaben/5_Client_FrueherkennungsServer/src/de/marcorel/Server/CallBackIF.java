package de.marcorel.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallBackIF extends Remote {
    public void setAnalyse(Entity.Bericht b) throws RemoteException;
}
