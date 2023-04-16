package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Members;
import model.Recherche;
import model.hotels.Hotels;
import view.ResearchView;

import java.time.LocalDate;

public class MemberController {

    public static ImageView defaultImageSignUp(Stage stage) {
        ImageView imageView = new ImageView("file:src/main/resources/Assets/UserGuest.png");
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        imageView.setOnMouseClicked(mouseEvent -> {
            WelcomeWindowController.signUp(stage);
        });
        return imageView;
    }

    public static ImageView defaultImageMember() {
        //todo: lire si le membre a une url de photo de profil (Personne n'en a)
        ImageView imageView = new ImageView("file:src/main/resources/Assets/UserGuest.png");
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        imageView.setOnMouseClicked(mouseEvent -> {
            System.out.println("BOUTON IMAGE");
            //todo: Member Controller, accès au menu membre
            // -> Update profile, delete profile, order history, etc.
        });
        return imageView;
    }

    public static void memberSearch(Stage stage, Members member, GridPane nav_search, Text actiontarget, TextField destination, DatePicker checkIn, DatePicker checkOut, TextField nbPersonnes) {
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

        member.setTempSearch(new Recherche(dest, checkInDate, checkOutDate, nbPers));
        member.addSearchHistory(member.getTempSearch().getDestination());
        for (Hotels hotel : member.getTempSearch().searchHotel()) {
            hotel.printHotel();
        }
        ResearchView.printResearch(stage, member);
    }

    public static GridPane gridPaneMember(Stage stage, Members member){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Navbar
        Text scenetitle = new Text("ECE-BOOKING");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
        scenetitle.setTextAlignment(TextAlignment.LEFT);
        ImageView defaultPicture = MemberController.defaultImageMember();

        //Search bar
        Text destination = new Text("Destination");
        destination.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        TextField destinationField = new TextField();
        destinationField.setPromptText("Kuala Lumpur?");

        Label checkIn = new Label("Check-in");
        DatePicker checkInDate = new DatePicker();
        Label checkOut = new Label("Check-out");
        DatePicker checkOutDate = new DatePicker();

        Label nbGuests = new Label("Number of guests");
        nbGuests.setOnMouseEntered(mouseEvent -> {
            nbGuests.setTextFill(Color.RED);
        });
        nbGuests.setOnMouseExited(mouseEvent -> {
            nbGuests.setTextFill(Color.BLACK);
        });

        TextField nbGuestsField = new TextField();

        Button searchButton = new Button("Search");
        searchButton.getStyleClass().add("search-button");
        searchButton.getStylesheets().add("file:src/main/resources/CSS_files/GuestPage.css");

        final Text actiontarget = new Text();

        GridPane navBar = new GridPane();
        navBar.setAlignment(Pos.CENTER);
        navBar.setHgap(450);
        navBar.setVgap(10);
        navBar.getStyleClass().add("background-yellow");
        navBar.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");
        navBar.add(scenetitle, 0, 0, 1, 1);
        navBar.add(defaultPicture, 1, 0, 1, 1);

        grid.add(destination, 0, 1, 2, 1);
        grid.add(destinationField, 0, 2, 1, 1);
        grid.add(checkIn, 1, 1, 1, 1);
        grid.add(checkInDate, 1, 2, 1, 1);
        grid.add(checkOut, 2, 1, 1, 1);
        grid.add(checkOutDate, 2, 2, 1, 1);
        grid.add(nbGuests, 3, 1, 1, 1);
        grid.add(nbGuestsField, 3, 2, 1, 1);
        grid.add(searchButton, 4, 1, 1, 2);
        grid.add(actiontarget, 0, 3, 1, 1);
        grid.getStyleClass().add("background-white");
        grid.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");

        GridPane gridTotal = new GridPane();
        gridTotal.setAlignment(Pos.TOP_CENTER);
        gridTotal.setHgap(10);
        gridTotal.setVgap(10);
        gridTotal.getStyleClass().add("background-blue");
        gridTotal.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");
        gridTotal.setPadding(new Insets(25, 25, 25, 25));
        gridTotal.add(navBar, 0, 0, 5, 1);
        gridTotal.add(grid, 0, 1, 5, 1);

        //Search button
        searchButton.setOnAction(e -> MemberController.memberSearch(
                stage,
                member,
                gridTotal,
                actiontarget,
                destinationField,
                checkInDate,
                checkOutDate,
                nbGuestsField
        ));
        return gridTotal;
    }
}
