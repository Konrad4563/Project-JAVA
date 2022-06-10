package Pracownik;

import javafx.scene.control.CheckBox;
import java.io.Serializable;

/**
 *
 * @author Konrad Basa
 *
 */

public class AmenitiesC implements Serializable {
    String nazwa;
    float cena;
    String typ_pokoju;
    CheckBox select;

    public AmenitiesC(String nazwa, float cena, String typ_pokoju) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.typ_pokoju = typ_pokoju;
        this.select = new CheckBox();
    }

    public AmenitiesC(String nazwa, float cena) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.select = new CheckBox();
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public String getTyp_pokoju() {
        return typ_pokoju;
    }

    public void setTyp_pokoju(String typ_pokoju) {
        this.typ_pokoju = typ_pokoju;
    }

    public CheckBox getSelect(){
        return select;
    }

    public void setSelect(CheckBox select){
        this.select = select;
    }
}
