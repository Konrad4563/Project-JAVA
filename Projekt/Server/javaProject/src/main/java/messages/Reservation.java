package messages;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Piotr Bielecki
 *
 */


public class Reservation implements Serializable {
    String imie;
    String nazwisko;
    String phoneNumber;
    String email;
    LocalDate dat_roz;
    LocalDate dat_zak;
    String roomType;
    List<String> amenitiesList = new LinkedList<String>();
    int errorCode;
    String idOfReservation;

    public Reservation(LocalDate dat_roz, LocalDate dat_zak, String idOfReservation, String roomType) {
        this.dat_roz = dat_roz;
        this.dat_zak = dat_zak;
        this.idOfReservation = idOfReservation;
        this.roomType = roomType;
    }

    public Reservation(String idOfReservation){
        this.idOfReservation=idOfReservation;
    }

    public Reservation(String imie, String nazwisko, String phoneNumber, String email, LocalDate dat_roz, LocalDate dat_zak, String roomType, List<String> amenitiesList) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dat_roz = dat_roz;
        this.dat_zak = dat_zak;
        this.roomType = roomType;
        this.amenitiesList = amenitiesList;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDat_roz() {
        return dat_roz;
    }

    public LocalDate getDat_zak() {
        return dat_zak;
    }

    public String getRoomType() {
        return roomType;
    }

    public List<String> getAmenitiesList() {
        return amenitiesList;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {this.errorCode = errorCode;}

    public String getIdOfReservation() {return idOfReservation;}

    public void setIdOfReservation(String idOfReservation) {this.idOfReservation = idOfReservation;}
}
