package view;

import controller.GuestController;
import controller.WelcomeWindowController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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

        VBox mainVBox = new VBox(35);
        mainVBox.setPadding(new Insets(25));



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
        ImageView imageView = new ImageView(hotel.getPhotos().get(0));
        imageView.setFitWidth(400);
        imageView.setFitHeight(300);
        gridPane.add(imageView, 1, 2);

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
        for (String string : hotel.getAmenities()){
            Label label = new Label(string);
            label.setFont(new Font("Arial", 16));
            label.setStyle(
                    "-fx-background-color: #c9c9c9;"
                            +"-fx-border-color: #000;"
                            +"-fx-padding: 5px;"
            );
            HBox.setHgrow(label, Priority.ALWAYS); // permet au dernier élément de s'étendre
            hBoxA.getChildren().add(label);
        }

        HBox hBoxF = new HBox();
        hBoxF.setMaxWidth(Double.MAX_VALUE);
        hBoxF.setFillHeight(true);
        for (String string : hotel.getActivities()){
            Label label = new Label(string);
            label.setFont(new Font("Arial", 16));
            label.setStyle(
                    "-fx-background-color: #c9c9c9;"
                            +"-fx-border-color: #000;"
                            +"-fx-padding: 5px;"
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

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefViewportWidth(1086);
        scrollPane.setPrefViewportHeight(724);

        //BLOCK3
        AtomicInteger index = new AtomicInteger();
        /*VBox roomBox = new VBox(30);
        roomBox.setAlignment(Pos.CENTER);
        roomBox.setPadding(new Insets(20));
        roomBox.setStyle("-fx-background-color: #043580; -fx-border-color: #043580;");*/

        Label roomLabel = new Label("Rooms");
        roomLabel.setFont(new Font("Arial", 24));
        roomLabel.setTextFill(Color.GREEN);
        /*
        for (Rooms room : hotel.getRooms()) {
            GridPane roomGrid = new GridPane();

            ColumnConstraints column1 = new ColumnConstraints();
            column1.setHalignment(HPos.CENTER);
            column1.setHgrow(Priority.ALWAYS);
            roomGrid.getColumnConstraints().add(column1);
        }*/

        ArrayList<Rooms> rooms = hotel.getRooms();
        ArrayList<String> bedImages = rooms.get(0).getPhotos();
        ArrayList<Image> images= new ArrayList<>();
        for (String bedImage : bedImages){
            Image image = new Image(bedImage);
            images.add(image);
        }
        ImageView imageViewBed = new ImageView(images.get(index.get()));
        imageViewBed.setFitHeight(200);
        imageViewBed.setFitWidth(200);
        Button previousButton = new Button("<");
        previousButton.setOnAction(e -> {
            index.getAndDecrement();
            if (index.get() < 0){
                index.set(images.size() - 1);
            }
            imageViewBed.setImage(images.get(index.get()));
        });
        Button nextButton = new Button(">");
        nextButton.setOnAction(e -> {
            index.getAndIncrement();
            if (index.get() >= images.size()){
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

        /*
        GridPane block3 = new GridPane();
        block3.setPadding(new Insets(25));
        block3.setHgap(20);
        block3.setVgap(10);
        block3.setStyle("-fx-border-color: #FFF; -fx-background-color: #FFF; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 5px;");
        */


        // Création de la scène
        Scene scene = new Scene(scrollPane, 1086, 724);

        // Affichage de la scène
        stage.setScene(scene);
        stage.show();
    }
        /*
        GridPane block1 = new GridPane();
        block1.setAlignment(Pos.CENTER);
        block1.setHgap(30);
        block1.setVgap(30);
        block1.setPadding(new Insets(25, 25, 25, 25));
        block1.getStyleClass().add("grid-hotel-component");
        block1.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");

        Text hotelName = new Text(hotel.getName());
        hotelName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        block1.add(hotelName, 1, 0, 2, 1);

        Text hotelAddress = new Text(hotel.getAdress().toString());
        block1.add(hotelAddress, 1, 1, 2, 1);

        ArrayList<String> photos = hotel.getPhotos();
        ImageView photoHotel = new ImageView(photos.get(0));
        block1.add(photoHotel, 1, 2, 3, 3);

        String amenities = ammenities_toString(hotel.getAmenities());
        Text hotelAmenities = new Text(amenities);
        block1.add(hotelAmenities, 1, 4, 3, 4);


        VBox page = new VBox(20);
        page.setPadding(new Insets(20, 20, 20, 20));
        page.setAlignment(Pos.CENTER);
        page.setStyle("-fx-background-color: #043580; -fx-border-color: #043580;");
        page.getChildren().add(block1);

        Scene scene = new Scene(page, 1086, 724);
        stage.setScene(scene);
        stage.show();

         */


    static String ammenities_toString(ArrayList<String> amenities) {
        StringBuilder amenitiesString = new StringBuilder();
        for (String amenity : amenities) {
            amenitiesString.append(amenity).append(", ");
        }
        return amenitiesString.toString();
    }

}
