package ua.foxminded.nikasgig.sqljdbcschool.app;

import java.sql.Connection;
import java.util.Scanner;

import ua.foxminded.nikasgig.sqljdbcschool.db.DatabaseChecker;
import ua.foxminded.nikasgig.sqljdbcschool.db.DatabaseManager;
import ua.foxminded.nikasgig.sqljdbcschool.db.PostgreSQLConnection;
import ua.foxminded.nikasgig.sqljdbcschool.model.DatabaseCredentials;
import ua.foxminded.nikasgig.sqljdbcschool.service.InsertDataService;
import ua.foxminded.nikasgig.sqljdbcschool.service.UpdateDataService;

public class Application {

    public static void main(String[] args) {
        System.out.println("Welcome to SQL JDBC School");
        DatabaseCredentials databaseCredentials = new DatabaseCredentials(
                "jdbc:postgresql://localhost:5432/school_foxminded", "postgres", "1234");
        PostgreSQLConnection postgreSQLConnection = new PostgreSQLConnection(databaseCredentials);
        Connection connection = postgreSQLConnection.getConnection();
        DatabaseManager databaseManager = new DatabaseManager(connection);
        DatabaseChecker databaseChecker = new DatabaseChecker();
        databaseChecker.complexCheck(connection);

        boolean isProgramWorks = true;
        boolean checkForMenuOne = true;
        Scanner in = new Scanner(System.in);
        while (isProgramWorks) {
            int menu;
            System.out.println("----------------------");
            System.out.println("1. Generate the test data");
            System.out.println("2. a. Find all groups with less or equal students’ number");
            System.out.println("3. b. Find all students related to the course with the given name");
            System.out.println("4. c. Add a new student");
            System.out.println("5. d. Delete a student by the STUDENT_ID");
            System.out.println("6. e. Add a student to the course (from a list)");
            System.out.println("7. f. Remove the student from one of their courses.");
            System.out.println("0. Exit");
            System.out.print(">>> ");
            menu = in.nextInt();
            System.out.println("\n----------------------");
            switch (menu) {
            case 1:
                if (checkForMenuOne) {
                    InsertDataService.commandGenerateTheTestData(databaseManager);
                    UpdateDataService.commandUpdateTheTestData(databaseManager);
                    checkForMenuOne = false;
                } else {
                    System.out.println("The test data has already generated");
                }
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 0:
                isProgramWorks = false;
                databaseChecker.close(connection);
                System.out.println("SQL JDBC School has been finished");
                break;

            default:
                System.out.println("Incorrect command");
                break;
            }
        }
    }
}
