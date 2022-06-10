package elemHotel;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */

public class MyLabel extends Label {
    public MyLabel(String labelName, int layoutX, int layoutY, Font font, Paint color){
        this.setText(labelName);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setFont(font);
        this.setTextFill(color);
    }
}
