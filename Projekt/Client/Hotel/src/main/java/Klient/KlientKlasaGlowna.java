package Klient;

import Pracownik.ModyfikacjaRezerwacji;
import Pracownik.SprawdzRozkladPracy;
import Pracownik.ZaPromocjami;
import Pracownik.ZakladkaAktualizacjaUdogodnien;
import elemHotel.ServerConnection;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import messages.EditReservation;
import messages.ServerOperation;

import java.util.LinkedList;

/**
 *
 * @author Piotr Bielecki
 *
 */

public class KlientKlasaGlowna extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        try{

            TabPane tabPane=new TabPane();
            //Zakładka Rezerwacja pobytu w hotelu przez klienta
            Tab tab1=new Tab("Zarezerwuj Pobyt");
            new Rezerwacja(tab1).wywolanieZakladkiRezerwacja();
            //Zakładka Formularz Kontaktowy
            Tab tab2=new Tab("Formularz Kontaktowy");
            new FormularzKontaktowy(tab2).wywolanieZakladkiFormularzKontaktowy();
            //Zakładka Opinia Klienta
            Tab tab3=new Tab("Dodaj opinie o hotelu");
            new OpiniaKlienta(tab3).wywolanieZakladkiOpiniaKlienta();
            //Zakładka Skargi Klienta
            Tab tab4=new Tab("Złóż Skargę");
            new Skargi(tab4).SkargiKlienta();





            tabPane.getTabs().addAll(tab1,tab2,tab3,tab4);
            Group group=new Group();

            group.getChildren().addAll(tabPane);
            AnchorPane root=new AnchorPane();
            root.getChildren().addAll(group);
            Scene scene=new Scene(root,1100,600);//szerokosc wysokosc
            stage.setX(100);
            stage.setY(50);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    ServerConnection.sendToServer.send(ServerOperation.disconnect, null);
                }
            });
            stage.show();

        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
