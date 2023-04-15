package view;

import controller.GuestController;
import controller.HotelController;
import controller.WelcomeWindowController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Users;
import model.hotels.Hotels;
import javafx.stage.Stage;
import model.hotels.Rating;
import model.hotels.Rooms;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class HotelView {
    static public void HotelData(Stage stage, Hotels hotel, Users user) {
        stage.setTitle(hotel.getName());

        // Création du GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25));

        Button backButton = new Button("Back");
        backButton.setOnAction(actionEvent ->ResearchView.printResearch(stage, user, null));

        VBox mainVBox = new VBox(35);
        mainVBox.setPadding(new Insets(25));

        mainVBox.getChildren().add(backButton);


        // Ajout de la VBox dans la première colonne
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20, 20, 10, 10));
        VBox.setMargin(vBox, new Insets(0, 300, 0, 0));

        Label label1 = new Label("Destination");
        TextField destinatonField = new TextField();
        Label label2 = new Label("checkinDate");
        DatePicker checkinDate = new DatePicker();
        Label label3 = new Label("checkoutDate");
        DatePicker checkoutDate = new DatePicker();
        Label label4 = new Label("nbAdults");
        TextField nbAdultsField = new TextField();
        Button button = new Button("Rechercher");
        Text actionTarget = new Text();

        button.setOnAction(actionEvent -> GuestController.Search(user, destinatonField, checkinDate, checkoutDate, nbAdultsField, actionTarget, stage));
        vBox.getChildren().addAll(label1, destinatonField, label2, checkinDate, label3, checkoutDate, label4, nbAdultsField, button, actionTarget);
        vBox.getStyleClass().add("background-yellow");
        vBox.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");
        gridPane.add(vBox, 0, 0, 1, 3);

        // Ajout du titre dans les 2 dernières colonnes de la première ligne
        Label titre = new Label(hotel.getName());
        titre.setFont(new Font("Arial", 24));
        gridPane.add(titre, 1, 0);

        //Ajout de l'adresse dans les 2 dernières colonnes de la deuxième ligne
        Label adresse = new Label(hotel.getAdress().toString());
        gridPane.add(adresse, 1, 1);

        // Ajout de la photo dans les 2 dernières colonnes de la 3ème ligne
        ImageView photoHotel1 = new ImageView(hotel.getPhotos().get(0));
        photoHotel1.setFitWidth(400);//320
        photoHotel1.setFitHeight(300);//240
        //TODO: Photo2 avec les margins
        //ImageView photoHotel2 = new ImageView(hotel.getPhotos().get(1));
        //photoHotel2.setFitWidth(320);
        //photoHotel2.setFitHeight(240);
        gridPane.add(photoHotel1, 1, 2);
        //gridPane.add(photoHotel2, 2, 2);

        // Ajout de la chaîne de caractères dans les 2 dernières colonnes de la dernière ligne
        Label stringLabel = new Label("Amenities:" + ammenities_toString(hotel.getAmenities()));
        stringLabel.setFont(new Font("Arial", 16));
        stringLabel.setWrapText(true);
        gridPane.add(stringLabel, 1, 3);

        gridPane.setStyle("-fx-border-color: #FFF; -fx-background-color: #FFF; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 5px;");
        mainVBox.getChildren().add(gridPane);
        mainVBox.setBackground(new Background(new BackgroundFill(Color.rgb(4, 53, 128), CornerRadii.EMPTY, Insets.EMPTY)));


        GridPane block2 = new GridPane();
        block2.setPadding(new Insets(25));
        block2.setHgap(20);
        block2.setVgap(10);
        block2.setStyle("-fx-border-color: #FFF; -fx-background-color: #FFF; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 5px;");

        Label description = new Label(hotel.getDescription());
        description.setMaxWidth(Double.MAX_VALUE);
        description.setWrapText(true);
        block2.add(description, 0, 0, 1, 1);

        HBox hBoxA = new HBox();
        hBoxA.setMaxWidth(Double.MAX_VALUE);
        hBoxA.setFillHeight(true);
        for (String string : hotel.getAmenities()) {
            Label label = new Label(string);
            label.setFont(new Font("Arial", 16));
            label.setStyle(
                    "-fx-background-color: #c9c9c9;"
                            + "-fx-border-color: #000;"
                            + "-fx-padding: 5px;"
            );
            HBox.setHgrow(label, Priority.ALWAYS); // permet au dernier élément de s'étendre
            hBoxA.getChildren().add(label);
        }

        HBox hBoxF = new HBox();
        hBoxF.setMaxWidth(Double.MAX_VALUE);
        hBoxF.setFillHeight(true);
        for (String string : hotel.getActivities()) {
            Label label = new Label(string);
            label.setFont(new Font("Arial", 16));
            label.setStyle(
                    "-fx-background-color: #c9c9c9;"
                            + "-fx-border-color: #000;"
                            + "-fx-padding: 5px;"
            );
            HBox.setHgrow(label, Priority.ALWAYS); // permet au dernier élément de s'étendre
            hBoxF.getChildren().add(label);
        }

        VBox vBox2 = new VBox(10);
        vBox2.setAlignment(Pos.CENTER);
        Label registerLabel = new Label("You have to register to access promotions");
        registerLabel.setWrapText(true);
        Button registerButton = new Button("Register");
        registerButton.setOnAction(actionEvent -> WelcomeWindowController.signUp());
        vBox2.getChildren().addAll(registerLabel, registerButton);
        vBox2.getStyleClass().add("background-yellow");
        vBox2.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");
        vBox2.setPadding(new Insets(10, 40, 15, 40));

        block2.add(hBoxA, 0, 1);
        block2.add(hBoxF, 0, 2);
        block2.add(vBox2, 1, 0, 1, 3);

        //ScrollPane scrollPaneBlock2 = new ScrollPane(block2);
        //mainVBox.getChildren().add(scrollPaneBlock2);
        mainVBox.getChildren().add(block2);



        //BLOCK3
        AtomicInteger index = new AtomicInteger();
        VBox roomBox = new VBox(30);
        roomBox.setAlignment(Pos.CENTER);
        roomBox.setPadding(new Insets(20));
        roomBox.setStyle("-fx-background-color: #043580; -fx-border-color: #043580;");

        Label roomLabel = new Label("Rooms");
        roomLabel.setFont(new Font("Arial", 24));
        roomLabel.setTextFill(Color.WHITE);
        roomLabel.setAlignment(Pos.CENTER_RIGHT);
        roomBox.getChildren().add(roomLabel);

        for (Rooms room : hotel.getRooms()) {
            GridPane roomGrid = new GridPane();

            ColumnConstraints column1 = new ColumnConstraints();
            column1.setHalignment(HPos.CENTER);
            column1.setHgrow(Priority.ALWAYS);
            roomGrid.getColumnConstraints().add(column1);

            ArrayList<String> bedImages = room.getPhotos();
            ArrayList<Image> images = new ArrayList<>();
            for (String bedImage : bedImages) {
                Image image = new Image(bedImage);
                images.add(image);
            }
            ImageView imageViewBed = new ImageView(images.get(index.get()));
            imageViewBed.setFitHeight(200);
            imageViewBed.setFitWidth(200);
            Button previousButton = new Button("<");
            previousButton.setOnAction(e -> {
                index.getAndDecrement();
                if (index.get() < 0) {
                    index.set(images.size() - 1);
                }
                imageViewBed.setImage(images.get(index.get()));
            });
            Button nextButton = new Button(">");
            nextButton.setOnAction(e -> {
                index.getAndIncrement();
                if (index.get() >= images.size()) {
                    index.set(0);
                }
                imageViewBed.setImage(images.get(index.get()));
            });
            HBox hBoxButton = new HBox(previousButton, nextButton);
            hBoxButton.setAlignment(Pos.CENTER);

            StackPane imgPane = new StackPane();
            imgPane.getChildren().addAll(imageViewBed, hBoxButton);
            StackPane.setAlignment(imgPane, Pos.CENTER_RIGHT);

            VBox vBoxRoom = new VBox(20, imgPane);
            vBoxRoom.setAlignment(Pos.CENTER);

            roomGrid.add(vBoxRoom, 0, 0, 1, 3);

            ColumnConstraints col2 = new ColumnConstraints();
            col2.setHalignment(HPos.LEFT);
            col2.setHgrow(Priority.ALWAYS);
            roomGrid.getColumnConstraints().add(col2);

            ColumnConstraints col3 = new ColumnConstraints();
            col3.setHalignment(HPos.LEFT);
            col3.setHgrow(Priority.ALWAYS);
            roomGrid.getColumnConstraints().add(col3);

            Background backgroundTitle = new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY));
            roomGrid.setBackground(backgroundTitle);

            Label roomType = new Label("Type : " + room.getType());
            roomType.setFont(new Font("Arial", 16));
            roomGrid.add(roomType, 1, 0, 2, 1);

            Label roomPrice = new Label("Price : " + room.getPrice() + "€");
            roomPrice.setFont(new Font("Arial", 16));
            roomGrid.add(roomPrice, 1, 2);

            Label guestNumber = new Label("Guest number : " + room.getNumber_of_pax());
            guestNumber.setFont(new Font("Arial", 16));
            roomGrid.add(guestNumber, 2, 2);

            Button bookButton = new Button("Book");
            bookButton.setOnAction(e -> {
                //TODO: MEMBER
                HotelController.paymentRecap(stage, room, hotel, user,null);

            });
            roomGrid.add(bookButton, 3, 0, 4, 2);

            roomGrid.setHgap(10);
            roomGrid.setVgap(10);
            roomGrid.setPadding(new Insets(10));

            roomGrid.getStyleClass().add("grid-hotel-component");
            roomGrid.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");

            //Click sur la chambre
            //roomGrid.setOnMouseClicked(e -> {room.printRoom();});

            roomBox.getChildren().add(roomGrid);
        }

        mainVBox.getChildren().add(roomBox);

        ///TODO : Ajouter d'autres photos pour les chambres, FLECHES FONCTIONNE

        //BLOCK4 REVIEW/RATINGS
        Label reviewLabel = new Label("Reviews");
        reviewLabel.setFont(new Font("Arial", 24));
        reviewLabel.setTextFill(Color.WHITE);
        mainVBox.getChildren().add(reviewLabel); //ATTENTION A CA

        HBox hBoxReview = new HBox(20);
        hBoxReview.setAlignment(Pos.CENTER);
        hBoxReview.setPadding(new Insets(20));
        hBoxReview.setStyle("-fx-background-color: #043580; -fx-border-color: #043580;");
        for (Rating rating : hotel.getRatings()){
            GridPane reviewGrid = new GridPane();
            reviewGrid.setMaxWidth(300);
            reviewGrid.setHgap(10);
            reviewGrid.setVgap(10);
            reviewGrid.setPadding(new Insets(10));
            reviewGrid.setAlignment(Pos.CENTER);

            Label author = new Label(rating.getAuthor());
            author.setFont(new Font("Tahoma", 16));
            reviewGrid.add(author, 0, 0);

            Label note = new Label("Note : " + rating.getRating() + "/5");
            note.setFont(new Font("Tahoma", 16));
            note.setAlignment(Pos.TOP_RIGHT);
            reviewGrid.add(note, 1, 0);

            Label comment = new Label(rating.getComment());
            comment.setFont(new Font("Tahoma", 16));
            comment.setWrapText(true);
            //comment.setMaxWidth(200);
            reviewGrid.add(comment, 0, 1, 2, 1);

            reviewGrid.getStyleClass().add("grid-hotel-component");
            reviewGrid.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");
            hBoxReview.getChildren().add(reviewGrid);
        }
        ScrollPane reviews  = new ScrollPane();
        reviews.setContent(hBoxReview);

        //reviews.setMaxWidth(700);
        reviews.setMinHeight(150);
        reviews.setStyle("-fx-background-color: #043580; -fx-border-color: #043580;");



        mainVBox.getChildren().add(reviews);



        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefViewportWidth(1086);
        scrollPane.setPrefViewportHeight(724);

        // Création de la scène
        Scene scene = new Scene(scrollPane, 1086, 724);

        // Affichage de la scène
        stage.setScene(scene);
        stage.show();
    }


    static String ammenities_toString(ArrayList<String> amenities) {
        StringBuilder amenitiesString = new StringBuilder();
        for (String amenity : amenities) {
            amenitiesString.append(amenity).append(", ");
        }
        return amenitiesString.toString();
    }

}
