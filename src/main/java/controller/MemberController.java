package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.*;
import model.hotels.Hotels;
import org.bson.Document;
import view.GuestWindow;
import view.MemberWindow;
import view.ResearchView;

import java.time.LocalDate;
import java.util.ArrayList;

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

    public static ImageView defaultImageMember(Members member) {
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
        ImageView defaultPicture = MemberController.defaultImageMember(member);

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

        //Hide Data
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

    static public GridPane menuMember(Members member, Stage stage){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("background-white");

        Label pastOrders = new Label("Past orders");
        Label friends = new Label("Friends");
        Label updateProfile = new Label("Update profile");
        Label logout = new Label("Logout");


        AnchorPane anchorPane1 = new AnchorPane();
        AnchorPane anchorPane2 = new AnchorPane();
        AnchorPane anchorPane3 = new AnchorPane();
        AnchorPane anchorPane4 = new AnchorPane();

        AnchorPane.setTopAnchor(pastOrders, 0.0);
        AnchorPane.setRightAnchor(pastOrders, 0.0);
        AnchorPane.setBottomAnchor(pastOrders, 0.0);
        AnchorPane.setLeftAnchor(pastOrders, 0.0);
        AnchorPane.setTopAnchor(friends, 0.0);
        AnchorPane.setRightAnchor(friends, 0.0);
        AnchorPane.setBottomAnchor(friends, 0.0);
        AnchorPane.setLeftAnchor(friends, 0.0);
        AnchorPane.setTopAnchor(updateProfile, 0.0);
        AnchorPane.setRightAnchor(updateProfile, 0.0);
        AnchorPane.setBottomAnchor(updateProfile, 0.0);
        AnchorPane.setLeftAnchor(updateProfile, 0.0);
        AnchorPane.setTopAnchor(logout, 0.0);
        AnchorPane.setRightAnchor(logout, 0.0);
        AnchorPane.setBottomAnchor(logout, 0.0);
        AnchorPane.setLeftAnchor(logout, 0.0);
        anchorPane1.getStyleClass().add("background-menu-member");
        anchorPane1.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");
        anchorPane2.getStyleClass().add("background-menu-member");
        anchorPane2.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");
        anchorPane3.getStyleClass().add("background-menu-member");
        anchorPane3.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");
        anchorPane4.getStyleClass().add("background-menu-member");
        anchorPane4.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");

        anchorPane1.getChildren().add(pastOrders);
        anchorPane2.getChildren().add(friends);
        anchorPane3.getChildren().add(updateProfile);
        anchorPane4.getChildren().add(logout);

        //Boites tempo
        VBox orderList = new VBox(10);
        VBox friendsList = new VBox(10);
        GridPane profile = new GridPane();

        anchorPane1.setOnMouseClicked(mouseEvent -> {
            orderList.getChildren().clear();
            friendsList.getChildren().clear();
            profile.getChildren().clear();
            for (PastOrder order : member.getOrder_history()) {
                Label orderLabel = new Label(order.toString());
                orderList.getChildren().add(orderLabel);
            }
            grid.add(orderList, 1, 0, 1, 3);
        });
        anchorPane2.setOnMouseClicked(mouseEvent -> {
            orderList.getChildren().clear();
            friendsList.getChildren().clear();
            profile.getChildren().clear();
            TextField friendField = new TextField();
            friendField.setPromptText("Add a friend");
            Button addFriend = new Button("Add");
            addFriend.setOnAction(e -> {
                Data data = new Data();
                ArrayList<Document> users = data.getAllUsers();
                for (Document user : users) {
                    if (user.getString("username").equals(friendField.getText())) {
                        member.addFriend(friendField.getText());
                        member.upDate();
                        Label friendLabel = new Label(friendField.getText());
                        friendsList.getChildren().add(friendLabel);
                        MemberWindow.memberWindow(stage, member);
                    }
                }
                System.out.println("User not found");
            });
            HBox addFriendBox = new HBox(10);
            addFriendBox.getChildren().addAll(addFriend, friendField);
            friendsList.getChildren().add(addFriendBox);
            for (String friend : member.getFriends()) {
                Label friendLabel = new Label(friend.toString());
                friendsList.getChildren().add(friendLabel);
            }
            grid.add(friendsList, 1, 1, 2, 5);
        });
        anchorPane3.setOnMouseClicked(mouseEvent -> {
        orderList.getChildren().clear();
        friendsList.getChildren().clear();
        profile.getChildren().clear();
        Label username = new Label("*Username : " + member.getUsername());
        Label email = new Label("*Email : " + member.getEmail());
        TextArea description = new TextArea("Description : ");
        if (member.getDescription() == null){
            description.setText("Description : ");
        } else {
            description.setText("Description : " + member.getDescription());
        }
        TextField newDescription = new TextField();
        newDescription.setPromptText("New description");
        Label cratedDate = new Label("Created date : " + member.getCreated_at());
        Label updatedDate = new Label("Updated date : " + member.getUpdated_at());
        TextField gender = new TextField();
        if (member.getGender() == null) {
            gender.setText("Gender ?");
        } else {
            gender.setPromptText(member.getGender());
        }

        TextField numero = new TextField();
        if (member.getPhoneNumber() == null){
            numero.setText("Phone number ?");
        } else {
            numero.setPromptText(member.getPhoneNumber());
        }

        ArrayList<Label> bucketListLabels = new ArrayList<>();
        for (String bucket : member.getBucketList()) {
            Label bucketLabel = new Label(bucket);
            bucketListLabels.add(bucketLabel);
        }
        ArrayList<Review> reviews = member.getReviews();
        ArrayList<Label> reviewLabels = new ArrayList<>();
        for (Review review : reviews) {
            Label reviewLabel = new Label(review.toString());
            reviewLabels.add(reviewLabel);
        }
        Button update = new Button("Update");

        profile.add(username, 0, 0, 2, 1);
        profile.add(email, 0, 1, 2, 1);
        profile.add(description, 0, 2, 2, 1);
        profile.add(newDescription, 0, 3, 2, 1);
        profile.add(cratedDate, 0, 4, 1, 1);
        profile.add(updatedDate, 1, 4, 1, 1);
        profile.add(gender, 0, 5, 1, 1);
        profile.add(numero, 1, 5, 1, 1);
        Label bucketListLabel = new Label("Bucket list : ");
        profile.add(bucketListLabel, 0, 6, 1, 1);
        VBox bucketList = new VBox(5);
        for (int i = 0; i < bucketListLabels.size(); i++) {
            bucketList.getChildren().add(bucketListLabels.get(i));
        }
        profile.add(bucketList, 0, 7, 1, 1);
        Label reviewListLabel = new Label("Reviews : ");
        profile.add(reviewListLabel, 1, 6, 1, 1);
        VBox reviewList = new VBox(5);
        for (int i = 0; i < reviewLabels.size(); i++) {
            reviewList.getChildren().add(reviewLabels.get(i));
        }
        profile.add(reviewList, 1, 7, 1, 1);

        update.setOnAction(e -> {
            if (!(newDescription.getText().isEmpty())){
                member.setDescription(newDescription.getText());
            }
            if (!(gender.getText().isEmpty())){
                member.setGender(gender.getText());
            }
            if (!(numero.getText().isEmpty())){
                member.setPhoneNumber(numero.getText());
            }
            member.upDate();
            MemberWindow.memberWindow(stage, member);
        });

        profile.add(update, 0, 8, 2, 1);
        grid.add(profile, 1, 0, 1, 3);
        });
        anchorPane4.setOnMouseClicked(mouseEvent -> {
            GuestWindow.guestWindow(stage, new Users());
        });


        grid.add(anchorPane1, 0, 0, 1, 1);
        grid.add(anchorPane2, 0, 1, 1, 1);
        grid.add(anchorPane3, 0, 2, 1, 1);
        grid.add(anchorPane4, 0, 3, 1, 1);

        grid.getStyleClass().add("background-blue");
        grid.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");
        return grid;
    }

}
