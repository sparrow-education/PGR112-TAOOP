package com.rinseo.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/databaseName";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final JDBC jdbc;

    static {
        jdbc = new JDBC();
    }

    // Prevents instantiation as this is a singleton utility class.
    private JDBC() {
    }

    // Returns the singleton instance.
    public static JDBC getInstance() {
        return jdbc;
    }

    // Returns a connection object to the database.
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    public static void main(String[] args) {

        var conn = JDBC.getInstance();
        try {
            conn.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
