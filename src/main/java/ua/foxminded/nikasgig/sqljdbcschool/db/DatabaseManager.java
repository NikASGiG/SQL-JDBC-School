package ua.foxminded.nikasgig.sqljdbcschool.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ua.foxminded.nikasgig.sqljdbcschool.sql.SQLFilesLoader;

public class DatabaseManager {
    private Connection connection;
    private SQLFilesLoader sqlFilesLoader;

    public DatabaseManager(Connection connection) {
        this.connection = connection;
        this.sqlFilesLoader = new SQLFilesLoader(connection);
    }

    public void createUser() {
        try {
            String checkUserQuery = "SELECT * FROM pg_user WHERE usename = ?";
            PreparedStatement checkUserStatement = connection.prepareStatement(checkUserQuery);
            checkUserStatement.setString(1, "root");
            ResultSet resultSet = checkUserStatement.executeQuery();
            if (!resultSet.next()) {
                Statement stmt = connection.createStatement();
                sqlFilesLoader.executeSQLFile("create_user.sql");
            }
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    public void createDatabase() {
        try {
            Statement stmt = connection.createStatement();
            sqlFilesLoader.executeSQLFile("create_database.sql");
            System.out.println("Tables created successfully");
        } catch (Exception e) {
            System.out.println("Error creating database: " + e.getMessage());
        }
    }

    public void dropDatabase() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP DATABASE IF EXISTS \"school_foxminded\";");
        } catch (SQLException e) {
            System.out.println("Error dropping database: " + e.getMessage());
        }
    }

    public void createTables() {
        try {
            Statement stmt = connection.createStatement();
            sqlFilesLoader.executeSQLFile("create_table.sql");
            System.out.println("Tables created successfully");
        } catch (Exception e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public void dropTables() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("DROP TABLE IF EXISTS student_course CASCADE");
            stmt.execute("DROP TABLE IF EXISTS students CASCADE");
            stmt.execute("DROP TABLE IF EXISTS groups CASCADE");
            stmt.execute("DROP TABLE IF EXISTS courses CASCADE");
            System.out.println("Tables dropped successfully");
        } catch (SQLException e) {
            System.out.println("Error dropping tables: " + e.getMessage());
        }
    }

    public void queryTestData(String query) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(query);
            System.out.println(query);
        } catch (Exception e) {
            System.out.println("Error query test data: " + e.getMessage());
        }
    }
}