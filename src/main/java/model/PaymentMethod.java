package model;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.*;
import java.time.*;
import java.util.Date;


public class PaymentMethod {
    private String cardNumber;
    private LocalDate expirationDate;
    private String cvv;



    public PaymentMethod() {
        cardNumber = "";
        expirationDate = LocalDate.now();
        cvv = "";
    }

    public PaymentMethod(String cardNumber, LocalDate expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }


    public PaymentMethod(Document paymentMethod) {
        this.cardNumber = paymentMethod.getString("card_number");
        this.expirationDate = paymentMethod.getDate("expiration_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.cvv = paymentMethod.getString("security_code");
    }

    //last 4 numbers
    public String getLast4Numbers(){
        return this.cardNumber.substring(this.cardNumber.length() - 4);
    }

    public Document toDocument() {
        return new Document("card_number", this.cardNumber)
                .append("expiration_date", this.expirationDate)
                .append("security_code", this.cvv);
    }

    public String toString(){
        return "Card number: " + cardNumber + "\nExpiration date: " + expirationDate + "\nSecurity code: " + cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }


    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void fillInformation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your card number: ");
        this.cardNumber = sc.nextLine();
        System.out.println("Enter your expiration date: (dd/mm/yyyy)");
        String Date = sc.nextLine();
        String[] date = Date.split("/");
        this.expirationDate = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
        System.out.println("Enter your cvv: ");
        this.cvv = sc.nextLine();
    }


    public List<Document> getPaymentMethods() {
        List<Document> paymentMethods = new ArrayList<>();
        Data data = new Data();
        List<Document> users = data.getAllUsers();
        for (Document user : users) {
            if (user.containsKey("payment_method")) {
                //System.out.println(user.get("payment_method"));
                List<Document> paymentMethodList = (List<Document>) user.get("payment_method");
                paymentMethods.addAll(paymentMethodList);
            }
        }

        return paymentMethods;
    }


    static public void main(String[] args){
        List<Document> paymentMethods = new PaymentMethod().getPaymentMethods();
        ListIterator<Document> iterator = paymentMethods.listIterator();
        while (iterator.hasNext()){
            Document paymentMethod = iterator.next();
            System.out.println(iterator.nextIndex());
            System.out.println(new PaymentMethod(paymentMethod));
        }
    }
}
