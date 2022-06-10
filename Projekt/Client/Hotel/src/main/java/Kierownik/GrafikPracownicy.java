package Kierownik;

import elemHotel.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import messages.Pracownik;
import messages.Grafik;
import messages.ServerOperation;

import java.util.LinkedList;

import static Kierownik.Constants.FIRST_TAB_IMAGE;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Marcin Bonar
 *
 */

public class GrafikPracownicy {
    Tab tab3;
    //stworzenie konstruktora klasy
    public  GrafikPracownicy(Tab tab3){
        this.tab3=tab3;
    }
    //Stworzenie funkcji
    public void open3(){
        Font font = Font.font("Arial", FontWeight.BOLD, 14);
        Image image = new Image("grafikPersonelu.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(600);
        imageView.setFitWidth(1100);


        //ustwaienie godziny rozpoczecia pracy
        Label opis = new Label("Godzina rozpoczecia pracy");
        opis.setLayoutX(40);
        opis.setLayoutY(30);
        opis.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        String[] hours = new String[] {
                "01","02","03","04","05","06",
                "07","08","09","10","11","12",
                "13","14","15","16","17","18",
                "19","20","21","22","23","24"
        };

        String[] minutes = new String[]{
                "00","05","10","15","20","25",
                "30", "35","40","45","50","55"
        };

        //godzina rozpoczecia pracy
        ChoiceBox<String> rh = new ChoiceBox<>();
        rh.getItems().addAll(hours);
        rh.setLayoutX(40);
        rh.setLayoutY(60);
        rh.setMinHeight(30);
        rh.setMinWidth(50);
        rh.setValue("07");

        //minuta rozpoczecia pracy
        ChoiceBox<String> rm = new ChoiceBox<>();
        rm.getItems().addAll(minutes);
        rm.setLayoutX(100);
        rm.setLayoutY(60);
        rm.setMinHeight(30);
        rm.setMinWidth(50);
        rm.setValue("00");


        //ustawienie godziny zakonczenia pracy
        Label opis2 = new Label("Godzina zakonczenia pracy");
        opis2.setLayoutX(40);
        opis2.setLayoutY(100);
        opis2.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        //godzina zakonczenia pracy
        ChoiceBox<String> zh = new ChoiceBox<>();
        zh.getItems().addAll(hours);
        zh.setLayoutX(40);
        zh.setLayoutY(130);
        zh.setMinHeight(30);
        zh.setMinWidth(50);
        zh.setValue("15");

        //minuta zakonczenia pracy
        ChoiceBox<String> zm = new ChoiceBox<>();
        zm.getItems().addAll(minutes);
        zm.setLayoutX(100);
        zm.setLayoutY(130);
        zm.setMinHeight(30);
        zm.setMinWidth(50);
        zm.setValue("00");

        //checkbox kontrolujacy czy w danym dniu mamy wpisac godziny pracy czy jest to dzien wolny
        CheckBox wolne = new CheckBox("Dzień wolny");
        wolne.setLayoutX(40);
        wolne.setLayoutY(170);
        wolne.prefHeight(30);
        wolne.setPrefWidth(200);
        wolne.setStyle("-fx-font-size: 15px; -fx-background-color: green; -fx-text-fill: white;");


        //wybor dnia tygodnia ktorego maja dotyczyc godziny/dzien wolny
        Label day = new Label("Dni tygodnia");
        day.setLayoutX(40);
        day.setLayoutY(200);
        day.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        CheckBox pon = new CheckBox("Poniedziałek");
        pon.setLayoutX(40);
        pon.setLayoutY(230);
        pon.prefHeight(20);
        pon.setPrefWidth(200);
        pon.setStyle("-fx-font-size: 12px; -fx-background-color: white;");

        CheckBox wt = new CheckBox("Wtorek");
        wt.setLayoutX(40);
        wt.setLayoutY(260);
        wt.prefHeight(20);
        wt.setPrefWidth(200);
        wt.setStyle("-fx-font-size: 12px; -fx-background-color: white;");

        CheckBox sr = new CheckBox("Środa");
        sr.setLayoutX(40);
        sr.setLayoutY(290);
        sr.prefHeight(20);
        sr.setPrefWidth(200);
        sr.setStyle("-fx-font-size: 12px; -fx-background-color: white;");

        CheckBox czw = new CheckBox("Czwartek");
        czw.setLayoutX(40);
        czw.setLayoutY(320);
        czw.prefHeight(20);
        czw.setPrefWidth(200);
        czw.setStyle("-fx-font-size: 12px; -fx-background-color: white;");

        CheckBox pt = new CheckBox("Piątek");
        pt.setLayoutX(40);
        pt.setLayoutY(350);
        pt.prefHeight(20);
        pt.setPrefWidth(200);
        pt.setStyle("-fx-font-size: 12px; -fx-background-color: white;");

        CheckBox sb = new CheckBox("Sobota");
        sb.setLayoutX(40);
        sb.setLayoutY(380);
        sb.prefHeight(20);
        sb.setPrefWidth(200);
        sb.setStyle("-fx-font-size: 12px; -fx-background-color: white;");

        CheckBox nd = new CheckBox("Niedziela");
        nd.setLayoutX(40);
        nd.setLayoutY(410);
        nd.prefHeight(20);
        nd.setPrefWidth(200);
        nd.setStyle("-fx-font-size: 12px; -fx-background-color: white;");


        MyButton g=new MyButton("Wprowadz zmiany",40,430,30,200,Color.BLUE);
        MyButton sg= new MyButton("Sprawdź Grafik",40,470,30,200,Color.BLUE);
        MyButton show=new MyButton("Sprawdź listę pracowników",40,510,30,200,Color.BLUE);


        //Tabela z pracownikami
        TableView<Person> workersTable = new TableView<>();
        ObservableList<Person> data = FXCollections.observableArrayList();
        workersTable.setEditable(true);
        workersTable.setLayoutX(270);
        workersTable.setLayoutY(30);
        workersTable.setMinHeight(100);
        workersTable.setPrefHeight(250);
        workersTable.setMinWidth(200);

        //kolumny w tabeli z pracownikami
        TableColumn firstNameCol = new TableColumn("Imie");
        firstNameCol.setMinWidth(50);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));

        TableColumn lastNameCol = new TableColumn("Nazwisko");
        lastNameCol.setMinWidth(50);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));

        TableColumn ageCol = new TableColumn("Wiek");
        ageCol.setMinWidth(20);
        ageCol.setCellValueFactory(
                new PropertyValueFactory<>("age"));

        TableColumn genderCol = new TableColumn("Płeć");
        genderCol.setMinWidth(50);
        genderCol.setCellValueFactory(
                new PropertyValueFactory<>("gender"));

        TableColumn peselCol = new TableColumn("Pesel");
        peselCol.setMinWidth(100);
        peselCol.setCellValueFactory(
                new PropertyValueFactory<>("pesel"));

        TableColumn placeCol = new TableColumn("Miejsce zamieszkania");
        placeCol.setMinWidth(100);
        placeCol.setCellValueFactory(
                new PropertyValueFactory<>("place"));

        TableColumn salaryCol = new TableColumn("Wynagrodzenie");
        salaryCol.setMinWidth(50);
        salaryCol.setCellValueFactory(
                new PropertyValueFactory<>("salary"));

        TableColumn positionCol = new TableColumn("Stanowisko");
        positionCol.setMinWidth(50);
        positionCol.setCellValueFactory(
                new PropertyValueFactory<>("position"));

        TableColumn checkCol = new TableColumn("X");
        checkCol.setMinWidth(10);
        checkCol.setStyle( "-fx-alignment: CENTER;");
        checkCol.setCellValueFactory(
                new PropertyValueFactory<>("select"));

        workersTable.setItems(data);
        workersTable.getColumns().addAll(firstNameCol, lastNameCol,ageCol,genderCol,peselCol,placeCol,salaryCol,positionCol,checkCol);

        //Tabela grafiku
        TableView<Grafik> grafik = new TableView<>();
        ObservableList<Grafik> gdata = FXCollections.observableArrayList();
        grafik.setEditable(true);
        grafik.setLayoutX(270);
        grafik.setLayoutY(300);
        grafik.setMinHeight(100);
        grafik.setPrefHeight(250);
        grafik.setMinWidth(200);

        //kolumny w tabeli z grafikiem
        TableColumn name = new TableColumn("Imie");
        name.setMinWidth(50);
        name.setCellValueFactory(
                new PropertyValueFactory<>("imie"));

        TableColumn surname = new TableColumn("Nazwisko");
        surname.setMinWidth(50);
        surname.setCellValueFactory(
                new PropertyValueFactory<>("nazwisko"));

        TableColumn ponn = new TableColumn("Pon");
        ponn.setMinWidth(20);
        ponn.setCellValueFactory(
                new PropertyValueFactory<>("pon"));

        TableColumn wtt = new TableColumn("Wt");
        wtt.setMinWidth(50);
        wtt.setCellValueFactory(
                new PropertyValueFactory<>("wt"));

        TableColumn srr = new TableColumn("Sr");
        srr.setMinWidth(100);
        srr.setCellValueFactory(
                new PropertyValueFactory<>("sr"));

        TableColumn czww = new TableColumn("Czw");
        czww.setMinWidth(100);
        czww.setCellValueFactory(
                new PropertyValueFactory<>("czw"));

        TableColumn ptt = new TableColumn("Pt");
        ptt.setMinWidth(50);
        ptt.setCellValueFactory(
                new PropertyValueFactory<>("pt"));

        TableColumn sbb = new TableColumn("Sob");
        sbb.setMinWidth(50);
        sbb.setCellValueFactory(
                new PropertyValueFactory<>("sb"));

        TableColumn ndd = new TableColumn("Nd");
        ndd.setMinWidth(10);
        ndd.setCellValueFactory(
                new PropertyValueFactory<>("nd"));

        grafik.setItems(gdata);
        grafik.getColumns().addAll(name,surname,ponn,wtt,srr,czww,ptt,sbb,ndd);


        //Wprowadzenie grafiku
        g.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(Person bean: data){
                    if(bean.getSelect().isSelected()){
                        //Wpisanie godzin/dnia wolnego do danego dnia wybieranego poprzez checkbox
                        if(!pon.isSelected() && !wt.isSelected() &&
                                !sr.isSelected() && !czw.isSelected() &&
                                !pt.isSelected() && !sb.isSelected() &&
                                !nd.isSelected())
                        {
                            AlertBox.wyswietl("Wiadomość","Zaznacz dni tygodnia",false);

                        } else{
                            //Zmienna przechowujaca ilsoc godzin w danym dniu lub 'wolne'
                            String h="";
                            if(wolne.isSelected()){
                                h="Wolne";
                            }
                            else{
                                h = rh.getValue()+":"+rm.getValue()+" - "+zh.getValue()+":"+zm.getValue();
                            }

                            //sprawdzenie w ktorych dniach nalezy zmienic dane
                            if(pon.isSelected()){
                                Grafik g = new Grafik(bean.getFirstName(), bean.getLastName(), "pon", h);
                                Grafik message = (Grafik) ServerConnection.sendToServer.send(ServerOperation.addSchedule, g);
                            }
                            if(wt.isSelected()){
                                Grafik g = new Grafik(bean.getFirstName(), bean.getLastName(), "wt", h);
                                Grafik message = (Grafik) ServerConnection.sendToServer.send(ServerOperation.addSchedule, g);
                            }
                            if(sr.isSelected()){
                                Grafik g = new Grafik(bean.getFirstName(), bean.getLastName(), "sr", h);
                                Grafik message = (Grafik) ServerConnection.sendToServer.send(ServerOperation.addSchedule, g);
                            }
                            if(czw.isSelected()){
                                Grafik g = new Grafik(bean.getFirstName(), bean.getLastName(), "czw", h);
                                Grafik message = (Grafik) ServerConnection.sendToServer.send(ServerOperation.addSchedule, g);
                            }
                            if(pt.isSelected()){
                                Grafik g = new Grafik(bean.getFirstName(), bean.getLastName(), "pt", h);
                                Grafik message = (Grafik) ServerConnection.sendToServer.send(ServerOperation.addSchedule, g);
                            }
                            if(sb.isSelected()){
                                Grafik g = new Grafik(bean.getFirstName(), bean.getLastName(), "sob", h);
                                Grafik message = (Grafik) ServerConnection.sendToServer.send(ServerOperation.addSchedule, g);
                            }
                            if(nd.isSelected()){
                                Grafik g = new Grafik(bean.getFirstName(), bean.getLastName(), "nd", h);
                                Grafik message = (Grafik) ServerConnection.sendToServer.send(ServerOperation.addSchedule, g);
                            }
                        }
                    }

                }
                pon.setSelected(false);
                wt.setSelected(false);
                sr.setSelected(false);
                czw.setSelected(false);
                pt.setSelected(false);
                sb.setSelected(false);
                nd.setSelected(false);
                wolne.setSelected(false);
            }
        });


        //---wyswietlenie pracownikow---
        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                data.clear();
                LinkedList<Pracownik> list = (LinkedList<Pracownik>) ServerConnection.sendToServer.send(ServerOperation.showPracownicy, null);

                for(Pracownik bean : list){
                    data.add(new Person(
                            bean.getName(),
                            bean.getSurname(),
                            Integer.toString(bean.getAge()),
                            bean.getGender(),
                            bean.getPesel(),
                            bean.getPlaceOfResidence(),
                            Float.toString(bean.getSalary()),
                            bean.getPosition()
                    ));
                }
            }
        });

        //---wyswietlenie grafiku---
        sg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gdata.clear();
                LinkedList<Grafik> list = (LinkedList<Grafik>) ServerConnection.sendToServer.send(ServerOperation.showSchedule, null);

                for(Grafik bean : list){
                    gdata.add(new Grafik(
                            bean.getImie(),
                            bean.getNazwisko(),
                            bean.getPon(),
                            bean.getWt(),
                            bean.getSr(),
                            bean.getCzw(),
                            bean.getPt(),
                            bean.getSb(),
                            bean.getNd()
                    ));
                }
            }
        });

        Group group2=new Group();
        group2.getChildren().addAll(
                imageView,show,
                g,sg,
                workersTable, grafik,
                opis,rh,rm,
                opis2,zh,zm,
                day,pon,wt,sr,czw,pt,sb,nd,wolne
        );

        tab3.setContent(group2);

    }
}
