package org.example;

import org.example.database.InitDatabase;

import java.sql.SQLException;

import static org.example.view.App.menu;

public class Main {

    public static void main(String[] args) throws SQLException {
        InitDatabase.getInstance();
        System.out.println("_________Init Complete______________");
        menu();

    }
}
