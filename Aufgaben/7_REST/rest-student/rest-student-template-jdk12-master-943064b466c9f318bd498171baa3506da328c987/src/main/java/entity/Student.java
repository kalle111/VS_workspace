package entity;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Student {

    @XmlAttribute
    private int matrikelNr;
    private String vorname;
    private String nachname;
    private Adresse anschrift;

    // Default-Konstruktor zwingend notwendig
    public Student() {}

    public Student(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public Student(int matrikelNr, String vorname, String nachname) {
        this(vorname, nachname);
        this.matrikelNr = matrikelNr;
    }
    // Ein Konstruktor mit Anschrift-Attribut erweitert
    public Student(int matrikelNr, String vorname, String nachname, Adresse anschrift) {
        this(vorname,nachname);
        this.matrikelNr = matrikelNr;
        this.anschrift = anschrift;
    }

    public String getAdresse() {
        return (this.anschrift.toString());
    }
    public int getMatrikelNr() {
        return matrikelNr;
    }

    public void setMatrikelNr(int matrikelNr) {
        this.matrikelNr = matrikelNr;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setAnschrift(Adresse anschrift) {
        this.anschrift = anschrift;
    }

    public Adresse getAnschrift() {
        return this.anschrift;
    }


    @Override
    public String toString() {
        return ("Nachname: " + this.nachname + ", Vorname: " + this.vorname + ", Matrikelnummer: " + this.matrikelNr + ", Anschrift: " + this.anschrift.toString());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return matrikelNr == student.matrikelNr;
    }

    @Override
    public int hashCode() {

        return Objects.hash(matrikelNr);
    }
}
