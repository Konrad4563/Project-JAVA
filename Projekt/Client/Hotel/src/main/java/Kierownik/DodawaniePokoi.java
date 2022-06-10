package Kierownik;

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
import messages.*;

import java.util.LinkedList;
import java.util.List;

import static Kierownik.Constants.THIRD_TAB_IMAGE;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Marcin Bonar
 *
 */

public class DodawaniePokoi {
    Tab tab5;
    ObservableList<String> listt = FXCollections.observableArrayList();
    int x;
    public DodawaniePokoi(Tab tab5){
        this.tab5 = tab5;
    }

    public DodawaniePokoi(){
    }

    //Dodawanie pokoi
    public void open5(){
        Font font = Font.font("Arial", FontWeight.BOLD, 14);

        Image image2 = new Image(THIRD_TAB_IMAGE);
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitHeight(600);
        imageView2.setFitWidth(1100);

        MyLabel label2= new MyLabel("Sprawdz liste pokoi",40,10,font, Color.BLACK);
        MyButton showAllRoom=new MyButton("Lista pokoi",190,10,30,200,Color.BLUE);

        Label typPokoju = new Label("Typ pokoju: ");
        typPokoju.setLayoutX(20);
        typPokoju.setLayoutY(50);
        typPokoju.setPrefHeight(30);
        typPokoju.setPrefWidth(200);
        typPokoju.setAlignment(Pos.CENTER);
        typPokoju.setStyle("-fx-font-size: 15px; -fx-background-color: white;");


        MyTextField nameOfRoom=new MyTextField(20,150,30,200,"Nazwa pokoju");
        MyButton dodajPokoj=new MyButton("Dodaj pokój",20,200,30,200,Color.BLUE);

        //pozyskanie ostatniego numeru pokoju i wpisanie do spinera
        LinkedList<Rooms> numberOfRooms = (LinkedList<Rooms>) ServerConnection.sendToServer.send(ServerOperation.showAllRoom, null);
        for(Rooms bean : numberOfRooms){
            x++;
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
        j.setLayoutX(800);
        j.setLayoutY(200);
        j.setPrefHeight(30);
        j.setPrefWidth(200);
        j.setAlignment(Pos.CENTER);
        j.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        Label d = new Label();
        d.setText("Dwuosobowy: "+listt.get(1));
        d.setLayoutX(800);
        d.setLayoutY(230);
        d.setPrefHeight(30);
        d.setPrefWidth(200);
        d.setAlignment(Pos.CENTER);
        d.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        Label a = new Label();
        a.setText("Apartament: "+listt.get(2));
        a.setLayoutX(800);
        a.setLayoutY(260);
        a.setPrefHeight(30);
        a.setPrefWidth(200);
        a.setAlignment(Pos.CENTER);
        a.setStyle("-fx-font-size: 15px; -fx-background-color: white;");


        //wybor typu pokoju
        String[] s = new String[] {"Jednoosobowy","Dwuosobowy","Apartament"};
        ChoiceBox<String> roomType = new ChoiceBox<>();
        roomType.getItems().addAll(s);
        roomType.setLayoutX(20);
        roomType.setLayoutY(80);
        roomType.setMinHeight(30);
        roomType.setMinWidth(200);


        Label numerPokoju = new Label("Numer pokoju: ");
        numerPokoju.setLayoutX(20);
        numerPokoju.setLayoutY(120);
        numerPokoju.setPrefHeight(30);
        numerPokoju.setPrefWidth(200);
        numerPokoju.setAlignment(Pos.CENTER);
        numerPokoju.setStyle("-fx-font-size: 15px; -fx-background-color: white;");


        //Spinner numer pokoju
        Spinner<Integer> numberRoom = new Spinner<>((x+1), 500, 0, 1);
        numberRoom.setLayoutX(20);
        numberRoom.setLayoutY(150);
        numberRoom.setPrefWidth(200);
        numberRoom.setPrefHeight(30);
        var factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(x+1, 500, 1);factory.setWrapAround(true);

        //Spinner numer pokoju
        Spinner<Integer> toFreeRoom = new Spinner<>((x+1), 500, 0, 1);
        var factory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,x+0,0,1);factory.setWrapAround(true);
        toFreeRoom.setLayoutX(20);
        toFreeRoom.setLayoutY(230);
        toFreeRoom.setPrefWidth(200);
        toFreeRoom.setPrefHeight(30);
        toFreeRoom.setValueFactory(factory2);

        MyButton free=new MyButton("Zwolnij pokój",20,270,30,200,Color.BLUE);

        MyButton add=new MyButton("Dodaj nowy pokój",20,190,30,200,Color.BLUE);


        //Tabela z z pokojami
        TableView<Rooms> roomTable = new TableView<>();
        ObservableList<Rooms> dataRoom = FXCollections.observableArrayList();
        roomTable.setEditable(true);
        roomTable.setLayoutX(250);
        roomTable.setLayoutY(50);
        roomTable.setMinHeight(500);
        roomTable.setMinWidth(200);

        //kolumny w tabeli z pokojami
        TableColumn stateofRoomCol = new TableColumn("Stan pokoju");
        stateofRoomCol.setMinWidth(150);
        stateofRoomCol.setCellValueFactory(
                new PropertyValueFactory<>("stan"));

        TableColumn numberofRoomCol = new TableColumn("Numer pokoju");
        numberofRoomCol.setMinWidth(150);
        numberofRoomCol.setCellValueFactory(
                new PropertyValueFactory<>("nr_pokoju"));

        TableColumn typeRoomCol = new TableColumn("Typ pokoju");
        typeRoomCol.setMinWidth(150);
        typeRoomCol.setCellValueFactory(
                new PropertyValueFactory<>("pokoj_typ_pokoju"));


        roomTable.setItems(dataRoom);
        roomTable.getColumns().addAll(stateofRoomCol, numberofRoomCol,typeRoomCol);

        Label tPokoju = new Label("Typ pokoju");
        tPokoju.setLayoutX(800);
        tPokoju.setLayoutY(50);
        tPokoju.setPrefHeight(30);
        tPokoju.setPrefWidth(200);
        tPokoju.setAlignment(Pos.CENTER);
        tPokoju.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        //wybor typu pokoju
        ChoiceBox<String> roomType2 = new ChoiceBox<>();
        roomType2.getItems().addAll(s);
        roomType2.setLayoutX(800);
        roomType2.setLayoutY(80);
        roomType2.setMinHeight(30);
        roomType2.setMinWidth(200);

        MyTextField koszt = new MyTextField(800,120,30,200,"Nowa cena pokoju");

        MyButton nowaCena=new MyButton("Zmień cenę",800,160,30,200,Color.BLUE);


        //---wyswietlenie pokoi---
        showAllRoom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dataRoom.clear();
                LinkedList<Rooms> list = (LinkedList<Rooms>) ServerConnection.sendToServer.send(ServerOperation.showAllRoom, null);

                for(Rooms bean : list){

                    String stan;
                    //zmiana formatu stanu pokoju
                    if(bean.getStan().equals("1")){
                        stan="Zajęty";
                    }
                    else{
                        stan="Wolny";
                    }

                    dataRoom.add(new Rooms(
                            stan,
                            bean.getNr_pokoju(),
                            bean.getPokoj_typ_pokoju(),
                            bean.getCena()
                    ));
                }
            }
        });

        //---zmiana ceny pokoi---
        nowaCena.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!koszt.getText().isEmpty() && roomType2.getValue()!=null){
                    Rooms h = new Rooms(
                            "0",
                            0,
                            roomType2.getValue(),
                            parseInt(koszt.getText())
                    );
                    Rooms message = (Rooms) ServerConnection.sendToServer.send(ServerOperation.updateRoomPrice, h);
                    if(message!=null){
                        AlertBox.wyswietl("Wiadomość","Zmieniono cenę pokoju.",true);
                    }
                    else{
                        AlertBox.wyswietl("Wiadomość","Błąd podczas zmiany ceny pokoju.",false);
                    }
                }
                else{
                    AlertBox.wyswietl("Wiadomość","Uzupełnij dane.",false);
                }
                koszt.clear();
                //zaktualizowanie cen pokoi
                listt.clear();
                LinkedList<ShowTypeRoom> roomPrice = (LinkedList<ShowTypeRoom>) ServerConnection.sendToServer.send(ServerOperation.showTypeRoom, null);
                for(ShowTypeRoom bean : roomPrice){
                    listt.add(String.valueOf(bean.getPrice()));
                }
                j.setText("Jednoosobowy: "+listt.get(0));
                d.setText("Dwyosobowy: "+listt.get(1));
                a.setText("Apartament: "+listt.get(2));

            }
        });

        //dodawanie pokoju
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int j;
                if(roomType.getValue()!=null) {
                    if (roomType.getValue().equals("Jednoosobowy")) {
                        j = 1;
                    } else if (roomType.getValue().equals("Dwuosobowy")) {
                        j = 2;
                    } else {
                        j = 3;
                    }

                    Rooms p = new Rooms(
                            "0",
                            numberRoom.getValue(),
                            String.valueOf(j),
                            100
                    );
                    Rooms message = (Rooms) ServerConnection.sendToServer.send(ServerOperation.addRoom, p);
                    if (message != null) {
                        AlertBox.wyswietl("Wiadomość", "Dodano pokój.",true);
                    } else {
                        AlertBox.wyswietl("Wiadomość", "Błąd przy dodawaniu pokoju.",false);
                    }
                }
                else{
                    AlertBox.wyswietl("Wiadomośc","Wybierz typ pokoju.",false);
                }
               //zaktualizowanie ostatniego wolnego numeru pokoju
                x=0;//liczba zajetych pokoi
                LinkedList<Rooms> numberOfRooms = (LinkedList<Rooms>) ServerConnection.sendToServer.send(ServerOperation.showAllRoom, null);

                for(Rooms bean : numberOfRooms){
                    x++;
                }
                var factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(x+1, 500, 1);factory.setWrapAround(true);
                numberRoom.setValueFactory(factory);
            }
        });

        //zwolnienie pokoju
        free.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Rooms p = new Rooms(
                        toFreeRoom.getValue()
                );
                Rooms message = (Rooms) ServerConnection.sendToServer.send(ServerOperation.freeRoom, p);
                if (message != null) {
                    AlertBox.wyswietl("Wiadomość", "Zwolniono pokój.",true);
                } else {
                    AlertBox.wyswietl("Wiadomość", "Błąd przy zwalnianiu pokoju.",false);
                }
            }});

        Group group5=new Group();
        group5.getChildren().addAll(
                imageView2,label2,
                roomTable,roomType,roomType2,
                typPokoju,add,
                numberRoom,numerPokoju,
                showAllRoom,koszt,
                nowaCena,tPokoju,
                toFreeRoom,free,
                j,a,d
        );
        tab5.setContent(group5);
    }
}
