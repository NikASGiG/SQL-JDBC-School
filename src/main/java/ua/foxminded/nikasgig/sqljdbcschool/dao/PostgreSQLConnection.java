package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ua.foxminded.nikasgig.sqljdbcschool.exception.ConnectionFailedException;

public class PostgreSQLConnection {

    private final static String URL = "jdbc:postgresql://localhost:5432/school_foxminded";
    private final static String USER = "postgres";
    private final static String PASSWORD = "1234";

    public static Connection getConnection() throws ConnectionFailedException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully");
            Runtime.getRuntime().addShutdownHook(new PostgreSQLConnectionCloser(connection));
        } catch (SQLException e) {
            throw new ConnectionFailedException("Connection failed: " + e.getMessage());
        }
        return connection;
    }
}
