package model;

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

    public Members(String email, Stack<String> searchHistory, ArrayList<PaymentMethod> paymentMethods, String id, String username, String password, boolean isAdmin, String country, String gender, String description, String profilePicture, ArrayList<PastOrder> pastOrders, int discount, Date dateOfCreation, Date dateOfUpdate, ArrayList<Review> reviews, ArrayList<String> friends, ArrayList<String> bucketList, String phoneNumber) {
        super(email, searchHistory, paymentMethods);
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
}
