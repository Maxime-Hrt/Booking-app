package model.hotels;

import org.bson.Document;

public class Contact {
    private String phone, email, website;

    public Contact() {
    }

    public Contact(Document hotel){
        if (hotel.containsKey("phone")) {
            this.phone = hotel.getString("phone");
        } else {
            this.phone = "No phone number found";
        }
        if (hotel.containsKey("email")) {
            this.email = hotel.getString("email");
        } else {
            this.email = "No email found";
        }
        if (hotel.containsKey("website")) {
            this.website = hotel.getString("website");
        } else {
            this.website = "No website found";
        }
    }

    public void printContact(){
        System.out.println("\tPhone: " + this.phone);
        System.out.println("\tEmail: " + this.email);
        System.out.println("\tWebsite: " + this.website);
    }
}
