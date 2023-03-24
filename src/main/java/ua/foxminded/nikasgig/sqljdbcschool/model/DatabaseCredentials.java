package ua.foxminded.nikasgig.sqljdbcschool.model;

public class DatabaseCredentials {

    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;
    
    public DatabaseCredentials(String DB_URL, String DB_USER, String DB_PASSWORD) {
        this.DB_URL = DB_URL;
        this.DB_USER = DB_USER;
        this.DB_PASSWORD = DB_PASSWORD;
    }
    
    public String getDbUrl() {
        return DB_URL;
    }
    public String getDbUser() {
        return DB_USER;
    }
    public String getDbPassword() {
        return DB_PASSWORD;
    }
    public String getDB_URL() {
        return DB_URL;
    }
    public void setDB_URL(String DB_URL) {
        this.DB_URL = DB_URL;
    }
    public String getDB_USER() {
        return DB_USER;
    }
    public void setDB_USER(String DB_USER) {
        this.DB_USER = DB_USER;
    }
    public String getDB_PASSWORD() {
        return DB_PASSWORD;
    }
    public void setDB_PASSWORD(String DB_PASSWORD) {
        this.DB_PASSWORD = DB_PASSWORD;
    }
}
