package Pracownik;

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
import messages.ServerOperation;

/**
 *
 * @author Konrad Basa
 *
 */

public class PracownikKlasaGlowna extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try{

            TabPane tabPane=new TabPane();
            //Zakładka aktualizacja udogodnień
            Tab tab1=new Tab("Aktualizacja Udogodnień");
            new ZakladkaAktualizacjaUdogodnien(tab1).wywolanieZakladkiAktualizacjaUdogo();

            //Zakładka Modyfikacja Rezerwacji
            Tab tab2=new Tab("Modyfikacja oraz Anulowanie Rezerwacji Klienta");
            new ModyfikacjaRezerwacji(tab2).wywolanieZakladkiModyfikacjaRezerwacji();

            //Zakładka Zarzadzanie Promocjami
            Tab tab3=new Tab("Zarządzanie Promocjami w Hotelu");
            new ZaPromocjami(tab3).wywolanieZakladkiZaPromocjami();

            //Zakładka Sprawdzenie Rozkładu Pracy przez Pracownika
            Tab tab4=new Tab("Sprawdź Grafik Pracy na Dany Tydzień");
            new SprawdzRozkladPracy(tab4).wywolanieZakladkiSprawdzRozkladPracy();



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
