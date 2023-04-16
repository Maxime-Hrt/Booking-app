package model;

import model.hotels.Hotels;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Recherche {
    private String Destination;
    private LocalDate dateOfDeparture;
    private LocalDate dateOfArrival;
    private int nbOfPax;
    private boolean isSearchDone = false;

    public Recherche() {

    }
    public Recherche(String Destination, LocalDate dateOfArrival, LocalDate dateOfDeparture, int nbOfPax) {
        this.Destination = Destination;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.nbOfPax = nbOfPax;
    }
    public String getDestination() {
        return Destination;
    }

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public LocalDate getDateOfArrival() {
        return dateOfArrival;
    }

    public int getNbOfPax() {
        return nbOfPax;
    }

    public void printSearch(){
        System.out.println("Destination: " + this.Destination);
        System.out.println("Date of arrival: " + this.dateOfArrival);
        System.out.println("Date of departure: " + this.dateOfDeparture);
        System.out.println("Number of pax: " + this.nbOfPax);
    }

    public ArrayList<Hotels> searchHotel(){
        ArrayList<Hotels> hotels = new Data().getAllHotels();
        ArrayList<Hotels> result = new ArrayList<>();
        for(Hotels hotel : hotels){
            if(hotel.getName().equals(this.Destination) || hotel.getAdress().getCity().equals(this.Destination) || hotel.getAdress().getCountry().equals(this.Destination)){
                result.add(hotel);
            }
        }
        return result;
    }

    public Recherche makeAResearch(){
        Scanner sc = new Scanner(System.in);
        String destination = null, response = null, dateOfArrival = null, dateOfDeparture = null;
        int nbOfPax = 0;
        LocalDate dateOfArrival1 = null, dateOfDeparture1 = null;
        //Make a research
        while (!isSearchDone) {
            System.out.println("Enter your destination: ");
            destination = sc.nextLine();
            System.out.println("Enter your date of arrival: (dd/mm/yyyy)" );
            dateOfArrival = sc.nextLine();
            String[] date_ofA = dateOfArrival.split("/");
            dateOfArrival1 = LocalDate.of(Integer.parseInt(date_ofA[2]), Integer.parseInt(date_ofA[1]), Integer.parseInt(date_ofA[0]));

            System.out.println("Enter your date of departure: (dd/mm/yyyy)");
            dateOfDeparture = sc.nextLine();
            String[] date_ofD = dateOfDeparture.split("/");
            dateOfDeparture1 = LocalDate.of(Integer.parseInt(date_ofD[2]), Integer.parseInt(date_ofD[1]), Integer.parseInt(date_ofD[0]));

            System.out.println("Enter the number of pax: ");
            nbOfPax = Integer.parseInt(sc.nextLine());
            System.out.println("Do you want to make another search? (y/n)");
            response = sc.nextLine();

            if (response.equals("n")) {
                setSearchDone(true);
            }
        }
        return new Recherche(destination, dateOfArrival1, dateOfDeparture1, nbOfPax);
    }

    public void setDateOfArrival(LocalDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public void setSearchDone(boolean searchDone) {
        isSearchDone = searchDone;
    }
}
