package messages;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Piotr Bielecki
 *
 */

public class Complaint implements Serializable {
    String imie;
    String nazwisko;
    String phoneNumber;

    LocalDate dat_roz;
    LocalDate dat_zak;
    String roomNumber;
    String roomType;
    String bill;
    String idOfReservation;
    String tresc;

    public Complaint(String imie, String nazwisko, String phoneNumber, String idOfReservation, String tresc) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.phoneNumber = phoneNumber;
        this.idOfReservation = idOfReservation;
        this.tresc = tresc;
    }

    public Complaint(String idOfReservation, String tresc) {
        this.idOfReservation = idOfReservation;
        this.tresc = tresc;
    }

    public Complaint(LocalDate dat_roz, LocalDate dat_zak, String roomNumber, String roomType, String bill, String idOfReservation) {
        this.dat_roz = dat_roz;
        this.dat_zak = dat_zak;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.bill = bill;
        this.idOfReservation = idOfReservation;
    }

    public Complaint(String imie, String nazwisko, String phoneNumber) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }
}
