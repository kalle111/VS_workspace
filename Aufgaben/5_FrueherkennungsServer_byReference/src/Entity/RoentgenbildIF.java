package Entity;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface RoentgenbildIF extends Remote {
    public Date getAufnahmeDate1() throws RemoteException;
    public String getPatientenName1() throws RemoteException;
    public byte[] getRawData1() throws RemoteException;
    //public Bericht analysieren(RoentgenbildIF rb);
}
