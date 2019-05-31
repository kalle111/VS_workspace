package Entity;

import java.rmi.Remote;
import java.util.Date;

public interface BerichtIF extends Remote {
    public String getWeiteresVorgehen();
    public String getDiagnose();
    public Date getDatum();

}
