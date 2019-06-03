package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Adresse {

    private String strasse;
    private String plz;
    private String ort;

    public Adresse(){}

    public Adresse(String s, String p, String o) {
        this.strasse = s;
        this.plz = p;
        this.ort = o;
    }


    @Override
    public String toString() {
        return (this.strasse + ", " + plz + ", " + ort);
    }


}
