package messages;

import java.io.Serializable;

/**
 *
 * @author Marcin Bonar
 *
 */

public class ShowTypeRoom implements Serializable {
    int id;
    String name;
    float price;

    public ShowTypeRoom(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
