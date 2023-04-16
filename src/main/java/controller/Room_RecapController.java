package controller;

import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.PaymentMethod;
import model.Recherche;
import model.Users;
import model.hotels.Hotels;
import model.hotels.Rooms;
import view.EndView;
import view.Room_RecapView;

import java.time.LocalDate;

public class Room_RecapController {

    static public void termsOfUse(Stage stage, Users users, CheckBox termsOfUse, Text actiontarget, TextField name, TextField email, TextField cardNumber, DatePicker cardExpiration, TextField cvc){
        actiontarget.setText("");

        if (name.getText().isEmpty()){
            actiontarget.setText("Please enter your name");
            actiontarget.setFill(javafx.scene.paint.Color.FIREBRICK);
            return;
        }
        if (email.getText().isEmpty()){
            actiontarget.setText("Please enter your email");
            actiontarget.setFill(javafx.scene.paint.Color.FIREBRICK);
            return;
        }
        if (cardNumber.getText().isEmpty()){
            actiontarget.setText("Please enter your card number");
            actiontarget.setFill(javafx.scene.paint.Color.FIREBRICK);
            return;
        }
        if (cardExpiration.getValue() == null){
            actiontarget.setText("Please enter your card expiration date");
            actiontarget.setFill(javafx.scene.paint.Color.FIREBRICK);
            return;
        }
        if (cvc.getText().isEmpty()){
            actiontarget.setText("Please enter your cvc");
            actiontarget.setFill(javafx.scene.paint.Color.FIREBRICK);
            return;
        }
        if (cardExpiration.getValue().isBefore(LocalDate.now())){
            actiontarget.setText("Please enter a valid card expiration date");
            actiontarget.setFill(javafx.scene.paint.Color.FIREBRICK);
            return;
        }
        if (cardNumber.getText().length() != 16){
            actiontarget.setText("Please enter a valid card number (16 digits)");
            actiontarget.setFill(javafx.scene.paint.Color.FIREBRICK);
            return;
        }
        if (cvc.getText().length() != 3){
            actiontarget.setText("Please enter a valid cvc (3 digits)");
            actiontarget.setFill(javafx.scene.paint.Color.FIREBRICK);
            return;
        }
        if(!email.getText().contains("@")){
            actiontarget.setText("Please enter a valid email");
            actiontarget.setFill(javafx.scene.paint.Color.FIREBRICK);
            return;
        }
        if (name.getText().length() > 50){
            actiontarget.setText("Please enter a valid name (max 50 characters)");
            actiontarget.setFill(javafx.scene.paint.Color.FIREBRICK);
            return;
        }
        if (email.getText().length() > 50){
            actiontarget.setText("Please enter a valid email (max 50 characters)");
            actiontarget.setFill(javafx.scene.paint.Color.FIREBRICK);
            return;
        }
        if(!termsOfUse.isSelected()){
            actiontarget.setText("Please accept the terms of use");
            actiontarget.setFill(javafx.scene.paint.Color.FIREBRICK);
            return;
        }
        users.setPaymentMethod(new PaymentMethod(cardNumber.getText(), cardExpiration.getValue(), cvc.getText()));
        System.out.println("WOUHOUUUUUU");
        EndView.endView(stage, users, name.getText(), email.getText(), null);
    }
    static public void ApplyCoupon(Stage stage, Rooms room, Hotels hotel, Users user, TextField coupon, Text actiontarget){
        actiontarget.setText("");
        if (coupon.getText().equals("ECE2020")){
            user.setDiscount(5);
            System.out.println("Coupon applied");
            Room_RecapView.roomRecap(stage, room, hotel, user);
        } else {
            actiontarget.setText("Invalid coupon");
            actiontarget.setFill(javafx.scene.paint.Color.FIREBRICK);
        }
    }
    static public void dateUpdate(Stage stage, Rooms room, Hotels hotel, Users user, DatePicker checkIn, DatePicker checkOut){
        if(checkIn.getValue() == null){
            System.out.println("Please enter a check-in date");
            return;
        }
        if(checkOut.getValue() == null){
            System.out.println("Please enter a check-out date");
            return;
        }
        if(checkIn.getValue().isAfter(checkOut.getValue())){
            System.out.println("Please enter a valid check-in date");
            return;
        }
        if(checkIn.getValue().isBefore(java.time.LocalDate.now())){
            System.out.println("Please enter a valid check-in date");
            return;
        }
        if(checkOut.getValue().isBefore(java.time.LocalDate.now())){
            System.out.println("Please enter a valid check-out date");
            return;
        }
        LocalDate checkInDate = checkIn.getValue();
        LocalDate checkOutDate = checkOut.getValue();

        user.setTempSearch(new Recherche(user.getTempSearch().getDestination(), checkInDate, checkOutDate, user.getTempSearch().getNbOfPax()));
        Room_RecapView.roomRecap(stage, room, hotel, user);
    }
}
