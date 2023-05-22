package org.example.repository;

import org.example.database.InitDatabase;
import org.example.model.Posts;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class PostRepository {
    public boolean postExists(Posts post) throws SQLException {
        String query = "SELECT id FROM posts";

        try (Connection connection = InitDatabase.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            int id = post.getPostId();

            while (result.next()) {
               int postId = result.getInt("id");

                if (postId == id) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void createPost(Posts post, User user) {

        String query = "INSERT INTO posts ( user_id, content) VALUES ( ?, ?);";

        try (Connection connection = InitDatabase.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, post.getPostText());
            preparedStatement.executeUpdate();
            System.out.println("Post created");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void getAllPosts(ArrayList<Integer> ids, ArrayList<String> posts) {

        try (Connection connection = InitDatabase.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT user_id,content FROM posts ORDER BY created DESC")) {

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_Id");
                ids.add(userId);
                String content = resultSet.getString("content");
                posts.add(content);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public String getSpecificPost(Posts post){
        String content = null;
        try (Connection connection = InitDatabase.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT content FROM posts WHERE id ="+post.getPostId())) {

            while (resultSet.next()) {
                content = resultSet.getString("content");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return content;
    }

    public void deletePost(Posts post){
        String postQuery = "DELETE FROM posts WHERE user_id = '" + post.getPostId() + "'";
        try (Connection connection = InitDatabase.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(postQuery);
        } catch (SQLException e) {
            System.out.println(e);
        }

        String commentsQuery = "DELETE FROM comments WHERE user_id = '" + post.getPostId() + "'";
        try (Connection connection = InitDatabase.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(commentsQuery);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
