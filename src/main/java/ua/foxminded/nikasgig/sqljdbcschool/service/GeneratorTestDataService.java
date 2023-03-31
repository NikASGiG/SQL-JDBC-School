package ua.foxminded.nikasgig.sqljdbcschool.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ua.foxminded.nikasgig.sqljdbcschool.model.Course;
import ua.foxminded.nikasgig.sqljdbcschool.model.Group;
import ua.foxminded.nikasgig.sqljdbcschool.model.Student;

public class GeneratorTestDataService {

    private static final String[] FIRST_NAMES = { "Emma", "Olivia", "Ava", "Isabella", "Sophia", "Mia", "Charlotte",
            "Amelia", "Harper", "Evelyn", "Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry",
            "Isabel", "Jack", "Kate", "Liam", "Milla", "Noah", "Oliver", "Peter", "Quinn", "Rachel", "Sarah", "Tom" };
    private static final String[] LAST_NAMES = { "Smith", "Johnson", "Brown", "Hanma", "Anderson", "Clark", "Wright",
            "Walker", "White", "Hall", "Adams", "Browny", "Carter", "Davis", "Evans", "Foster", "Garcia", "Harris",
            "Ingram", "Johnson", "Klein", "Lee", "Martinez", "Nelson", "Olsen", "Parker", "Quinn", "Reed", "Neo",
            "Taylor" };
    private static final String[] COURSES = { "Mathematics", "Biology", "History", "Physics", "Chemistry",
            "Computer Science", "Literature", "Psychology", "Economics", "Art" };

    private static final Random RANDOM = new Random();

    public static List<Group> generateGroupData(int numGroups) {
        List<Group> groupData = new ArrayList<>();
        for (int i = 0; i <= numGroups; i++) {
            groupData.add(new Group(i, generateGroup()));
        }
        return groupData;
    }

    private static String generateGroup() {
        String result = "";
        for (int i = 0; i < 2; i++) {
            result += (char) (RANDOM.nextInt(26) + 'a');
        }
        result += "-";
        for (int i = 0; i < 2; i++) {
            result += (int) RANDOM.nextInt(10);
        }
        return result;
    }

    public static List<Course> generateCourseData(int numCourses) {
        List<Course> courseData = new ArrayList<>();
        for (int i = 1; i <= numCourses; i++) {
            String courseName = COURSES[RANDOM.nextInt(COURSES.length)];
            courseData.add(new Course(i, courseName, "some information"));
        }
        return courseData;
    }

    public static List<Student> generateStudentData(int numStudents) {
        List<Student> studentData = new ArrayList<>();
        for (int i = 1; i <= numStudents; i++) {
            studentData.add(new Student(i, 1, FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)],
                    LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)]));
        }
        return studentData;
    }

    public static List<Integer> generateIntList(int num) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            result.add(RANDOM.nextInt(10));
        }
        return result;
    }

    public static int random10to30() {
        return RANDOM.nextInt(21) + 10;
    }

    public static String[] getCourses() {
        return COURSES;
    }

    public static String getCoursesWithNumbers() {
        StringBuilder result = new StringBuilder(COURSES.length);
        int index = 0;
        for (String course : COURSES) {
            result.append(index + 1 + " " + course + "\n");
            index++;
        }
        return result.toString();
    }

    public static List<Integer> generateIntListForStudentCourse(int num) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            result.add(RANDOM.nextInt(10) + 1);
        }
        return result;
    }
}
