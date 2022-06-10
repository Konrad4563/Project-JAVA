package elemHotel;

import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */

public class MyCheckBox extends CheckBox {
    public MyCheckBox(String buttonName, int layoutX, int layoutY, int minHeight, int minWidth, Font font,Boolean setSelected) {
        this.setText(buttonName);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.minHeight(minHeight);
        this.minWidth(minWidth);
        this.setFont(font);
        this.setSelected(setSelected);



    }
}
