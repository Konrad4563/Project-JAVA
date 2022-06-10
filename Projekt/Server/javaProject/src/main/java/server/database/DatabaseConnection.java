package server.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */

//Klasa odpwiedzialna za łączenie serwera z lokalną bazą danych.
public class DatabaseConnection {
    //Metoda łącząca serwer z bazą danych. Obsługująca dany typ bazy danych.
    // zwraca obiekt bieżącego połączenia z lokalną bazy danych

    public Connection getConnection(){
        Connection databaseLink = null;
        String databaseUser = "root";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost:3306/hotel";
        try{
            //ładowanie sterownika jdbc.mysql
            //sposob archiwizacji bazy danych JDBC
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}