package messages;

import java.io.Serializable;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */

public class Position  implements Serializable {
    int id;
    String nazwa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Position(int id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }


}
