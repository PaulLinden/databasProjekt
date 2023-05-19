package org.example.repository;

import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.example.database.InitDatabase.GetConnection;

@SuppressWarnings("ThrowablePrintedToSystemOut")
public class UserRepository {

    public UserRepository() {
    }
    public List<HashMap<String, Object>> getAllUserData() throws SQLException {
        List<HashMap<String, Object>> userList = new ArrayList<>();

        try (Connection connection = GetConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                HashMap<String, Object> user = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);

                    user.put(columnName, columnValue);
                }
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userList;
    }
    public String getSpecificUser(User user) {
        String userName = null;
        try (Connection connection = GetConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name FROM users WHERE id =" + user.getId())) {

            while (resultSet.next()) {
                userName = resultSet.getString("name");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userName;
    }
    public List<HashMap<String, Object>> getUsersNameAndId() {
        List<HashMap<String, Object>> userList = new ArrayList<>();

        try (Connection connection = GetConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, name FROM users")) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                HashMap<String, Object> user = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);

                    user.put(columnName, columnValue);
                }
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userList;
    }
    public void createUserRow(User user) throws SQLException {
        String query = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (Connection connection = GetConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public boolean userExists(User user) throws SQLException {
        String query = "SELECT id FROM users WHERE id = ?";
        boolean exists = false;

        try (Connection connection = GetConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());

            try (ResultSet result = preparedStatement.executeQuery()) {
                exists = result.next();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return exists;
    }
    public void getUsersOnline() {
        try (Connection connection = GetConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name,email FROM users WHERE online=1")) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println(name + ":" + email);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void getUsersOffline() {
        try (Connection connection = GetConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name,email FROM users WHERE online=0")) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println(name + ":" + email);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeUserById(User user) {
        String query = "DELETE FROM users WHERE id = '" + user.getId() + "'";
        try (Connection connection = GetConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        }

        String postQuery = "DELETE FROM posts WHERE user_id = '" + user.getId() + "'";
        try (Connection connection = GetConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(postQuery);
        } catch (SQLException e) {
            System.out.println(e);
        }

        String commentsQuery = "DELETE FROM comments WHERE user_id = '" + user.getId() + "'";
        try (Connection connection = GetConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(commentsQuery);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int countUsers() {
        int userCount = 0;

        String query = "SELECT COUNT(id) FROM users;";
        try( Connection connection = GetConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                userCount = result.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userCount;
    }
}

