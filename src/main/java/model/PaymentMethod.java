package model;

import java.util.Scanner;

public class PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private Date expirationDate;
    private String cvv;


    public PaymentMethod() {
    }

    public PaymentMethod(String cardNumber, String cardHolderName, Date expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public Date getExpirationDate() {
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

    public void setExpirationDate(Date expirationDate) {
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
        this.expirationDate = Date.stringToDate(sc.nextLine());
        System.out.println("Enter your cvv: ");
        this.cvv = sc.nextLine();
    }
}
