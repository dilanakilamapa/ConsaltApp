package com.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSingletonConnection {
    private static DBSingletonConnection instance = null;
    private Connection connection;

    private String jdbcURL = "jdbc:mysql://localhost:3306/db_appointment?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "hy6Per@lex";

    private DBSingletonConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Use the new driver class name
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error initializing database connection: " + e.getMessage(), e);
        }
    }

    public static DBSingletonConnection getInstance() {
        if (instance == null) {
            synchronized (DBSingletonConnection.class) {
                if (instance == null) {
                    instance = new DBSingletonConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection.isClosed()) {
                // If the connection is closed, create a new one
                connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting database connection: " + e.getMessage(), e);
        }
        return connection;
    }
}
