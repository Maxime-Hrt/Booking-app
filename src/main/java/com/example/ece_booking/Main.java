package com.example.ece_booking;

import model.*;
import model.hotels.Hotels;

import java.util.ArrayList;

public class Main {
    static public void main(String[] args) {
        MainPage mainPage = new MainPage();
        Recherche search;
        Users user = new Users();

        int choix = 0;

        //System.out.println(DateTime.date_actual());

        while (choix != 4) {
            choix = mainPage.menu();
            switch (choix) {
                case 1 -> mainPage.login();
                //case 2 -> mainPage.create_account();
                case 3 -> {
                    user.setTempSearch(new Recherche().makeAResearch());
                    user.getTempSearch().printSearch();
                    user.addSearchHistory(user.getTempSearch().getDestination());
                    ArrayList<Hotels> hotels = user.getTempSearch().searchHotel();
                    for (Hotels hotel : hotels) {
                        hotel.printHotel();
                    }
                }
                case 4 -> System.out.println("Bye!");
                default -> System.out.println("Wrong choice!");
            }
        }
    }
}
