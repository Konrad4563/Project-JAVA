package Klient;

import Kierownik.AlertBox;
import Pracownik.AmenitiesC;
import elemHotel.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import messages.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 *
 * @author Piotr Bielecki
 *
 */

public class Rezerwacja {
    Tab tab;
    String wybor;
    ObservableList<String> listt = FXCollections.observableArrayList();
    public Rezerwacja(Tab tab){
        this.tab=tab;
    }
    public void wywolanieZakladkiRezerwacja(){
        Font font = Font.font("Arial", FontWeight.BOLD, 14);

        Image image = new Image("promocje.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(600);
        imageView.setFitWidth(1100);

        //ograniczenia dla poszczegolnych TextFieldow
        UnaryOperator<TextFormatter.Change> noNumbers = change -> {
            String input = change.getText();

            if ((!input.matches("[\\d\\.]+")) || change.isDeleted()) {
                return change;
            }
            return null;
        };
        UnaryOperator<TextFormatter.Change> phoneNum = change -> {
            String newText = change.getControlNewText();
            if (newText.length() > 9 || !change.getControlNewText().matches("\\d*")) {
                return null ;
            } else {
                return change ;
            }
        };



        MyTextField name=new MyTextField(10,10,30,200,"Podaj imie");
        name.setTextFormatter(new TextFormatter<String> (noNumbers));
        MyTextField surname=new MyTextField(10,50,30,200,"Podaj nazwisko");
        surname.setTextFormatter(new TextFormatter<String> (noNumbers));
        MyTextField phoneNumber=new MyTextField(10,90,30,200,"Podaj numer telefonu");
        phoneNumber.setTextFormatter(new TextFormatter<String> (phoneNum));
        MyTextField email=new MyTextField(10,130,30,200,"Podaj adres email");

        MyButton reserve=new MyButton("Zarezerwuj Pobyt",10,420,30,200,Color.BLUE);

        Label dr = new Label("Data rozpoczęcia");
        dr.setLayoutY(170);
        dr.setLayoutX(10);
        dr.setAlignment(Pos.CENTER);
        dr.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        Label dz = new Label("Data zakończenia");
        dz.setLayoutY(250);
        dz.setLayoutX(10);
        dz.setAlignment(Pos.CENTER);
        dz.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        //stworzenie datapickerow od daty rozpoczecia rezerwacji i zakonczenia rezerwacji
        DatePicker data = new DatePicker();
        DatePicker z = new DatePicker();
        data.setLayoutY(210);
        data.setLayoutX(10);
        z.setLayoutY(290);
        z.setLayoutX(10);

        final LocalDate[] i = new LocalDate[1];
        final LocalDate[] y = new LocalDate[1];

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                //pobranie daty z pickera
                i[0] = data.getValue();
                y[0] = z.getValue();
            }
        };

        //wywolanie po nacisnieciu datapickera
        data.setOnAction(event);
        z.setOnAction(event);

        //wybor typu pokoju
        String[] s = new String[] {"Jednoosobowy","Dwuosobowy","Apartament"};
        ChoiceBox<String> roomType = new ChoiceBox<>();
        roomType.getItems().addAll(s);
        roomType.setLayoutX(10);
        roomType.setLayoutY(330);
        roomType.setMinHeight(30);
        roomType.setMinWidth(200);


        //tabela do wyswietlania udogodnien
        TableView<AmenitiesC> amenitiesTable = new TableView<>();
        ObservableList<AmenitiesC> am = FXCollections.observableArrayList();
        amenitiesTable.setEditable(true);
        amenitiesTable.setLayoutX(250);
        amenitiesTable.setLayoutY(20);
        amenitiesTable.setMinHeight(320);
        amenitiesTable.setPrefHeight(340);
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


        TableColumn checkCol = new TableColumn("X");
        checkCol.setMinWidth(10);
        checkCol.setStyle( "-fx-alignment: CENTER;");
        checkCol.setCellValueFactory(
                new PropertyValueFactory<>("select"));

        amenitiesTable.setItems(am);
        amenitiesTable.getColumns().addAll(nazwaCol,priceCol,checkCol);

        //wyswietlanie udogodnien w zalenosci od wybranego typu pokoju
        roomType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                wybor = s[t1.intValue()];
                if(wybor=="Jednoosobowy"){
                    am.clear();
                    LinkedList<Amenities> list = (LinkedList<Amenities>) ServerConnection.sendToServer.send(ServerOperation.showAmenities, null);

                    for(Amenities bean : list){
                        if(bean.getTyp_pokoju().equals("1")){
                            am.add(new AmenitiesC(
                                    bean.getNazwa(),
                                    bean.getCena(),
                                    "Jednoosobowy"
                            ));
                        }
                    }
                }
                if(wybor=="Dwuosobowy"){
                    am.clear();
                    LinkedList<Amenities> list = (LinkedList<Amenities>) ServerConnection.sendToServer.send(ServerOperation.showAmenities, null);

                    for(Amenities bean : list){
                        if(bean.getTyp_pokoju().equals("2")){
                            am.add(new AmenitiesC(
                                    bean.getNazwa(),
                                    bean.getCena(),
                                    "Dwuosobowy"
                            ));
                        }
                    }
                }
                if(wybor=="Apartament"){
                    am.clear();
                    LinkedList<Amenities> list = (LinkedList<Amenities>) ServerConnection.sendToServer.send(ServerOperation.showAmenities, null);

                    for(Amenities bean : list){
                        if(bean.getTyp_pokoju().equals("3")){
                            am.add(new AmenitiesC(
                                    bean.getNazwa(),
                                    bean.getCena(),
                                    "Apartament"
                            ));
                        }
                    }
                }

            }
        });

        //dodanie rezerwacji
        reserve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //lista z zaznaczonymi przez klienta udogodnieniami
                List<String> amenitiesList = new LinkedList<String>();
                for(AmenitiesC bean : am){
                    if(bean.getSelect().isSelected()){
                        amenitiesList.add(bean.getNazwa());
                    }
                }

                if(!name.getText().isEmpty() && !surname.getText().isEmpty() &&
                        !phoneNumber.getText().isEmpty() && !email.getText().isEmpty() &&
                        data.getValue()!=null && z.getValue()!=null && roomType.getValue()!=null
                ){
                    Reservation newReservation = new Reservation(
                            name.getText(),
                            surname.getText(),
                            phoneNumber.getText(),
                            email.getText(),
                            data.getValue(),
                            z.getValue(),
                            roomType.getValue(),
                            amenitiesList
                    );
                    Reservation message = (Reservation) ServerConnection.sendToServer.send(ServerOperation.addReservation, newReservation);
                    if(message.getErrorCode()==0){
                        AlertBox.wyswietl("Wiadomość ","Zarezerwowano pomyślnie",true);
                    }else{
                        AlertBox.wyswietl("Wiadomość","Brak wolnych pokoi "+message.getRoomType()+" proszę spróbować innego.",false);
                    }
                    name.clear();
                    surname.clear();
                    phoneNumber.clear();
                    email.clear();
                } else{
                    AlertBox.wyswietl("Wiadomosc ","Uzupelnij dane",false);
                }



            }
        });

        //tabela z lista promocji
        TableView<Promotion> promocje = new TableView<>();
        ObservableList<Promotion> gdata = FXCollections.observableArrayList();
        promocje.setEditable(true);
        promocje.setLayoutX(580);
        promocje.setLayoutY(20);
        promocje.setMinHeight(100);
        promocje.setPrefHeight(200);
        promocje.setMinWidth(300);
        promocje.setPrefWidth(510);

        //kolumny w tabeli z promocjami
        TableColumn namee = new TableColumn("Nazwa");
        namee.setMinWidth(100);
        namee.setPrefWidth(100);
        namee.setCellValueFactory(
                new PropertyValueFactory<>("nazwa"));

        TableColumn wartosc = new TableColumn("Promocja");
        wartosc.setMinWidth(50);
        wartosc.setPrefWidth(100);
        wartosc.setStyle( "-fx-alignment: CENTER;");
        wartosc.setCellValueFactory(
                new PropertyValueFactory<>("wartosc"));

        TableColumn dat_roz = new TableColumn("Data rozpoczecia");
        dat_roz.setMinWidth(100);
        dat_roz.setPrefWidth(100);
        dat_roz.setCellValueFactory(
                new PropertyValueFactory<>("dat_roz"));

        TableColumn dat_zak = new TableColumn("Data zakończenia");
        dat_zak.setMinWidth(100);
        dat_zak.setPrefWidth(100);
        dat_zak.setCellValueFactory(
                new PropertyValueFactory<>("dat_zak"));

        TableColumn prom_rodz = new TableColumn("Rodzaj pokoju");
        prom_rodz.setMinWidth(100);
        prom_rodz.setPrefWidth(100);
        prom_rodz.setCellValueFactory(
                new PropertyValueFactory<>("rodzaj_pokoju"));


        promocje.setItems(gdata);
        promocje.getColumns().addAll(namee,wartosc,dat_roz,dat_zak,prom_rodz);

        gdata.clear();
        LinkedList<Promotion> list = (LinkedList<Promotion>) ServerConnection.sendToServer.send(ServerOperation.showPromotion, null);

        //zmiana z formy np 1 na Jednoosobowy itp
        for(Promotion bean : list){
            String r;
            if(bean.getRodzaj_pokoju().equals("1")){
                r="Jednoosobowy";
            }
            else if(bean.getRodzaj_pokoju().equals("2")){
                r="Dwuosobowy";
            }
            else{
                r="Apartament";
            }

            gdata.add(new Promotion(
                    bean.getNazwa(),
                    bean.getWartosc()+"%",
                    bean.getDat_roz(),
                    bean.getDat_zak(),
                    r
            ));
        }

        //pozyskanie aktualnych cen pokoi
        listt.clear();
        LinkedList<ShowTypeRoom> roomPrice = (LinkedList<ShowTypeRoom>) ServerConnection.sendToServer.send(ServerOperation.showTypeRoom, null);
        for(ShowTypeRoom bean : roomPrice){
            listt.add(String.valueOf(bean.getPrice()));
        }

        //Napisy z aktualna cena pokoi roznego typu
        Label j = new Label();
        j.setText("Jednoosobowy: "+listt.get(0));
        j.setLayoutX(580);
        j.setLayoutY(250);
        j.setPrefHeight(30);
        j.setPrefWidth(200);
        j.setAlignment(Pos.CENTER_LEFT);
        j.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        Label d = new Label();
        d.setText("Dwuosobowy: "+listt.get(1));
        d.setLayoutX(580);
        d.setLayoutY(280);
        d.setPrefHeight(30);
        d.setPrefWidth(200);
        d.setAlignment(Pos.CENTER_LEFT);
        d.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        Label a = new Label();
        a.setText("Apartament: "+listt.get(2)+"        /noc");
        a.setLayoutX(580);
        a.setLayoutY(310);
        a.setPrefHeight(30);
        a.setPrefWidth(200);
        a.setAlignment(Pos.CENTER_LEFT);
        a.setStyle("-fx-font-size: 15px; -fx-background-color: white;");



        Group group1=new Group();

        group1.getChildren().addAll(
                imageView,
                name,surname,phoneNumber,email,
                data,z,
                dr,dz,
                roomType,amenitiesTable,
                reserve,promocje,j,d,a
        );
        tab.setContent(group1);

    }
}
