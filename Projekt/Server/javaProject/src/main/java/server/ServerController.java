package server;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */

 //Klasa kontrolera interfejsu graficznego serwera
 //Klasa słuzy do
public class ServerController {

    //Numer portu serwera
    int port = 1234;
    //Deklaracja gniazda serwera
    ServerSocket server;

    @FXML
    private TextArea statusText;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;

    @FXML
    public void initialize() {
        stopButton.setDisable(true);
    }

     // Metoda wykonywana po naciśnięciu przycisku start
      //aktywuje i dezaktywuje przycisk start i stop
      //Uruchamia serwer poprzez wywołanie metody startServer().

    public void startButtonOnAction() {
        Thread thread = new Thread(() -> {
            statusText.appendText("Serwer wystartował: port - " + port+"\n");
            statusText.appendText("Serwer w trybie nasłuchiwania \n");
            startServer();
        });
        thread.start();
        startButton.setDisable(true);
        stopButton.setDisable(false);
    }

     //Metoda wykonywana po naciśnięciu przycisku stop
     //aktywuje i dezaktywuje przycisk start i stop
     //Zamyka aktualne gniazdo serwera oraz wyświetla stosowny komunikat.

    public void stopButtonOnAction() {
        Thread thread = new Thread(() -> {
            statusText.appendText("Serwer wstrzymany\n");
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stopButton.setDisable(true);
            startButton.setDisable(false);
        });
        thread.start();
    }
    /**
     * Metoda uruchamiająca gniazdo serwera i nasłuchująca nowego klienta.
     * Dla nowego klienta tworzy osobny wątek do jego obsługi.
     */
    public void startServer() {

        try {
            //serwer nasłuchuje na porcie 1234
            server = new ServerSocket(port);

            //nieskończona pętla do pobierania żądań klienta
            while (true) {
                //obiekt gniazda odbierający połączenie
                Socket client = server.accept();
                //komunikat wyświetlający adres nowo połączonego klienta
                statusText.appendText("Nowy klient: \n" + InetAddress.getLocalHost()+" połączył się" +
                        " z serwem\n");

                ClientHandler clientSock = new ClientHandler(client);
                //wątek obsługujący klienta
                new Thread(clientSock).start();
            }
        } catch
        (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch
                (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}