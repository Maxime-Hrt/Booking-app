package model;

import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;

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
            this.dateOfCreation = user.getDate("created_at").toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        } else {
            this.dateOfCreation = LocalDate.now();
        }


        if (user.containsKey("updated_at")) {
            this.dateOfUpdate = user.getDate("updated_at").toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        } else {
            this.dateOfUpdate = LocalDate.now();
        }
        try {
            Object orderHistObj = user.get("order_history");
            if (orderHistObj == null) {
                throw new Exception("No history found");
            }
            ArrayList<Document> history = (ArrayList<Document>) orderHistObj;
            ArrayList<PastOrder> historyList = new ArrayList<>();
            for (Document hist : history) {
                historyList.add(new PastOrder(hist));
            }
            this.order_history = historyList;
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


    public Members login(String email, String password){ // Constructor for login
        try {
            if (!verifyUser(email, password)){
                throw new Exception(email + " or " + password + " not found");
            }
            System.out.println("Successfully login");
            return new Members(findUser(email, password));
        } catch (Exception e) {
            e.printStackTrace();
            return new Members();
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
                //System.out.println("User found");
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
        System.out.println("phone number: " + this.phoneNumber);
        System.out.println("Date of creation: " + this.dateOfCreation);
        System.out.println("Date of update: " + this.dateOfUpdate);
        System.out.println("Order history: ");
        for (PastOrder pastorder : this.order_history) {
            System.out.println("\t" + pastorder);
        }
        System.out.println("Gender: " + this.gender);
        System.out.println("Country: " + this.country);
        System.out.println("Description: " + this.description);
        System.out.println("Profile picture: " + this.profilePicture);
        for (Review review : this.reviews) {
            System.out.println("\tReview: " + review);
        }
        System.out.println("Bucket list: " + this.bucketList);
        System.out.println("Friends: " + this.friends);
        System.out.println("Search history: " + this.searchHistory);
        System.out.println("Payment method: \n" + this.paymentMethod);
        System.out.println("Discount: " + this.discount + "%");

        System.out.println("\n\n");
    }

    static public void main(String[] args) {
        ArrayList<Members> members = new Data().getAllMembers();
        for (Members member : members) {
            member.printMember();
        }
    }
}
