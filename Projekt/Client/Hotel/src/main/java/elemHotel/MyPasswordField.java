package elemHotel;

import javafx.scene.control.PasswordField;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */

public class MyPasswordField extends PasswordField {
    MyPasswordField(int layoutX, int layoutY, int minHeight, int minWidth,String promText){
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setMinHeight(minHeight);
        this.setMinWidth(minWidth);
        this.setPromptText(promText);
    }
}
