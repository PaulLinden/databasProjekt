package org.example;

import java.sql.SQLException;

import static org.example.database.InitDatabase.InitializeDatabase;
import static org.example.view.App.menu;

public class Main {

    public static void main(String[] args) throws SQLException {
        InitializeDatabase();
        System.out.println("_________Init Complete______________");
        menu();

    }
}
