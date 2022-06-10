package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */


//Główna Klasa serwera, otwiera nowe okno z pliku .fxml

public class Main extends Application {


    //Metoda otwierająca nowe okno z niestandardową ikoną i tytułem sceny
    //primaryStage nowa zmienna okna
    //throws Exception obsługa wyjątków

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("server.fxml"));
        primaryStage.setTitle("serwer");
        primaryStage.setScene(new Scene(root, 570, 350));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
