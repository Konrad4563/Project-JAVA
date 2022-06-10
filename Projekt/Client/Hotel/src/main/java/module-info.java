module Hotel {
    requires java.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;
    requires java.sql;


    exports elemHotel to javafx.graphics,javafx.fxml,javafx.base,javafx.controls,javafx.web,javafx.media;
    exports Kierownik to javafx.graphics,javafx.fxml,javafx.base,javafx.controls,javafx.web,javafx.media;
    exports Pracownik to javafx.base,javafx.controls,javafx.web,javafx.media,javafx.graphics,javafx.fxml;
    exports Klient  to javafx.graphics,javafx.fxml,javafx.base,javafx.controls,javafx.web,javafx.media;
    exports messages  to javafx.graphics,javafx.fxml,javafx.base,javafx.controls,javafx.web,javafx.media;
    //exports Polaczenie_Server_Klient to junit,javafx.graphics,javafx.fxml,javafx.base,javafx.controls,javafx.web,javafx.media;
    opens Kierownik to javafx.base,javafx.controls,javafx.web,javafx.media,javafx.graphics,javafx.fxml;
    opens Pracownik to javafx.base,javafx.controls,javafx.web,javafx.media,javafx.graphics,javafx.fxml;
    opens Klient to javafx.base,javafx.controls,javafx.web,javafx.media,javafx.graphics,javafx.fxml;
    opens elemHotel to javafx.base,javafx.controls,javafx.web,javafx.media,javafx.graphics,javafx.fxml;
    opens messages to javafx.base,javafx.controls,javafx.web,javafx.media,javafx.graphics,javafx.fxml;
    //opens Polaczenie_Server_Klient;



}