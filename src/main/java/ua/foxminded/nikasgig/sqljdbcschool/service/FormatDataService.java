package ua.foxminded.nikasgig.sqljdbcschool.service;

import java.util.ArrayList;
import java.util.List;

import ua.foxminded.nikasgig.sqljdbcschool.model.Course;
import ua.foxminded.nikasgig.sqljdbcschool.model.Group;
import ua.foxminded.nikasgig.sqljdbcschool.model.Student;

public class FormatDataService {

    public final int STUDENT_COUNT = 200;

    public List<String> formatGroupData(List<Group> list) {
        List<String> result = new ArrayList<>(list.size());
        for (Group element : list) {
            result.add("INSERT INTO groups (group_id, group_name) " + "VALUES (" + element.getGroupId() + ", '"
                    + element.getGroupName() + "');");
        }
        return result;
    }

    public List<String> formatCourseData(List<Course> list) {
        List<String> result = new ArrayList<>(list.size());
        for (Course element : list) {
            result.add("INSERT INTO courses (course_id, course_name, course_description) " + "VALUES ("
                    + element.getCourseId() + ", '" + element.getCourseName() + "', '" + element.getCourseDescription()
                    + "');");
        }
        return result;
    }

    public List<String> formatStudentData(List<Student> list) {
        List<String> result = new ArrayList<>(list.size());
        for (Student element : list) {
            result.add("INSERT INTO students (student_id, group_id, first_name, last_name) " + "VALUES ("
                    + element.getStudentId() + ", " + element.getGroupId() + ", '" + element.getFirstName() + "', '"
                    + element.getLastName() + "');");
        }
        return result;
    }

    public List<String> formatgroupId(List<Integer> list) {
        List<String> result = new ArrayList<>(STUDENT_COUNT);
        int courseIndex = 1;
        for (int i = 0; i < STUDENT_COUNT; i++) {
            for (int j = 10; j < GeneratorTestDataService.random10to30(); j++) {
                result.add("UPDATE students SET group_id = " + list.get(courseIndex) + " WHERE student_id = " + i);
                i++;
            }
            if (i > STUDENT_COUNT - 30 - 1) {
                break;
            }
            if (courseIndex == 9) {
                courseIndex = 0;
            }
            courseIndex++;
        }
        return result;
    }
}
