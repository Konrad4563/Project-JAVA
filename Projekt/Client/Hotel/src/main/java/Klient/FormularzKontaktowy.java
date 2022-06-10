package Klient;

import Kierownik.AlertBox;
import elemHotel.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import messages.*;

import java.util.LinkedList;
import java.util.function.UnaryOperator;

/**
 *
 * @author Piotr Bielecki
 *
 */

public class FormularzKontaktowy {
    Tab tab;
    public FormularzKontaktowy(Tab tab){
        this.tab=tab;
    }
    public void wywolanieZakladkiFormularzKontaktowy(){
        Font font = Font.font("Arial", FontWeight.BOLD, 14);

        Image image = new Image("promocje.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(600);
        imageView.setFitWidth(1100);

        //ograniczenia dla poszczegolnych TextFieldow
        UnaryOperator<TextFormatter.Change> noNumbers = change -> {
            String input = change.getText();

            if ((!input.matches("[\\d\\.]+")) || change.isDeleted()) {
                return change;
            }
            return null;
        };
        UnaryOperator<TextFormatter.Change> phoneNum = change -> {
            String newText = change.getControlNewText();
            if (newText.length() > 9 || !change.getControlNewText().matches("\\d*")) {
                return null ;
            } else {
                return change ;
            }
        };
        UnaryOperator<TextFormatter.Change> mes = change -> {
            String newText = change.getControlNewText();
            if (newText.length() > 500) {
                return null ;
            } else {
                return change ;
            }
        };

        MyLabel label=new MyLabel("Formularz Kontaktowy z Hotelem",10,10,font,Color.WHITE);

        MyLabel nameLabel=new MyLabel("Imie",10,40,font,Color.WHITE);
        MyTextField nameTextField=new MyTextField(10,60,30,200,"");
        nameTextField.setTextFormatter(new TextFormatter<String> (noNumbers));

        MyLabel surnameLabel=new MyLabel("Nazwisko",10,100,font,Color.WHITE);
        MyTextField surnameTextField=new MyTextField(10,130,30,200,"");
        surnameTextField.setTextFormatter(new TextFormatter<String> (noNumbers));

        MyLabel numberLabel=new MyLabel("Numer telefonu",10,170,font,Color.WHITE);
        MyTextField numberTextField=new MyTextField(10,200,30,200,"");
        numberTextField.setTextFormatter(new TextFormatter<String> (phoneNum));

        MyLabel emailLabel=new MyLabel("Adres email",10,240,font,Color.WHITE);
        MyTextField emailTextField=new MyTextField(10,270,30,200,"");


        MyLabel messageLabel=new MyLabel("Treść wiadomości",250,10,font,Color.WHITE);
        TextArea messageArea=new TextArea();
        messageArea.setLayoutX(250);
        messageArea.setLayoutY(50);
        messageArea.setMinWidth(300);
        messageArea.setMinHeight(200);
        messageArea.setWrapText(true);
        messageArea.setTextFormatter(new TextFormatter<String> (mes));

        MyButton send=new MyButton("Wyślij wiadomość",250,270,30,200,Color.BLUE);

        //wyslanie wiadomosci z formularza kontaktowego
        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!nameTextField.getText().isEmpty() && !surnameTextField.getText().isEmpty() &&
                    !numberTextField.getText().isEmpty() && !emailTextField.getText().isEmpty() &&
                        !messageArea.getText().isEmpty()
                ){
                    ContactForm form = new ContactForm(
                            nameTextField.getText(),
                            surnameTextField.getText(),
                            numberTextField.getText(),
                            emailTextField.getText(),
                            messageArea.getText()
                    );

                    ContactForm message = (ContactForm) ServerConnection.sendToServer.send(ServerOperation.addConForm, form);
                    if(message!=null){
                        AlertBox.wyswietl("Wiadomość","Wysłano wiadomość",true);
                    }
                    else{
                        AlertBox.wyswietl("Wiadomość","Coś poszło nie tak",false);
                    }
                    nameTextField.clear();
                    surnameTextField.clear();
                    numberTextField.clear();
                    emailTextField.clear();
                    messageArea.clear();

                }
                else{
                    AlertBox.wyswietl("Wiadomość","Uzupełnij dane",false);
                }

            }
        });


        Group group1=new Group();

        group1.getChildren().addAll(imageView,
                label,
                nameLabel,nameTextField,
                surnameLabel,surnameTextField,
                numberLabel,numberTextField,
                emailLabel,emailTextField,
                messageLabel,messageArea,
                send);
        tab.setContent(group1);

    }
}
