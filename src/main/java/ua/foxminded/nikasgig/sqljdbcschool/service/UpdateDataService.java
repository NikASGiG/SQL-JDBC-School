package ua.foxminded.nikasgig.sqljdbcschool.service;

import java.util.List;

import ua.foxminded.nikasgig.sqljdbcschool.db.DatabaseManager;

public class UpdateDataService {

    private static FormatDataService formatDataService = new FormatDataService();

    public static void commandUpdateTheTestData(DatabaseManager databaseManager) {
        updateStudent(databaseManager);
        updateManyToMany(databaseManager);
    }

    private static void updateStudent(DatabaseManager databaseManager) {
        List<Integer> groupIdList = GeneratorTestDataService.generateIntList(10);
        List<String> groupIdFormated = formatDataService.formatgroupId(groupIdList);
        for (String string : groupIdFormated) {
            databaseManager.queryTestData(string);
        }
    }

    private static void updateManyToMany(DatabaseManager databaseManager) {
        databaseManager.queryTestData("INSERT INTO student_course (student_id, course_id) SELECT students.student_id,"
                + " courses.course_id FROM (SELECT student_id FROM students)"
                + " students CROSS JOIN (SELECT course_id FROM courses ORDER BY random()  LIMIT 3) courses ORDER BY random();");
    }
}
