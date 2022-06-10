package Kierownik;

import Pracownik.AmenitiesC;
import elemHotel.MyButton;
import elemHotel.MyLabel;
import elemHotel.ServerConnection;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import messages.*;

import java.util.Arrays;
import java.util.LinkedList;

import static Kierownik.Constants.*;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Marcin Bonar
 *
 */

public class ZakStatystykiHotelu{
    Tab tab1;
    String wybor;
    //stworzenie konstruktora klasy
    public  ZakStatystykiHotelu(Tab tab1){
        this.tab1=tab1;
    }
    public  ZakStatystykiHotelu(){

    }

    //Stworzenie funkcji
    public void open1(){
        Font font = Font.font("Arial", FontWeight.BOLD, 14);

        Image image = new Image(SECOND_TAB_IMAGE);
        ImageView imageView2 = new ImageView(image);
        imageView2.setFitHeight(600);
        imageView2.setFitWidth(1200);

        Label opinion = new Label("Srednia ocena hotelu");
        opinion.setLayoutX(650);
        opinion.setLayoutY(10);
        opinion.setAlignment(Pos.CENTER);
        opinion.setStyle("-fx-font-size: 15px; -fx-background-color: #C6EEEF;");

        //zdjecia gwiazdek
        Image image1 = new Image("star.png");
        Image image2 = new Image("emptyStar.png");

        //ustwaienia do złotej gwiazdki
        ImageView star1 = new ImageView(image1);
        star1.setLayoutX(800);
        star1.setLayoutY(10);
        ImageView star2 = new ImageView(image1);
        star2.setLayoutX(830);
        star2.setLayoutY(10);
        ImageView star3 = new ImageView(image1);
        star3.setLayoutX(860);
        star3.setLayoutY(10);
        ImageView star4 = new ImageView(image1);
        star4.setLayoutX(890);
        star4.setLayoutY(10);
        ImageView star5 = new ImageView(image1);
        star5.setLayoutX(920);
        star5.setLayoutY(10);

        //ustwaienia do pustej gwiazdki
        ImageView estar2 = new ImageView(image2);
        estar2.setLayoutX(830);
        estar2.setLayoutY(10);
        ImageView estar3 = new ImageView(image2);
        estar3.setLayoutX(860);
        estar3.setLayoutY(10);
        ImageView estar4 = new ImageView(image2);
        estar4.setLayoutX(890);
        estar4.setLayoutY(10);
        ImageView estar5 = new ImageView(image2);
        estar5.setLayoutX(920);
        estar5.setLayoutY(10);

        //srednia liczba gwiazdek
        float starOpinion;
        Opinion message = (Opinion) ServerConnection.sendToServer.send(ServerOperation.showStarsOpinion, null);
        starOpinion=message.getGwiazdki();

        //wyswietlanie gwiazdek pustych i pelnych w zaleznosci od sredniej oceny hotelu
        if(starOpinion >=2){
            estar2.setVisible(false);
            star2.setVisible(true);
        }
        else {
            star2.setVisible(false);
            estar2.setVisible(true);
        }
        if(starOpinion >=3){
            estar3.setVisible(false);
            star3.setVisible(true);
        }
        else {
            star3.setVisible(false);
            estar3.setVisible(true);
        }
        if(starOpinion >=4){
            estar4.setVisible(false);
            star4.setVisible(true);
        }
        else {
            star4.setVisible(false);
            estar4.setVisible(true);
        }
        if(starOpinion>=5){
            estar5.setVisible(false);
            star5.setVisible(true);
        }
        else {
            star5.setVisible(false);
            estar5.setVisible(true);
        }

        //wybor rodzaju informacji
        String[] s = new String[] {"Opinie","Skargi","Wiadomości"};
        ChoiceBox<String> messages = new ChoiceBox<>();
        messages.getItems().addAll(s);
        messages.setLayoutX(650);
        messages.setLayoutY(50);
        messages.setMinHeight(30);
        messages.setMinWidth(200);

        //lista z danymi
        ListView listView = new ListView();
        listView.setLayoutX(650);
        listView.setLayoutY(100);
        listView.setPrefWidth(400);

        String t= "Treść opinii:\n";
        String g= "\nIlośc gwiazdek: ";
        String f= "Dane klienta: \n";
        String tf = "Treśc wiadomości:\n";


        //wyswietlanie opinii/skarg/wiadomosci
        messages.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                wybor = s[t1.intValue()];
                //wyswietlenie opinii
                if(wybor=="Opinie"){
                    listView.getItems().clear();
                    LinkedList<Opinion> list = (LinkedList<Opinion>) ServerConnection.sendToServer.send(ServerOperation.showOpinions, null);
                    for(Opinion bean : list){
                        TextArea cell = new TextArea();
                        cell.setEditable(false);
                        cell.setPrefWidth(380);
                        cell.setPrefHeight(60);
                        cell.setText(t+bean.getTresc()+g+bean.getGwiazdki());
                        if(bean.getGwiazdki() == 5){
                            cell.setStyle("-fx-background-color: green;");
                        }
                        if(bean.getGwiazdki() < 3){
                            cell.setStyle("-fx-background-color: red;");
                        }
                        if(bean.getGwiazdki() > 3 && bean.getGwiazdki() <5){
                            cell.setStyle("-fx-background-color: orange;");
                        }
                        listView.getItems().add(cell);
                    }
                }
                //wyswietlenie skarg
                if(wybor=="Skargi"){
                    listView.getItems().clear();
                    LinkedList<Complaint> list = (LinkedList<Complaint>) ServerConnection.sendToServer.send(ServerOperation.showComplaints, null);
                    for(Complaint bean : list){
                        TextArea cell = new TextArea();
                        cell.setEditable(false);
                        cell.setPrefWidth(380);
                        cell.setPrefHeight(100);

                        cell.setText("Dane klienta: \n"+
                                bean.getImie()+" "+bean.getNazwisko()+
                                "\nId rezerwacji: "+bean.getIdOfReservation()+
                                "\nTreść skargi: "+bean.getTresc()
                        );

                        listView.getItems().add(cell);
                    }
                }
                //wyswietlenie wiadomosci z formularzy kontaktowych
                if(wybor=="Wiadomości"){
                    listView.getItems().clear();
                    LinkedList<ContactForm> list = (LinkedList<ContactForm>) ServerConnection.sendToServer.send(ServerOperation.showForMess, null);
                    for(ContactForm bean : list){
                        TextArea cell = new TextArea();
                        cell.setEditable(false);
                        cell.setPrefWidth(380);
                        cell.setPrefHeight(100);
                        cell.setWrapText(true);
                        cell.setText( f+
                                bean.getImie()+" "+bean.getNazwisko()+"\n"+
                                bean.getNr_tel()+" "+ bean.getEmail()+"\n"+
                                tf+
                                bean.getTresc()+"\n");
                        listView.getItems().add(cell);
                    }
                }

            }
        });


        ObservableList<String> listt = FXCollections.observableArrayList();
        //pozyskanie aktualnych cen pokoi
        listt.clear();
        LinkedList<Rooms> roomPrice = (LinkedList<Rooms>) ServerConnection.sendToServer.send(ServerOperation.showstatistics, null);
        for(Rooms bean : roomPrice){
            listt.add(String.valueOf(bean.getCena()));
        }

        //Ustawienie osi X danych
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Nocleg")));

        //Ustawienie osi Y
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Przychód(zł)");


        //Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Porównanie Przychodów z Pokoji w Hotelu (liczone wraz z udogodnieniami)");
        barChart.setStyle("-fx-background-color: #C6EEEF;");
        barChart.setLayoutY(10);
        barChart.setLayoutX(10);
        barChart.setPrefWidth(600);
        barChart.setPrefHeight(520);

        //Prepare XYChart.Series objects by setting data
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Pokoj jednoosobowy");
        series1.getData().add(new XYChart.Data<>("Nocleg", parseInt(listt.get(0))));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Pokoj dwuosobowy");
        series2.getData().add(new XYChart.Data<>("Nocleg", parseInt(listt.get(1))));

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Apartament");
        series3.getData().add(new XYChart.Data<>("Nocleg", parseInt(listt.get(2))));

        barChart.getData().addAll(series1,series2,series3);

        Group group2=new Group();

        group2.getChildren().addAll(imageView2,barChart,opinion,star1,star2,star3,star4,star5,
                estar2,estar3,estar4,estar5,
                listView,messages);
        tab1.setContent(group2);

    }
}
