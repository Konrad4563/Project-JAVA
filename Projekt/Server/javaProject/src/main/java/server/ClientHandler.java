package server;

import messages.*;
import server.database.DataManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */


// Klasa obsługująca połączenie serwera z klientem.
//  Klasa implementuje interfejs Runnable.

public  class ClientHandler implements Runnable {

    //Zmienna przehowywująca obiekt gniazda serwera */
    private final Socket clientSocket;
    //Deklaracja  strumienia wejściowego, przez który odbierane są dane */
    ObjectInputStream in;
    //Deklaracja  strumienia wyjściowego, przez który wysyłane są dane */
    ObjectOutputStream out;

    //Konstruktor inicjalizujący obiekt klasy ClientHandler.
    //socket zmienna przehowywująca obiekt gniazda serwera
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    // Metoda nasłuchująca kodów operacji wysyłanych przez klienta oraz odpowiadająca na nie.
    public void run() {
        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                String read = in.readObject().toString();
                ServerOperation serverOperation = ServerOperation.valueOf(read);
                if (serverOperation == ServerOperation.disconnect) {
                    out.writeObject(null);
                    System.out.println("Wylogowano klienta: "+ InetAddress.getLocalHost());
                    clientSocket.close();
                    return;
                } else {
                    Object object = in.readObject();
                    executeOperation(serverOperation, object);
                    System.out.println(serverOperation);//<====
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
      //Metoda odpowidająca za wykonanie odpowiedniej operacji serwera oraz odesłanie odpowiedzi do klienta
     //serverOperation operacja jaka ma zostać wykonana na serwerze
     //object obiekt zawierający parametry operacji przesłane przez klienta

    private synchronized void executeOperation(ServerOperation serverOperation, Object object) {
        switch (serverOperation) {
            case verifyLogin:
                LoginMessage verifyLogin = DataManager.verifyLogin(object);
                try {
                    out.writeObject(verifyLogin);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            //-------------------------Kierownik-----------------------------------
            case addPracownik:
                Pracownik addPracownik = DataManager.addPracownik(object);
                try {
                    out.writeObject(addPracownik);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case deletePracownik:
                Pracownik deletePracownik = DataManager.deletePracownik(object);
                try {
                    out.writeObject(deletePracownik);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showPracownicy:
                LinkedList<Pracownik> showPracownicy = DataManager.showPracownicy();
                try {
                    out.writeObject(showPracownicy);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showPositions:
                LinkedList<Position> showPositions = DataManager.showPositions();
                try {
                    out.writeObject(showPositions);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case newSalary:
                Pracownik newSalary = DataManager.newSalary(object);
                try {
                    out.writeObject(newSalary);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showTypeRoom:
                LinkedList<ShowTypeRoom> showTypeRoom = DataManager.showTypeRoom();
                try {
                    out.writeObject(showTypeRoom);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showAllRoom:
                LinkedList<Rooms> showAllRoom = DataManager.showAllRoom();
                try {
                    out.writeObject(showAllRoom);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addRoom:
                Rooms addRoom = DataManager.addRoom(object);
                try {
                    out.writeObject(addRoom);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case updateRoomPrice:
                Rooms updateRoomPrice = DataManager.updateRoomPrice(object);
                try {
                    out.writeObject(updateRoomPrice);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addSchedule:
                Grafik addSchedule = DataManager.addSchedule(object);
                try {
                    out.writeObject(addSchedule);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showSchedule:
                LinkedList<Grafik> showSchedule = DataManager.showSchedule();
                try {
                    out.writeObject(showSchedule);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showstatistics:
                LinkedList<Rooms> showstatistics = DataManager.showstatistics();
                try {
                    out.writeObject(showstatistics);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showStarsOpinion:
                Opinion showStarsOpinion = DataManager.showStarsOpinion(object);
                try {
                    out.writeObject(showStarsOpinion);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showOpinions:
                LinkedList<Opinion> showOpinions = DataManager.showOpinions();
                try {
                    out.writeObject(showOpinions);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showForMess:
                LinkedList<ContactForm> showForMess = DataManager.showForMess();
                try {
                    out.writeObject(showForMess);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showComplaints:
                LinkedList<Complaint> showComplaints = DataManager.showComplaints();
                try {
                    out.writeObject(showComplaints);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case freeRoom:
                Rooms freeRoom = DataManager.freeRoom(object);
                try {
                    out.writeObject(freeRoom);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            //-------------------------Pracownik-----------------------------------
            case showAmenities:
                LinkedList<Amenities> showAmenities = DataManager.showAmenities();
                try {
                    out.writeObject(showAmenities);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addAmenities:
                Amenities addAmenities = DataManager.addAmenities(object);
                try {
                    out.writeObject(addAmenities);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case deleteAmenities:
                Amenities deleteAmenities = DataManager.deleteAmenities(object);
                try {
                    out.writeObject(deleteAmenities);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addPromotion:
                Promotion addPromotion = DataManager.addPromotion(object);
                try {
                    out.writeObject(addPromotion);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addPromRoom:
                PromRoom addPromRoom = DataManager.addPromRoom(object);
                try {
                    out.writeObject(addPromRoom);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showPromotion:
                LinkedList<Promotion> showPromotion = DataManager.showPromotion();
                try {
                    out.writeObject(showPromotion);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case deletePromotion:
                PromRoom deletePromotion = DataManager.deletePromotion(object);
                try {
                    out.writeObject(deletePromotion);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case promotionList:
                LinkedList<Promotion> promotionList = DataManager.promotionList();
                try {
                    out.writeObject(promotionList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showReservation:
                LinkedList<EditReservation> showReservation = DataManager.showReservation();
                try {
                    out.writeObject(showReservation);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case cancellationMyAmenities:
                Amenities cancellationMyAmenities = DataManager.cancellationMyAmenities(object);
                try {
                    out.writeObject(cancellationMyAmenities);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addResAmenities:
                Amenities addResAmenities = DataManager.addResAmenities(object);
                try {
                    out.writeObject(addResAmenities);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case cancellationRes:
                Reservation cancellationRes = DataManager.cancellationRes(object);
                try {
                    out.writeObject(cancellationRes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case updateResTime:
                Reservation updateResTime = DataManager.updateResTime(object);
                try {
                    out.writeObject(updateResTime);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

                //----------------------Klient----------------------------------------

            case addReservation:
                Reservation addReservation = DataManager.addReservation(object);
                try {
                    out.writeObject(addReservation);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showMyAmenities:
                LinkedList<Amenities> showMyAmenities = DataManager.showMyAmenities(object);
                try {
                    out.writeObject(showMyAmenities);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showPosAmenities:
                LinkedList<Amenities> showPosAmenities = DataManager.showPosAmenities(object);
                try {
                    out.writeObject(showPosAmenities);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addOpinion:
                Opinion addOpinion = DataManager.addOpinion(object);
                try {
                    out.writeObject(addOpinion);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addConForm:
                ContactForm addConForm = DataManager.addConForm(object);
                try {
                    out.writeObject(addConForm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case showResCom:
                LinkedList<Complaint> showResCom = DataManager.showResCom(object);
                try {
                    out.writeObject(showResCom);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case addComplaint:
                Complaint addComplaint = DataManager.addComplaint(object);
                try {
                    out.writeObject(addComplaint);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case disconnect:
                break;

        }
    }
}
