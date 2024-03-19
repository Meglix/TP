module com.example.aplicatie {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aplicatie to javafx.fxml;
    exports com.example.aplicatie;
    exports clase;
    opens clase to javafx.fxml;
}