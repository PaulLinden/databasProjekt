package org.example.repository;

import org.example.model.Posts;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;

import static org.example.database.InitDatabase.GetConnection;

@SuppressWarnings("ThrowablePrintedToSystemOut")
public class CommentsRepository {

    public void getAllComments(ArrayList<Integer> ids, ArrayList<Integer> postIds, ArrayList<String> comments ){

        try (Connection connection = GetConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM comments ORDER BY created DESC")) {

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                ids.add(userId);
                int postId = resultSet.getInt("post_id");
                postIds.add(postId);
                String content = resultSet.getString("content");
                comments.add(content);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void createComment(User user, Posts post, String text) {
        String query = "INSERT INTO comments ( user_id, post_id, content) VALUES ( ?, ?, ?);";

        try (Connection connection = GetConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, post.getPostId());
            preparedStatement.setString(3, text);
            int result = preparedStatement.executeUpdate();
            System.out.println("Result: " + result);
        }
        catch (SQLException e){
            System.out.println(e);
        }

    }

}
