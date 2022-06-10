package Kierownik;

import elemHotel.MyButton;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import messages.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;
import javafx.scene.control.cell.PropertyValueFactory;

import static Kierownik.Constants.FIRST_TAB_IMAGE;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Marcin Bonar
 *
 */

public class Pierwszy_Tab {
    Tab tab;
    String position;
    //stworzenie konstruktora klasy
    public  Pierwszy_Tab(Tab tab){
        this.tab=tab;
    }
    //Stworzenie funkcji
    public void open(){
        AlertBox alertBox=new AlertBox();
        //Załadowanie tła do zakładki zarządzania pracownikami
        Image image = new Image(FIRST_TAB_IMAGE);
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
        UnaryOperator<TextFormatter.Change> peselNumbers = change -> {
            String newText = change.getControlNewText();
            if (newText.length() > 11 || !change.getControlNewText().matches("\\d*")) {
                return null ;
            } else {
                return change ;
            }
        };
        UnaryOperator<TextFormatter.Change> salary = change -> {
            String newText = change.getControlNewText();
            if (newText.length() > 6 || !change.getControlNewText().matches("\\d*")) {
                return null ;
            } else {
                return change ;
            }
        };


        //formularz dodawania pracownika

        //pola tekstowe
        MyTextField nameTF=new MyTextField(40,20,30,200,"Podaj imie");
        nameTF.setTextFormatter(new TextFormatter<String> (noNumbers));
        MyTextField surnameTF=new MyTextField(40,60,30,200,"Podaj nazwisko");
        surnameTF.setTextFormatter(new TextFormatter<String> (noNumbers));

        Label wiek = new Label("Wiek");
        wiek.setLayoutX(40);
        wiek.setLayoutY(100);
        wiek.setPrefHeight(30);
        wiek.setPrefWidth(200);
        wiek.setAlignment(Pos.CENTER);
        wiek.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        //Spinner wieku pracownika
        Spinner<Integer> ageSP = new Spinner<>(16, 100, 0, 1);
        ageSP.setLayoutX(40);
        ageSP.setLayoutY(130);
        ageSP.setPrefWidth(200);
        ageSP.setPrefHeight(30);
        var factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(16, 100, 1);factory.setWrapAround(true);

        //radiobuttony okreslajace plec
        final ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Kobieta");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        rb1.setLayoutX(40);
        rb1.setLayoutY(170);
        rb1.setStyle("-fx-font-size: 15px; -fx-background-color: white;");
        rb1.setPrefWidth(100);
        rb1.setPrefHeight(30);

        RadioButton rb2 = new RadioButton("Mężczyzna");
        rb2.setToggleGroup(group);
        rb2.setLayoutX(140);
        rb2.setLayoutY(170);
        rb2.setPrefHeight(30);
        rb2.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        //pola tekstowe
        MyTextField peselTF=new MyTextField(40,210,30,200,"Podaj numer pesel");
        peselTF.setTextFormatter(new TextFormatter<String> (peselNumbers));
        MyTextField placeTF=new MyTextField(40,250,30,200,"Podaj miejsce zamieszkania");
        MyTextField salaryTF=new MyTextField(40,290,30,200,"Ustal wynagrodzenie");
        salaryTF.setTextFormatter(new TextFormatter<String>(salary));

        Label stanowisko = new Label("Stanowisko");
        stanowisko.setLayoutX(40);
        stanowisko.setLayoutY(330);
        stanowisko.setPrefHeight(30);
        stanowisko.setPrefWidth(200);
        stanowisko.setAlignment(Pos.CENTER);
        stanowisko.setStyle("-fx-font-size: 15px; -fx-background-color: white;");

        //choiceboxy dotyczace stanowiska
        //pobranie mozliwych stanowisk z bazy danych
        List<String> positions = new LinkedList<String>();
        LinkedList<Position> listOfPositions = (LinkedList<Position>) ServerConnection.sendToServer.send(ServerOperation.showPositions, null);

        for(Position bean : listOfPositions){
                   positions.add(bean.getNazwa());
        }

        ChoiceBox<String> choicebox = new ChoiceBox<>();
        choicebox.getItems().addAll(positions);
        choicebox.setLayoutX(40);
        choicebox.setLayoutY(360);
        choicebox.setMinHeight(30);
        choicebox.setMinWidth(200);

        //wpisanie do stanowiska pierwszej wartosci z listy
        choicebox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                int l = parseInt(String.valueOf(t1));
                l=l+1;
                position = String.valueOf(l);
            }
        });


        //przyciski oblusgujace akcje dotyczace pracownika
        MyButton show=new MyButton("Sprawdź Listę Pracowników",490,520,30,200,Color.BLUE);
        MyButton remove=new MyButton("Zwolnij Pracownika",40,440,30,200,Color.BLUE);
        MyButton add=new MyButton("Zatrudnij Pracownika",40,400,30,200,Color.BLUE);

        //Tabela z pracownikami
        TableView<Person> workersTable = new TableView<>();
        ObservableList<Person> data = FXCollections.observableArrayList();
        workersTable.setEditable(true);
        workersTable.setLayoutX(250);
        workersTable.setLayoutY(20);
        workersTable.setMinHeight(500);
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


        //dodawanie nowego pracownika
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //sprawdzenie czy zostalty podane niezbedne dane
                if(nameTF.getText().isEmpty() ||
                        surnameTF.getText().isEmpty() ||
                        peselTF.getText().isEmpty() ||
                        placeTF.getText().isEmpty() ||
                        salaryTF.getText().isEmpty() ||
                        choicebox.getValue()==null)
                {
                    AlertBox.wyswietl("Wiadomość","Uzupełnij dane",false);
                }
                else{
                    String gender="";
                    if(rb1.isSelected()){
                        gender=rb1.getText();
                    }
                    if(rb2.isSelected()){
                        gender=rb2.getText();
                    }
                    Pracownik messageOut = new Pracownik(
                            nameTF.getText(),
                            surnameTF.getText(),
                            ageSP.getValue(),
                            gender,
                            peselTF.getText(),
                            placeTF.getText(),
                            parseFloat(salaryTF.getText()),
                            position
                    );
                    Pracownik message = (Pracownik) ServerConnection.sendToServer.send(ServerOperation.addPracownik, messageOut);
                    nameTF.clear();
                    surnameTF.clear();
                    peselTF.clear();
                    placeTF.clear();
                    salaryTF.clear();
                    if(message==null){
                        AlertBox.wyswietl("Wiadomość","Nie udało się dodać pracownika "+messageOut.getName()+" "+messageOut.getSurname(),false);
                    }
                    else{
                        AlertBox.wyswietl("Wiadomość","Dodano pracownika "+messageOut.getName()+" "+messageOut.getSurname(),true);
                    }

                }
            }
        });

        //---usuwanie pracownika----
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(Person bean: data){
                    if(bean.getSelect().isSelected()){
                        Pracownik d = new Pracownik(
                                bean.firstName,
                                bean.lastName,
                                parseInt(bean.age),
                                bean.gender,
                                bean.pesel,
                                bean.place,
                                parseFloat(bean.salary),
                                bean.position
                        );
                        Pracownik message = (Pracownik) ServerConnection.sendToServer.send(ServerOperation.deletePracownik, d);
                        if(message==null){
                            AlertBox.wyswietl("Wiadomość","Nie udało się usunąć pracownika "+d.getName()+" "+d.getSurname(),false);
                        }
                        else{
                            AlertBox.wyswietl("Wiadomość","Usunięto pracownika "+d.getName()+" "+d.getSurname(),true);
                        }
                    }
                }
            }
        });
        //---------------------------

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

        Group group1=new Group();

        group1.getChildren().addAll(
                imageView,
                wiek,stanowisko,
                nameTF,surnameTF,ageSP,peselTF, placeTF,salaryTF,
                rb1,rb2,
                choicebox,
                add,remove,show,
                workersTable
        );
        tab.setContent(group1);
}

}



