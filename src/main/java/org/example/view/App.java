package org.example.view;

import org.example.controller.ExtendedSearchController;

import java.sql.SQLException;
import java.util.Scanner;

import static org.example.view.AllDataView.allDataMenu;
import static org.example.view.CommentsView.commentsMenu;
import static org.example.view.Mediaview.addImage;
import static org.example.view.PostView.postsMenu;
import static org.example.view.UsersView.userMenu;

public class App {
    public static void menu() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        String menuOptionInput;

        while (true) {

            System.out.println("Choose option:(number)");
            System.out.println("1. Data");
            System.out.println("2. Users");
            System.out.println("3. Posts");
            System.out.println("4. Comments");
            System.out.println("5.Extended search");
            System.out.println("6.Add file");
            System.out.println("10. Quit");

            menuOptionInput = scanner.nextLine();

            if (menuOptionInput.equalsIgnoreCase("Quit") || menuOptionInput.equals("10")) {
                System.out.println("Connection closed");
                break;
            }

            switch (menuOptionInput.toLowerCase()) {
                case "1" -> allDataMenu();
                case "2" -> userMenu();
                case "3" -> postsMenu();
                case "4" -> commentsMenu();
                case"5" -> extendedSearchView();
                case"6" -> addImage();
                default -> System.out.println("Invalid input");
            }
        }
        scanner.close();
    }

    public static void extendedSearchView(){
        ExtendedSearchController extendedSearchController = new ExtendedSearchController();
        //extendedSearchController.getUsersWithPosts();
        //extendedSearchController.getCommentsSub();
        //extendedSearchController.getCommentsByDate();
        //extendedSearchController.updatePassword();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Make visable [y/n]");
        String input = scanner.nextLine();

        extendedSearchController.changeVisable(input);
    }
}
