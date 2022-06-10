package messages;

import java.io.Serializable;

/**
 *
 * @author Piotr Bielecki
 *
 */

public class Opinion implements Serializable {
    String tresc;
    float gwiazdki;

    public Opinion(String tresc, float gwiazdki) {
        this.tresc = tresc;
        this.gwiazdki = gwiazdki;
    }

    public String getTresc() {return tresc;}

    public void setTresc(String tresc) {this.tresc = tresc;}

    public float getGwiazdki() {return gwiazdki;}

    public void setGwiazdki(float gwiazdki) {this.gwiazdki = gwiazdki;}
}
