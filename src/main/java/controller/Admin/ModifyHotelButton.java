package controller.Admin;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Data;
import model.Members;
import model.hotels.*;
import view.Admin.AdminWindow;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;


public class ModifyHotelButton {
    static public void hotelList(Stage stage, Members member) {
        Scene scene = new Scene(new VBox(), 650, 400);
        VBox hotelList = new VBox(10);
        Data data = new Data();
        ArrayList<Hotels> hotels = data.getAllHotels();
        Button backBtn = new Button("Back");
        backBtn.setOnAction(actionEvent -> {
            AdminWindow.adminWindow(stage, member);
        });
        for (Hotels hotel : hotels){
            Button hotelBtn = new Button(hotel.getName());
            hotelBtn.setOnAction(actionEvent -> {
                modifyHotelWindow(stage, hotel, member);
            });
            hotelList.getChildren().add(hotelBtn);
        }
        scene.setRoot(hotelList);
        stage.setScene(scene);
        stage.show();
    }

    static public void modifyHotelWindow(Stage stage, Hotels hotel, Members member) {
        Scene scene = new Scene(new VBox(), 600, 400);
    }

}
