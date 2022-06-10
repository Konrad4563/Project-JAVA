package messages;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Konrad Basa
 *
 */

public class Promotion implements Serializable {
    String nazwa;
    String wartosc;
    LocalDate dat_roz;
    LocalDate dat_zak;
    String rodzaj_pokoju;

    public Promotion(String nazwa, String wartosc, LocalDate dat_roz, LocalDate dat_zak,String rodzaj_pokoju) {
        this.nazwa = nazwa;
        this.wartosc = wartosc;
        this.dat_roz = dat_roz;
        this.dat_zak = dat_zak;
        this.rodzaj_pokoju = rodzaj_pokoju;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getWartosc() {
        return wartosc;
    }

    public LocalDate getDat_roz() {
        return dat_roz;
    }

    public LocalDate getDat_zak() {
        return dat_zak;
    }

    public String getRodzaj_pokoju() {
        return rodzaj_pokoju;
    }

}
