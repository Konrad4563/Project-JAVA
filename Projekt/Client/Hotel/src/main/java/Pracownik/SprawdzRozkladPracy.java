package Pracownik;

import elemHotel.MyButton;
import elemHotel.MyLabel;
import elemHotel.MyTextField;
import elemHotel.ServerConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import messages.Grafik;
import messages.ServerOperation;
import elemHotel.MainHotel;

import java.util.LinkedList;

/**
 *
 * @author Konrad Basa
 *
 */

public class SprawdzRozkladPracy {
    Tab tab;

    public SprawdzRozkladPracy(Tab tab) {
        this.tab = tab;
    }

    public void wywolanieZakladkiSprawdzRozkladPracy() {
        Font font = Font.font("Arial", FontWeight.BOLD, 14);

        Image image1 = new Image("grafikPracownicy.jpg");
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitHeight(600);
        imageView1.setFitWidth(1100);

        //Tabela grafiku
        TableView<Grafik> grafik = new TableView<>();
        ObservableList<Grafik> gdata = FXCollections.observableArrayList();
        grafik.setEditable(true);
        grafik.setLayoutX(100);
        grafik.setLayoutY(40);
        grafik.setMinHeight(100);
        grafik.setPrefHeight(250);
        grafik.setMinWidth(200);

        //kolumny w tabeli z pracownikami
        TableColumn name = new TableColumn("Imie");
        name.setPrefWidth(100);
        name.setCellValueFactory(
                new PropertyValueFactory<>("imie"));

        TableColumn surname = new TableColumn("Nazwisko");
        surname.setPrefWidth(100);
        surname.setCellValueFactory(
                new PropertyValueFactory<>("nazwisko"));

        TableColumn ponn = new TableColumn("Pon");
        ponn.setPrefWidth(100);
        ponn.setCellValueFactory(
                new PropertyValueFactory<>("pon"));

        TableColumn wtt = new TableColumn("Wt");
        wtt.setPrefWidth(100);
        wtt.setCellValueFactory(
                new PropertyValueFactory<>("wt"));

        TableColumn srr = new TableColumn("Sr");
        srr.setPrefWidth(100);
        srr.setCellValueFactory(
                new PropertyValueFactory<>("sr"));

        TableColumn czww = new TableColumn("Czw");
        czww.setPrefWidth(100);
        czww.setCellValueFactory(
                new PropertyValueFactory<>("czw"));

        TableColumn ptt = new TableColumn("Pt");
        ptt.setPrefWidth(100);
        ptt.setCellValueFactory(
                new PropertyValueFactory<>("pt"));

        TableColumn sbb = new TableColumn("Sob");
        sbb.setPrefWidth(100);
        sbb.setCellValueFactory(
                new PropertyValueFactory<>("sb"));

        TableColumn ndd = new TableColumn("Nd");
        ndd.setPrefWidth(100);
        ndd.setCellValueFactory(
                new PropertyValueFactory<>("nd"));

        grafik.setItems(gdata);
        grafik.getColumns().addAll(name,surname,ponn,wtt,srr,czww,ptt,sbb,ndd);

        //wpisanie danych do tabeli
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

        Group group1 = new Group();

        group1.getChildren().addAll(imageView1,grafik);
        tab.setContent(group1);
    }
}
