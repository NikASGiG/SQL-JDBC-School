package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseManager {

    public void createUser() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            SQLFilesLoader sqlFilesLoader = new SQLFilesLoader(connection);
            sqlFilesLoader.executeSQLFile("create_user.sql");
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    public void createDatabase() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            SQLFilesLoader sqlFilesLoader = new SQLFilesLoader(connection);
            sqlFilesLoader.executeSQLFile("create_database.sql");
            System.out.println("Tables created successfully");
        } catch (Exception e) {
            System.out.println("Database has already created");
        }
    }

    public void createTables() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            SQLFilesLoader sqlFilesLoader = new SQLFilesLoader(connection);
            sqlFilesLoader.executeSQLFile("create_table.sql");
            System.out.println("Tables created successfully");
        } catch (Exception e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public void queryTestData(String query) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println(query);
        } catch (Exception e) {
            System.out.println("Error query test data: " + e.getMessage());
        }
    }
}