package Klient;

import javafx.scene.control.RadioButton;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Piotr Bielecki
 *
 */

public class ComplaintC implements Serializable {
    LocalDate dat_roz;
    LocalDate dat_zak;
    String roomNumber;
    String roomType;
    String bill;
    String idOfReservation;
    RadioButton select;

    public ComplaintC(LocalDate dat_roz, LocalDate dat_zak, String roomNumber, String roomType, String bill, String idOfReservation) {
        this.dat_roz = dat_roz;
        this.dat_zak = dat_zak;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.bill = bill;
        this.idOfReservation = idOfReservation;
        this.select = new RadioButton();
    }

    public LocalDate getDat_roz() {
        return dat_roz;
    }

    public void setDat_roz(LocalDate dat_roz) {
        this.dat_roz = dat_roz;
    }

    public LocalDate getDat_zak() {
        return dat_zak;
    }

    public void setDat_zak(LocalDate dat_zak) {
        this.dat_zak = dat_zak;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getIdOfReservation() {
        return idOfReservation;
    }

    public void setIdOfReservation(String idOfReservation) {
        this.idOfReservation = idOfReservation;
    }

    public RadioButton getSelect() {
        return select;
    }

    public void setSelect(RadioButton select) {
        this.select = select;
    }
}
