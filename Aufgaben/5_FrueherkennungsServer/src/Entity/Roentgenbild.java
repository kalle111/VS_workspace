package Entity;

import java.util.Date;

public class Roentgenbild {
    private Date aufnahmeVom;
    private transient String patientenName;
    private byte[] rawData;

    public Roentgenbild(Date d, String patName, byte[] rawData) {
        this.aufnahmeVom = d;
        this.patientenName = patName;
        this.rawData = rawData;
    }
}
