package model;

import java.util.ArrayList;
import java.util.Stack;

public class Users {
    protected String email;
    protected Stack<String> searchHistory;
    protected ArrayList<PaymentMethod> paymentMethods;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Stack<String> getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(Stack<String> searchHistory) {
        this.searchHistory = searchHistory;
    }

    public void addSearchHistory(String search) {
        searchHistory.push(search);
    }

    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
