package view;

import controller.GuestController;
import controller.WelcomeWindowController;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model.Recherche;
import model.Users;

import java.util.ArrayList;

public class GuestWindow {
    static public void guestWindow(Stage stage, Users user) {
        stage.setTitle("ECE-BOOKING - Guest");


        Rectangle rect = new Rectangle(1086, 724);
        rect.setFill(Color.YELLOWGREEN);


        GridPane grid = new GridPane();
        //grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(5);
        grid.setPadding(new Insets(25, 25, 25, 25));
        /*for(Node node : grid.getChildren()){
            GridPane.setHalignment(node, HPos.CENTER);
        }*/
        grid.getStyleClass().add("grid-background");
        grid.getStylesheets().add("file:src/main/resources/CSS_files/GuestPage.css");



        ImageView imageView = new ImageView("file:src/main/resources/Assets/UserGuest.png");
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        imageView.setOnMouseClicked(mouseEvent -> {
            WelcomeWindowController.signUp();
        });
        grid.add(imageView, 4, 0);

        Text Destination = new Text("Destination");
        Destination.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(Destination, 0, 1);
        TextField DestinationTextField = new TextField();
        grid.add(DestinationTextField, 0, 2);

        Label checkInLabel = new Label("Check-in");
        grid.add(checkInLabel, 1, 1);
        DatePicker checkIn = new DatePicker();
        grid.add(checkIn, 1, 2);

        Label checkOutLabel = new Label("Check-out");
        grid.add(checkOutLabel, 2, 1);
        DatePicker checkOut = new DatePicker();
        grid.add(checkOut, 2, 2);

        Label nbGuestsLabel = new Label("Number of guests");
        grid.add(nbGuestsLabel, 3, 1);
        TextField nbGuests = new TextField();
        grid.add(nbGuests, 3, 2);

        Button searchButton = new Button("Search");
        searchButton.getStyleClass().add("search-button");
        searchButton.getStylesheets().add("file:src/main/resources/CSS_files/GuestPage.css");
        grid.add(searchButton, 4, 2);


        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        /*
        Label searchHistoryLabel = new Label("Search history");
        grid.add(searchHistoryLabel, 0, 3);
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        grid.add(vbox, 0, 4);*/

        // Make a research
        Users finalUser = user;
        searchButton.setOnAction(e -> GuestController.Search(finalUser, DestinationTextField, checkIn, checkOut, nbGuests, actiontarget, stage));
        //vbox.getChildren().clear();

        StackPane root = new StackPane();
        root.getChildren().addAll(rect, grid);
        Scene scene = new Scene(root, 1086, 724);
        stage.setScene(scene);
        stage.show();
    }
}
