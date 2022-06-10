package Pracownik;

import Kierownik.AlertBox;
import Kierownik.Person;
import Klient.KlientKlasaGlowna;
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
import messages.Grafik;
import messages.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

import static elemHotel.ConstStringName.CLIENT_TAB_NAME;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Konrad Basa
 *
 */

public class ZaPromocjami {
    Tab tab;
    ObservableList<String> listt = FXCollections.observableArrayList();
    public ZaPromocjami(Tab tab){
        this.tab=tab;
    }
    public void wywolanieZakladkiZaPromocjami(){
        Font font = Font.font("Arial", FontWeight.BOLD, 14);

        Image image = new Image("zarzaPromocjami.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(600);
        imageView.setFitWidth(1100);
        UnaryOperator<TextFormatter.Change> pr = change -> {
            String newText = change.getControlNewText();
            if (newText.length() > 6 || !change.getControlNewText().matches("\\d*")) {
                return null ;
            } else {
                return change ;
            }
        };

        MyTextField promotionName=new MyTextField(10,10,30,200,"Podaj nazwe promocji");
        MyTextField promotionValue=new MyTextField(10,50,30,200,"Podaj wartość promocji w %");
        promotionValue.setTextFormatter(new TextFormatter<String> (pr));
        MyButton list = new MyButton("Wyświetl",250,440,30,200,Color.BLUE);
        MyButton anulowanie = new MyButton("Anuluj promocje",720,440,30,200,Color.BLUE);

        //promocje do usuniecia
        ComboBox<String> combo = new ComboBox();
        combo.setLayoutX(500);
        combo.setLayoutY(440);
        combo.setMinHeight(30);
        combo.setMinWidth(200);

        Label roz = new Label("Data rozpoczęcia");
        roz.setLayoutY(120);
        roz.setLayoutX(10);
        roz.setAlignment(Pos.CENTER);
        roz.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        Label zak = new Label("Data zakończenia");
        zak.setLayoutY(120);
        zak.setLayoutX(250);
        zak.setAlignment(Pos.CENTER);
        zak.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        MyButton button=new MyButton("Zapisz promocje",10,200,30,200,Color.BLUE);

        //stworzenie datapickerow od daty rozpoczecia promocji zakonczenia promocji
        DatePicker data = new DatePicker();
        DatePicker z = new DatePicker();
        data.setLayoutY(150);
        data.setLayoutX(10);
        z.setLayoutY(150);
        z.setLayoutX(250);

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

        CheckBox j = new CheckBox("Jednoosobowy");
        j.setLayoutX(250);
        j.setLayoutY(10);
        j.setPrefWidth(200);
        j.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        CheckBox d = new CheckBox("Dwuosobowy");
        d.setLayoutX(250);
        d.setLayoutY(50);
        d.setPrefWidth(200);
        d.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        CheckBox a = new CheckBox("Apartament");
        a.setLayoutX(250);
        a.setLayoutY(90);
        a.setPrefWidth(200);
        a.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        //tabela z lista promocji
        TableView<Promotion> promocje = new TableView<>();
        ObservableList<Promotion> gdata = FXCollections.observableArrayList();
        promocje.setEditable(true);
        promocje.setLayoutX(250);
        promocje.setLayoutY(180);
        promocje.setMinHeight(100);
        promocje.setPrefHeight(250);
        promocje.setMinWidth(300);
        promocje.setPrefWidth(780);

        //kolumny w tabeli z promocjami
        TableColumn name = new TableColumn("Nazwa");
        name.setMinWidth(150);
        name.setCellValueFactory(
                new PropertyValueFactory<>("nazwa"));

        TableColumn wartosc = new TableColumn("Wartosc promocji");
        wartosc.setMinWidth(150);
        wartosc.setCellValueFactory(
                new PropertyValueFactory<>("wartosc"));

        TableColumn dat_roz = new TableColumn("Data rozpoczecia");
        dat_roz.setMinWidth(100);
        dat_roz.setPrefWidth(150);
        dat_roz.setCellValueFactory(
                new PropertyValueFactory<>("dat_roz"));

        TableColumn dat_zak = new TableColumn("Data zakończenia");
        dat_zak.setMinWidth(100);
        dat_zak.setPrefWidth(150);
        dat_zak.setCellValueFactory(
                new PropertyValueFactory<>("dat_zak"));

        TableColumn prom_rodz = new TableColumn("Rodzaj pokoju");
        prom_rodz.setMinWidth(100);
        prom_rodz.setPrefWidth(150);
        prom_rodz.setCellValueFactory(
                new PropertyValueFactory<>("rodzaj_pokoju"));


        promocje.setItems(gdata);
        promocje.getColumns().addAll(name,wartosc,dat_roz,dat_zak,prom_rodz);

        //dodanie nowej promocji
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                            //sprawdzenie czy dane zostaly podane
                            if(!promotionName.getText().isEmpty() && !promotionValue.getText().isEmpty() &&
                                            (j.isSelected() || d.isSelected() || a.isSelected()) &&
                                            data.getValue()!=null && z.getValue()!=null)
                            {

                                    //dodanie promocji do tabeli promocja
                                    Promotion p = new Promotion(
                                            promotionName.getText(),
                                            promotionValue.getText(),
                                            i[0],
                                            y[0],
                                            ""
                                    );
                                    Promotion message = (Promotion) ServerConnection.sendToServer.send(ServerOperation.addPromotion, p);
                                    PromRoom mess=null;
                                    //w zaleznosci od tego jakie typy pokoi obejmuje promocja dodanie danych do tabeli promocja_pokoj
                                    if(j.isSelected()){
                                        PromRoom o = new PromRoom(promotionName.getText(),"1");
                                         mess = (PromRoom) ServerConnection.sendToServer.send(ServerOperation.addPromRoom, o);
                                        j.setSelected(false);
                                    }
                                    if(d.isSelected()){
                                        PromRoom f = new PromRoom(promotionName.getText(),"2");
                                         mess = (PromRoom) ServerConnection.sendToServer.send(ServerOperation.addPromRoom, f);
                                        d.setSelected(false);
                                    }
                                    if(a.isSelected()){
                                        PromRoom u = new PromRoom(promotionName.getText(),"3");
                                         mess = (PromRoom) ServerConnection.sendToServer.send(ServerOperation.addPromRoom, u);
                                        a.setSelected(false);
                                    }

                                    if(message!=null && mess!=null ){
                                        AlertBox.wyswietl("Wiadomość","Dodano pomyślnie",true);
                                    }
                                    else{
                                        AlertBox.wyswietl("Wiadomość","Wystąpił problem podczas anulowania promocji.",false);
                                    }

                                promotionName.clear();
                                promotionValue.clear();
                            }
                            else{
                                AlertBox.wyswietl("Wiadomość","Wprowadz dane!",false);
                            }
            }
        });

        //wyswietlenie listy promocji
        list.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gdata.clear();
                LinkedList<Promotion> list = (LinkedList<Promotion>) ServerConnection.sendToServer.send(ServerOperation.showPromotion, null);

                //zmiana z formatu
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

                    //zaktualizowanie listy mozliwych promocji
                    listt.clear();
                    LinkedList<Promotion> listOfPositions = (LinkedList<Promotion>) ServerConnection.sendToServer.send(ServerOperation.promotionList, null);

                    for(Promotion bean : listOfPositions){
                        listt.add(bean.getNazwa());
                    }
                    combo.setItems(listt);
            }
        });

        //anulowanie promocji
        anulowanie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(combo.getValue()!=null){
                    PromRoom p = new PromRoom(combo.getValue().toString(),"1");
                    PromRoom mess = (PromRoom) ServerConnection.sendToServer.send(ServerOperation.deletePromotion, p);

                    if(mess!=null){
                        AlertBox.wyswietl("Wiadomość","Anulowano promocje.",true);
                    }
                    else{
                        AlertBox.wyswietl("Wiadomość","Wystąpił problem podczas anulowania promocji.",false);
                    }
                }
                else{
                    AlertBox.wyswietl("Wiadomość","Wybierz promocje z listy",false);
                }

                //zaktualizowanie listy mozliwych promocji
                listt.clear();
                LinkedList<Promotion> listOfPositions = (LinkedList<Promotion>) ServerConnection.sendToServer.send(ServerOperation.promotionList, null);

                for(Promotion bean : listOfPositions){
                    listt.add(bean.getNazwa());
                }
                combo.setItems(listt);
            }
        });



        Group group1=new Group();

        group1.getChildren().addAll(
                imageView,
                promotionName,promotionValue,
                data,z,button,
                roz,zak,promocje,list,
                j,d,a,anulowanie,combo
        );
        tab.setContent(group1);
    }
}
