package messages;

import java.io.Serializable;

/**
 *
 * @author Marcin Bonar
 *
 */

public class Grafik implements Serializable {
    private String imie;
    private String nazwisko;

    //dane potrzebne do dodania grafiku
    private String day;
    private String hours;


    //dane potrzebne do wyswietlenia grafiku
    private String pon;
    private String wt;
    private String sr;
    private String czw;
    private String pt;
    private String sb;
    private String nd;



    public Grafik(String imie, String nazwisko, String day,String hours) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.day = day;
        this.hours = hours;
    }

    public Grafik(String imie, String nazwisko, String pon,String wt,String sr,String czw,String pt,String sb,String nd) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pon = pon;
        this.wt = wt;
        this.sr = sr;
        this.czw = czw;
        this.pt = pt;
        this.sb = sb;
        this.nd = nd;
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

    public String getDay() {return day;}

    public void setDay(String day) {this.day = day;}

    public String getHours() {return hours;}

    public void setHours(String hours) {this.hours = hours;}

    public String getPon() {return pon;}

    public String getWt() {return wt;}

    public String getSr() {return sr;}

    public String getCzw() {return czw;}

    public String getPt() {return pt;}

    public String getSb() {return sb;}

    public String getNd() {return nd;}
}
