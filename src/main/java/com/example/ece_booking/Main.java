package com.example.ece_booking;

import model.*;
import model.hotels.Hotels;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static public void main(String[] args) {
        MainPage mainPage = new MainPage();
        Data data = new Data();
        Recherche search;
        Users user = new Users();
        ArrayList<Hotels> all_hotels = data.getAllHotels();

        int choix = 0;

        //System.out.println(DateTime.date_actual());

        while (choix != 4) {
            choix = mainPage.menu();
            switch (choix) {
                case 1 -> mainPage.login();
                case 2 -> mainPage.create_account();
                case 3 -> {
                    search = mainPage.makeAResearch();
                    user.addSearchHistory(search.getDestination());
                    search.printSearch();
                }
                case 4 -> System.out.println("Bye!");
                default -> System.out.println("Wrong choice!");
            }
        }
    }
}
