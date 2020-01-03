package UEB_1_3;

import java.util.Random;

public class Car {
    private String kennzeichen = "";
    //Constructor
    public Car(String kennz) {
        this.kennzeichen = kennz;

    }
    public String getKennzeichen() {
        return kennzeichen;
    }
    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }
}
