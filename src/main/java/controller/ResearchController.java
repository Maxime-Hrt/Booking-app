package controller;

import javafx.stage.Stage;
import model.Users;
import model.hotels.Hotels;
import view.HotelView;

import java.util.ArrayList;

public class ResearchController {
    static public void HotelData(Stage stage, Hotels hotel, Users user) {
        hotel.printHotel();
        HotelView.HotelData(stage, hotel, user);
    }
}
