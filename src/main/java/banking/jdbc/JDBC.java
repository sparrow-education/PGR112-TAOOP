package banking.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBC {
    private static Connection connection;

    public JDBC() {
        getConnection();
    }


    public static Connection getConnection() {
        if (connection == null) {
            ResourceBundle dbConfig = ResourceBundle.getBundle("dbconfig");
            String url = dbConfig.getString("URL");
            String user = dbConfig.getString("userName");
            String password = dbConfig.getString("userPass");

            try {
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connection successful");
            } catch (SQLException e) {
                System.out.println("Connection to database failed: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                System.out.println("Connection could not be closed: " + e.getMessage());
            }
        }
    }

}
