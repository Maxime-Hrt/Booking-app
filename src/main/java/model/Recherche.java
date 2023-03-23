package model;

public class Recherche {
    private String Destination;
    private Date dateOfDeparture;
    private Date dateOfArrival;
    private int nbOfPax;

    public Recherche() {

    }
    public Recherche(String Destination, Date dateOfDeparture, Date dateOfArrival, int nbOfPax) {
        this.Destination = Destination;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.nbOfPax = nbOfPax;
    }
    public String getDestination() {
        return Destination;
    }
    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }
    public Date getDateOfArrival() {
        return dateOfArrival;
    }
    public void printSearch(){
        System.out.println("Destination: " + this.Destination);
        System.out.println("Date of departure: " + this.dateOfDeparture);
        System.out.println("Date of arrival: " + this.dateOfArrival);
        System.out.println("Number of pax: " + this.nbOfPax);
    }
}
