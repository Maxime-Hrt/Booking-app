package model.hotels;

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
}
