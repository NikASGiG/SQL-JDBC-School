package ua.foxminded.nikasgig.sqljdbcschool.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ua.foxminded.nikasgig.sqljdbcschool.model.DatabaseCredentials;

public class PostgreSQLConnection implements DatabaseConnection {

    private DatabaseCredentials databaseCredentials;
    
    public PostgreSQLConnection(DatabaseCredentials databaseCredentials) {
        this.databaseCredentials = databaseCredentials;
    }

    @Override
    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(databaseCredentials.getDB_URL(), databaseCredentials.getDB_USER(),
                    databaseCredentials.getDB_PASSWORD());
            System.out.println("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return con;
    }
}
