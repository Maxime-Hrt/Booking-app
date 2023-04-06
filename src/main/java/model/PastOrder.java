package model;

import model.hotels.Hotels;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;

public class PastOrder{ //Rajouter ACCOMODATIONS
    Hotels hotel = new Hotels();
    LocalDate dateOfArrival, dateOfDeparture;

    public PastOrder() {
    }

    public PastOrder(Hotels hotel, LocalDate dateOfArrival, LocalDate dateOfDeparture) {
        this.hotel = hotel;
        this.dateOfArrival = dateOfArrival;
        this.dateOfDeparture = dateOfDeparture;
    }
}
