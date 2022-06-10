package elemHotel;


import Kierownik.AlertBox;
import Kierownik.ZakladkaPracownicy;
import Klient.KlientKlasaGlowna;
import Pracownik.PracownikKlasaGlowna;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import messages.LoginMessage;
import messages.ServerOperation;

import java.util.Objects;

import static elemHotel.ConstStringName.*;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */

public class MainHotel extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    MyPasswordField passwordField;
    MyTextField textField;
    MyButton button;
    String wybor;
    public String s;


    @Override
    public void start(Stage stage) throws Exception {
        Font font = Font.font("Arial", FontWeight.BOLD, 20);
        Font font1 = Font.font("Arial", FontWeight.EXTRA_BOLD, 11);
        new ServerConnection().ServerConn();//laczysz sie z serwerem

        String[] listaWyborow = {CLIENT_TAB_NAME, OWNER_TAB_NAME, WORKER_TAB_NAME};
        ChoiceBox<String> choicebox = new ChoiceBox<>();
        choicebox.getItems().addAll(listaWyborow);
        choicebox.setLayoutX(100);
        choicebox.setLayoutY(150);
        choicebox.setMinHeight(30);
        choicebox.setMinWidth(200);


        button = new MyButton("Zaloguj się",100,300,30,200,Color.BLUE);
        textField=new MyTextField(100,200,30,200,"Podaj login");
        passwordField=new MyPasswordField(100,250,30,200,"Podaj hasło");


        choicebox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                wybor = listaWyborow[t1.intValue()];
                if(wybor==CLIENT_TAB_NAME){
                    textField.setDisable(true);
                    passwordField.setDisable(true);
                }
                else{
                    textField.setDisable(false);
                    passwordField.setDisable(false);
                }

                }
        });
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(textField.getText().isEmpty() && passwordField.getText().isEmpty()){
                            KlientKlasaGlowna klientKlasaGlowna = new KlientKlasaGlowna();
                            try {
                                klientKlasaGlowna.start(stage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                }
                else{
                    validateLogin(stage);
                }
            }
        });

        MyLabel label=new MyLabel(APP_TAB_NAME,100,70,font,Color.WHITE);
        MyLabel label1 = new MyLabel("Logowanie",140,100,font,Color.WHITE);

        Image image = new Image("panelWejscia.gif");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(700);
        imageView.setFitWidth(450);



        Group root = new Group();
        root.getChildren().add(imageView);
        root.getChildren().add(label);
        root.getChildren().add(label1);
        root.getChildren().add(button);
        root.getChildren().add(textField);
        root.getChildren().add(passwordField);
        root.getChildren().add(choicebox);

        Scene scene = new Scene(root, 400, 700);
        stage.setTitle(APP_TAB_NAME);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                ServerConnection.sendToServer.send(ServerOperation.disconnect, null);
            }
        });
        stage.show();



    }

    public void validateLogin(Stage stage){
        LoginMessage messageOut = new LoginMessage(textField.getText(), passwordField.getText());
        LoginMessage message = (LoginMessage) ServerConnection.sendToServer.send(ServerOperation.verifyLogin, messageOut);


        if(message.getVerNum() == 1){
            if (Objects.equals(wybor, OWNER_TAB_NAME) && textField.getText().equals("Admin") && passwordField.getText().equals("Admin")) {
                        ZakladkaPracownicy zakladkaPracownicy = new ZakladkaPracownicy();
                        zakladkaPracownicy.start(stage);
            }
            if(Objects.equals(wybor, WORKER_TAB_NAME) && !textField.getText().equals("Admin") && !passwordField.getText().equals("Admin")){
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        PracownikKlasaGlowna pracownikKlasaGlowna=new PracownikKlasaGlowna();
                        try {
                            pracownikKlasaGlowna.start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }else {
            AlertBox.wyswietl("Wiadomość","Nieprawidłowe dane!",false);
        }
}}
