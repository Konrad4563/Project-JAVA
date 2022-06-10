package elemHotel;

import messages.ServerOperation;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */


  //Klasa odpwiedzialna za przesyłanie komunikatów między serwerem, a klientem.

public class SendToServer {

    // Deklaracja  strumienia wyjściowego, przez który wysyłane są dane */
    ObjectOutputStream out;
    //Deklaracja  strumienia wejściowego, przez który odbierane są dane */
    ObjectInputStream in;

     // Konstruktor inicjalizujący obiekt klasy SendToServer.
      //out obiekt strumienia wyjściowego
     //param in obiekt strumienia wejściowego

    public SendToServer(ObjectOutputStream out,ObjectInputStream in) {
        this.out = out;
        this.in = in;
    }


    //Metoda wysyłająca i odbierająca wiadomości.
    //serverOperation operacja wykonywana przez serwer
    //object obiekt przechowywujący parametry wymagane do wykonania akcji serwera.
     //zdeserializowany obiekt odczytany ze strumienia przechowujący odpowiedź serwera

    public Object send(ServerOperation serverOperation, Object object) {
        try {
            out.writeObject(serverOperation.toString());
            out.writeObject(object);
            return in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
