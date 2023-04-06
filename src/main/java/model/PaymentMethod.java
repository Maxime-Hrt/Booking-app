package model;

import org.bson.Document;
import org.bson.codecs.jsr310.LocalDateCodec;

import java.util.Locale;
import java.util.Scanner;
import java.util.Date;
import java.time.*;

public class PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private LocalDate expirationDate;
    private String cvv;



    public PaymentMethod() {
    }

    public PaymentMethod(String cardNumber, String cardHolderName, LocalDate expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public PaymentMethod(Document paymentMethod) {
        Object date = paymentMethod.get("expiration_date");
        if (date instanceof LocalDate){
            this.expirationDate = (LocalDate) date;
        } else {
            this.expirationDate = LocalDate.parse((String) date);
        }
        this.cardNumber = paymentMethod.getString("card_number");
        this.cvv = paymentMethod.getString("security_code");
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
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

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
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

    static public void main(String[] args){
        LocalDate date = LocalDate.of(2023, 4, 6);
        System.out.println(date);
    }
}
