package org.example.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static org.example.database.InitDatabase.GetConnection;

public class ExtendedSearchRepository {

    public void getPostsAndComments() {
        String query = "SELECT users.name, posts.id AS postId, posts.content AS post_content, comments.content AS comments_content, comment_users.name AS comment_user_name " +
                "FROM users " +
                "INNER JOIN posts ON users.id = posts.user_id " +
                "INNER JOIN comments ON posts.id = comments.post_id " +
                "INNER JOIN users AS comment_users ON comments.user_id = comment_users.id";

        Map<Integer, List<String>> postsWithComments = new HashMap<>();
        Map<Integer, String> postUsers = new HashMap<>();
        Map<Integer, String> postContents = new HashMap<>();

        try (Connection connection = GetConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                int postId = result.getInt("postId");
                String username = result.getString("name");
                String content = result.getString("post_content");
                String comment = result.getString("comments_content");
                String commentUser = result.getString("comment_user_name");

                postUsers.put(postId, username);

                List<String> comments = postsWithComments.getOrDefault(postId, new ArrayList<>());
                comments.add(commentUser + ": " + comment);
                postsWithComments.put(postId, comments);

                postContents.put(postId, content);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        for (Map.Entry<Integer, List<String>> entry : postsWithComments.entrySet()) {
            int postId = entry.getKey();
            String postUser = postUsers.get(postId);
            String postContent = postContents.get(postId);
            List<String> comments = entry.getValue();

            System.out.println(postUser + ": " + postContent);
            System.out.println("Comments:");
            System.out.println("----------------------");

            for (String comment : comments) {
                System.out.println(comment);
            }
            System.out.println("**********************");
        }
    }

    public void getCommentsWithSub(){
        String query = "SELECT users.name, posts.content " +
                       "FROM users, posts " +
                       "WHERE posts.user_id = users.id";
        /*SELECT users.name, posts.content
        FROM users
        INNER JOIN posts ON users.id = posts.user_id;*/
        try(Connection connection = GetConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String content = resultSet.getString("content");

                System.out.println(name +": "+ content);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getCommentsFromDate(){
        String query = "SELECT users.name, posts.content " +
                       "FROM users, posts " +
                       "WHERE posts.user_id = users.id AND posts.created > '2023-05-17 14:00:00'";

        try(Connection connection = GetConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String content = resultSet.getString("content");

                System.out.println(name +": "+ content);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changePassword() {
        String query = "UPDATE users " +
                       "SET password = 'nyttLosen' " +
                       "WHERE id = (SELECT id WHERE id = 10);";

        try (Connection connection = GetConnection();
             Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("Password changed");
            } else {
                System.out.println("User doesn't exist");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void changeVisable(String input) {

        int trueOrFalse = 0;

        if (Objects.equals(input, "y")){
            trueOrFalse = 1;
        }else if (Objects.equals(input, "n")){
            trueOrFalse = 0;
        }else{
            System.out.println("Something went wrong");
        }

        String query = "UPDATE posts " +
                "SET visable = '"+trueOrFalse+"' " +
                "WHERE user_id = (SELECT id FROM users WHERE id = 5);";

        try (Connection connection = GetConnection();
             Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("Visibility changed");
            } else {
                System.out.println("User doesn't exist");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
