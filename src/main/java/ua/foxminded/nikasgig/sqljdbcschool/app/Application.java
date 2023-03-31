package ua.foxminded.nikasgig.sqljdbcschool.app;

import ua.foxminded.nikasgig.sqljdbcschool.service.DataService;

public class Application {

    public static void main(String[] args) {
        System.out.println("Welcome to SQL JDBC School");
        DataService dataService = new DataService();
        dataService.complexCheck();

        MenuHandler menuHandler = new MenuHandler(dataService);
        do {
            menuHandler.printMenu();
            int menu = menuHandler.scanMenu();
            menuHandler.printLine();
            switch (menu) {
            case 1:
                menuHandler.generateTestData();
                break;
            case 2:
                menuHandler.findGroupsWithStudents();
                break;
            case 3:
                menuHandler.findStudentsByCourse();
                break;
            case 4:
                menuHandler.createNewStudent();
                break;
            case 5:
                menuHandler.deleteStudent();
                break;
            case 6:
                menuHandler.addStudentToCourse();
                break;
            case 7:
                menuHandler.deleteStudentFromCourse();
                break;
            case 0:
                menuHandler.closeProgram();
                break;
            default:
                System.out.println("Incorrect command");
            }
        } while (true);
    }
}
