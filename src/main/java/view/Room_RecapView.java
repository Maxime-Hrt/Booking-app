package view;

import controller.Room_RecapController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Users;
import model.hotels.Hotels;
import model.hotels.Rooms;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.abs;

public class Room_RecapView {
    private int currentIndex = 0;

    Room_RecapView() {
    }

    static public void roomRecap(Stage stage, Rooms room, Hotels hotel, Users user) {

        GridPane paymentMethod = new GridPane();
        paymentMethod.setPadding(new Insets(25));
        paymentMethod.setHgap(10);
        paymentMethod.setVgap(10);
        paymentMethod.setAlignment(Pos.CENTER);
        paymentMethod.getStyleClass().add("background-white");
        paymentMethod.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");

        //Left Side
        Label name = new Label("Name: ");
        TextField nameField = new TextField();
        Label email = new Label("Email: ");
        TextField emailField = new TextField();
        Label cardNumber = new Label("Card Number: ");
        TextField cardNumberField = new TextField();
        Label cardDate = new Label("Card Date: ");
        DatePicker cardDateField = new DatePicker();
        Label cardCVC = new Label("Card CVC: ");
        TextField cardCVCField = new TextField();
        CheckBox newsletter = new CheckBox("I want to receive the newsletter");
        CheckBox termsOfUse = new CheckBox("I accept the terms of use");
        Button pay = new Button("Pay");
        Button cancel = new Button("Cancel");
        pay.setOnAction(e -> {
            if (termsOfUse.isSelected()) {
                Room_RecapController.paymentDone();
            } else {
                Room_RecapController.termsOfUse();
            }
        });
        cancel.setOnAction(e -> {
            HotelView.HotelData(stage, hotel, user);
        });
        paymentMethod.add(name, 0, 0, 2, 1);
        paymentMethod.add(nameField, 0, 1, 2, 1);
        paymentMethod.add(email, 0, 2, 2, 1);
        paymentMethod.add(emailField, 0, 3, 2, 1);
        paymentMethod.add(cardNumber, 0, 4, 2, 1);
        paymentMethod.add(cardNumberField, 0, 5, 2, 1);
        paymentMethod.add(cardDate, 0, 6, 1, 1);
        paymentMethod.add(cardDateField, 0, 7, 1, 1);
        paymentMethod.add(cardCVC, 1, 6, 1, 1);
        paymentMethod.add(cardCVCField, 1, 7, 1, 1);
        paymentMethod.add(newsletter, 0, 8, 2, 1);
        paymentMethod.add(termsOfUse, 0, 9, 2, 1);
        paymentMethod.add(pay, 0, 10, 1, 1);
        paymentMethod.add(cancel, 1, 10, 1, 1);

        //Right Side
        GridPane recap = new GridPane();
        recap.setPadding(new Insets(25));
        recap.setHgap(10);
        recap.setVgap(10);
        recap.setAlignment(Pos.CENTER);
        recap.getStyleClass().add("background-yellow");
        recap.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");

        Label checkInLabel = new Label("Check-in: ");
        Label checkOutLabel = new Label("Check-out: ");
        DatePicker checkIn = new DatePicker(user.getTempSearch().getDateOfArrival());
        DatePicker checkOut = new DatePicker(user.getTempSearch().getDateOfDeparture());
        Button update = new Button("Update");
        update.setOnAction(e -> {
            user.getTempSearch().setDateOfArrival(checkIn.getValue());
            user.getTempSearch().setDateOfDeparture(checkOut.getValue());
            Room_RecapView.roomRecap(stage, room, hotel, user);
        });
        paymentMethod.add(checkInLabel, 2, 0, 1, 1);
        paymentMethod.add(checkIn, 2, 1, 1, 1);
        paymentMethod.add(checkOutLabel, 3, 0, 1, 1);
        paymentMethod.add(checkOut, 3, 1, 1, 1);
        paymentMethod.add(update, 4, 1, 1, 1);


        long nights = abs(ChronoUnit.DAYS.between(
                user.getTempSearch().getDateOfDeparture(),
                user.getTempSearch().getDateOfArrival()
        ));
        Label nightNumber = new Label("Number of nights: " + nights);
        Label prix = new Label("Price: " +
                nights +
                "x" + room.getType() +
                " = " + room.getPrice() * nights + "€");
        double reduction = (double) user.getDiscount() / 100;
        Label reductionLabel = new Label("Reduction: " + reduction * 100 + "%");
        Label total = new Label("Total: " + room.getPrice() * nights * (1 - reduction) + "€");
        Label coupon = new Label("Coupon code: ");
        TextField couponField = new TextField();
        Button apply = new Button("Apply");
        apply.setOnAction(e -> {
            if (Integer.parseInt(couponField.getText()) > 100 || Integer.parseInt(couponField.getText()) < 0) {
                return;
            }
            user.setDiscount(Integer.parseInt(couponField.getText()));
            Room_RecapView.roomRecap(stage, room, hotel, user);
        });
        recap.add(nightNumber, 0, 0, 2, 1);
        recap.add(prix, 0, 1, 2, 1);
        recap.add(reductionLabel, 0, 2, 2, 1);
        recap.add(total, 0, 3, 2, 1);
        recap.add(coupon, 0, 4, 1, 1);
        recap.add(couponField, 1, 4, 1, 1);
        recap.add(apply, 0, 5, 2, 1);

        Room_RecapView roomRecapView = new Room_RecapView();
        VBox photo = roomRecapView.photoScroll(hotel);
        paymentMethod.add(photo, 2, 2, 3, 4);


        paymentMethod.add(recap, 2, 6, 3, 5); //3??






        Scene scene = new Scene(paymentMethod, 1086, 724);
        stage.setScene(scene);
        stage.show();
    }

    public VBox photoScroll(Hotels hotel){
        ArrayList<String> photos = hotel.getPhotos();
        ArrayList<Image> images = new ArrayList<>();
        for (String photo : photos) {
            images.add(new Image(photo));
        }
        ImageView imageView = new ImageView(images.get(this.currentIndex));
        imageView.setFitHeight(300);
        imageView.setFitWidth(400);

        Button previous = new Button("<");
        previous.setOnAction(e -> {
            if (this.currentIndex == 0) {
                this.currentIndex = images.size() - 1;
            } else {
                this.currentIndex--;
            }
            imageView.setImage(images.get(this.currentIndex));
        });

        Button next = new Button(">");
        next.setOnAction(e -> {
            if (this.currentIndex == images.size() - 1) {
                this.currentIndex = 0;
            } else {
                this.currentIndex++;
            }
            imageView.setImage(images.get(this.currentIndex));
        });

        HBox hBox = new HBox(previous, next);
        hBox.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane(imageView, hBox);
        stackPane.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(20, stackPane);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }
}
