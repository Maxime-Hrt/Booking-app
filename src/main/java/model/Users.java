package model;

import com.mongodb.client.MongoClients;
import dao.UsersDao;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Stack;

public class Users {
    protected String email;
    protected Recherche search;
    protected Stack<Recherche> searchHistory;
    protected ArrayList<PaymentMethod> paymentMethods;


    public Users() {
        this.email = null;
        this.search = null;
        this.paymentMethods = null;
        this.searchHistory = new Stack<Recherche>();
    }
    public Users(String email, Recherche search, Stack<Recherche> searchHistory, ArrayList<PaymentMethod> paymentMethods){
        this.email = email;
        this.search = search;
        this.paymentMethods = paymentMethods;
        this.searchHistory = searchHistory;
    }

    public void addHistory(Recherche search){
        this.searchHistory.push(search);
    }
}
