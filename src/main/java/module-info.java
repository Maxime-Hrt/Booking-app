module com.example.ece_booking {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires org.jfree.jfreechart;
    requires jfreechart.fx;


    opens com.example.ece_booking to javafx.fxml;
    exports com.example.ece_booking;
    exports view;
    exports controller;
    exports controller.Admin;
}

