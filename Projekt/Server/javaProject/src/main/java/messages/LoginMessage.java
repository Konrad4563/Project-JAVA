package messages;

import java.io.Serializable;

/**
 *
 * @author Konrad Basa
 * @author Marcin Bonar
 * @author Piotr Bielecki
 *
 */

//Klasa definiująca format wiadomości.

public class LoginMessage implements Serializable {
    int verNum, userId;
    String login, password;

    public LoginMessage(int verNum, int userId) {
        this.verNum = verNum;
        this.userId = userId;
    }
    public LoginMessage(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    public int getVerNum() {
        return verNum;
    }

    public int getUserId() {
        return userId;
    }
}
