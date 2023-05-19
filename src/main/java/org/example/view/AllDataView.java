package org.example.view;

import org.example.controller.DatabaseController;

import java.sql.SQLException;
import java.util.Scanner;

public class AllDataView {

    public static void allDataMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DatabaseController databaseController = new DatabaseController();
        String menuOptionInput;

        System.out.println("Users: ");
        System.out.println("1.Get all data from chosen table");
        System.out.println("2.Update data");
        System.out.println("3.Create new table");

        menuOptionInput = scanner.nextLine();

        switch (menuOptionInput) {
            case "1" -> {
                System.out.println("Write table name:");
                String tableName = scanner.nextLine();
                databaseController.getAllData(tableName);
            }
            case"2" -> {
                System.out.print("Enter table name: ");
                String tableName = scanner.nextLine();
                System.out.print("Enter the ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter column name: ");
                String columnName = scanner.nextLine();
                System.out.print("Enter value: ");
                String value = scanner.nextLine();
                databaseController.updateRecord(tableName,id, columnName,value);
            }
            case"3" -> {
                System.out.print("Enter table name: ");
                String tableName = scanner.nextLine();

                //name VARCHAR(50), email VARCHAR(50), created DATE, online BOOLEAN, lastonline DATETIME, newsletter BOOLEAN,
                System.out.print("Enter column definitions (comma-separated): ");
                String columnDefinitionsInput = scanner.nextLine();

                databaseController.createTable(tableName,columnDefinitionsInput);
            }
        }

    }
}
