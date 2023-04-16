package model;

import org.bson.Document;
import java.time.LocalDate;

public class PastOrder{ //Rajouter ACCOMODATIONS
    String destination;
    LocalDate dateOfArrival, dateOfDeparture;

    public PastOrder() {
    }

    public PastOrder(String destination, LocalDate dateOfArrival, LocalDate dateOfDeparture) {
        this.destination = destination;
        this.dateOfArrival = dateOfArrival;
        this.dateOfDeparture = dateOfDeparture;
    }

    public PastOrder(Document pastOrder){
        this.destination = pastOrder.getString("destination");
        this.dateOfArrival = pastOrder.getDate("date_of_arrival").toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        this.dateOfDeparture = pastOrder.getDate("date_of_departure").toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }

    public String toString(){
        return "Destination: " + this.destination + " | Date of arrival: " + this.dateOfArrival + " | Date of departure: " + this.dateOfDeparture;
    }

    public Document toDocument() {
        return new Document("destination", this.destination)
                .append("date_of_arrival", this.dateOfArrival)
                .append("date_of_departure", this.dateOfDeparture);
    }
}
