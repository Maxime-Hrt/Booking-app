/**
 * @author Maxime_hrt
 * This class represents a user object in the system. A user object contains information such as
 * search history, email, phone number, payment method, discount, and temporary searc
 */

package model;

import org.bson.Document;
import java.util.ArrayList;
import java.util.List;


public class Users {
    protected ArrayList<String> searchHistory; //destination history
    protected String email, phoneNumber;
    protected PaymentMethod paymentMethod;
    protected int discount;
    protected Recherche tempSearch;

    /**
     * Constructor for creating a new user object with default values.
     */
    public Users() {
        this.searchHistory = new ArrayList<>();
        this.email = "";
        this.phoneNumber = "";
        this.paymentMethod = new PaymentMethod();
        this.discount = 0;
        this.tempSearch = new Recherche();
    }

    /**
     * Constructor for creating a user object from a MongoDB document.
     *
     * @param user the MongoDB document containing user information
     */
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

    /**
     * Returns the search history of the user.
     *
     * @return the search history of the user
     */
    public ArrayList<String> getSearchHistory() {
        return searchHistory;
    }

    /**
     * Sets the search history of the user.
     *
     * @param searchHistory the search history of the user
     */
    public void setSearchHistory(ArrayList<String> searchHistory) {
        this.searchHistory = searchHistory;
    }

    /**
     * Adds a search query to the user's search history.
     *
     * @param search the search query to be added
     */
    public void addSearchHistory(String search) {
        this.searchHistory.add(search);
    }

    /**
     * Returns the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the phone number of the user.
     *
     * @return the phone number of the user
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param phoneNumber the phone number of the user
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the discount of the user.
     *
     * @return the discount of the user
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * Sets the discount of the user.
     *
     * @param discount the discount of the user
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * Returns the payment method of the user.
     *
     * @return the payment method of the user
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the payment method of the user.
     *
     * @param paymentMethod the payment method of the user
     */
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Returns the temporary search of the user.
     *
     * @return the temporary search of the user
     */
    public Recherche getTempSearch() {
        return tempSearch;
    }

    /**
     * Sets the temporary search of the user.
     *
     * @param tempSearch the temporary search of the user
     */
    public void setTempSearch(Recherche tempSearch) {
        this.tempSearch = tempSearch;
    }
}
