package model;

import com.mongodb.client.MongoClients;
import dao.UsersDao;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Stack;

public class Members extends Users {
    private String id;
    private String username;
    private String password;
    private boolean isAdmin;
    private String country;
    private String gender;
    private String description;
    private String profilePicture;
    private ArrayList<PastOrder> pastOrders;
    private int discount;
    private Date dateOfCreation;
    private Date dateOfUpdate;
    private ArrayList<Review> reviews;
    private ArrayList<String> friends;
    private ArrayList<String> bucketList;
    private String phoneNumber;

    public Members() {

    }

    public Members(String email, Recherche search, Stack<Recherche> searchHistory, ArrayList<PaymentMethod> paymentMethods, String id, String username, String password, boolean isAdmin, String country, String gender, String description, String profilePicture, ArrayList<PastOrder> pastOrders, int discount, Date dateOfCreation, Date dateOfUpdate, ArrayList<Review> reviews, ArrayList<String> friends, ArrayList<String> bucketList, String phoneNumber) {
        super(email, search, searchHistory, paymentMethods);
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.country = country;
        this.gender = gender;
        this.description = description;
        this.profilePicture = profilePicture;
        this.pastOrders = pastOrders;
        this.discount = discount;
        this.dateOfCreation = dateOfCreation;
        this.dateOfUpdate = dateOfUpdate;
        this.reviews = reviews;
        this.friends = friends;
        this.bucketList = bucketList;
        this.phoneNumber = phoneNumber;
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
        Data data = new Data();
        ArrayList<Document> datas = data.getAllUsers();
        for (Document doc : datas) {
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
}
