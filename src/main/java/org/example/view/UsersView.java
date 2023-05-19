package org.example.view;

import org.example.controller.UserController;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class UsersView {

    public static void userMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();

        System.out.println("Users: ");
        System.out.println("1.Get all user data");
        System.out.println("2.Get user");
        System.out.println("3.Check user id");
        System.out.println("4.Create user");
        System.out.println("5.Check user online");
        System.out.println("6.Check user offline");
        System.out.println("7.Remove user");
        System.out.println("8.Get list of users and ids");
        System.out.println("9.Get number of users");

        String menuOptionInput = scanner.nextLine();
        switch (menuOptionInput) {
            case "1" -> userController.getAllUsers();
            case "2" -> {
                System.out.println("Write id: ");
                int userId = Integer.parseInt(scanner.nextLine());
                userController.getUser(userId);
            }
            case "3" -> {
                System.out.println("Write id: ");
                int userId = Integer.parseInt(scanner.nextLine());
                System.out.println(userId);
                userController.checkUserId(userId);
            }
            case "4" -> {
                System.out.println("Write username:");
                String userName = scanner.nextLine();
                System.out.println("Write email:");
                String email = scanner.nextLine();
                userController.createUser(userName, email);
            }
            case "5" ->{
                userController.userOnlineController();
            }
            case "6" ->{
                userController.userOfflineController();
            }
            case"7" ->{
                System.out.println("Write id: ");
                int userId = Integer.parseInt(scanner.nextLine());

                System.out.println("Are you sure you want to remove user "+userId+"?");
                System.out.println("[y/n]");
                String sure = scanner.nextLine();

                if (Objects.equals(sure, "y")) {
                    userController.removeUser(userId);
                }
            }
            case"8" ->userController.getUsersNameAndIdList();
            case"9" ->userController.countUsers();
        }
    }

}
