package messages;

import java.io.Serializable;

/**
 *
 * @author Marcin Bonar
 *
 */

public class Rooms implements Serializable {
    String stan;
    int nr_pokoju;
    String pokoj_typ_pokoju;
    int cena;

    public Rooms(int nr_pokoju) {
        this.nr_pokoju = nr_pokoju;
    }

    public Rooms(String stan, int nr_pokoju, String pokoj_typ_pokoju, int cena) {
        this.stan = stan;
        this.nr_pokoju = nr_pokoju;
        this.pokoj_typ_pokoju = pokoj_typ_pokoju;
        this.cena = cena;
    }

    public String getStan() {
        return stan;
    }

    public int getNr_pokoju() {
        return nr_pokoju;
    }

    public String getPokoj_typ_pokoju() {
        return pokoj_typ_pokoju;
    }

    public int getCena() {
        return cena;
    }

}
