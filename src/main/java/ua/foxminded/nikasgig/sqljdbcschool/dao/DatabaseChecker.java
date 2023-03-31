package ua.foxminded.nikasgig.sqljdbcschool.dao;

public class DatabaseChecker {

    public static void complexCheck() {
        DatabaseManager databaseManager = new DatabaseManager();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Checking user existence...");
        databaseManager.createUser();
        System.out.println("Checking database existence...");
        databaseManager.createDatabase();
        System.out.println("Checking tables existence...");
        databaseManager.createTables();
    }
}