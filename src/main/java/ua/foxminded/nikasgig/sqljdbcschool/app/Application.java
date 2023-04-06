package ua.foxminded.nikasgig.sqljdbcschool.app;

import ua.foxminded.nikasgig.sqljdbcschool.service.DataService;

public class Application {

    public static void main(String[] args) {
        System.out.println("Welcome to SQL JDBC School");
        DataService dataService = new DataService();
        dataService.complexСreating();

        MenuHandler menuHandler = new MenuHandler(dataService);
        menuHandler.runApplicationLoop();
    }
}
