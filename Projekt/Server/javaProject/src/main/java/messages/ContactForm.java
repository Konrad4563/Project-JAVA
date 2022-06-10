package messages;

import java.io.Serializable;

/**
 *
 * @author Piotr Bielecki
 *
 */

public class ContactForm implements Serializable {
    String imie;
    String nazwisko;
    String nr_tel;
    String email;
    String tresc;

    public ContactForm(String imie, String nazwisko, String nr_tel, String email, String tresc) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nr_tel = nr_tel;
        this.email = email;
        this.tresc = tresc;
    }

    public String getImie() {return imie;}

    public void setImie(String imie) {this.imie = imie;}

    public String getNazwisko() {return nazwisko;}

    public void setNazwisko(String nazwisko) {this.nazwisko = nazwisko;}

    public String getNr_tel() {return nr_tel;}

    public void setNr_tel(String nr_tel) {this.nr_tel = nr_tel;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getTresc() {return tresc;}

    public void setTresc(String tresc) {this.tresc = tresc;}


}
