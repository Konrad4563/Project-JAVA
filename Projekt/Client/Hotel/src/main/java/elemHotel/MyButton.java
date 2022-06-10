package elemHotel;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */

public class MyButton extends Button {
    public MyButton(String buttonName, int layoutX, int layoutY, int minHeight, int minWidth, Color color) {

        this.setText(buttonName);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setMinHeight(minHeight);
        this.setMinWidth(minWidth);
        this.setTextFill(color);
    }
}
