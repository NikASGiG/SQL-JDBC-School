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
            result.add("INSERT INTO groups (group_id, group_name) " + "VALUES (" + element.getId() + ", '"
                    + element.getName() + "');");
        }
        return result;
    }

    public List<String> formatCourseData(List<Course> list) {
        List<String> result = new ArrayList<>(list.size());
        for (Course element : list) {
            result.add("INSERT INTO courses (course_id, course_name, course_description) " + "VALUES ("
                    + element.getId() + ", '" + element.getName() + "', '" + element.getDescription() + "');");
        }
        return result;
    }

    public List<String> formatStudentData(List<Student> list) {
        List<String> result = new ArrayList<>(list.size());
        for (Student element : list) {
            result.add("INSERT INTO students (student_id, group_id, first_name, last_name) " + "VALUES ("
                    + element.getId() + ", " + element.getGroupId() + ", '" + element.getFirstName() + "', '"
                    + element.getLastName() + "');");
        }
        return result;
    }

    public List<String> formatgroupId(List<Integer> list) {
        List<String> result = new ArrayList<>(STUDENT_COUNT);
        for (int i = 0; i < STUDENT_COUNT; i++) {
            result.add("UPDATE students SET group_id = " + list.get(i) + " WHERE student_id = " + i);
        }
        return result;
    }

    public String formatFindGroupsWithLessOrEqualStudents(List<Group> list) {
        StringBuilder result = new StringBuilder();
        for (Group group : list) {
            result.append(group.getName() + "\n");
        }
        return result.toString();
    }

    public String formatFindStudentsByCourseName(List<Student> list) {
        StringBuilder result = new StringBuilder();
        for (Student student : list) {
            result.append(student.getId() + " " + student.getFirstName() + " " + student.getLastName() + "\n");
        }
        return result.toString();
    }

    public List<String> manyToMany(List<Integer> list) {
        List<String> result = new ArrayList<>(STUDENT_COUNT);
        for (int i = 0; i < STUDENT_COUNT; i++) {
            result.add("INSERT INTO student_course (student_id, course_id) VALUES (" + i + ", " + list.get(i) + ");");
        }
        return result;
    }
    // INSERT INTO student_course (student_id, course_id) VALUES (1, 2);
}
