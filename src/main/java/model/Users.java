package model;

import com.mongodb.client.MongoClients;
import dao.UsersDao;
import model.hotels.Hotels;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;


public class Users {
    protected ArrayList<String> searchHistory; //destination history
    protected String email, phoneNumber;
    protected ArrayList<PaymentMethod> paymentMethods;
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
            Object paymentMethodObj = user.get("payment_method");
            if (paymentMethodObj == null) {
                throw new Exception("No paymentMethods found");
            }
            ArrayList<Document> paymentMethods = (ArrayList<Document>) paymentMethodObj;
            ArrayList<PaymentMethod> paymentMethodsList = new ArrayList<>();
            for (Document paymentMethod : paymentMethods) {
                paymentMethodsList.add(new PaymentMethod(paymentMethod));
            }
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

    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
