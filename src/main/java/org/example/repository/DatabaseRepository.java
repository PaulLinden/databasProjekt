package org.example.repository;

import org.example.model.AllData;

import java.sql.*;
import java.util.HashMap;

import static org.example.database.InitDatabase.GetConnection;

@SuppressWarnings("ThrowablePrintedToSystemOut")
public class DatabaseRepository {
    public void createTable(AllData newTableData) {

        String columnDefinitionsInput = newTableData.getQuery();
        String[] columnDefinitions = columnDefinitionsInput.split(",");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE IF NOT EXISTS ").append(newTableData.getTableName()).append(" (id INT PRIMARY KEY AUTO_INCREMENT,");

        for (int i = 0; i < columnDefinitions.length; i++) {
            queryBuilder.append(columnDefinitions[i].trim());

            if (i < columnDefinitions.length - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(");");
        String query = queryBuilder.toString();

        try(Connection connection = GetConnection();
            Statement statement = connection.createStatement()) {

            statement.executeUpdate(query);
            System.out.println("Table created");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    public HashMap<String, Object> getAllData(String tableName) throws SQLException {
        String sql = "SELECT * FROM " + tableName;

        HashMap<String, Object> data;
        data = new HashMap<>();
        try (Connection connection = GetConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();


            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);

                    data.put(columnName, columnValue);
                }
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return data;
    }

    public void updateRecord(AllData newData) {

        String tableName = newData.getTableName();
        int id = newData.getId();
        String columnName = newData.getColumnName();
        String value = newData.getValue();

        String sqlUp = "UPDATE " + tableName + " SET " + columnName + " = ? WHERE id = ?";

        try (Connection connection = GetConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlUp)) {
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        System.out.println("Update complete");
    }

}
