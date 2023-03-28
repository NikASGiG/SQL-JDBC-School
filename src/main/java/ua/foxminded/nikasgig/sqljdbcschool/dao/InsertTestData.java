package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.util.List;

import ua.foxminded.nikasgig.sqljdbcschool.model.Course;
import ua.foxminded.nikasgig.sqljdbcschool.model.Group;
import ua.foxminded.nikasgig.sqljdbcschool.model.Student;
import ua.foxminded.nikasgig.sqljdbcschool.service.FormatDataService;
import ua.foxminded.nikasgig.sqljdbcschool.service.GeneratorTestDataService;

public class InsertTestData {

    private static FormatDataService formatDataService = new FormatDataService();
    private DatabaseManager databaseManager;

    public InsertTestData(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void commandGenerateTheTestData() {
        insertGroup();
        insertCourses();
        insertStudent();
        updateStudent();
        updateManyToMany();
    }

    private void insertGroup() {
        List<Group> groups = GeneratorTestDataService.generateGroupData(10);
        List<String> groupsFormated = formatDataService.formatGroupData(groups);
        for (String string : groupsFormated) {
            databaseManager.queryTestData(string);
        }
    }

    private void insertCourses() {
        List<Course> courses = GeneratorTestDataService.generateCourseData(10);
        List<String> coursesFormated = formatDataService.formatCourseData(courses);
        for (String string : coursesFormated) {
            databaseManager.queryTestData(string);
        }
    }

    private void insertStudent() {
        List<Student> courses = GeneratorTestDataService.generateStudentData(200);
        List<String> coursesFormated = formatDataService.formatStudentData(courses);
        for (String string : coursesFormated) {
            databaseManager.queryTestData(string);
        }
    }

    private void updateStudent() {
        List<Integer> groupIdList = GeneratorTestDataService.generateIntList(200);
        List<String> groupIdFormated = formatDataService.formatgroupId(groupIdList);
        for (String string : groupIdFormated) {
            databaseManager.queryTestData(string);
        }
    }

    private void updateManyToMany() {
        List<String> groupIdFormated = formatDataService
                .manyToMany(GeneratorTestDataService.generateIntListForStudentCourse(200));
        for (String string : groupIdFormated) {
            databaseManager.queryTestData(string);
        }
    }
}
