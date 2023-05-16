package org.example.view;

import org.example.controller.CommentsController;
import org.example.controller.DatabaseController;
import org.example.controller.PostsController;
import org.example.controller.UserController;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

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
            System.out.println("5. Quit");

            menuOptionInput = scanner.nextLine();

            if (menuOptionInput.equalsIgnoreCase("Quit") || menuOptionInput.equals("5")) {
                System.out.println("Connection closed");
                break;
            }

            switch (menuOptionInput.toLowerCase()) {
                case "1" -> allDataMenu();
                case "2" -> userMenu();
                case "3" -> postsMenu();
                case "4" -> commentsMenu();
                default -> System.out.println("Invalid input");
            }
        }
        scanner.close();
    }

    private static void allDataMenu() throws SQLException {
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
    private static void userMenu() throws SQLException {
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
        }
    }
    private static void postsMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        PostsController postsController = new PostsController();
        String menuOptionInput;

        System.out.println("1.Create post");
        System.out.println("2.Get all posts");
        System.out.println("3.Get offline posts");
        System.out.println("4.Check if post exist");

        menuOptionInput = scanner.nextLine();
        switch (menuOptionInput){
            case"1": {
                System.out.println("Insert user id:");
                int userId = Integer.parseInt(scanner.nextLine());

                System.out.println("Write Comment:");
                String postText = scanner.nextLine();

                postsController.createPost(userId,postText);}
            case"2": postsController.getAllPosts();
            case"3": postsController.getAllOfflinePosts();
            case"4": {
                System.out.println("Insert post id:");
                int postId = Integer.parseInt(scanner.nextLine());

                postsController.postExists(postId);
            }
        }
        //createPost
        //createComment
        //getPostId
    }

    private static void commentsMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        CommentsController commentsController = new CommentsController();

        System.out.println("1.Create comment");
        System.out.println("2.Check all comments");

        String menuOptionInput = scanner.nextLine();
        switch (menuOptionInput) {

            case"1" -> {
                UserController userController = new UserController();
                userController.getAllUsers();

                System.out.println("Insert userId:");
                int userId = Integer.parseInt(scanner.nextLine());

                PostsController post = new PostsController();
                post.getAllPosts();

                System.out.println("Insert postId:");
                int postId = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter text for comment: ");
                String text = scanner.nextLine();

                commentsController.createComment(userId, postId, text);
            }
            case"2" -> commentsController.getAllComments();


        }
    }
}
