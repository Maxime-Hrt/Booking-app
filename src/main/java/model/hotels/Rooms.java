package model.hotels;

import org.bson.Document;
import java.util.ArrayList;
import java.time.*;

public class Rooms {
    private String type;
    private int price, number_of_pax;
    private ArrayList<LocalDate> unavailableDates;
    private ArrayList<String> photos;

    public Rooms() {

    }

    public Rooms(Document room){
        this.type = room.getString("type");
        this.price = room.getInteger("price");
        this.number_of_pax = room.getInteger("numberOfPax");
        this.unavailableDates = (ArrayList<LocalDate>) room.get("dates");
        if (room.containsKey("photos")){
            this.photos = (ArrayList<String>) room.get("photos");
        } else {
            this.photos = new ArrayList<String>();
        }
    }

    public void printRoom(){
        System.out.println("\tType: " + this.type);
        System.out.println("\tPrice: " + this.price);
        System.out.println("\tNumber of pax: " + this.number_of_pax);
        System.out.println("\tUnavailable dates: " + this.unavailableDates);
        System.out.println("\tPhotos: " + this.photos + "\n");
    }
}
