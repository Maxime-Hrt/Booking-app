package model;

import com.mongodb.client.MongoClients;
import dao.UsersDao;
import javafx.scene.control.Label;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public void create_account(String username, String email, String password, String place, String gender, String phoneNumber){
        UsersDao usersDao = new UsersDao(MongoClients.create("mongodb+srv://Maxime:lOQWdn8hDNv94JFz@ece-booking.h35vdkg.mongodb.net/ECE-BOOKING"), "ECE-BOOKING", "Users");

        Document toInsert = new Document();
        toInsert.append("admin", false);
        toInsert.append("username", username);
        toInsert.append("email", email);
        toInsert.append("password", password);

        toInsert.append("country", place);
        toInsert.append("gender", gender);
        toInsert.append("phone_number", phoneNumber);

        toInsert.append("created_at", DateTime.date_actual());
        usersDao.insertUser(toInsert);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<PastOrder> getOrder_history() {
        return order_history;
    }

    public void addOrder(PastOrder order){
        this.order_history.add(order);
    }

    public ArrayList<String> getFriends() {
        return friends;
    }
    public void addFriend(String friend){
        this.friends.add(friend);
    }

    ///MEMBERS TO DOCUMENT
    public void upDate(){
        Document toInsert = new Document();
        toInsert.append("admin", this.isAdmin);
        toInsert.append("username", this.username);
        toInsert.append("email", this.email);
        toInsert.append("password", this.password);

        toInsert.append("country", this.country);
        toInsert.append("gender", this.gender);
        toInsert.append("phone_number", this.phoneNumber != null ? this.phoneNumber : "");
        toInsert.append("created_at", this.dateOfCreation);
        toInsert.append("updated_at", DateTime.date_actual());

        List<Document> pastOrdersDOC = new ArrayList<>();
        for (PastOrder pastOrder : this.order_history) {
            pastOrdersDOC.add(pastOrder.toDocument());
        }
        toInsert.append("order_history", pastOrdersDOC);

        toInsert.append("search_history", this.searchHistory);
        toInsert.append("reduction", this.discount);
        toInsert.append("bucket_list", this.bucketList);
        toInsert.append("profile_picture", this.profilePicture);
        toInsert.append("friends", this.friends);
        //toInsert.append("description", this.description);
        toInsert.append("description", this.description != null ? this.description : "");

        List<Document> reviewsDOC = new ArrayList<>();
        for (Review review : this.reviews) {
            reviewsDOC.add(review.toDocument());
        }
        toInsert.append("reviews", reviewsDOC);

        List<Document> paymentMethodDOC = new ArrayList<>();
        if (this.paymentMethod == null){
            this.paymentMethod = new PaymentMethod();
        }
        paymentMethodDOC.add(this.paymentMethod.toDocument());
        toInsert.append("payment_method", paymentMethodDOC);
        /*
        ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(this.paymentMethod);
        for (PaymentMethod paymentMethod : paymentMethods) {
            paymentMethodDOC.add(paymentMethod.toDocument());
        }
        toInsert.append("payment_method", paymentMethodDOC);*/

        UsersDao usersDao = new UsersDao(MongoClients.create("mongodb+srv://Maxime:lOQWdn8hDNv94JFz@ece-booking.h35vdkg.mongodb.net/ECE-BOOKING"), "ECE-BOOKING", "Users");
        usersDao.updateUserEmail(this.email, toInsert);
    }

    static public void main(String[] args) {
        ArrayList<Members> members = new Data().getAllMembers();
        for (Members member : members) {
            member.printMember();
        }
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreated_at() {
        return (this.dateOfCreation);
    }

    public LocalDate getUpdated_at() {
        return (this.dateOfUpdate);
    }

    public ArrayList<String> getBucketList() {
        return bucketList;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public String getGender() {
        return gender;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public boolean getRole(){
        return this.isAdmin;
    }
}
