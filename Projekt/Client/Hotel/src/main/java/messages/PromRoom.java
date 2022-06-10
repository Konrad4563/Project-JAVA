package messages;

import java.io.Serializable;

/**
 *
 * @author Konrad Basa
 *
 */

public class PromRoom implements Serializable {
    String nazwa;
    String typ_pokoju;


    public PromRoom(String nazwa,String typ_pokoju) {
        this.nazwa = nazwa;
        this.typ_pokoju = typ_pokoju;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getTyp_pokoju() {
        return typ_pokoju;
    }

}
