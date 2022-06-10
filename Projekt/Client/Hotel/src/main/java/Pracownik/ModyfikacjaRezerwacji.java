package Pracownik;

import Kierownik.AlertBox;
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

/**
 *
 * @author Konrad Basa
 *
 */

public class ModyfikacjaRezerwacji {
    Tab tab;
    public ModyfikacjaRezerwacji(Tab tab){
        this.tab=tab;
    }
    public void wywolanieZakladkiModyfikacjaRezerwacji(){
        Font font = Font.font("Arial", FontWeight.BOLD, 14);

        Image image = new Image("promocje.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(600);
        imageView.setFitWidth(1100);

        Label edycja = new Label();
        edycja.setText("Edycja czasu pobytu");
        edycja.setLayoutX(10);
        edycja.setLayoutY(20);
        edycja.setPrefWidth(200);
        edycja.setPrefHeight(30);
        edycja.setAlignment(Pos.CENTER);
        edycja.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        Label dr = new Label("Data rozpoczęcia");
        dr.setLayoutY(60);
        dr.setLayoutX(10);
        dr.setAlignment(Pos.CENTER);
        dr.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        Label dz = new Label("Data zakończenia");
        dz.setLayoutY(140);
        dz.setLayoutX(10);
        dz.setAlignment(Pos.CENTER);
        dz.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        //stworzenie datapickerow od daty rozpoczecia rezerwacji i zakonczenia rezerwacji
        DatePicker data = new DatePicker();
        DatePicker z = new DatePicker();
        data.setLayoutY(100);
        data.setLayoutX(10);
        z.setLayoutY(180);
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

        //tabela do wyswietlania rezerwacji
        TableView<EditReservationC> reservations = new TableView<>();
        ObservableList<EditReservationC> res = FXCollections.observableArrayList();
        reservations.setEditable(true);
        reservations.setLayoutX(250);
        reservations.setLayoutY(20);
        reservations.setPrefHeight(200);
        reservations.setMinWidth(300);

        //kolumny w tabeli z rezerwacjami
        TableColumn nameCol = new TableColumn("Imie");
        nameCol.setPrefWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("imie"));

        TableColumn surnameCol = new TableColumn("Nazwisko");
        surnameCol.setPrefWidth(100);
        surnameCol.setCellValueFactory(
                new PropertyValueFactory<>("nazwisko"));

        TableColumn roomNumberCol = new TableColumn("Numer pokoju");
        roomNumberCol.setPrefWidth(100);
        roomNumberCol.setCellValueFactory(
                new PropertyValueFactory<>("nr_pokoju"));

        TableColumn datRozCol = new TableColumn("Data przyjazdu");
        datRozCol.setMinWidth(100);
        datRozCol.setPrefWidth(100);
        datRozCol.setCellValueFactory(
                new PropertyValueFactory<>("dataPrzyjazdu"));

        TableColumn datZakCol = new TableColumn("Data odjazdu");
        datZakCol.setMinWidth(100);
        datZakCol.setPrefWidth(100);
        datZakCol.setCellValueFactory(
                new PropertyValueFactory<>("dataOdjazdu"));

        TableColumn toPayCol = new TableColumn("Do zapłaty");
        toPayCol.setPrefWidth(100);
        toPayCol.setCellValueFactory(
                new PropertyValueFactory<>("toPay"));

        TableColumn checkCol = new TableColumn("X");
        checkCol.setPrefWidth(50);
        checkCol.setCellValueFactory(
                new PropertyValueFactory<>("select"));
        //grupa aby wybierany byl tylko jeden radiobutton z mozliwych
        ToggleGroup group = new ToggleGroup();

        reservations.setItems(res);
        reservations.getColumns().addAll(nameCol,surnameCol,roomNumberCol,datRozCol,datZakCol,toPayCol,checkCol);

        //tabela do wyswietlania udogodnien wybranych przez klienta
        TableView<AmenitiesC> chosenAminties = new TableView<>();
        ObservableList<AmenitiesC> ca = FXCollections.observableArrayList();
        chosenAminties.setEditable(true);
        chosenAminties.setLayoutX(250);
        chosenAminties.setLayoutY(270);
        chosenAminties.setPrefHeight(200);
        chosenAminties.setPrefWidth(240);

        //kolumny w tabeli do wyswietlania udogodnien wybranych przez klienta
        TableColumn nameOfAminties = new TableColumn("Nazwa");
        nameOfAminties.setPrefWidth(100);
        nameOfAminties.setCellValueFactory(
                new PropertyValueFactory<>("nazwa"));

        TableColumn priceOfAminties = new TableColumn("Cena");
        priceOfAminties.setPrefWidth(100);
        priceOfAminties.setCellValueFactory(
                new PropertyValueFactory<>("cena"));

        TableColumn checkCol2 = new TableColumn("X");
        checkCol2.setPrefWidth(40);
        checkCol2.setStyle( "-fx-alignment: CENTER;");
        checkCol2.setCellValueFactory(
                new PropertyValueFactory<>("select"));


        chosenAminties.setItems(ca);
        chosenAminties.getColumns().addAll(nameOfAminties,priceOfAminties,checkCol2);

        //tabela do wyswietlania udogodnien ktore moze jeszcze dobrać
        TableView<AmenitiesC> toChosenAminties = new TableView<>();
        ObservableList<AmenitiesC> ta = FXCollections.observableArrayList();
        toChosenAminties.setEditable(true);
        toChosenAminties.setLayoutX(510);
        toChosenAminties.setLayoutY(270);
        toChosenAminties.setPrefHeight(200);
        toChosenAminties.setPrefWidth(240);

        //kolumny w tabeli do wyswietlania udogodnien ktore moze jeszcze dobrać
        TableColumn nameOfAmintiess = new TableColumn("Nazwa");
        nameOfAmintiess.setPrefWidth(100);
        nameOfAmintiess.setCellValueFactory(
                new PropertyValueFactory<>("nazwa"));

        TableColumn priceOfAmintiess = new TableColumn("Cena");
        priceOfAmintiess.setPrefWidth(100);
        priceOfAmintiess.setCellValueFactory(
                new PropertyValueFactory<>("cena"));

        TableColumn checkCol3 = new TableColumn("X");
        checkCol3.setPrefWidth(40);
        checkCol3.setStyle( "-fx-alignment: CENTER;");
        checkCol3.setCellValueFactory(
                new PropertyValueFactory<>("select"));


        toChosenAminties.setItems(ta);
        toChosenAminties.getColumns().addAll(nameOfAmintiess,priceOfAmintiess,checkCol3);

        MyButton show = new MyButton("Wyświetl rezerwacje",920,150,30,100,Color.BLUE);


        //---wyswietlenie rezerwacji---
        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                res.clear();
                LinkedList<EditReservation> list = (LinkedList<EditReservation>) ServerConnection.sendToServer.send(ServerOperation.showReservation, null);

                for(EditReservation bean : list){
                        res.add(new EditReservationC(
                                bean.getIdOfReservation(),
                                bean.getImie(),
                                bean.getNazwisko(),
                                bean.getNr_pokoju(),
                                bean.getDataPrzyjazdu(),
                                bean.getDataOdjazdu(),
                                bean.getToPay()
                                )
                        );
                }
                //dodanie kazdego radiobuttona do grupy aby mozliwy byl wybor tylko jednego
                for(EditReservationC bean: res){
                    bean.select.setToggleGroup(group);
                    bean.select.setId(bean.idOfReservation);
                }
                ca.clear();
                ta.clear();
            }
        });

        //wyswietlanie udogodnien ktore wybral klient i ktore moze wybrac, dotyczy klienta wybranego przez radiobutton
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton)group.getSelectedToggle();

                if (rb != null) {
                    //wyswietlenie udogodnien ktore wybral klient
                    ca.clear();
                    Amenities a = new Amenities(null,0,rb.getId());
                    LinkedList<Amenities> list = (LinkedList<Amenities>) ServerConnection.sendToServer.send(ServerOperation.showMyAmenities, a);

                    for(Amenities bean : list){
                        ca.add(new AmenitiesC(
                                bean.getNazwa(),
                                bean.getCena()
                        ));
                    }
                    //wyswietlenie udogodnien ktore moze dodac w takim pokoju
                    ta.clear();
                    LinkedList<Amenities> list2 = (LinkedList<Amenities>) ServerConnection.sendToServer.send(ServerOperation.showPosAmenities, a);

                    for(Amenities bean : list2){

                        ta.add(new AmenitiesC(
                                bean.getNazwa(),
                                bean.getCena()
                        ));
                    }
                }
            }
        });

        MyButton cancellationAmenities = new MyButton("Anuluj udogodnienie",250,510,30,200,Color.BLUE);

        //---anulowanie udogodnienia--
        cancellationAmenities.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //id rezerwacji w ktorej chcemy dokonac zmiany
                String id_rez="";
                for(EditReservationC bean: res) {
                   if(bean.select.isSelected()){
                       id_rez=bean.idOfReservation;
                   }
                }
                //nazwa udogodnienia ktore chcemy wycofac
                for(AmenitiesC bean: ca){
                    if(bean.select.isSelected()){
                        Amenities a = new Amenities(
                                bean.nazwa,
                                bean.cena,
                                id_rez          //jako typ_pokoju z klasy Amenities podajemy id rezerwacji
                        );
                        Amenities mess = (Amenities) ServerConnection.sendToServer.send(ServerOperation.cancellationMyAmenities, a);
                        if(mess!=null){
                            AlertBox.wyswietl("Wiadomość","Anulowano udogodnienie",true);
                        }
                        else{
                            AlertBox.wyswietl("Wiadomość","Spróbuj ponownie",false);
                        }
                    }
                }
                ca.clear();
                ta.clear();
                if(id_rez.equals("")){
                    AlertBox.wyswietl("Wiadomość","Zaznacz niezbędne informacje z list",false);
                }
            }
        });

        MyButton addAmenities = new MyButton("Dodaj udogodnienie",510,510,30,200,Color.BLUE);

        //---dodawanie nowego udogodnienia--
        addAmenities.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //id rezerwacji w ktorej chcemy dokonac zmiany
                String id_rez="";
                for(EditReservationC bean: res) {
                    if(bean.select.isSelected()){
                        id_rez=bean.idOfReservation;
                    }
                }
                //nazwa udogodnienia ktore chcemy dodac
                for(AmenitiesC bean: ta){
                    if(bean.select.isSelected()){
                        Amenities a = new Amenities(
                                bean.nazwa,
                                bean.cena,
                                id_rez          //jako typ_pokoju z klasy Amenities podajemy id rezerwacji
                        );
                        Amenities mess = (Amenities) ServerConnection.sendToServer.send(ServerOperation.addResAmenities, a);
                        if(mess!=null){
                            AlertBox.wyswietl("Wiadomość","Dodano udogodnienie",true);
                        }
                        else{
                            AlertBox.wyswietl("Wiadomość","Spóbuj ponownie, być może do udogodnienie już jest dodane",false);
                        }
                    }
                }
                ca.clear();
                ta.clear();
                if(id_rez.equals("")){
                    AlertBox.wyswietl("Wiadomość","Zaznacz niezbędne informacje z list",false);
                }
            }
        });

        MyButton cancelationReservation = new MyButton("Anuluj",920,190,30,100,Color.BLUE);

        //---anulowanie rezerwacji--
        cancelationReservation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //id rezerwacji w ktorej chcemy dokonac zmiany
                String id_rez="";
                for(EditReservationC bean: res) {
                    if(bean.select.isSelected()){
                        id_rez=bean.idOfReservation;
                        Reservation a = new Reservation(
                                id_rez
                        );
                        Reservation mess = (Reservation) ServerConnection.sendToServer.send(ServerOperation.cancellationRes, a);
                        if(mess!=null){
                            AlertBox.wyswietl("Wiadomość","Anulowano rezerwacje",true);
                        }
                        else{
                            AlertBox.wyswietl("Wiadomość","Wystąpił błąd podczas anulowania rezerwacji",false);
                        }
                    }
                }
                if(id_rez.equals("")){
                    AlertBox.wyswietl("Wiadomość","Zaznacz niezbędne informacje z list",false);
                }
            }
        });

        MyButton updateTime = new MyButton("Aktualizuj",10,220,30,200,Color.BLUE);

        //---zmiana czasu pobytu---
        updateTime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(data.getValue()!=null && z.getValue()!=null){
                    //id rezerwacji w ktorej chcemy dokonac zmiany
                    String id_rez="";
                    for(EditReservationC bean: res) {
                        if(bean.select.isSelected()){
                            id_rez=bean.idOfReservation;
                            Reservation up = new Reservation(
                                    data.getValue(),
                                    z.getValue(),
                                    id_rez,
                                    bean.nr_pokoju
                            );
                            Reservation mess = (Reservation) ServerConnection.sendToServer.send(ServerOperation.updateResTime, up);
                            if(mess!=null){
                                AlertBox.wyswietl("Wiadomość","Zmieniono poprawnie",true);
                                data.setValue(null);
                                z.setValue(null);
                            }
                            else{
                                AlertBox.wyswietl("Wiadomość","Wystąpił błąd podczas zmiany czasu pobytu",false);
                            }
                        }
                    }

                }
                if(data.getValue()==null && z.getValue()== null){
                    AlertBox.wyswietl("Wiadomość","Uzupełnij dane",false);
                }
            }
        });

        Group group1=new Group();

        group1.getChildren().addAll(imageView,
        edycja,data,z,dr,dz,updateTime,
        reservations,chosenAminties,toChosenAminties,
        cancelationReservation,cancellationAmenities,addAmenities,show
        );
        tab.setContent(group1);

    }
}
