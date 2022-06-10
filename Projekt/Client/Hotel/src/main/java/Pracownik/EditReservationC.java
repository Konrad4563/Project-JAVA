package Pracownik;

import javafx.scene.control.RadioButton;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Konrad Basa
 *
 */

public class EditReservationC implements Serializable {
    String idOfReservation;
    String imie;
    String nazwisko;
    String nr_pokoju;
    LocalDate dataPrzyjazdu;
    LocalDate dataOdjazdu;
    String toPay;
    RadioButton select;

    public EditReservationC(String idOfReservation,String imie, String nazwisko, String nr_pokoju, LocalDate dataPrzyjazdu, LocalDate dataOdjazdu, String toPay) {
        this.idOfReservation = idOfReservation;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nr_pokoju = nr_pokoju;
        this.dataPrzyjazdu = dataPrzyjazdu;
        this.dataOdjazdu = dataOdjazdu;
        this.toPay = toPay;
        this.select = new RadioButton();
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNr_pokoju() {
        return nr_pokoju;
    }

    public void setNr_pokoju(String nr_pokoju) {
        this.nr_pokoju = nr_pokoju;
    }

    public LocalDate getDataPrzyjazdu() {
        return dataPrzyjazdu;
    }

    public void setDataPrzyjazdu(LocalDate dataPrzyjazdu) {
        this.dataPrzyjazdu = dataPrzyjazdu;
    }

    public LocalDate getDataOdjazdu() {
        return dataOdjazdu;
    }

    public void setDataOdjazdu(LocalDate dataOdjazdu) {
        this.dataOdjazdu = dataOdjazdu;
    }

    public String getToPay() {
        return toPay;
    }

    public void setToPay(String toPay) {
        this.toPay = toPay;
    }

    public RadioButton getSelect() {
        return select;
    }

    public void setSelect(RadioButton select) {
        this.select = select;
    }

    public String getIdOfReservation() {return idOfReservation;}

    public void setIdOfReservation(String idOfReservation) {this.idOfReservation = idOfReservation;}

}
