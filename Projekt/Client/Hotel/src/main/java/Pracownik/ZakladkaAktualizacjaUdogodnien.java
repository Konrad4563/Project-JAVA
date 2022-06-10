package Pracownik;

import Kierownik.AlertBox;
import Kierownik.Person;
import elemHotel.MyButton;
import elemHotel.MyLabel;
import elemHotel.MyTextField;
import elemHotel.ServerConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import messages.Amenities;
import messages.Pracownik;
import messages.Rooms;
import messages.ServerOperation;

import java.util.LinkedList;
import java.util.function.UnaryOperator;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Konrad Basa
 *
 */

public class ZakladkaAktualizacjaUdogodnien {
    Tab tab;
    public ZakladkaAktualizacjaUdogodnien(Tab tab){
        this.tab=tab;
    }
    public void wywolanieZakladkiAktualizacjaUdogo(){
        Font font = Font.font("Arial", FontWeight.BOLD, 14);

        Image image1 = new Image("AktuaUdog.jpg");
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitHeight(600);
        imageView1.setFitWidth(1100);
        UnaryOperator<TextFormatter.Change> pr = change -> {
            String newText = change.getControlNewText();
            if (newText.length() > 6 || !change.getControlNewText().matches("\\d*")) {
                return null ;
            } else {
                return change ;
            }
        };

        MyTextField amenitiesName=new MyTextField(20,60,30,200,"Podaj nazwę udogodnienia");
        MyTextField amenitiesPrice =new MyTextField(20,100,30,200,"Podaj cenę udogodnienia");
        amenitiesPrice.setTextFormatter(new TextFormatter<String> (pr));
        MyButton addAmenities =new MyButton("Dodaj",20,260,30,200,Color.BLUE);
        MyButton showAmenities =new MyButton("Lista",250,530,30,200,Color.BLUE);
        MyButton deleteAmenities =new MyButton("Usuń",460,530,30,200,Color.BLUE);

        Label label = new Label("Rodzaj pokoju");
        label.setLayoutX(20);
        label.setLayoutY(140);
        label.setPrefWidth(200);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        //checkboxy do wybory jakiego pokoju ma dotyczyc udogodnienie
        CheckBox j = new CheckBox("Jednoosobowy");
        j.setLayoutX(20);
        j.setLayoutY(170);
        j.setPrefWidth(200);
        j.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        CheckBox d = new CheckBox("Dwuosobowy");
        d.setLayoutX(20);
        d.setLayoutY(200);
        d.setPrefWidth(200);
        d.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        CheckBox a = new CheckBox("Apartament");
        a.setLayoutX(20);
        a.setLayoutY(230);
        a.setPrefWidth(200);
        a.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        //tabela do wyswietlania udogodnien
        TableView<AmenitiesC> amenitiesTable = new TableView<>();
        ObservableList<AmenitiesC> data = FXCollections.observableArrayList();
        amenitiesTable.setEditable(true);
        amenitiesTable.setLayoutX(250);
        amenitiesTable.setLayoutY(20);
        amenitiesTable.setMinHeight(500);
        amenitiesTable.setMinWidth(300);

        //kolumny w tabeli z udogodnieniami
        TableColumn nazwaCol = new TableColumn("Udogodnienie");
        nazwaCol.setMinWidth(100);
        nazwaCol.setPrefWidth(100);
        nazwaCol.setCellValueFactory(
                new PropertyValueFactory<>("nazwa"));

        TableColumn priceCol = new TableColumn("Cena");
        priceCol.setMinWidth(100);
        priceCol.setPrefWidth(100);
        priceCol.setCellValueFactory(
                new PropertyValueFactory<>("cena"));

        TableColumn roomTypeCol = new TableColumn("Rodzaj pokoju");
        roomTypeCol.setMinWidth(100);
        roomTypeCol.setPrefWidth(100);
        roomTypeCol.setCellValueFactory(
                new PropertyValueFactory<>("typ_pokoju"));

        TableColumn checkCol = new TableColumn("X");
        checkCol.setMinWidth(10);
        checkCol.setStyle( "-fx-alignment: CENTER;");
        checkCol.setCellValueFactory(
                new PropertyValueFactory<>("select"));

        amenitiesTable.setItems(data);
        amenitiesTable.getColumns().addAll(nazwaCol,priceCol,roomTypeCol,checkCol);

        //---wyswietlenie udogodnien---
        showAmenities.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                data.clear();
                LinkedList<Amenities> list = (LinkedList<Amenities>) ServerConnection.sendToServer.send(ServerOperation.showAmenities, null);

                for(Amenities bean : list){
                    String t;
                    //zmiana formatu
                    if(bean.getTyp_pokoju().equals("1")){
                        t="Jednoosobowy";
                    }
                    else if(bean.getTyp_pokoju().equals("2")){
                        t="Dwuosobowy";
                    }
                    else{
                        t="Apartament";
                    }

                    data.add(new AmenitiesC(
                            bean.getNazwa(),
                            bean.getCena(),
                            t
                    ));
                }
            }
        });

        //---dodawanie udogodnien---
        addAmenities.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //sprawdzenie czy dane zostaly podane
                if(!amenitiesName.getText().isEmpty() || !amenitiesPrice.getText().isEmpty()){
                    Amenities message=null;
                    //istrukcje warunkowe aby przypisac udogodnienie do konkretnych typow pokoi
                    if(j.isSelected()){
                        Amenities a = new Amenities(
                                amenitiesName.getText(),
                                parseFloat(amenitiesPrice.getText()),
                                "1"
                        );
                         message = (Amenities) ServerConnection.sendToServer.send(ServerOperation.addAmenities, a);
                    }
                    if(d.isSelected()){
                        Amenities a = new Amenities(
                                amenitiesName.getText(),
                                parseFloat(amenitiesPrice.getText()),
                                "2"
                        );
                         message = (Amenities) ServerConnection.sendToServer.send(ServerOperation.addAmenities, a);
                    }
                    if(a.isSelected()){
                        Amenities a = new Amenities(
                                amenitiesName.getText(),
                                parseFloat(amenitiesPrice.getText()),
                                "3"
                        );
                         message = (Amenities) ServerConnection.sendToServer.send(ServerOperation.addAmenities, a);
                    }
                    if(!j.isSelected() && !d.isSelected() && !a.isSelected()){
                        AlertBox.wyswietl("Wiadomość","Zaznacz rodzaj pokoju!",false);
                    }
                    else {
                        if (message != null) {
                            AlertBox.wyswietl("Wiadomość", "Dodano pomyślnie.",true);
                        } else {
                            AlertBox.wyswietl("Wiadomość", "Wystąpił błąd podczas dodawania.",false);
                        }
                    }
                }
                else{
                    AlertBox.wyswietl("Wiadomość","Uzupełnij dane!",false);
                }

                amenitiesName.clear();
                amenitiesPrice.clear();
                j.setSelected(false);
                d.setSelected(false);
                a.setSelected(false);
            }
        });

        //usuwanie udogodnien
        deleteAmenities.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(AmenitiesC bean: data){
                    if(bean.getSelect().isSelected()){
                        String t;
                        //zmiana formatu
                        if(bean.getTyp_pokoju().equals("Jednoosobowy")){
                            t="1";
                        }
                        else if(bean.getTyp_pokoju().equals("Dwuosobowy")){
                            t="2";
                        }
                        else{
                            t="3";
                        }

                        Amenities d = new Amenities(
                                bean.getNazwa(),
                                bean.getCena(),
                                t
                        );
                        Amenities message = (Amenities) ServerConnection.sendToServer.send(ServerOperation.deleteAmenities, d);
                        if(message==null){
                            AlertBox.wyswietl("Wiadomość","Błąd podczas usuwania",false);
                        }
                        else{
                            AlertBox.wyswietl("Wiadomość", "Usunięto pomyślnie",true);
                        }
                    }
                }
            }
        });

        Group group1=new Group();

        group1.getChildren().addAll(
                imageView1,label,
                amenitiesName,amenitiesPrice,
                showAmenities,addAmenities,deleteAmenities,
                j,d,a,
                amenitiesTable
        );
        tab.setContent(group1);

    }
}
