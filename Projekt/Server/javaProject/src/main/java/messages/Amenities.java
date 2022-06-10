package messages;

import java.io.Serializable;

/**
 *
 * @author Konrad Basa
 *
 */

public class Amenities implements Serializable {
    String nazwa;
    float cena;
    String typ_pokoju;

    public Amenities(String nazwa, float cena, String typ_pokoju) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.typ_pokoju = typ_pokoju;
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


}
