package Entity;

import java.io.Serializable;
import java.util.Date;

public class Roentgenbild implements Serializable {
    private Date aufnahmeVom;
    private transient String patientenName;
    private byte[] rawData;

    public Roentgenbild(Date d, String patName, byte[] rawData) {
        this.aufnahmeVom = d;
        this.patientenName = patName;
        this.rawData = rawData;
    }

    public Date getAufnahmeVom() {
        return aufnahmeVom;
    }

    public void setAufnahmeVom(Date aufnahmeVom) {
        this.aufnahmeVom = aufnahmeVom;
    }

    public String getPatientenName() {
        return patientenName;
    }

    public void setPatientenName(String patientenName) {
        this.patientenName = patientenName;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }
}
