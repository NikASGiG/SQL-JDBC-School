package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgreSQLConnectionCloser extends Thread {
    private Connection connection;

    public PostgreSQLConnectionCloser(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
