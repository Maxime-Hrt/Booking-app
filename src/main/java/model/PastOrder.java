package model;

public class PastOrder {
    private Date date_of_arrival;
    private Date date_of_departure;
    private String destination;

    public PastOrder() {
    }
    public PastOrder(Date date_of_arrival, Date date_of_departure, String destination) {
        this.date_of_arrival = date_of_arrival;
        this.date_of_departure = date_of_departure;
        this.destination = destination;
    }
    public Date getDate_of_arrival() {
        return date_of_arrival;
    }
    public Date getDate_of_departure() {
        return date_of_departure;
    }
    public String getDestination() {
        return destination;
    }
}
