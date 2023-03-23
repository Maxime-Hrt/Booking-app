package model;

import com.mongodb.client.MongoClients;
import dao.AccommodationsDao;
import dao.UsersDao;
import org.bson.Document;

import java.util.ArrayList;

public class Data {
    private UsersDao tabOfUsers;
    private AccommodationsDao tabOfAccommodations;

    public Data() {
        this.tabOfUsers = new UsersDao(MongoClients.create("mongodb+srv://Maxime:lOQWdn8hDNv94JFz@ece-booking.h35vdkg.mongodb.net/ECE-BOOKING"), "ECE-BOOKING", "Users");
        this.tabOfAccommodations = new AccommodationsDao(MongoClients.create("mongodb+srv://Maxime:lOQWdn8hDNv94JFz@ece-booking.h35vdkg.mongodb.net/ECE-BOOKING"), "ECE-BOOKING", "Accomodations");
    }
    public ArrayList<Document> getAllUsers(){
        return new ArrayList<>(tabOfUsers.getAllUsers());
    }
    public ArrayList<Document> getAllAccommodations(){
        return new ArrayList<>(tabOfAccommodations.getAllAccommodations());
    }
}
