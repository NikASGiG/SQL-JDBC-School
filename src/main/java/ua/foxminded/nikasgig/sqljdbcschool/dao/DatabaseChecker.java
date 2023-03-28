package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.sql.*;

public class DatabaseChecker {

    public static void complexCheck(Connection connection) {
        DatabaseManager databaseManager = new DatabaseManager(connection);
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Checking user existence...");
        databaseManager.createUser();
        System.out.println("Checking database existence...");
        // databaseManager.dropDatabase();
        // databaseManager.createDatabase();
        System.out.println("Checking tables existence...");
        databaseManager.dropTables();
        databaseManager.createTables();
    }
}