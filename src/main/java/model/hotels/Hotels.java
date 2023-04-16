package model.hotels;

import com.mongodb.client.MongoClients;
import dao.AccommodationsDao;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;


public class Hotels {
    private String name, description, category;
    private Adress adress;
    private ArrayList<Rating> ratings;
    private ArrayList<String> amenities, activities, photos;
    private ArrayList<Rooms> rooms;
    private Contact contact;
    private boolean add_by_user;

    public Hotels() {

    }

    public Hotels(String name, String description, String category, Adress adress, ArrayList<Rating> ratings, ArrayList<String> amenities, ArrayList<String> activities, ArrayList<String> photos, ArrayList<Rooms> rooms, Contact contact, boolean add_by_user) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.adress = adress;
        this.ratings = ratings;
        this.amenities = amenities;
        this.activities = activities;
        this.photos = photos;
        this.rooms = rooms;
        this.contact = contact;
        this.add_by_user = add_by_user;
    }

    public Hotels(Document hotel) {
        try {
            if (!hotel.containsKey("name")) {
                throw new Exception("No name found");
            }
            this.name = hotel.getString("name");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            if (!hotel.containsKey("description")) {
                throw new Exception("No description found");
            }
            this.description = hotel.getString("description");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            if (!hotel.containsKey("category")) {
                throw new Exception("No category found");
            }
            this.category = hotel.getString("category");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            if (!hotel.containsKey("address")) {
                throw new Exception("No address found");
            }
            this.adress = new Adress(hotel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        //System.out.println(hotel.get("rating"));
        try {
            Object ratingObj = hotel.get("rating");
            if (ratingObj == null) {
                throw new Exception("No rating found");
            }
            ArrayList<Document> ratingDocuments = (ArrayList<Document>) ratingObj;
            ArrayList<Rating> ratings = new ArrayList<>();
            for (Document rating : ratingDocuments) {
                ratings.add(new Rating(rating));
            }
            this.ratings = ratings;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.ratings = new ArrayList<>();
        }


        if (hotel.containsKey("amenities")) {
            this.amenities = (ArrayList<String>) hotel.get("amenities");
        } else {
            this.amenities = new ArrayList<>();
        }
        if (hotel.containsKey("activities")) {
            this.activities = (ArrayList<String>) hotel.get("activities");
        } else {
            this.activities = new ArrayList<>();
        }


        try {
            Object roomObj = hotel.get("rooms");
            if (roomObj == null) {
                throw new Exception("No room found");
            }
            ArrayList<Document> roomDocuments = (ArrayList<Document>) roomObj;
            ArrayList<Rooms> rooms = new ArrayList<>();
            for (Document room : roomDocuments) {
                rooms.add(new Rooms(room));
            }
            this.rooms = rooms;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.rooms = new ArrayList<>();
        }

        if (hotel.containsKey("contact")) {
            this.contact = new Contact((Document) hotel.get("contact"));
        } else {
            this.contact = new Contact();
        }
        try {
            if (!hotel.containsKey("add_by_an_user")) {
                throw new Exception("No add_by_an_user found");
            }
            this.add_by_user = hotel.getBoolean("add_by_an_user");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (hotel.containsKey("photos")) {
            this.photos = (ArrayList<String>) hotel.get("photos");
        } else {
            this.photos = new ArrayList<>();
        }
    }

    public Document toDocument() {
        Document toUpdate = new Document();

        // Add fields to the Document object
        toUpdate.append("name", this.name);
        toUpdate.append("description", this.description);
        toUpdate.append("category", this.category);
        toUpdate.append("address", this.adress.toDocument());
        toUpdate.append("amenities", this.amenities);
        toUpdate.append("activities", this.activities);

        List<Document> ratingDocs = new ArrayList<>();
        for (Rating rating : this.ratings) {
            ratingDocs.add(rating.toDocument());
        }
        toUpdate.append("rating", ratingDocs);

        List<Document> roomDocs = new ArrayList<>();
        for (Rooms room : this.rooms) {
            roomDocs.add(room.toDocument());
        }
        toUpdate.append("rooms", roomDocs);

        toUpdate.append("contact", this.contact.toDocument());
        toUpdate.append("add_by_an_user", this.add_by_user);
        toUpdate.append("photos", this.photos);

        return toUpdate;
    }

    public void upDate() {
        AccommodationsDao dataHotels = new AccommodationsDao(MongoClients.create("mongodb+srv://Maxime:lOQWdn8hDNv94JFz@ece-booking.h35vdkg.mongodb.net/ECE-BOOKING"), "ECE-BOOKING", "Accomodations");
        dataHotels.updateAccommodationName(this.name, this.toDocument());
    }

    public void printHotel() {
        System.out.println("Name : " + this.name);
        System.out.println("Description : " + this.description);
        System.out.println("Category : " + this.category);
        System.out.println("Adress : " + this.adress);

        System.out.println("Ratings : ");
        for (Rating rating : this.ratings) {
            rating.printRating();
        }

        System.out.println("Amenities : " + this.amenities);
        System.out.println("Activities : " + this.activities);

        System.out.println("Rooms : ");
        for (Rooms room : this.rooms) {
            room.printRoom();
        }

        System.out.println("Contact : ");
        this.contact.printContact();
        System.out.println("Add by an user : " + this.add_by_user);
        System.out.println("Photos : " + this.photos);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Adress getAdress() {
        return adress;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public ArrayList<String> getAmenities() {
        return amenities;
    }

    public ArrayList<String> getActivities() {
        return activities;
    }

    public ArrayList<Rooms> getRooms() {
        return rooms;
    }

    static public void main(String[] args) {
        AccommodationsDao acoD = new AccommodationsDao(MongoClients.create("mongodb+srv://Maxime:lOQWdn8hDNv94JFz@ece-booking.h35vdkg.mongodb.net/ECE-BOOKING"), "ECE-BOOKING", "Accomodations");
        List<Document> hotel = acoD.getAllAccommodations();
        (new Hotels(hotel.get(0))).printHotel();
        System.out.println(hotel.get(0).toJson());
    }
}
