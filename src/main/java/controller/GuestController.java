package controller;

import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Recherche;
import model.Users;
import model.hotels.Hotels;
import view.ResearchView;

import java.time.LocalDate;

public class GuestController {

    static public void Search(Users user, TextField destination, DatePicker checkIn, DatePicker checkOut, TextField nbPersonnes, Text actiontarget, Stage stage) {
        actiontarget.setText("");
        if(checkIn.getValue() == null){
            actiontarget.setText("Please enter a check-in date");
            actiontarget.setFill(Color.FIREBRICK);
            return;
        }
        if(checkOut.getValue() == null){
            actiontarget.setText("Please enter a check-out date");
            actiontarget.setFill(Color.FIREBRICK);
            return;
        }
        if(checkIn.getValue().isAfter(checkOut.getValue())){
            actiontarget.setText("Please enter a valid check-in date");
            actiontarget.setFill(Color.FIREBRICK);
            return;
        }
        if(checkIn.getValue().isBefore(LocalDate.now())){
            actiontarget.setText("Please enter a valid check-in date");
            actiontarget.setFill(Color.FIREBRICK);
            return;
        }
        if(checkOut.getValue().isBefore(LocalDate.now())){
            actiontarget.setText("Please enter a valid check-out date");
            actiontarget.setFill(Color.FIREBRICK);
            return;
        }
        if(nbPersonnes.getText().isEmpty()){
            actiontarget.setText("Please enter the number of people");
            actiontarget.setFill(Color.FIREBRICK);
            return;
        }
        if(destination.getText().isEmpty()){
            actiontarget.setText("Please enter a destination");
            actiontarget.setFill(Color.FIREBRICK);
            return;
        }
        String dest = destination.getText();
        LocalDate checkInDate = checkIn.getValue();
        LocalDate checkOutDate = checkOut.getValue();
        int nbPers = Integer.parseInt(nbPersonnes.getText());

        /*
        for (String history : user.getSearchHistory()){
            Label label = new Label(history);
            vbox.getChildren().add(label);
        }*/

        user.setTempSearch(new Recherche(dest, checkInDate, checkOutDate, nbPers));
        user.addSearchHistory(user.getTempSearch().getDestination());
        user.getTempSearch().printSearch();
        for (Hotels hotel : user.getTempSearch().searchHotel()){
            hotel.printHotel();
        }

        ResearchView.printResearch(stage, user, null);


    }
}
