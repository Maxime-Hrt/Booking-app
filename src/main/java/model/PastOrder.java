package model;

public class PastOrder extends Recherche{ //Rajouter ACCOMODATIONS

    public PastOrder() {
    }
    public PastOrder(String destination, Date date_of_arrival, Date date_of_departure, int nbr_pax) {
        super(destination, date_of_arrival, date_of_departure, nbr_pax);
    }
}
