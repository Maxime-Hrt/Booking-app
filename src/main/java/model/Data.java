package model;

import com.mongodb.client.MongoClients;
import dao.AccommodationsDao;
import dao.UsersDao;
import model.hotels.Hotels;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

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
    public ArrayList<Hotels> getAllHotels(){
        Data data = new Data();
        ArrayList<Document> hotels = data.getAllAccommodations();
        ArrayList<Hotels> hotelsList = new ArrayList<>();
        for (Document hotel : hotels) {
            hotelsList.add(new Hotels(hotel));
        }
        return hotelsList;
    }

    public ArrayList<Members> getAllMembers(){
        List<Document> members = tabOfUsers.getAllUsers();
        ArrayList<Members> membersList = new ArrayList<>();
        for (Document member : members) {
            membersList.add(new Members(member));
        }
        return membersList;
    }
}
