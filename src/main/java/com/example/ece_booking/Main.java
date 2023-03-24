package com.example.ece_booking;

import model.Date;
import model.MainPage;
import model.Recherche;
import model.Users;

import java.util.Scanner;

public class Main {
    static public void main(String[] args) {
        MainPage mainPage = new MainPage();
        Recherche search;
        Users user = new Users();
        int choix = 0;

        while (choix != 3) {
            choix = mainPage.menu();
            switch (choix) {
                case 1 -> mainPage.login();
                case 2 -> {
                    search = mainPage.makeAResearch();
                    user.addHistory(search);
                    search.printSearch();
                }
                case 3 -> System.out.println("Bye!");
                default -> System.out.println("Wrong choice!");
            }
        }
    }
}
