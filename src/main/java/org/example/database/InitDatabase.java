package org.example.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

@SuppressWarnings("ThrowablePrintedToSystemOut")
public class InitDatabase {

    private static InitDatabase instance;
    private MysqlDataSource dataSource;
    private String url = "localhost";
    private int port = 3306;
    private String database = "myData";
    private String username = "root";
    private String password = "";

    private InitDatabase() {
        // private constructor
    }

    public static synchronized InitDatabase getInstance() {
        if (instance == null) {
            instance = new InitDatabase();
            instance.initializeDatabase();
        }
        return instance;
    }

    private void initializeDatabase() {
        try {
            System.out.print("Configuring data source...");
            dataSource = new MysqlDataSource();
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setUrl("jdbc:mysql://" + url + ":" + port + "/" + database + "?serverTimezone=UTC");
            dataSource.setUseSSL(false);
            System.out.print("done!\n");
        } catch (SQLException e) {
            System.out.print("failed!\n");
            System.out.println(e);
            System.exit(0);
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.print("failed!\n");
            System.out.println(e);
            System.exit(0);
            return null;
        }
    }
}

