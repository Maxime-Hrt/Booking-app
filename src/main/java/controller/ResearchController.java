package controller;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Members;
import model.Users;
import model.hotels.Hotels;
import view.HotelView;

import java.util.ArrayList;

public class ResearchController {
    static public void HotelData(Stage stage, Hotels hotel, Users user){
        hotel.printHotel();
        HotelView.HotelData(stage, hotel, user);
    }
}
