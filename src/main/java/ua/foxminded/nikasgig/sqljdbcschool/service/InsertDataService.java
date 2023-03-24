package ua.foxminded.nikasgig.sqljdbcschool.service;

import java.util.List;

import ua.foxminded.nikasgig.sqljdbcschool.db.DatabaseManager;
import ua.foxminded.nikasgig.sqljdbcschool.model.Course;
import ua.foxminded.nikasgig.sqljdbcschool.model.Group;
import ua.foxminded.nikasgig.sqljdbcschool.model.Student;

public class InsertDataService {

    private static FormatDataService formatDataService = new FormatDataService();

    public static void commandGenerateTheTestData(DatabaseManager databaseManager) {
        insertGroup(databaseManager);
        insertCourses(databaseManager);
        insertStudent(databaseManager);
    }

    private static void insertGroup(DatabaseManager databaseManager) {
        List<Group> groups = GeneratorTestDataService.generateGroupData(10);
        List<String> groupsFormated = formatDataService.formatGroupData(groups);
        for (String string : groupsFormated) {
            databaseManager.queryTestData(string);
        }
    }

    private static void insertCourses(DatabaseManager databaseManager) {
        List<Course> courses = GeneratorTestDataService.generateCourseData(10);
        List<String> coursesFormated = formatDataService.formatCourseData(courses);
        for (String string : coursesFormated) {
            databaseManager.queryTestData(string);
        }
    }

    private static void insertStudent(DatabaseManager databaseManager) {
        List<Student> courses = GeneratorTestDataService.generateStudentData(200);
        List<String> coursesFormated = formatDataService.formatStudentData(courses);
        for (String string : coursesFormated) {
            databaseManager.queryTestData(string);
        }
    }
}
