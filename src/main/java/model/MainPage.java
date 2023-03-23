package model;

import java.util.Scanner;

public class MainPage {
    private Recherche search;
    private boolean searchDone;

    public MainPage() {
        this.searchDone = false;
    }
    public void initSearch(String Destination, Date dateOfDeparture, Date dateOfArrival, int nbOfPax){
        this.search = new Recherche(Destination, dateOfDeparture, dateOfArrival, nbOfPax);
    }
    public void initSearch(Recherche search){
        this.search = search;
    }
    public boolean isSearchDone() {
        return searchDone;
    }
    public void setSearchDone(boolean searchDone) {
        this.searchDone = searchDone;
    }

    public Recherche makeAResearch(){
        Scanner sc = new Scanner(System.in);
        String destination = null, response = null, dateOfArrival = null, dateOfDeparture = null;
        int nbOfPax = 0;
        //Make a research
        while (!isSearchDone()) {
            System.out.println("Enter your destination: ");
            destination = sc.nextLine();
            System.out.println("Enter your date of arrival: ");
            dateOfArrival = sc.nextLine();
            System.out.println("Enter your date of departure: ");
            dateOfDeparture = sc.nextLine();
            System.out.println("Enter the number of pax: ");
            nbOfPax = Integer.parseInt(sc.nextLine());
            System.out.println("Do you want to make another search? (y/n)");
            response = sc.nextLine();

            if (response.equals("n")) {
                setSearchDone(true);
            }
        }
        return new Recherche(destination, Date.stringToDate(dateOfDeparture), Date.stringToDate(dateOfArrival), nbOfPax);
    }
}
