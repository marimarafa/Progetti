package main.java.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDB {

    private static final String URL = "jdbc:mysql://localhost:3306/corso_java";
    private static final String USER = "root";
    private static final String PASSWORD = "MysqlMarim.2004";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL non trovato.");
            throw new RuntimeException(e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
