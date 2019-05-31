package Entity;

import java.io.Serializable;
import java.util.Date;

public class Bericht implements BerichtIF {
    private Date datum;
    private String diagnose;
    private String weiteresVorgehen;


    public Bericht(String diagn, String wVorgehen) {
        this.datum = new Date();
        this.diagnose = diagn;
        this.weiteresVorgehen = wVorgehen;
    }


    @Override //Für Remote-access
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override //Für Remote-access
    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    @Override //Für Remote-access
    public String getWeiteresVorgehen() {
        System.out.println("weiteres Vorgehen.");
        return this.weiteresVorgehen;
    }

    public void setWeiteresVorgehen(String weiteresVorgehen) {
        this.weiteresVorgehen = weiteresVorgehen;
    }
}
