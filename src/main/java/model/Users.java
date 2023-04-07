package model;

import com.mongodb.client.MongoClients;
import dao.UsersDao;
import model.hotels.Hotels;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;


public class Users {
    protected ArrayList<String> searchHistory; //destination history
    protected String email, phoneNumber;
    protected PaymentMethod paymentMethod;
    protected int discount;


    public Users() {

    }

    public Users(Document user) {
        try {
            if (!user.containsKey("email")) {
                throw new Exception("No email found");
            }
            this.email = user.getString("email");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            if (!user.containsKey("phone_number")) {
                throw new Exception("No phoneNumber found");
            }
            this.phoneNumber = user.getString("phone_number");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            if (!user.containsKey("reduction")) {
                throw new Exception("No discount found");
            }
            this.discount = user.getInteger("reduction");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            if (!user.containsKey("payment_method")) {
                throw new Exception("No payment method found");
            }
            //System.out.println(user.get("payment_method"));
            List<Document> paymentMethod = new ArrayList<>((List<Document>) user.get("payment_method"));
            for (Document doc : paymentMethod) {
                this.paymentMethod = new PaymentMethod(doc);
            }

            this.paymentMethod = new PaymentMethod(user.get("payment_method", Document.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }




        if (user.containsKey("search_history")) {
            this.searchHistory = (ArrayList<String>) user.get("search_history");
        } else {
            this.searchHistory = new ArrayList<>();
        }
    }



    public ArrayList<String> getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(ArrayList<String> searchHistory) {
        this.searchHistory = searchHistory;
    }

    public void addSearchHistory(String search) {
        this.searchHistory.add(search);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
