package elemHotel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */

 //Klasa odpwiedzialna za połączenie klienta z serwerem.

public class ServerConnection {

    //statyczny obiekt klasy obsługującej komunikację z serwerem
    public static SendToServer sendToServer;


     //Metoda łącząca sie z serwerem na zadanym porcie.
     //Toworzy strumienie do komunikacji z serwerem.

    public void ServerConn() {
        Socket socket;
        try {
            socket = new Socket("localhost", 1234);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            sendToServer = new SendToServer(out,in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
