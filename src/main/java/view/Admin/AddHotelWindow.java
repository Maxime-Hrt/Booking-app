package view.Admin;

import controller.Admin.AddHotelButton;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Members;
import model.hotels.Contact;
import model.hotels.Rating;
import model.hotels.Rooms;

import java.time.LocalDate;
import java.util.ArrayList;

public class AddHotelWindow {
    static public void addHotelWindow(Stage stage, Members member){
        Scene scene = new Scene(new VBox(), 650, 400);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(5);


        Button backBtn = new Button("Back");
        backBtn.setOnAction(actionEvent -> {
            AdminWindow.adminWindow(stage, member);
        });

        //TOP SECTION
        VBox topVBox = new VBox(5);
        TextField hotelName = new TextField();
        hotelName.setPromptText("Hotel Name");
        TextField photos = new TextField();
        photos.setPromptText("Photo link");
        Button addPhoto = new Button("Add Photo");
        TextArea hotelDescription = new TextArea();
        hotelDescription.setPromptText("Description");
        topVBox.getChildren().addAll(hotelName, photos, addPhoto, hotelDescription);

        //MIDDLE SECTION
        GridPane middleGrid = new GridPane();
        middleGrid.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        middleGrid.setVgap(5);
        middleGrid.setHgap(5);
        TextField category = new TextField();
        category.setPromptText("Category");
        TextField amenities = new TextField();
        amenities.setPromptText("Amenity");
        Button addAmenity = new Button("Add Amenity");
        TextField activities = new TextField();
        activities.setPromptText("Activity");
        Button addActivity = new Button("Add Activity");
        //RatingPanel
        GridPane ratingGrid = new GridPane();
        ratingGrid.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        ratingGrid.setVgap(5);
        ratingGrid.setHgap(5);
        ratingGrid.getStyleClass().add("background-yellow");
        ratingGrid.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");
        TextField rating = new TextField();
        rating.setPromptText("Note/5");
        TextField author = new TextField();
        author.setPromptText("Author");
        TextField comment = new TextField();
        comment.setPromptText("Comment");
        Button addRating = new Button("Add Rating");
        ratingGrid.add(rating, 0, 0, 1, 1);
        ratingGrid.add(author, 1, 0, 1, 1);
        ratingGrid.add(comment, 0, 1, 2, 1);
        ratingGrid.add(addRating, 0, 2, 2, 1);
        //END RatingPanel
        middleGrid.add(category, 0, 0, 1, 1);
        middleGrid.add(amenities, 0, 1, 1, 1);
        middleGrid.add(addAmenity, 1, 1, 1, 1);
        middleGrid.add(activities, 0, 2, 1, 1);
        middleGrid.add(addActivity, 1, 2, 1, 1);
        middleGrid.add(ratingGrid, 2, 0, 1, 3);

        //ROOMS SECTION
        GridPane roomsGrid = new GridPane();
        roomsGrid.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        roomsGrid.setVgap(5);
        roomsGrid.setHgap(5);
        roomsGrid.getStyleClass().add("background-blue");
        roomsGrid.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");
        TextField roomType = new TextField();
        roomType.setPromptText("Room Type");
        TextField roomPrice = new TextField();
        roomPrice.setPromptText("Price");
        TextField guestNumber = new TextField();
        guestNumber.setPromptText("Guest Number");
        TextField photoLinker = new TextField();
        photoLinker.setPromptText("Photo Link");
        Button addRoomPhoto = new Button("Add Photo");
        DatePicker bookingDate = new DatePicker();
        bookingDate.setPromptText("Booking Date");
        Button addBooking = new Button("Add Date");
        Button addRoom = new Button("Add Room");
        roomsGrid.add(roomType, 0, 0, 1, 1);
        roomsGrid.add(guestNumber, 1, 0, 1, 1);
        roomsGrid.add(roomPrice, 2, 0, 1, 1);
        roomsGrid.add(photoLinker, 0, 1, 2, 1);
        roomsGrid.add(addRoomPhoto, 2, 1, 1, 1);
        roomsGrid.add(bookingDate, 0, 2, 2, 1);
        roomsGrid.add(addBooking, 2, 2, 1, 1);
        roomsGrid.add(addRoom, 0, 3, 3, 1);

        //BOTTOM SECTION
        GridPane contactGrid = new GridPane();
        contactGrid.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        contactGrid.setVgap(5);
        contactGrid.setHgap(5);
        TextField phone = new TextField();
        phone.setPromptText("Phone");
        TextField email = new TextField();
        email.setPromptText("Email");
        TextField website = new TextField();
        website.setPromptText("Website");
        //AddressPanel
        GridPane addressGrid = new GridPane();
        addressGrid.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        addressGrid.setVgap(5);
        addressGrid.setHgap(5);
        addressGrid.getStyleClass().add("background-blue");
        addressGrid.getStylesheets().add("file:src/main/resources/CSS_files/Hotel.css");
        TextField street = new TextField();
        street.setPromptText("Street");
        TextField city = new TextField();
        city.setPromptText("City");
        TextField country = new TextField();
        country.setPromptText("Country");
        TextField number = new TextField();
        number.setPromptText("Number");
        addressGrid.add(street, 0, 0, 3, 1);
        addressGrid.add(city, 0, 1, 3, 1);
        addressGrid.add(country, 0, 2, 1, 1);
        addressGrid.add(number, 2, 2, 1, 1);
        //END AddressPanel
        contactGrid.add(phone, 0, 0, 1, 1);
        contactGrid.add(email, 1, 0, 1, 1);
        contactGrid.add(website, 2, 0, 1, 1);
        contactGrid.add(addressGrid, 0, 1, 3, 1);

        //ADD HOTEL BUTTON
        ArrayList<Rating> hotelRatings = new ArrayList<>();
        ArrayList<String> hotelAmenities = new ArrayList<>();
        ArrayList<String> hotelActivities = new ArrayList<>();
        ArrayList<String> hotelPhotos = new ArrayList<>(), roomPhotos = new ArrayList<>();
        ArrayList<Rooms> hotelRooms = new ArrayList<>();
        ArrayList<LocalDate> hotelDates = new ArrayList<>();


        //TODO: BOOLEAN ADD BY A USER

        //BUTTONS
        addPhoto.setOnAction(actionEvent -> {
            hotelPhotos.add(photos.getText());
            System.out.println(hotelPhotos);
        });
        addAmenity.setOnAction(actionEvent -> {
            hotelAmenities.add(amenities.getText());
            System.out.println(hotelAmenities);
        });
        addActivity.setOnAction(actionEvent -> {
            hotelActivities.add(activities.getText());
            System.out.println(hotelActivities);
        });
        addRating.setOnAction(actionEvent -> {
            hotelRatings.add(new Rating(Integer.parseInt(rating.getText()),
                    comment.getText(), author.getText()));
            System.out.println(hotelRatings);
        });
        addBooking.setOnAction(actionEvent -> {
            hotelDates.add(bookingDate.getValue());
            System.out.println(hotelDates);
        });
        addRoomPhoto.setOnAction(actionEvent -> {
            roomPhotos.add(photoLinker.getText());
            System.out.println(roomPhotos);
        });
        addRoom.setOnAction(actionEvent -> {
            hotelRooms.add(new Rooms(roomType.getText(),
                    Integer.parseInt(roomPrice.getText()),
                    Integer.parseInt(guestNumber.getText()),
                    hotelDates, roomPhotos)
            );
            System.out.println(hotelRooms);
        });

        Button addHotel = new Button("Add Hotel");
        addHotel.getStyleClass().add("search-button");
        addHotel.getStylesheets().add("file:src/main/resources/CSS_files/GuestPage.css");
        addHotel.setOnAction(e -> AddHotelButton.fillHotel(hotelName, hotelDescription,
                category, rating, roomType, roomPrice, bookingDate, phone, email, website, street,
                city, country, number, hotelPhotos, hotelAmenities, hotelActivities, hotelRatings,
                hotelRooms

        ));




        //ADD TO GRIDPANE
        gridPane.add(backBtn, 0, 0, 1, 1);
        gridPane.add(topVBox, 0, 1, 2, 1);
        gridPane.add(middleGrid, 0, 2, 2, 1);
        gridPane.add(roomsGrid, 0, 3, 2, 1);
        gridPane.add(contactGrid, 0, 4, 2, 1);
        gridPane.add(addHotel, 0, 5, 2, 1);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxWidth(700);
        scrollPane.setContent(gridPane);
        scene.setRoot(scrollPane);
        stage.setScene(scene);
        stage.show();
    }
}
