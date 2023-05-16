package org.example.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.database.InitDatabase.GetConnection;

public class BackUp {
    public static void createDatabase(){
        String [] tables = {"users","posts","comments"};
        String [] input = {"name VARCHAR(50), email VARCHAR(50), created DATE, online BOOLEAN, lastonline DATETIME, newsletter BOOLEAN,\";" ,
                "user_id INT, content TEXT, created DATETIME,",
                "user_id INT, post_id INT, content TEXT, created DATETIME"};
        for (int i = 0; i < tables.length; i++) {

            String columnDefinitionsInput = input[i];
            String[] columnDefinitions = columnDefinitionsInput.split(",");

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("CREATE TABLE IF NOT EXISTS ").append(tables[i]).append(" (id INT PRIMARY KEY AUTO_INCREMENT,");

            for (int j = 0; j < columnDefinitions.length; j++) {
                queryBuilder.append(columnDefinitions[j].trim());

                if (j < columnDefinitions.length - 1) {
                    queryBuilder.append(", ");
                }
            }
            queryBuilder.append(");");
            String query = queryBuilder.toString();

            try (Connection connection = GetConnection();
                 Statement statement = connection.createStatement();) {

                statement.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
