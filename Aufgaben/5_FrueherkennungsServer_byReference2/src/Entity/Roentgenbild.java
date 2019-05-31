package Entity;

import java.io.Serializable;
import java.util.Date;

public class Roentgenbild implements RoentgenbildIF {
    private Date aufnahmeVom;
    private transient String patientenName;
    private byte[] rawData;


    public Roentgenbild(Date d, String patName, byte[] rawData) {
        this.aufnahmeVom = d;
        this.patientenName = patName;
        this.rawData = rawData;
    }
    // ################  Methoden zum Referenzierten Anlegen des Roentgenbildes!
    @Override
    public Date getAufnahmeDate1() {
        //Beispiel Date, August 31, 2008
        return this.aufnahmeVom;
    }

    @Override
    public String getPatientenName1() {
        return this.patientenName;
    }

    @Override
    public byte[] getRawData1() {
        return this.rawData;
    }
    //################################################################


    public String getName() {
        return this.patientenName;
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
