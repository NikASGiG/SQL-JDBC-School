package ua.foxminded.nikasgig.sqljdbcschool.service;

import java.util.List;

import ua.foxminded.nikasgig.sqljdbcschool.dao.DatabaseManager;
import ua.foxminded.nikasgig.sqljdbcschool.dao.impl.CourseDAOImpl;
import ua.foxminded.nikasgig.sqljdbcschool.dao.impl.GroupDAOImpl;
import ua.foxminded.nikasgig.sqljdbcschool.dao.impl.StudentCourseDAOImpl;
import ua.foxminded.nikasgig.sqljdbcschool.dao.impl.StudentDAOImpl;
import ua.foxminded.nikasgig.sqljdbcschool.model.Course;
import ua.foxminded.nikasgig.sqljdbcschool.model.Group;
import ua.foxminded.nikasgig.sqljdbcschool.model.Student;
import ua.foxminded.nikasgig.sqljdbcschool.model.StudentCourse;

public class DataService {

    private GroupDAOImpl groupDAO = new GroupDAOImpl();
    private CourseDAOImpl courseDAOImpl = new CourseDAOImpl();
    private StudentDAOImpl studentDAO = new StudentDAOImpl();
    private StudentCourseDAOImpl studentCourseDAO = new StudentCourseDAOImpl();

    private final int STUDENT_COUNT = 200;
    private final int COURSE_COUNT = 10;

    public void complexСreating() {
        DatabaseManager databaseManager = new DatabaseManager();
        System.out.println("Сreating user existence...");
        databaseManager.createUser();
        System.out.println("Сreating database existence...");
        databaseManager.createDatabase();
        System.out.println("Сreating tables existence...");
        databaseManager.createTables();
    }

    public void generateTestData() {
        System.out.println("0%");
        createGroupsRandomly();
        System.out.println("10%");
        createCoursesRandomly();
        System.out.println("20%");
        createStudentsRandomly();
        System.out.println("50%");
        assignStudentsToGroupsRandomly();
        System.out.println("70%");
        assignStudentsToCourseRandomlyBy1To3();
        System.out.println("100%");
    }

    private void createGroupsRandomly() {
        for (Group element : GeneratorTestDataService.generateGroupData(COURSE_COUNT)) {
            groupDAO.create(element);
        }
    }

    private void createCoursesRandomly() {
        for (Course element : GeneratorTestDataService.generateCourseData(COURSE_COUNT)) {
            courseDAOImpl.create(element);
        }
    }

    private void createStudentsRandomly() {
        for (Student element : GeneratorTestDataService.generateStudentData(STUDENT_COUNT)) {
            studentDAO.create(element);
        }
    }

    private void assignStudentsToGroupsRandomly() {
        List<Integer> groupIdList = GeneratorTestDataService.generateIntList(STUDENT_COUNT);
        for (int i = 0; i < STUDENT_COUNT; i++) {
            studentDAO.update(new Student(groupIdList.get(i), null, null));
        }
    }

    private void assignStudentsToCourseRandomlyBy1To3() {
        List<Integer> groupIdFormated = GeneratorTestDataService.generateIntListForStudentCourse(STUDENT_COUNT);
        for (int i = 1; i < STUDENT_COUNT; i++) {
            studentCourseDAO.create(new StudentCourse(i, groupIdFormated.get(i)));
        }
    }

    public List<Group> findGroupsWithLessOrEqualStudents(int number) {
        return groupDAO.findGroupsByLessStudensNumber(number);
    }

    public List<Student> findStudentsByCourseName(String courseName) {
        return studentDAO.findByCourseName(courseName);
    }

    public Student createNewStudent(String firstName, String lastName) {
        Student student = new Student(0, firstName, lastName);
        studentDAO.create(student);
        return student;
    }

    public void deleteStudent(int studentId) {
        studentDAO.delete(studentId);
    }

    public StudentCourse addStudentToCourse(int studentId, int courseId) {
        StudentCourse studentCourse = new StudentCourse(studentId, courseId);
        studentCourseDAO.create(studentCourse);
        return studentCourse;
    }

    public List<StudentCourse> readStudentCourse(int studentId, int courseId) {
        return studentCourseDAO.findById(studentId, courseId);
    }

    public StudentCourse deleteStudentFromCourse(int studentId, int courseId) {
        StudentCourse studentCourse = new StudentCourse(studentId, courseId);
        studentCourseDAO.delete(studentId, courseId);
        return studentCourse;
    }
}
