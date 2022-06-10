package Kierownik;

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

import static Kierownik.Constants.*;

/**
 *
 * @author Marcin Bonar
 *
 */

public class ZakladkaPracownicy extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try{
            //Dodanie TabPane
            TabPane tabPane=new TabPane();
            //Zakładka Pracownicy
            //Dodanie pierwszej zakładki(Tabsa)
            Tab tab1=new Tab(FIRST_TAB_NAME);
            new Pierwszy_Tab(tab1).open();
            //Zakladka Statystyki Hotelu
            Tab tab2=new Tab(SECOND_TAB_NAME);
            new ZakStatystykiHotelu(tab2).open1();
            //Zakładka modyfikacje ceny pokoji
            Tab tab3=new Tab(THIRD_TAB_NAME);
            new ModyfikacjiaPensjiPracownikow(tab3).open2();
            //Tworzenie grafiku dla pracowników
            Tab tab4=new Tab(FOURTH_TAB_NAME);
            new GrafikPracownicy(tab4).open3();
            //Dodawanie nowych Pokojów
            Tab tab5=new Tab(FITH_TAB_NAME);
            new DodawaniePokoi(tab5).open5();


            //Podanie poszczególnych tabsów do Tabpane
            tabPane.getTabs().addAll(tab1,tab3,tab4,tab5,tab2);
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
