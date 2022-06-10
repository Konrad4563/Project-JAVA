package Klient;

import Kierownik.AlertBox;
import Pracownik.AmenitiesC;
import Pracownik.EditReservationC;
import elemHotel.MyButton;
import elemHotel.MyLabel;
import elemHotel.MyTextField;
import elemHotel.ServerConnection;
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
import messages.Amenities;
import messages.Complaint;
import messages.EditReservation;
import messages.ServerOperation;

import java.util.LinkedList;
import java.util.function.UnaryOperator;

/**
 *
 * @author Piotr Bielecki
 *
 */

public class Skargi {
    Tab tab;
    String idOfReservation="0";
    public Skargi(Tab tab){
        this.tab=tab;
    }
    public void SkargiKlienta(){
        Font font = Font.font("Arial", FontWeight.BOLD, 14);

        Image image = new Image("OpiniaKlienta.jpg");
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
        UnaryOperator<TextFormatter.Change> mes = change -> {
            String newText = change.getControlNewText();
            if (newText.length() > 500) {
                return null ;
            } else {
                return change ;
            }
        };

        MyLabel label=new MyLabel("Podaj dane aby znaleźć swoją rezerwację",10,10,font, Color.WHITE);

        MyLabel nameLabel=new MyLabel("Imie",10,40,font,Color.WHITE);
        MyTextField nameTextField=new MyTextField(10,60,30,200,"");
        nameTextField.setTextFormatter(new TextFormatter<String> (noNumbers));

        MyLabel surnameLabel=new MyLabel("Nazwisko",10,100,font,Color.WHITE);
        MyTextField surnameTextField=new MyTextField(10,130,30,200,"");
        surnameTextField.setTextFormatter(new TextFormatter<String> (noNumbers));

        MyLabel numberLabel=new MyLabel("Numer telefonu",10,170,font,Color.WHITE);
        MyTextField numberTextField=new MyTextField(10,200,30,200,"");
        numberTextField.setTextFormatter(new TextFormatter<String> (phoneNum));


        Label messageLabel=new Label();
        messageLabel.setLayoutY(370);
        messageLabel.setLayoutX(250);
        messageLabel.setPrefWidth(200);
        messageLabel.setPrefHeight(30);
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.setText("Treść skargi");
        messageLabel.setStyle("-fx-font-size: 15px; -fx-background-color: white;");
        TextArea messageArea=new TextArea();
        messageArea.setLayoutX(250);
        messageArea.setLayoutY(400);
        messageArea.setMinWidth(300);
        messageArea.setPrefHeight(100);
        messageArea.setWrapText(true);
        messageArea.setTextFormatter(new TextFormatter<String> (mes));

        MyButton find=new MyButton("Wyszukaj",10,310,30,200,Color.BLUE);

        MyButton add=new MyButton("Wyślij",10,350,30,200,Color.BLUE);

        //tabela do wyswietlania rezerwacji
        TableView<ComplaintC> reservations = new TableView<>();
        ObservableList<ComplaintC> res = FXCollections.observableArrayList();
        reservations.setEditable(true);
        reservations.setLayoutX(250);
        reservations.setLayoutY(60);
        reservations.setPrefHeight(200);
        reservations.setMinWidth(300);

        //kolumny w tabeli z rezerwacjami
        TableColumn datRozCol = new TableColumn("Data przyjazdu");
        datRozCol.setMinWidth(100);
        datRozCol.setPrefWidth(100);
        datRozCol.setCellValueFactory(
                new PropertyValueFactory<>("dat_roz"));

        TableColumn datZakCol = new TableColumn("Data odjazdu");
        datZakCol.setMinWidth(100);
        datZakCol.setPrefWidth(100);
        datZakCol.setCellValueFactory(
                new PropertyValueFactory<>("dat_zak"));

        TableColumn roomNumberCol = new TableColumn("Numer pokoju");
        roomNumberCol.setPrefWidth(100);
        roomNumberCol.setCellValueFactory(
                new PropertyValueFactory<>("roomNumber"));

        TableColumn rtype = new TableColumn("Typ pokoju");
        rtype.setPrefWidth(100);
        rtype.setCellValueFactory(
                new PropertyValueFactory<>("roomType"));

        TableColumn toPayCol = new TableColumn("Do zapłaty");
        toPayCol.setPrefWidth(100);
        toPayCol.setCellValueFactory(
                new PropertyValueFactory<>("bill"));

        TableColumn checkCol = new TableColumn("X");
        checkCol.setPrefWidth(50);
        checkCol.setCellValueFactory(
                new PropertyValueFactory<>("select"));
        //grupa aby wybierany byl tylko jeden radiobutton z mozliwych
        ToggleGroup group = new ToggleGroup();

        reservations.setItems(res);
        reservations.getColumns().addAll(datRozCol,datZakCol,roomNumberCol,rtype,toPayCol,checkCol);

        //---wyswietlenie rezerwacji---
        find.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                res.clear();
                if(!nameTextField.getText().isEmpty() && !surnameTextField.getText().isEmpty() && !numberTextField.getText().isEmpty()){
                    Complaint send = new Complaint(
                            nameTextField.getText(),
                            surnameTextField.getText(),
                            numberTextField.getText()
                    );
                    LinkedList<Complaint> list = (LinkedList<Complaint>) ServerConnection.sendToServer.send(ServerOperation.showResCom, send);

                    for(Complaint bean : list){
                        String rt;
                        if(bean.getRoomType().equals("1")){
                            rt="Jednoosobowy";
                        }
                        else if(bean.getRoomType().equals("2")){
                            rt="Dwuosobowy";
                        }
                        else{
                            rt="Apartament";
                        }
                        res.add(new ComplaintC(
                                        bean.getDat_roz(),
                                        bean.getDat_zak(),
                                        bean.getRoomNumber(),
                                        rt,
                                        bean.getBill(),
                                        bean.getIdOfReservation()
                                )
                        );
                    }
                    //dodanie kazdego radiobuttona do grupy aby mozliwy byl wybor tylko jednego
                    for(ComplaintC bean: res){
                        bean.select.setToggleGroup(group);
                        bean.select.setId(bean.idOfReservation);

                    }
                }
                else{
                    AlertBox.wyswietl("Wiadomość","Uzupełnij dane aby wyszukać rezerwacji",false);
                }

            }
        });


        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton)group.getSelectedToggle();

                if (rb != null) {
                    idOfReservation=rb.getId();
                }
            }
        });

        //--dodanie skargi--
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!idOfReservation.equals("0") && !messageArea.getText().isEmpty()){
                    Complaint a = new Complaint(
                            idOfReservation,
                            messageArea.getText()
                    );

                    Complaint mess = (Complaint) ServerConnection.sendToServer.send(ServerOperation.addComplaint, a);
                    if(mess!=null){
                        AlertBox.wyswietl("Wiadomość","Dodano pomyślnie",true);
                    }
                    else{
                        AlertBox.wyswietl("Wiadomość","Coś poszło nie tak",false);
                    }
                }
                else{
                    AlertBox.wyswietl("Wiadomość","Zaznacz rezerwacje lub uzupełnij treśc skargi.",false);
                }
            }
        });



        Group group1=new Group();

        group1.getChildren().addAll(imageView,label,
                nameLabel,nameTextField,
                surnameLabel,surnameTextField,
                numberLabel,numberTextField,
                messageLabel,messageArea,
                find,add,
                reservations);
        tab.setContent(group1);

    }
}
