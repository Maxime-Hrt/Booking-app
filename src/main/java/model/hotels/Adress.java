package model.hotels;

import org.bson.Document;

public class Adress {
    int number;
    private String street;
    private String city;
    private String country;

    public Adress() {
    }

    public Adress(int number, String street, String city, String country) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public Adress (Document hotel){
        Document adress = (Document) hotel.get("address");
        this.number = adress.getInteger("number");
        this.street = adress.getString("street");
        this.city = adress.getString("city");
        this.country = adress.getString("country");
    }

    @Override
    public String toString(){
        return number + " " + street + ", " + city + ", " + country;
    }

    public int getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
