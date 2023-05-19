package org.example.view;

import org.example.controller.CommentsController;
import org.example.controller.PostsController;
import org.example.controller.UserController;

import java.sql.SQLException;
import java.util.Scanner;

public class CommentsView {
    public static void commentsMenu() throws SQLException {
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
