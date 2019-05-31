package Entity;

import java.io.Serializable;
import java.util.Date;

public class Bericht implements Serializable {
    private Date datum;
    private String diagnose;
    private String weiteresVorgehen;

    public Bericht(String diagn, String wVorgehen) {
        this.datum = new Date();
        this.diagnose = diagn;
        this.weiteresVorgehen = wVorgehen;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getWeiteresVorgehen() {
        return weiteresVorgehen;
    }

    public void setWeiteresVorgehen(String weiteresVorgehen) {
        this.weiteresVorgehen = weiteresVorgehen;
    }
}
