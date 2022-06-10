package server.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import messages.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.time.temporal.ChronoUnit;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */


//Klasa odpowiedzialna za komunikację z bazą danych.
// Wysyła odpowiednia zapytania do bazy danych.

public class DataManager {


     // Metoda przesyłająca komunikat do bazy danych. Obsługuje zapytania typu SELECT
     //sql treść zapytania
     //zwraca obiekt ResultSet(funkcja)

    public static ResultSet executeQuery(String sql){
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();
            return statement.executeQuery(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Metoda przesyłająca komunikat do bazy danych. Obsługuje zapytania typu INSERT, UPDATE, DELETE
    // sql treść zapytania
    //zwraca wartość logiczną, true - jeśli operacja zakończyła się sukcesem,
    // jeśli został zgłoszony wyjątek(niewpowodzenie) - false

    public static Boolean execute(String sql){
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

     // Metoda konstruująca treść zapytania na podstawie odebranych od klienta parametrów.
      //Weryfikuje poprawność podanego hasła i loginu.
      //object obiekt będący odebraną od klienta wiadomością.
     // zwraca obiekt przechowujący odebrane z bazy danych rezultaty zapytania
    public static LoginMessage verifyLogin(Object object){
        LoginMessage messout = (LoginMessage) object;
        LoginMessage message = null;
        String query = "SELECT count(1), account_id FROM user_account WHERE username = '"+messout.getLogin()+
                "' AND password = '"+messout.getPassword()+"'";
        ResultSet rs = executeQuery(query);
        try {
            while(rs.next()){
                message = new LoginMessage(rs.getInt(1), rs.getInt(2));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

//------------------------------------------------------Kierownik----------------------------------------------------------------------------
    //dodawanie nowego pracownika
   public static Pracownik addPracownik(Object object){
       Pracownik messout = (Pracownik) object;

        String sql = "INSERT INTO `pracownik`(`IMIE`, `NAZWISKO`, `WIEK`, `PLEC`, `NR_PESEL`, `MIEJSCE_ZAMIESZKANIA`, `WYNAGRODZENIE`, `hotel_id_hotel`, `stanowisko_id_stanowisko`) " +
                "VALUES('"+
                messout.getName()+"','"+
                messout.getSurname()+"','"+
                messout.getAge()+"','"+
                messout.getGender()+"','"+
                messout.getPesel()+"','"+
                messout.getPlaceOfResidence()+"','"+
                messout.getSalary()+
                "','1','"+
                messout.getPosition()+
                "'); ";

       String sql2 = "INSERT INTO `user_account`(`firstname`,`lastname`,`username`,`password`)"+
       "VALUES('"+
               messout.getName()+"','"+
               messout.getSurname()+"','"+
               messout.getName()+"','"+
               messout.getPesel()+
               "');";

       String sql3 = "INSERT INTO `grafik`(`imie`,`nazwisko`,`pon`,`wt`,`sr`,`czw`,`pt`,`sob`,`nd`)"+
               "VALUES('"+messout.getName()+"','"+messout.getSurname()+"','Wolne','Wolne','Wolne','Wolne','Wolne','Wolne','Wolne');";

        Boolean result =execute(sql);
        Boolean result2 =execute(sql2);
        Boolean result3 =execute(sql3);
        if(!result || !result2 || !result3){
            System.out.println("Błąd przy dodaniu do bazy");
            return null;
        }
        else{
            System.out.println("Dodano pracownika do bazy");
            return messout;
        }
    }

    //usuwanie pracownika z listy
    public static Pracownik deletePracownik(Object object) {
        Pracownik p = (Pracownik) object;

        String sql = "DELETE FROM  `pracownik` WHERE `IMIE` ='"+p.getName()+"' AND `NR_PESEL`= '"+p.getPesel()+"';";
        String sql2 = "DELETE FROM  `user_account` WHERE `firstname` ='"+p.getName()+"' AND `password`= '"+p.getPesel()+"';";
        String sql3 = "DELETE FROM  `grafik` WHERE `imie` ='"+p.getName()+"' AND `nazwisko`= '"+p.getSurname()+"';";

            Boolean result=execute(sql);
            Boolean result2=execute(sql2);
            Boolean result3=execute(sql3);
            if(!result||!result2 || !result3){
                System.out.println("Błąd podczas usuwania");
                return null;
            }
            else{
                System.out.println("Usunieto pracownika");
                return p;
            }
    }

    //wyswietlenie listy pracownikow
    public static LinkedList<Pracownik> showPracownicy(){
        String query = "SELECT  `IMIE`, `NAZWISKO`, `WIEK`, `PLEC`, `NR_PESEL`, `MIEJSCE_ZAMIESZKANIA`, `WYNAGRODZENIE`, `nazwa_stanowiska` " +
                "FROM `pracownik`,`stanowisko` " +
                "WHERE pracownik.stanowisko_id_stanowisko = stanowisko.id_stanowisko ;";
        ResultSet rs = executeQuery(query);
        LinkedList<Pracownik> list = new LinkedList<Pracownik>();
        try {
            while(rs.next()){
                Pracownik m = new Pracownik(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getFloat(7),
                        rs.getString(8)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //wyswietlenie w choiceboxie na jakie stanowisko mozemy zatrdunic pracownika
    public static LinkedList<Position> showPositions(){
        String query = "SELECT `id_stanowisko`, `nazwa_stanowiska` FROM `stanowisko`;";
        ResultSet rs = executeQuery(query);
        LinkedList<Position> list = new LinkedList<Position>();
        try {
            while(rs.next()){
                Position m = new Position(
                        rs.getInt(1),
                        rs.getString(2)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //aktualizacja wynagrodzenia pracownikow
    public static Pracownik newSalary(Object object) {
        Pracownik p = (Pracownik) object;

        String sql = "UPDATE `pracownik` SET `WYNAGRODZENIE` = '"+p.getSalary()+"' WHERE `IMIE` ='"+p.getName()+"' AND `NR_PESEL`= '"+p.getPesel()+"';";

        Boolean result=execute(sql);
        if(!result){
            System.out.println("Błąd");
            return null;
        }
        else{
            System.out.println("Zmieniono wynagrodzenie");
            return p;
        }
    }

    //wyswietlenie typow pokoi
    public static LinkedList<ShowTypeRoom> showTypeRoom(){
        String query = "SELECT `id_typ_pokoju`, `rodzaj_pokoju`,`cena_noclegu` FROM `typ_pokoju`;";
        ResultSet rs = executeQuery(query);
        LinkedList<ShowTypeRoom> list = new LinkedList<ShowTypeRoom>();
        try {
            while(rs.next()){
                ShowTypeRoom m = new ShowTypeRoom(
                        rs.getInt(1),
                        rs.getString(2),
                        (int) rs.getFloat(3)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //wyswietlenie listy dostepnych pokoi
    public static LinkedList<Rooms> showAllRoom(){
        String query = "SELECT `stan`, `nr_pokoju`,`rodzaj_pokoju`,`cena_noclegu`"+
                "FROM `pokoj`,`typ_pokoju` " +
                "WHERE pokoj.pokoj_typ_pokoju = typ_pokoju.id_typ_pokoju ORDER BY `nr_pokoju`;";
        ResultSet rs = executeQuery(query);
        LinkedList<Rooms> list = new LinkedList<Rooms>();
        try {
            while(rs.next()){
                Rooms m = new Rooms(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //dodawanie nowego pokoju
    public static Rooms addRoom(Object object) {
        Rooms messout = (Rooms) object;

        String sql = "INSERT INTO `pokoj`(`stan`, `nr_pokoju`, `hotel_id_hotel`, `pokoj_typ_pokoju`) " +
                "VALUES ("+
                messout.getStan()+",'"+
                messout.getNr_pokoju()+"','"+
                "1','"+
                messout.getPokoj_typ_pokoju()+
                "'); ";

        Boolean result =execute(sql);

        //wyszukanie czy sa promocje dotyczace tego typu pokoju
        String sql2="SELECT `promocja_id_promocja` FROM `promocja_pokoj` WHERE `pokoj_id_pokoj`='"+messout.getPokoj_typ_pokoju()+"'";
        ResultSet rs = executeQuery(sql2);

        //lista z id promocji ktore dotycza danego typu pokoju
        List<Integer> list = new ArrayList<Integer>();
        try {
            while(rs.next()){
                list.add(parseInt(rs.getString(1)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        //jesli sa promocje dotyczace tego typu pokoju to dodajemy dane do tabeli promocja_pokoj
        if(!list.isEmpty()){
            int i=0;
            for(int bean : list){
                String sql3 = "INSERT INTO `promocja_pokoj`(`promocja_id_promocja`, `pokoj_id_pokoj`) " +
                        "VALUES ( '"+list.get(i)+"','"+messout.getNr_pokoju()+"');";
                execute(sql3);
                i++;
            }
        }

        if(!result){
            System.out.println("Błąd przy dodaniu pokoju do bazy");
            return null;
        }
        else{
            System.out.println("Dodano pokoj do bazy");
            return messout;
        }
    }

    //aktualizacja ceny danego pokoju
    public static Rooms updateRoomPrice(Object object) {
        Rooms p = (Rooms) object;
        String sql = "UPDATE `typ_pokoju` SET `cena_noclegu` = '"+p.getCena()+"' WHERE `rodzaj_pokoju` ='"+p.getPokoj_typ_pokoju()+"';";

        Boolean result=execute(sql);
        if(!result){
            System.out.println("Błąd przy zmianie ceny");
            return  null;
        }
        else{
            System.out.println("Zmieniono cene pokoju");
            return p;
        }
    }

    //edycja grafiku
    public static Grafik addSchedule(Object object) {

        Grafik p = (Grafik) object;
        String sql = "UPDATE `grafik` SET "+
                "`"+p.getDay()+"` = '"+p.getHours()+"'"+
                " WHERE `imie` ='"+p.getImie()+"' AND `nazwisko` = '"+p.getNazwisko()+"';";

        Boolean result=execute(sql);
        if(!result){
            System.out.println("Błąd przy zmianie grafiku");
            return null;
        }
        else{
            System.out.println("Grafik zmieniony pomyślnie");
            return p;
        }
    }

    //wyswietlanie grafiku
    public static LinkedList<Grafik> showSchedule() {
        String query = "SELECT *"+
                "FROM `grafik` ;";
        ResultSet rs = executeQuery(query);
        LinkedList<Grafik> list = new LinkedList<Grafik>();
        try {
            while(rs.next()){
                Grafik m = new Grafik(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //statystyki hotelu
    public static LinkedList<Rooms> showstatistics() {
        String query = "SELECT SUM(rezerwacja.caly_koszt) " +
                "FROM rezerwacja,pokoj " +
                "WHERE rezerwacja.pokoj_id_pokoj=pokoj.id_pokoj " +
                "GROUP BY pokoj.pokoj_typ_pokoju";

        ResultSet rs = executeQuery(query);
        LinkedList<Rooms> list = new LinkedList<Rooms>();
        try {
            while(rs.next()){
                Rooms m = new Rooms(
                        "1",
                        0,
                        "0",
                        rs.getInt(1)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //pobranie sredniej oceny gwiazdkowej
    public static Opinion showStarsOpinion(Object object) {
        Opinion messout = (Opinion) object;
        String query = "SELECT AVG(gwiazdki) FROM opinie;";

        ResultSet rs = executeQuery(query);
        Opinion m=null;
        try {
            while(rs.next()){
                m = new Opinion(
                        "",
                        rs.getFloat(1)
                );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        if(m!=null){
            return  m;
        }
        else{
            return  null;
        }

    }

    //wyswietlenie opinii klientow
    public static LinkedList<Opinion> showOpinions() {
        String query = "SELECT `tresc`,`gwiazdki`  FROM `opinie`";
        ResultSet rs = executeQuery(query);
        LinkedList<Opinion> list = new LinkedList<Opinion>();
        try {
            while(rs.next()){
                Opinion m = new Opinion(
                        rs.getString(1),
                        rs.getFloat(2)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //wyswietlanie wiadomosci z formularzy kontaktowych
    public static LinkedList<ContactForm> showForMess() {
        String query = "SELECT `imie`, `nazwisko`, `nr_tel`, `email`, `tresc`  FROM `formularz`";
        ResultSet rs = executeQuery(query);
        LinkedList<ContactForm> list = new LinkedList<ContactForm>();
        try {
            while(rs.next()){
                ContactForm m = new ContactForm(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //wyswietlenie skarg
    public static LinkedList<Complaint> showComplaints() {
        String query = "SELECT klient.imie,klient.nazwisko,klient.nr_tel,rezerwacja.id_rezer,skargi.tresc  " +
                "FROM klient,rezerwacja,skargi,rezerwacja_klient " +
                "WHERE rezerwacja.id_rezer=skargi.rez_id_rez AND " +
                "klient.id_klient = rezerwacja_klient.klient_id_klient AND " +
                "rezerwacja_klient.rezer_id_rezer=rezerwacja.id_rezer";
        ResultSet rs = executeQuery(query);
        LinkedList<Complaint> list = new LinkedList<Complaint>();
        try {
            while(rs.next()){
                Complaint m = new Complaint(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static Rooms freeRoom(Object object) {
        Rooms messout = (Rooms) object;

        String sql ="UPDATE `pokoj` SET `stan`=0 " +
                "WHERE nr_pokoju='"+messout.getNr_pokoju()+"';";

        Boolean result =execute(sql);

        if(!result){
            System.out.println("Błąd przy zwlanianiu pokoju");
            return  null;
        }
        else{
            System.out.println("Zwolniono pokój");
            return messout;
        }
    }
//------------------------------------------------------Pracownik----------------------------------------------------------------------------
    //wyswietlenie udogodnien
    public static LinkedList<Amenities> showAmenities() {
        String query = "SELECT `nazwa`, `cena`,`udo_typ_pokoju` "+
                "FROM `rodza_udo` ;";
        ResultSet rs = executeQuery(query);
        LinkedList<Amenities> list = new LinkedList<Amenities>();
        try {
            while(rs.next()){
                Amenities m = new Amenities(
                        rs.getString(1),
                        rs.getFloat(2),
                        rs.getString(3)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //dodawanie udogodnien
    public static Amenities addAmenities(Object object) {
        Amenities messout = (Amenities) object;
        String sql = "INSERT INTO `rodza_udo`(`nazwa`, `cena`, `udo_typ_pokoju`) " +
                "VALUES ('"+
                messout.getNazwa()+"','"+
                messout.getCena()+"','"+
                messout.getTyp_pokoju()+
                "'); ";

        Boolean result =execute(sql);

        if(!result){
            System.out.println("Błąd przy dodaniu udogodnienia do bazy");
            return null;
        }
        else{
            System.out.println("Dodano udogodnienie do bazy");
            return messout;
        }
    }

    //usuwanie udogodnien
    public static Amenities deleteAmenities(Object object) {
        Amenities p = (Amenities) object;

        String sql3 = "SELECT `id_rodz_udo` FROM `rodza_udo` WHERE `nazwa`='" + p.getNazwa() +
                "' AND `udo_typ_pokoju` = '"+p.getTyp_pokoju()+"';";

        ResultSet rs = executeQuery(sql3);
        String id_udogodnienia = null;
        try {
            while (rs.next()) {
                id_udogodnienia = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "DELETE FROM  `rodza_udo` WHERE `id_rodz_udo` ='"+id_udogodnienia+"';";

        Boolean result=execute(sql);
        if(!result){
            System.out.println("Błąd");
            return null;
        }
        else{
            System.out.println("Usunieto udogodnienia");
            return p;
        }

    }

    //dodawanie promocji
    public static Promotion addPromotion(Object object) {
        Promotion messout = (Promotion) object;
        String sql = "INSERT INTO `promocja`(`nazwa`, `wartosc_promocji`, `data_rozpo`, `data_zako`) " +
                "VALUES ('"+
                messout.getNazwa()+"','"+
                messout.getWartosc()+"','"+
                messout.getDat_roz()+"','"+
                messout.getDat_zak()+
                "'); ";

        Boolean result =execute(sql);

        if(!result){
            System.out.println("Błąd przy dodaniu promocji do bazy");
            return null;
        }
        else{
            System.out.println("Dodano promocje do bazy");
            return messout;
        }
    }

    //wpisanie danych do tabeli promocja_pokoj podczas dodawania nowej promocji
    public static PromRoom addPromRoom(Object object) {
        PromRoom messout = (PromRoom) object;
        String sql = "INSERT INTO `promocja_pokoj`(`promocja_id_promocja`, `pokoj_id_pokoj`) " +
                "SELECT `id_promocja`,`id_pokoj`"+
                " FROM `promocja`,`pokoj` WHERE promocja.nazwa ='"+
                messout.getNazwa()+"' AND pokoj.pokoj_typ_pokoju ='"+
                messout.getTyp_pokoju()+"';";

        Boolean result = execute(sql);

        if(!result){
            System.out.println("Błąd przy dodaniu danych promocja_pokoj");
            return null;
        }
        else{
            System.out.println("Dodano dane promocja_pokoj do bazy");
            return messout;
        }
    }
    //wyswietlenie promocji
    public static LinkedList<Promotion> showPromotion() {
        String sql = "SELECT `nazwa`, `wartosc_promocji`,`data_rozpo` , `data_zako` , `pokoj_typ_pokoju` " +
                "FROM `promocja`,`pokoj`,`promocja_pokoj` " +
                "WHERE  promocja_pokoj.pokoj_id_pokoj = pokoj.pokoj_typ_pokoju " +
                "AND promocja_pokoj.promocja_id_promocja = promocja.id_promocja group by`nazwa`,`pokoj_typ_pokoju` order by `data_rozpo` DESC";

        ResultSet rs = executeQuery(sql);
        LinkedList<Promotion> list = new LinkedList<Promotion>();
        try {
            while(rs.next()){
                Promotion m = new Promotion(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getObject("data_rozpo", LocalDate.class),
                        rs.getObject("data_zako", LocalDate.class),
                        rs.getString(5)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    //usuwanie promocji
    public static PromRoom deletePromotion(Object object) {
        PromRoom p = (PromRoom) object;

        String sql3 = "SELECT `id_promocja` FROM `promocja` WHERE `nazwa`='" + p.getNazwa() + "';";

        ResultSet rs = executeQuery(sql3);
        String id_promocji = null;
        try {
            while (rs.next()) {
                id_promocji = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "DELETE FROM  `promocja_pokoj` WHERE `promocja_id_promocja` ='" + id_promocji +"';";
        Boolean result2 = execute(sql);
        String sql2 = "DELETE FROM  `promocja` WHERE `nazwa` ='" + p.getNazwa() + "';";
        Boolean result = execute(sql2);

        if (!result || !result2) {
            System.out.println("Błąd");
            return  null;
        } else {
            System.out.println("Usunieto promocje");
            return p;
        }
    }

    //wyswietlenei promocji
    public static LinkedList<Promotion> promotionList() {
        String sql = "SELECT `nazwa`, `wartosc_promocji`,`data_rozpo` , `data_zako` , `pokoj_typ_pokoju` " +
                "FROM `promocja`,`pokoj`,`promocja_pokoj` " +
                "WHERE  promocja_pokoj.pokoj_id_pokoj = pokoj.pokoj_typ_pokoju " +
                "AND promocja_pokoj.promocja_id_promocja = promocja.id_promocja group by`nazwa`";

        ResultSet rs = executeQuery(sql);
        LinkedList<Promotion> list = new LinkedList<Promotion>();
        try {
            while(rs.next()){
                Promotion m = new Promotion(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getObject("data_rozpo", LocalDate.class),
                        rs.getObject("data_zako", LocalDate.class),
                        rs.getString(5)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //wyswietlenie rezerwacji
    public static LinkedList<EditReservation> showReservation() {

        String query = "SELECT rezerwacja.id_rezer,klient.imie,klient.nazwisko,pokoj.nr_pokoju,rezerwacja.data_przyjazdu,rezerwacja.data_wyjazdu,rezerwacja.caly_koszt "+
                "FROM `klient`,`pokoj`,`rezerwacja`,`rezerwacja_klient` WHERE "+
                "rezerwacja.pokoj_id_pokoj = pokoj.id_pokoj AND "+
                "rezerwacja_klient.rezer_id_rezer = rezerwacja.id_rezer AND "+
                "rezerwacja_klient.klient_id_klient = klient.id_klient;";

        ResultSet rs = executeQuery(query);

        LinkedList<EditReservation> list = new LinkedList<EditReservation>();
        try {
            while(rs.next()){
                EditReservation m = new EditReservation(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getObject("data_przyjazdu", LocalDate.class),
                        rs.getObject("data_wyjazdu", LocalDate.class),
                        rs.getString(7)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //anulowanie udogodnienia wybranego przez klienta
    public static Amenities cancellationMyAmenities(Object object) {
        //w tej funkcji typ pokoju odpowiada za id rezerwacji
        Amenities p = (Amenities) object;
        //wyszukanie id z tabeli rezerwacja_udo
        String sql="SELECT DISTINCT rezerwacja_udo.id_rez_udo " +
                "FROM rodza_udo,rezerwacja_udo,rezerwacja " +
                "WHERE rezerwacja_udo.rez_id_rez="+p.getTyp_pokoju()+
                " AND rezerwacja_udo.udo_id_udo=rodza_udo.id_rodz_udo " +
                "AND rodza_udo.nazwa='"+p.getNazwa()+"';";
        String id_rez_udo="";
        ResultSet rs = executeQuery(sql);
        try {
            while(rs.next()){
                id_rez_udo=rs.getString(1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        //usuniecie danych z tabeli rezerwacja_udo
        String sql2 = "DELETE FROM  `rezerwacja_udo` WHERE `id_rez_udo` ="+id_rez_udo+";";
        Boolean result=execute(sql2);

        //obnizenie ceny o cene danego udogodnienia
        String sql3 = "UPDATE rezerwacja SET caly_koszt=caly_koszt-"+p.getCena()+" WHERE id_rezer="+p.getTyp_pokoju()+";";

        Boolean result2=execute(sql3);
        if(!result || !result2){
            System.out.println("Błąd");
            return null;
        }
        else {
            System.out.println("Usunieto udogodnienia");
            return p;
        }
    }

    //dodanie nowego udogodnienia do rezerwacji
    public static Amenities addResAmenities(Object object) {
        //w tej funkcji typ pokoju odpowiada za id rezerwacji
        Amenities p = (Amenities) object;

        //jakie udogodnienia juz ma(id)
        String sql4="  SELECT rezerwacja_udo.udo_id_udo " +
                "FROM rezerwacja_udo " +
                " WHERE rezerwacja_udo.rez_id_rez="+p.getTyp_pokoju()+" " +
                "ORDER BY rezerwacja_udo.udo_id_udo ASC;";

        //lista z id udogodnien ktore juz mamy
        List<String> list = new ArrayList<>();
        ResultSet rs2 = executeQuery(sql4);
        try {
            while(rs2.next()){
                list.add(rs2.getString(1));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        //jakie udogodnienia moze wybrac(id)
        String sql5="SELECT rodza_udo.id_rodz_udo " +
                "FROM rodza_udo,rezerwacja,pokoj " +
                "WHERE rezerwacja.pokoj_id_pokoj=pokoj.id_pokoj " +
                "AND rodza_udo.udo_typ_pokoju=pokoj.pokoj_typ_pokoju " +
                "AND rezerwacja.id_rezer="+p.getTyp_pokoju()+" ORDER BY rodza_udo.id_rodz_udo ASC;";

        //lista z udogodneiniami ktore mozemy miec ogolnie
        List<String> list2 = new ArrayList<>();
        ResultSet rs3 = executeQuery(sql5);
        try {
            while(rs3.next()){
                list2.add(rs3.getString(1));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        boolean result=false;
        boolean result2=false;
        //przeksztalcenei listy z mozliwymi udogodneiniami na liste z udogodnieniami ktore mozemy miec i jeszcze ich nie mamy
        if(list.size()==list2.size()){
            System.out.println("Klient ma wszystkie dostepne udogodnienia");
        }
        else{
            for(int j=0;j<list.size();j++){
                for(int i=0;i<list2.size();i++){
                    if(list2.get(i).equals(list.get(j))){
                        list2.remove(i);
                    }
                }
            }
            //wyszukanie id z tabeli rodza_udo
            String sql="SELECT DISTINCT rodza_udo.id_rodz_udo " +
                    "FROM rodza_udo,rezerwacja,pokoj " +
                    "WHERE pokoj.id_pokoj=rezerwacja.pokoj_id_pokoj " +
                    "AND rodza_udo.udo_typ_pokoju=pokoj.pokoj_typ_pokoju " +
                    "AND rodza_udo.nazwa='"+p.getNazwa()+"' " +
                    "AND rezerwacja.id_rezer="+p.getTyp_pokoju()+";";
            String idUdogodnienia="";
            ResultSet rs = executeQuery(sql);
            try {
                while(rs.next()){
                    idUdogodnienia=rs.getString(1);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            //wpisanie danych o nowym udogodnieniu do tabeli rezerwacja_udo oraz aktualizacja kosztow calkowitych pobytu
            for(String bean: list2){
                if(bean.equals(idUdogodnienia)){
                    //dodanie danych do tabeli rezerwacja_udo
                    String sql2="INSERT INTO `rezerwacja_udo`(`udo_id_udo`, `rez_id_rez`) VALUES ('"+
                            idUdogodnienia+"','"+
                            p.getTyp_pokoju()+"');";
                    result=execute(sql2);

                    //dodanie ceny nowego udogodnienia do rachunku
                    String sql3 = "UPDATE rezerwacja SET caly_koszt=caly_koszt+"+p.getCena()+" WHERE id_rezer="+p.getTyp_pokoju()+";";

                    result2=execute(sql3);
                }
                else{
                    System.out.println("Klient juz ma to udogodnienie");
                }
            }
        }
        if(!result || !result2){
            System.out.println("Błąd przy dodawaniu udogodnienia");
            return null;
        }
        else {
            System.out.println("Dodano udogodnienia");
            return p;
        }
    }

    //anulowanie rezerwacji
    public static Reservation cancellationRes(Object object) {
        Reservation p = (Reservation) object;
        String idOfReservation = p.getIdOfReservation();

        //ustwanienie stanu pokoju na 0(wolny)
        String sql1="UPDATE pokoj SET pokoj.stan=0 " +
                "WHERE pokoj.id_pokoj=(SELECT rezerwacja.pokoj_id_pokoj FROM rezerwacja WHERE rezerwacja.id_rezer="+idOfReservation+");";

        //usuniecie danych z tabeli rezerwacja_klient
        String sql2 = "DELETE FROM  `rezerwacja_klient` WHERE `rezer_id_rezer` ='"+idOfReservation+"';";

        //usuniecie danych z tabeli rezerwacja_udo
        String sql3 = "DELETE FROM  `rezerwacja_udo` WHERE `rez_id_rez` ='"+idOfReservation+"';";

        //usuniecie danych z tabeli rezerwacja
        String sql4="DELETE FROM  `rezerwacja` WHERE `id_rezer` ='"+idOfReservation+"';";

        Boolean result1 = execute(sql1);
        Boolean result2 = execute(sql2);
        Boolean result3 = execute(sql3);
        Boolean result4 = execute(sql4);

        if (!result1 || !result2 || !result3 || !result4) {
            System.out.println("Błąd");
            return null;
        } else {
            System.out.println("Anulowano rezerwacje");
            return p;
        }
    }

    //zmiana czasu pobytu
    public static Reservation updateResTime(Object object) {
        Reservation p = (Reservation) object;
        String idOfReservation = p.getIdOfReservation();

        //zmiana czasu pobytu
        String sql1="UPDATE rezerwacja SET rezerwacja.data_przyjazdu='"+p.getDat_roz()+"' WHERE rezerwacja.id_rezer='"+idOfReservation+"'";
        Boolean result1 = execute(sql1);

        String sql2="UPDATE rezerwacja SET rezerwacja.data_wyjazdu='"+p.getDat_zak()+"' WHERE rezerwacja.id_rezer='"+idOfReservation+"'";
        Boolean result2 = execute(sql2);

        //ilosc nocy podczas pobytu
        int iloscNocy=0;
        iloscNocy= (int) ChronoUnit.DAYS.between(p.getDat_roz(), p.getDat_zak());

        //znalezienie ceny pokoju oraz jego id
        String sql3 ="SELECT typ_pokoju.cena_noclegu,pokoj.id_pokoj " +
                "FROM typ_pokoju,pokoj " +
                "WHERE typ_pokoju.id_typ_pokoju=pokoj.pokoj_typ_pokoju AND " +
                "pokoj.nr_pokoju='"+p.getRoomType()+"';";

        ResultSet rs = executeQuery(sql3);
        float roomPrice=1;
        String roomID="0";
        try {
            while(rs.next()){
                roomPrice=parseFloat(rs.getString(1));
                roomID= rs.getString(2);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        //lista z id promocji ktore dotycza naszego pokoju
        ObservableList<Promotion> listOfPromotions = FXCollections.observableArrayList();
        //wartosc promocji w %
        float discountValue=1;


        //wyszukanie promocji dotyczacych naszego pokoju, id, data rozpoczecia i zakonczenia promocji oraz jej wartosc
        String sql4 = "SELECT `id_promocja`,`data_rozpo`,`data_zako`,`wartosc_promocji`" +
                "FROM `promocja`,`promocja_pokoj` " +
                "WHERE  promocja_pokoj.pokoj_id_pokoj = '"+roomID+"' GROUP BY `id_promocja` ";
        ResultSet rss = executeQuery(sql4);
        listOfPromotions.clear();
        try {
            while(rss.next()){
                listOfPromotions.add(new Promotion("",
                        rss.getString(4),
                        rss.getObject("data_rozpo", LocalDate.class),
                        rss.getObject("data_zako", LocalDate.class),
                        rss.getString(1)
                ));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        //przejscie po liscie z promocjami dotyczacymi naszego pokoju
        for(Promotion bean: listOfPromotions ){
            //sprawdzenie czy nasz termin pokrywa się z terminem promocji
            //jesli tak to obliczamy wartosc procentowa promocju np 0.95 w przeciwnym wypadku zostawiamy 1
            if(p.getDat_roz().isAfter(bean.getDat_roz()) && p.getDat_zak().isBefore(bean.getDat_zak())){
                discountValue=(1-(parseFloat(bean.getWartosc())/100));
            }
        }

        //koszt udogodnien
        float costPerAminties=0;

        //podliczenie ceny wszystkich wybranych udogodnien
        String sql5 ="SELECT SUM(rodza_udo.cena)\n" +
                "FROM rodza_udo,rezerwacja,rezerwacja_udo\n" +
                "WHERE rezerwacja.id_rezer="+idOfReservation+" AND\n" +
                "rezerwacja_udo.rez_id_rez=rezerwacja.id_rezer AND \n" +
                "rodza_udo.id_rodz_udo=rezerwacja_udo.udo_id_udo;";
        ResultSet wynik = executeQuery(sql5);
        try {
            while(wynik.next()){
                costPerAminties=parseFloat(wynik.getString(1));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        float toPay=((roomPrice*iloscNocy)*discountValue)+costPerAminties;

        String sql6="UPDATE rezerwacja SET rezerwacja.caly_koszt='"+toPay+
                "' WHERE rezerwacja.id_rezer='"+idOfReservation+"';";

        Boolean result6 = execute(sql6);

        if (!result1 || !result2) {
            System.out.println("Błąd");
            return null;
        } else {
            System.out.println("Zmieniono czas pobytu");
            return p;
        }
    }
//------------------------------------------------------Klient----------------------------------------------------------------------------

    //dodawanie rezerwacji
    public static Reservation addReservation(Object object) {
        Reservation newReservation = (Reservation) object;
        //ustawienie informacji o braku bledow
        newReservation.setErrorCode(0);
        float calyKoszt=0;

        //sprawdzenie czy klient o takich danych istnieje juz w bazie danych
        String clientID=null;
        String sql9 ="SELECT klient.id_klient " +
                "FROm klient " +
                "WHERE klient.imie='"+newReservation.getImie()+"' AND " +
                "klient.nazwisko='"+newReservation.getNazwisko()+"' AND klient.nr_tel='"+newReservation.getPhoneNumber()+"'";
        //id klienta
        ResultSet cl = executeQuery(sql9);
        try {
            while(cl.next()){
                clientID=cl.getString(1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        //wyszukanie pierwszego wolnego pokoju typu wybranego przez klienta oraz ceny za noc
        String format;
        //zmiana formatu zapisu
        if(newReservation.getRoomType().equals("Jednoosobowy")){
            format="1";
        }
        else if(newReservation.getRoomType().equals("Dwuosobowy")){
            format="2";
        }
        else{
            format="3";
        }
        //zapytanie wyszukujace pierwszego wolnego pokoju oraz ceny za noc w takim pokoju
        String sql="SELECT `id_pokoj`,`cena_noclegu` FROM `pokoj`,`typ_pokoju` WHERE pokoj.stan='0' AND pokoj.pokoj_typ_pokoju='"+format+
                "' AND typ_pokoju.rodzaj_pokoju = '"+newReservation.getRoomType()+"' LIMIT 1";
        //id pierwszego wolnego pokoju
        String firstFreeRoom="";
        float costPerNight=0;
        ResultSet rs = executeQuery(sql);
        try {
            while(rs.next()){
                firstFreeRoom=rs.getString(1);
                costPerNight = parseFloat(rs.getString(2));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        //---------------------------------------------------------------------------
        //jezeli jest wolny pokoj takiego typu o jaki prosi klient to dodajemy dane
        //jesli nie, rezerwacji nie bedzie moze zrobic na inny typ pokoju
        if(!firstFreeRoom.isEmpty()){
            //zmiana stanu pokoju z wolnego na zajety

            String sql7 ="UPDATE `pokoj` SET `stan`= 1 "+
                    " WHERE `id_pokoj` ='"+firstFreeRoom+"';";

            Boolean result5 =execute(sql7);
            if(!result5){
                System.out.println("Błąd podczas zmiany stanu pokoju z wolnego na zajety");
            }


            //lista z id promocji ktore dotycza naszego pokoju
            ObservableList<Promotion> listOfPromotions = FXCollections.observableArrayList();
            //wartosc promocji w %
            float discountValue=1;


            //wyszukanie promocji dotyczacych naszego pokoju, id, data rozpoczecia i zakonczenia promocji oraz jej wartosc
            String sql8 = "SELECT `id_promocja`,`data_rozpo`,`data_zako`,`wartosc_promocji`" +
                    "FROM `promocja`,`promocja_pokoj` " +
                    "WHERE  promocja_pokoj.pokoj_id_pokoj = '"+firstFreeRoom+"' GROUP BY `id_promocja` ";
            ResultSet rss = executeQuery(sql8);
            listOfPromotions.clear();
            try {
                while(rss.next()){
                    listOfPromotions.add(new Promotion("",
                            rss.getString(4),
                            rss.getObject("data_rozpo", LocalDate.class),
                            rss.getObject("data_zako", LocalDate.class),
                            rss.getString(1)
                    ));
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

            //przejscie po liscie z promocjami dotyczacymi naszego pokoju
            for(Promotion bean: listOfPromotions ){
                //sprawdzenie czy nasz termin pokrywa się z terminem promocji
                //jesli tak to obliczamy wartosc procentowa promocju np 0.95 w przeciwnym wypadku zostawiamy 1
                if(newReservation.getDat_roz().isAfter(bean.getDat_roz()) && newReservation.getDat_zak().isBefore(bean.getDat_zak())){
                    discountValue=(1-(parseFloat(bean.getWartosc())/100));
                }
            }

            //liczba udogodnien wybranych przez klienta
            int numberOfAminties = newReservation.getAmenitiesList().size();
            //koszt udogodnien
            float costPerAminties=0;

            //podliczenie ceny wszystkich wybranych udogodnien
            for(int i=0;i<numberOfAminties;i++){
                String sql2 = "SELECT `cena` FROM `rodza_udo` WHERE `nazwa`='" + newReservation.getAmenitiesList().get(i) + "' AND `udo_typ_pokoju` = '"+format+"';";
                ResultSet wynik = executeQuery(sql2);
                try {
                    while(wynik.next()){
                        costPerAminties=costPerAminties+parseFloat(wynik.getString(1));
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            //ilosc nocy podczas pobytu
            int iloscNocy=0;
            iloscNocy= (int) ChronoUnit.DAYS.between(newReservation.getDat_roz(), newReservation.getDat_zak());

            //obliczenie calkowitej ceny : ((ilosc nocy * cena za noc )*obnizka z tytulu promocji)+koszt udogodnien
            calyKoszt=((iloscNocy*costPerNight)*discountValue)+costPerAminties;

            System.out.println("Wartosc obnizki: "+discountValue);
            System.out.println("Koszt po obnizce: "+calyKoszt);

            //jesli nie znalezlismy klienta o takich danych, dodajemy nowego do bazy
            if(clientID==null) {
                //dodanie do tabeli klient
                String sql3 = "INSERT INTO `klient`(`imie`, `nazwisko`, `nr_tel`, `email`) " +
                        "VALUES ('" +
                        newReservation.getImie() + "','" +
                        newReservation.getNazwisko() + "','" +
                        newReservation.getPhoneNumber() + "','" +
                        newReservation.getEmail() +
                        "'); ";

                Boolean result = execute(sql3);
                if (!result) {
                    System.out.println("Błąd podczas wpisania do tabeli klient");
                }
            }

            //dodanie do tabeli rezerwacja
            String sql4 = "INSERT INTO `rezerwacja`(`data_przyjazdu`, `data_wyjazdu`, `caly_koszt`, `pokoj_id_pokoj`) " +
                    "VALUES ('"+
                    newReservation.getDat_roz()+"','"+
                    newReservation.getDat_zak()+"','"+
                    calyKoszt+"','"+
                    firstFreeRoom+
                    "'); ";

            Boolean result2 =execute(sql4);
            if(!result2){
                System.out.println("Błąd podczas wpisania do tabeli rezerwacja");
            }

            //wpisanie danych do tabeli rezerwacja_klient
            String sql5 = "INSERT INTO `rezerwacja_klient`(`rezer_id_rezer`, `klient_id_klient`) " +
                    "SELECT `id_rezer`,`id_klient`"+
                    " FROM `rezerwacja`,`klient` WHERE rezerwacja.data_przyjazdu ='"+
                    newReservation.getDat_roz()+"'AND rezerwacja.data_wyjazdu ='"+
                    newReservation.getDat_zak()+"' AND rezerwacja.pokoj_id_pokoj = '"+
                    firstFreeRoom+"' AND klient.imie ='"+
                    newReservation.getImie()+"' AND klient.nazwisko='"+
                    newReservation.getNazwisko()+"' AND klient.nr_tel = '"+
                    newReservation.getPhoneNumber()+"';";

            Boolean result3 =execute(sql5);
            if(!result3){
                System.out.println("Błąd podczas wpisania do tabeli rezerwacja_klient");
            }

            //wpisanie do tabeli rezerwacja_udo jakie podczas tej rezerwacji zostały dodane udogodnienia
            for(int j=0;j<numberOfAminties;j++){
                String sql6 = "INSERT INTO `rezerwacja_udo`(`udo_id_udo`, `rez_id_rez`) " +
                        "SELECT `id_rodz_udo`,`id_rezer`"+
                        " FROM `rezerwacja`,`rodza_udo` WHERE rezerwacja.data_przyjazdu ='"+
                        newReservation.getDat_roz()+"'AND rezerwacja.data_wyjazdu ='"+
                        newReservation.getDat_zak()+"' AND rezerwacja.pokoj_id_pokoj = '"+
                        firstFreeRoom+"' AND rodza_udo.nazwa ='"+
                        newReservation.getAmenitiesList().get(j)+"' AND rodza_udo.udo_typ_pokoju='"+
                        format+"';";
                Boolean result4 =execute(sql6);
                if(!result4){
                    System.out.println("Błąd podczas wpisania do tabeli rezerwacja_udo");
                }
            }
        }
        else{
            //errorCode 1 - brak wolnych pokoi
            newReservation.setErrorCode(1);
            System.out.println("Brak wolnych pokoi");
        }

        return newReservation;
    }

    //wyswietlenie wybranych udogodnien
    public static LinkedList<Amenities> showMyAmenities(Object object) {

        Amenities messout = (Amenities) object;
        String query = "SELECT rodza_udo.nazwa, rodza_udo.cena "+
                "FROM `rezerwacja_udo`,`rezerwacja`,`rodza_udo` WHERE "+
                "rezerwacja_udo.rez_id_rez = "+messout.getTyp_pokoju()+" AND "+
                "rezerwacja_udo.udo_id_udo = rodza_udo.id_rodz_udo GROUP BY rodza_udo.nazwa;";

        ResultSet rs = executeQuery(query);
        LinkedList<Amenities> list = new LinkedList<Amenities>();
        try {
            while(rs.next()){
                Amenities m = new Amenities(
                        rs.getString(1),
                        rs.getFloat(2),
                        ""
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //wyswietlenie jakie udogodnienia mozna jeszcze dolozyc
    public static LinkedList<Amenities> showPosAmenities(Object object) {
        Amenities messout = (Amenities) object;
        String query = "SELECT rodza_udo.nazwa,rodza_udo.cena " +
                "FROM rodza_udo,rezerwacja,pokoj " +
                "WHERE rezerwacja.pokoj_id_pokoj=pokoj.id_pokoj AND rodza_udo.udo_typ_pokoju=pokoj.pokoj_typ_pokoju AND rezerwacja.id_rezer="+
                messout.getTyp_pokoju()+" ORDER BY rodza_udo.nazwa ;";

        ResultSet rs = executeQuery(query);
        LinkedList<Amenities> list = new LinkedList<Amenities>();
        try {
            while(rs.next()){
                Amenities m = new Amenities(
                        rs.getString(1),
                        rs.getFloat(2),
                        ""
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //dodawanie nowej opinii
    public static Opinion addOpinion(Object object) {
        Opinion messout = (Opinion) object;
        String sql = "INSERT INTO `opinie`(`tresc`, `gwiazdki`, `hotel_id_hotel`) " +
                "VALUES ('"+
                messout.getTresc()+"','"+
                messout.getGwiazdki()+"','"+
                "1'); ";

        Boolean result =execute(sql);

        if(!result){
            System.out.println("Błąd przy dodaniu opinii do bazy");
            return null;
        }
        else{
            System.out.println("Dodano opinie do bazy");
            return messout;
        }
    }

    //wyslanie formularza kontaktowego
    public static ContactForm addConForm(Object object) {
        ContactForm messout = (ContactForm) object;
        String sql = "INSERT INTO `formularz`(`imie`, `nazwisko`, `nr_tel`, `email`, `tresc`, `hotel_id_hotel`) " +
                "VALUES ('"+
                messout.getImie()+"','"+
                messout.getNazwisko()+"','"+
                messout.getNr_tel()+"','"+
                messout.getEmail()+"','"+
                messout.getTresc()+"','"+
                "1'); ";

        Boolean result =execute(sql);

        if(!result){
            System.out.println("Błąd przy dodaniu formularza do bazy");
            return null;
        }
        else{
            System.out.println("Dodano dormularz do bazy");
            return messout;
        }
    }

    //wyswietlanie rezerwacji przez klienta przy skladaniu skargi
    public static LinkedList<Complaint> showResCom(Object object) {
        Complaint n = (Complaint) object;
        String query = "SELECT rezerwacja.data_przyjazdu,rezerwacja.data_wyjazdu,pokoj.nr_pokoju,pokoj.pokoj_typ_pokoju,rezerwacja.caly_koszt,rezerwacja.id_rezer " +
                "FROM rezerwacja,pokoj,klient,rezerwacja_klient\n" +
                "WHERE klient.imie='"+n.getImie()+"' AND " +
                "klient.nazwisko='"+n.getNazwisko()+"' AND " +
                "klient.nr_tel='"+n.getPhoneNumber()+"' AND " +
                "rezerwacja_klient.rezer_id_rezer=rezerwacja.id_rezer AND " +
                "rezerwacja_klient.klient_id_klient=klient.id_klient AND " +
                "rezerwacja.pokoj_id_pokoj=pokoj.id_pokoj";
        ResultSet rs = executeQuery(query);
        LinkedList<Complaint> list = new LinkedList<Complaint>();
        try {
            while(rs.next()){
                Complaint m = new Complaint(
                        rs.getObject("data_przyjazdu", LocalDate.class),
                        rs.getObject("data_przyjazdu", LocalDate.class),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
                list.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //wpisanie skargi do bazy
    public static Complaint addComplaint(Object object) {
        Complaint messout = (Complaint) object;
        String sql = "INSERT INTO `skargi`( `tresc`, `rez_id_rez`) " +
                "VALUES ('"+
                messout.getTresc()+"','"+
                messout.getIdOfReservation()+
                "'); ";

        Boolean result =execute(sql);

        if(!result){
            System.out.println("Błąd przy dodaniu skargi do bazy");
            return null;
        }
        else{
            System.out.println("Dodano skarge do bazy");
            return messout;
        }
    }



//.
}


