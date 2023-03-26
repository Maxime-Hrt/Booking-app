package model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dao.UsersDao;
import org.bson.Document;

import java.util.*;

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

    //Doit vérifier les doublons et obliger les informations obligatoires
    public void create_account(){
        UsersDao usersDao = new UsersDao(MongoClients.create("mongodb+srv://Maxime:lOQWdn8hDNv94JFz@ece-booking.h35vdkg.mongodb.net/ECE-BOOKING"), "ECE-BOOKING", "Users");
        Members member = new Members();
        String place = null;
        boolean isRegistered = false;
        int choix = 0, genderChoice = 0;

        Scanner sc = new Scanner(System.in);
        Document toInsert = new Document();
        toInsert.append("admin", false);

        while (!isRegistered){
            choix = menuCreateAccount();
            switch (choix){
                case 1 -> {
                    String username = sc.nextLine();
                    toInsert.append("username", username);
                }
                case 2 -> {
                    String email = sc.nextLine();
                    toInsert.append("email", email);
                }
                case 3 -> {
                    String password = sc.nextLine();
                    toInsert.append("password", password);
                }
                case 4 -> {
                    ArrayList<String> bucketList = new ArrayList<>();
                    while (!Objects.equals(place, "NN")){
                        System.out.println("Enter a place to add (press NN to stop): ");
                        place = sc.nextLine();
                        if (!Objects.equals(place, "NN")){
                            bucketList.add(place);
                        }
                    }
                    toInsert.append("bucketList", bucketList);
                }
                case 5 -> {
                    place = sc.nextLine();
                    toInsert.append("countryOfResidence", place);
                }
                case 6 -> {
                    while (genderChoice != 1 && genderChoice != 2 && genderChoice != 3) {
                        System.out.println("1. Male");
                        System.out.println("2. Female");
                        System.out.println("3. Other");
                        genderChoice = Integer.parseInt(sc.nextLine());
                    }
                    switch (genderChoice){
                        case 1 -> toInsert.append("gender", "Male");
                        case 2 -> toInsert.append("gender", "Female");
                        default -> toInsert.append("gender", "Other");
                    }
                }
                case 7 -> {
                    String phoneNumber = sc.nextLine();
                    toInsert.append("phoneNumber", phoneNumber);
                }
                case 8 -> {
                    PaymentMethod card = new PaymentMethod();
                    Calendar calendar = Calendar.getInstance();
                    card.fillInformation();
                    calendar.set(card.getExpirationDate().getYear(), card.getExpirationDate().getMonth(), card.getExpirationDate().getDay());
                    toInsert.append("PaymentMethod", Collections.singletonList(
                            new Document("card_number", card.getCardNumber())
                                    .append("expiration_date", calendar.getTime())
                                    .append("security_code", card.getCvv())
                    ));
                }
                case 9 -> {
                    System.out.println("Do you want to register? (y/n)");
                    String response = sc.nextLine();
                    if (response.equals("y")) {
                        isRegistered = true;
                    }
                }
            }
        }
        usersDao.insertUser(toInsert);
    }

    public int menu(){
        Scanner sc = new Scanner(System.in);
        int choix = 0;
        System.out.println("1. Login");
        System.out.println("2. Create an account");
        System.out.println("3. Make a research");
        System.out.println("4. Quit");
        choix = Integer.parseInt(sc.nextLine());
        return choix;
    }

    public int menuCreateAccount(){
        Scanner sc = new Scanner(System.in);
        int choix = 0;
        System.out.println("1. Set username");
        System.out.println("2. Set email");
        System.out.println("3. Set password");
        System.out.println("4. Set Bucket list");
        System.out.println("5. Country of residence");
        System.out.println("6. Gender");
        System.out.println("7. Phone number");
        System.out.println("8. Payment method");
        System.out.println("9. Quit");

        choix = Integer.parseInt(sc.nextLine());
        return choix;
    }
}
