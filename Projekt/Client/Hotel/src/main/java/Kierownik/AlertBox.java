package Kierownik;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */

public class AlertBox {
    public static void wyswietl(String title, String message,boolean o){
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinHeight(150);
        stage.setMinWidth(150);

        Font serif = Font.font("Serif", 30);
        Label label=new Label();
        label.setFont(serif);
        label.setText(message);
        label.setAlignment(Pos.CENTER);
        if(o==true){
            label.setStyle("-fx-font-size: 20px; -fx-background-color: #61E938; -fx-padding: 10px;");
        }
        else{
            label.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-background-color: C93939; -fx-padding: 10px;");
        }


        Button closeButton=new Button("Zamknij okno");
        closeButton.setOnAction(e->stage.close());

        VBox vBox=new VBox();
        vBox.getChildren().addAll(label,closeButton);
        vBox.setAlignment(Pos.CENTER);

        Scene scene=new Scene(vBox);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.showAndWait();

    }

    public void display(String dodano_pracownika, String toString) {
    }
}
