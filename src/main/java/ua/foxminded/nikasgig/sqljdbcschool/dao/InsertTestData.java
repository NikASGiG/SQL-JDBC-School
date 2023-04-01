package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.util.List;

import ua.foxminded.nikasgig.sqljdbcschool.model.Course;
import ua.foxminded.nikasgig.sqljdbcschool.model.Group;
import ua.foxminded.nikasgig.sqljdbcschool.model.Student;
import ua.foxminded.nikasgig.sqljdbcschool.service.GeneratorTestDataService;

public class InsertTestData {

    private DatabaseManager databaseManager;
    public final int STUDENT_COUNT = 200;

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
        for (Group element : GeneratorTestDataService.generateGroupData(10)) {
            databaseManager.queryTestData("INSERT INTO groups (group_id, group_name) " + "VALUES (" + element.getId()
                    + ", '" + element.getName() + "');");
        }
    }

    private void insertCourses() {
        for (Course element : GeneratorTestDataService.generateCourseData(10)) {
            databaseManager
                    .queryTestData("INSERT INTO courses (course_id, course_name, course_description) " + "VALUES ("
                            + element.getId() + ", '" + element.getName() + "', '" + element.getDescription() + "');");
        }
    }

    private void insertStudent() {
        for (Student element : GeneratorTestDataService.generateStudentData(200)) {
            databaseManager.queryTestData("INSERT INTO students (group_id, first_name, last_name) " + "VALUES ("
                    + element.getGroupId() + ", '" + element.getFirstName() + "', '" + element.getLastName() + "');");
        }
    }

    private void updateStudent() {
        List<Integer> groupIdList = GeneratorTestDataService.generateIntList(200);
        for (int i = 0; i < STUDENT_COUNT; i++) {
            databaseManager
                    .queryTestData("UPDATE students SET group_id = " + groupIdList.get(i) + " WHERE student_id = " + i);
        }
    }

    private void updateManyToMany() {
        List<Integer> groupIdFormated = GeneratorTestDataService.generateIntListForStudentCourse(200);
        for (int i = 0; i < STUDENT_COUNT; i++) {
            databaseManager.queryTestData("INSERT INTO student_course (student_id, course_id) VALUES (" + i + ", "
                    + groupIdFormated.get(i) + ");");
        }
    }
}
