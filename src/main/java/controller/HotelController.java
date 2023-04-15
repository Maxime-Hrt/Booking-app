package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Members;
import model.Users;
import model.hotels.Hotels;
import model.hotels.Rooms;
import view.Room_RecapView;

public class HotelController {
    static public void paymentRecap(Stage stage, Rooms room, Hotels hotel, Users user, Members member){
        //MEMBER
        if (member != null){
            //Room_RecapView

        } else {
            Room_RecapView.roomRecap(stage, room, hotel, user);
        }
    }
}
