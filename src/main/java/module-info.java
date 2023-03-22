module com.example.ece_booking {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ece_booking to javafx.fxml;
    exports com.example.ece_booking;
}