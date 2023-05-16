package org.example.database;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

@SuppressWarnings("ThrowablePrintedToSystemOut")
public class InitDatabase {

    static MysqlDataSource dataSource;
    static String url = "localhost";
    static int port = 3306;
    static String database = "myData";
    static String username = "root";
    static String password = "";
    public static void InitializeDatabase(){
        try {
            System.out.print("Configuring data source...");
            dataSource = new MysqlDataSource();
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setUrl("jdbc:mysql://" + url + ":" + port + "/" + database + "?serverTimezone=UTC");
            dataSource.setUseSSL(false);
            System.out.print("done!\n");
        }
        catch(SQLException e){
            System.out.print("failed!\n");
            System.out.println(e);
            System.exit(0);
        }
    }

    public static Connection GetConnection(){
        try{
            //System.out.print("Fetching connection to database...");
            //System.out.print("done!\n");
            return dataSource.getConnection();
        }
        catch(SQLException e){
            System.out.print("failed!\n");
            System.out.println(e);
            System.exit(0);
            return null;
        }
    }

}
