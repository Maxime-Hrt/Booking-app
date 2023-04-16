package controller.Admin;

import com.mongodb.client.MongoClients;
import dao.AccommodationsDao;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Members;
import model.hotels.*;
import view.Admin.AddHotelWindow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class AddHotelButton {
    static public void addHotelButton(Stage stage, Members member){
        AddHotelWindow.addHotelWindow(stage, member);
    }

    static public void fillHotel(TextField name, TextArea description,
                                 TextField category, TextField rating,
                                 TextField roomType, TextField roomPrice,
                                 DatePicker bookingDate,
                                 TextField phone, TextField email,
                                 TextField website, TextField street,
                                 TextField city, TextField country,
                                 TextField addressNumber, ArrayList<String> hotelPhotos,
                                    ArrayList<String> hotelAmenities, ArrayList<String> hotelActivities,
                                 ArrayList<Rating> hotelRatings, ArrayList<Rooms> hotelRooms
                                 ){

        //MANDATORY FIELDS: name, description, address, rating, category, rooms
        if (name.getText().isEmpty() || description.getText().isEmpty() ||
                street.getText().isEmpty() || city.getText().isEmpty() ||
                country.getText().isEmpty() || addressNumber.getText().isEmpty() ||
                rating.getText().isEmpty() || category.getText().isEmpty() ||
                roomType.getText().isEmpty() || roomPrice.getText().isEmpty() ||
                bookingDate.getValue() == null){
            System.out.println("Mandatory fields are empty");
            return;
        }

        String hotelName = name.getText();
        String hotelDescription = description.getText();
        String hotelCategory = category.getText();
        Adress hotelAdress = new Adress(Integer.parseInt(addressNumber.getText()),
                street.getText(), city.getText(),
                country.getText()
        );
        Contact hotelContact = new Contact(phone.getText(), email.getText(),
                website.getText()
        );


        Hotels hotel = new Hotels(hotelName, hotelDescription, hotelCategory,
                hotelAdress, hotelRatings, hotelAmenities, hotelActivities,
                hotelPhotos, hotelRooms, hotelContact, false);

        hotel.printHotel();
        AccommodationsDao acDAO = new AccommodationsDao(MongoClients.create("mongodb+srv://Maxime:lOQWdn8hDNv94JFz@ece-booking.h35vdkg.mongodb.net/ECE-BOOKING"), "ECE-BOOKING", "Accomodations");
        acDAO.insertAccommodation(hotel.toDocument());
    }
}
