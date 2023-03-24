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
        return new Recherche(destination, Date.stringToDate(dateOfArrival), Date.stringToDate(dateOfDeparture), nbOfPax);
    }

    public void login(){
        Scanner sc = new Scanner(System.in);
        String str_tempo = null, password = null, email = null, username = null, name = null;
        Members member = new Members();
        boolean registered = false;

        while (!registered){
            System.out.println("Enter your username/email: ");
            name = sc.nextLine();
            /*
            if (str_tempo.contains("@")) {
                email = str_tempo;
            } else {
                username = str_tempo;
            }*/
            System.out.println("Enter your password: ");
            password = sc.nextLine();
            System.out.println("Log In? (y/n)");
            str_tempo = sc.nextLine();
            if (str_tempo.equals("y")) {
                registered = true;
            }
        }
        member.verifyUser(name, password);
    }

    public void create_account(){

    }

    public int menu(){
        Scanner sc = new Scanner(System.in);
        int choix = 0;
        System.out.println("1. Login");
        System.out.println("2. Make a research");
        System.out.println("3. Quit");
        choix = Integer.parseInt(sc.nextLine());
        return choix;
    }
}
