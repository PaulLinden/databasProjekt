package org.example.view;

import org.example.controller.PostsController;

import java.sql.SQLException;
import java.util.Scanner;

public class PostView {
    public static void postsMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        PostsController postsController = new PostsController();
        String menuOptionInput;

        System.out.println("1.Create post");
        System.out.println("2.Get all posts");
        System.out.println("3.Get offline posts");
        System.out.println("4.Check if post exist");
        System.out.println("5.Delete post");

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
            case"5": {
                System.out.println("Insert post id:");
                int postId = Integer.parseInt(scanner.nextLine());

                postsController.deletePost(postId);}
        }
    }
}
