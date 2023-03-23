package com.example.ece_booking;

import model.Date;
import model.MainPage;
import model.Recherche;
import model.Users;

import java.util.Scanner;

public class Main {
    static public void main(String[] args) {
        MainPage mainPage = new MainPage();
        Recherche search = mainPage.makeAResearch();
        search.printSearch();

        Users user = new Users();
        user.addHistory(search);
    }
}
