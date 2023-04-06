package model;

import com.mongodb.client.MongoClients;
import dao.UsersDao;
import model.hotels.Hotels;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

public class Members extends Users {
    private String id, username, password, gender, country, description, profilePicture;
    private LocalDate dateOfCreation, dateOfUpdate;
    private ArrayList<PastOrder> order_history;
    private boolean isAdmin;

    private ArrayList<String> bucketList, friends;
    private ArrayList<Review> reviews;

    public Members() {

    }

    public Members(Document user) {
        super(user);
        try {
            if (!user.containsKey("username")) {
                throw new Exception("No username found");
            }
            this.username = user.getString("username");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            if (!user.containsKey("password")) {
                throw new Exception("No password found");
            }
            this.password = user.getString("password");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            if (!user.containsKey("admin")) {
                throw new Exception("No admin found");
            }
            this.isAdmin = user.getBoolean("admin");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (user.containsKey("created_at")) {
            System.out.println("CACACACACACACACACACACACACACACACCA");
            Object date = user.get("created_at");
            if (date instanceof LocalDate) {
                this.dateOfCreation = (LocalDate) date;
            }
        } else {
            this.dateOfCreation = LocalDate.now();
        }


        if (user.containsKey("updated_at")) {
            Object date = user.get("updated_at");
            if (date instanceof LocalDate) {
                this.dateOfUpdate = (LocalDate) date;
            }
        } else {
            this.dateOfUpdate = LocalDate.now();
        }
        try {
            Object orderHistoryObj = user.get("order_history");
            if (orderHistoryObj == null) {
                throw new Exception("No orderHistory found");
            }
            ArrayList<Document> orderHistory = (ArrayList<Document>) orderHistoryObj;
            ArrayList<PastOrder> orderHistoryList = new ArrayList<>();
            ArrayList<Hotels> allHotels = new Data().getAllHotels();
            for (Document order : orderHistory) {
                for (Hotels hotel : allHotels) {
                    if (order.getString("destination").equals(hotel.getName())) {
                        Object dateOfDepartureObj = order.get("date_of_departure");
                        Object dateOfArrivalObj = order.get("date_of_arrival");
                        if (dateOfDepartureObj instanceof LocalDate && dateOfArrivalObj instanceof  LocalDate){
                            orderHistoryList.add(new PastOrder(hotel, (LocalDate) dateOfDepartureObj, (LocalDate) dateOfArrivalObj));
                        } else {
                            orderHistoryList.add(new PastOrder(hotel, LocalDate.parse(order.getString("date_of_departure")), LocalDate.parse(order.getString("date_of_arrival"))));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.order_history = new ArrayList<>();
        }
        if (user.containsKey("gender")) {
            this.gender = user.getString("gender");
        } else {
            this.gender = "Not specified";
        }
        if (user.containsKey("country")) {
            this.country = user.getString("country");
        } else {
            this.country = "Not specified";
        }
        if (user.containsKey("description")) {
            this.description = user.getString("description");
        } else {
            this.description = "Not specified";
        }
        if (user.containsKey("profile_picture")) {
            this.profilePicture = user.getString("profile_picture");
        } else {
            this.profilePicture = "Not specified";
        }
        try {
            Object reviewsObj = user.get("reviews");
            if (reviewsObj == null) {
                throw new Exception("No reviews found");
            }
            ArrayList<Document> reviews = (ArrayList<Document>) reviewsObj;
            ArrayList<Review> reviewsList = new ArrayList<>();
            for (Document review : reviews) {
                reviewsList.add(new Review(review));
            }
            this.reviews = reviewsList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.reviews = new ArrayList<>();
        }
        if (user.containsKey("bucket_list")) {
            this.bucketList = (ArrayList<String>) user.get("bucket_list");
        } else {
            this.bucketList = new ArrayList<>();
        }
        if (user.containsKey("friends")) {
            this.friends = (ArrayList<String>) user.get("friends");
        } else {
            this.friends = new ArrayList<>();
        }
    }


    public String getLastSearch(){
        return searchHistory.get(searchHistory.size()-1);
    }


    public Members(String email, String password){ // Constructor for login
        try {
            if (!verifyUser(email, password)){
                throw new Exception(email + " or " + password + " not found");
            }
            Document user = findUser(email, password);
            this.id = user.getString("id");
            this.username = user.getString("username");
            this.password = user.getString("password");
            this.isAdmin = user.getBoolean("isAdmin");
            System.out.println("Successfully login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifyUser(String email, String password){
        ArrayList<Document> data = new Data().getAllUsers();
        for (Document doc : data) {
            if (doc.getString("email").equals(email) && doc.getString("password").equals(password) ||
                    doc.getString("username").equals(email) && doc.getString("password").equals(password)) {
                System.out.println("User found");
                return true;
            }
        }
        return false;
    }

    public Document findUser(String email, String password){
        Data data = new Data();
        ArrayList<Document> datas = data.getAllUsers();
        for (Document doc : datas) {
            if (doc.getString("email").equals(email) && doc.getString("password").equals(password) ||
                    doc.getString("username").equals(email) && doc.getString("password").equals(password)) {
                System.out.println("User found");
                return doc;
            }
        }
        return null;
    }

    public void printMember(){
        System.out.println("Username: " + this.username);
        System.out.println("Email: " + this.email);
        System.out.println("Password: " + this.password);
        System.out.println("Admin: " + this.isAdmin);
        System.out.println("Date of creation: " + this.dateOfCreation);
        System.out.println("Date of update: " + this.dateOfUpdate);
        System.out.println("Order history: " + this.order_history);
        System.out.println("Gender: " + this.gender);
        System.out.println("Country: " + this.country);
        System.out.println("Description: " + this.description);
        System.out.println("Profile picture: " + this.profilePicture);
        System.out.println("Reviews: " + this.reviews);
        System.out.println("Bucket list: " + this.bucketList);
        System.out.println("Friends: " + this.friends);
        System.out.println("Search history: " + this.searchHistory);
        System.out.println("\n\n");
    }

    static public void main(String[] args) {
        ArrayList<Members> members = new Data().getAllMembers();
        for (Members member : members) {
            member.printMember();
        }
    }
}
