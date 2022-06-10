package Klient;

import Kierownik.AlertBox;
import Pracownik.AmenitiesC;
import elemHotel.MyButton;
import elemHotel.MyLabel;
import elemHotel.ServerConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import messages.Amenities;
import messages.Opinion;
import messages.Reservation;
import messages.ServerOperation;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Piotr Bielecki
 *
 */


public class OpiniaKlienta {
    Tab tab;
    String starCounter="1";
    public OpiniaKlienta(Tab tab){
        this.tab=tab;
    }
    public void wywolanieZakladkiOpiniaKlienta(){
        Font font = Font.font("Arial", FontWeight.BOLD, 14);

        Image image = new Image("OpiniaKlienta.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(600);
        imageView.setFitWidth(1100);

        //ograniczenia dla poszczegolnych TextFieldow
        UnaryOperator<TextFormatter.Change> mes = change -> {
            String newText = change.getControlNewText();
            if (newText.length() > 500) {
                return null ;
            } else {
                return change ;
            }
        };

        MyLabel label4=new MyLabel("Dodaj Opinie na Temat Hotelu ",10,10,font, Color.WHITE);
        TextArea myOpinion=new TextArea();
        myOpinion.setLayoutX(10);
        myOpinion.setLayoutY(40);
        myOpinion.setPrefWidth(380);
        myOpinion.setMinHeight(280);
        myOpinion.setWrapText(true);
        myOpinion.setTextFormatter(new TextFormatter<String> (mes));

        MyButton addOpinion=new MyButton("Dodaj Opinie",400,130,30,200,Color.BLUE);

        MyLabel opinion=new MyLabel("Oceń nas",440,30,font, Color.WHITE);

        Slider slider1 = new Slider(1, 5, 1);
        slider1.setLayoutX(400);
        slider1.setLayoutY(100);
        slider1.setPrefWidth(150);

        Image image1 = new Image("star.png");
        Image image2 = new Image("emptyStar.png");


        //ustwaienia do złotej gwiazdki
        ImageView star1 = new ImageView(image1);
        star1.setLayoutX(400);
        star1.setLayoutY(60);
        ImageView star2 = new ImageView(image1);
        star2.setLayoutX(430);
        star2.setLayoutY(60);
        star2.setVisible(false);
        ImageView star3 = new ImageView(image1);
        star3.setLayoutX(460);
        star3.setLayoutY(60);
        star3.setVisible(false);
        ImageView star4 = new ImageView(image1);
        star4.setLayoutX(490);
        star4.setLayoutY(60);
        star4.setVisible(false);
        ImageView star5 = new ImageView(image1);
        star5.setLayoutX(520);
        star5.setLayoutY(60);
        star5.setVisible(false);

        //ustwaienia do pustej gwiazdki
        ImageView estar2 = new ImageView(image2);
        estar2.setLayoutX(430);
        estar2.setLayoutY(60);
        estar2.setVisible(true);
        ImageView estar3 = new ImageView(image2);
        estar3.setLayoutX(460);
        estar3.setLayoutY(60);
        estar3.setVisible(true);
        ImageView estar4 = new ImageView(image2);
        estar4.setLayoutX(490);
        estar4.setLayoutY(60);
        estar4.setVisible(true);
        ImageView estar5 = new ImageView(image2);
        estar5.setLayoutX(520);
        estar5.setLayoutY(60);
        estar5.setVisible(true);


        slider1.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                if(new_val.doubleValue() >=2){
                    estar2.setVisible(false);
                    star2.setVisible(true);
                }
                else {
                    star2.setVisible(false);
                    estar2.setVisible(true);
                }
                if(new_val.doubleValue() >=3){
                    estar3.setVisible(false);
                    star3.setVisible(true);
                }
                else {
                    star3.setVisible(false);
                    estar3.setVisible(true);
                }
                if(new_val.doubleValue() >=4){
                    estar4.setVisible(false);
                    star4.setVisible(true);
                }
                else {
                    star4.setVisible(false);
                    estar4.setVisible(true);
                }
                if(new_val.doubleValue() >=5){
                    estar5.setVisible(false);
                    star5.setVisible(true);
                }
                else {
                    star5.setVisible(false);
                    estar5.setVisible(true);
                }

                //liczba gwiazdek
                starCounter = String.valueOf((Math.round(parseFloat(String.valueOf(new_val)))));

            }
        });

        //dodanie opinii
        addOpinion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!myOpinion.getText().isEmpty()) {
                    Opinion newOpinion = new Opinion(
                                myOpinion.getText(),
                                parseFloat(starCounter)
                    );
                    Opinion message = (Opinion) ServerConnection.sendToServer.send(ServerOperation.addOpinion, newOpinion);
                    if(message!=null){
                        AlertBox.wyswietl("Wiadomość","Dodano opinie",true);
                        myOpinion.clear();
                        slider1.setValue(1);
                    }
                    else{
                        AlertBox.wyswietl("Wiadomość","Coś poszło nie tak",false);
                    }
                }
                else{
                    AlertBox.wyswietl("Wiadomość","Uzupełnij pole tekstowe",false);
                }
            }
        });

        Group group1=new Group();

        group1.getChildren().addAll(imageView,
                star1,star2,star3,star4,star5,
                estar2,estar3,estar4,estar5,
                label4,addOpinion,myOpinion,slider1,opinion);
        tab.setContent(group1);

    }
}
