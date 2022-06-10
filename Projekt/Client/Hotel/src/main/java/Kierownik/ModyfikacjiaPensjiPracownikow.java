package Kierownik;

import elemHotel.MyButton;
import elemHotel.MyLabel;
import elemHotel.MyTextField;
import elemHotel.ServerConnection;
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
import messages.ServerOperation;

import java.util.LinkedList;
import java.util.function.UnaryOperator;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Marcin Bonar
 *
 */

public class ModyfikacjiaPensjiPracownikow {
    Tab tab2;
    public ModyfikacjiaPensjiPracownikow(Tab tab2){
        this.tab2=tab2;
    }
    public void open2(){
        Font font = Font.font("Arial", FontWeight.BOLD, 14);

        Image image2 = new Image("cena.jpg");
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitHeight(600);
        imageView2.setFitWidth(1100);

        UnaryOperator<TextFormatter.Change> salary = change -> {
            String newText = change.getControlNewText();
            if (newText.length() > 6 ||!change.getControlNewText().matches("\\d*")) {
                return null ;
            } else {
                return change ;
            }
        };

        MyLabel label2= new MyLabel("Wybierz pracownika z listy",40,10,font,Color.WHITE);
        MyButton ns=new MyButton("Wprowadz zmiane",240,260,30,200,Color.BLUE);
        MyButton show=new MyButton("Lista pracownikow",230,10,30,200,Color.BLUE);
        MyTextField newsalary = new MyTextField(40,260,30,200,"Nowe wynagrodzenie");
        newsalary.setTextFormatter(new TextFormatter<String> (salary));

        //Tabela z pracownikami
        TableView<Person> table = new TableView<>();
        ObservableList<Person> data = FXCollections.observableArrayList();
        table.setEditable(true);
        table.setLayoutX(40);
        table.setLayoutY(50);
        table.setMinHeight(100);
        table.setPrefHeight(200);
        table.setMinWidth(200);

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

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol,ageCol,genderCol,peselCol,placeCol,salaryCol,positionCol,checkCol);

        //wyswietlenie listy pracownikow
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

        //wprowadzenie nowego wynagrodzenia
        ns.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!newsalary.getText().isEmpty()) {
                    for (Person bean : data) {
                        if (bean.getSelect().isSelected()) {
                            Pracownik d = new Pracownik(
                                    bean.firstName,
                                    bean.lastName,
                                    parseInt(bean.age),
                                    bean.gender,
                                    bean.pesel,
                                    bean.place,
                                    parseFloat(newsalary.getText()),
                                    bean.position
                            );
                            Pracownik message = (Pracownik) ServerConnection.sendToServer.send(ServerOperation.newSalary, d);
                            if (message != null) {
                                AlertBox.wyswietl("Wiadomość", "Zmieniono wynagrodzenie.",true);
                            } else {
                                AlertBox.wyswietl("Wiadomość", "Błąd podczas zmiany wynagrodzenia.",false);
                            }
                        }
                    }
                    newsalary.clear();
                }
            else{
                AlertBox.wyswietl("Wiadomość","Wprowadź dane.",false);
            }
        }});

        Group group2=new Group();
        group2.getChildren().addAll(imageView2,label2,table,show,newsalary,ns);
        tab2.setContent(group2);

    }
}
