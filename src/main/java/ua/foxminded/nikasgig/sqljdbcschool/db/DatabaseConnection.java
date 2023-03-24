package ua.foxminded.nikasgig.sqljdbcschool.db;

import java.sql.Connection;

public interface DatabaseConnection {

    Connection getConnection();
}
