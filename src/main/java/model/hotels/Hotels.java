package model.hotels;

import model.hotels.Adress;

import java.util.ArrayList;

public class Hotels {
    private String name, description, category;
    private Adress adress;
    private ArrayList<Rating> ratings;
    private ArrayList<String> amenities, facilities, photos;
    private ArrayList<Rooms> rooms;
    private Contact contact;
    private boolean add_by_user;

    public Hotels() {
    }
}
