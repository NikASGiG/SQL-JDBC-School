package ua.foxminded.nikasgig.sqljdbcschool.service;

import java.util.List;

import ua.foxminded.nikasgig.sqljdbcschool.dao.DatabaseChecker;
import ua.foxminded.nikasgig.sqljdbcschool.dao.DatabaseManager;
import ua.foxminded.nikasgig.sqljdbcschool.dao.GroupDAO;
import ua.foxminded.nikasgig.sqljdbcschool.dao.InsertTestData;
import ua.foxminded.nikasgig.sqljdbcschool.dao.StudentCourseDAO;
import ua.foxminded.nikasgig.sqljdbcschool.dao.StudentDAO;
import ua.foxminded.nikasgig.sqljdbcschool.model.Group;
import ua.foxminded.nikasgig.sqljdbcschool.model.Student;
import ua.foxminded.nikasgig.sqljdbcschool.model.StudentCourse;

public class DataService {

    private GroupDAO groupDAO = new GroupDAO();
    private StudentDAO studentDAO = new StudentDAO();
    private StudentCourseDAO studentCourseDAO = new StudentCourseDAO();

    public void complexCheck() {
        DatabaseChecker.complexCheck();
    }

    public void generateTestData() {
        InsertTestData insertTestData = new InsertTestData(new DatabaseManager());
        insertTestData.commandGenerateTheTestData();
    }

    public List<Group> findGroupsWithLessOrEqualStudents(int number) {
        return groupDAO.readList(number);
    }

    public List<Student> findStudentsByCourseName(String courseName) {
        return studentDAO.findStudentsByCourseName(courseName);
    }

    public Student createNewStudent(String firstName, String lastName) {
        Student student = new Student(studentDAO.getMax(), 0, firstName, lastName);
        studentDAO.create(student);
        return student;
    }

    public void deleteStudent(int studentId) {
        studentDAO.deleteRelations(studentId);
        studentDAO.delete(studentId);
    }

    public StudentCourse addStudentToCourse(int studentId, int courseId) {
        StudentCourse studentCourse = new StudentCourse(studentId, courseId);
        studentCourseDAO.create(studentCourse);
        return studentCourse;
    }

    public List<StudentCourse> readStudentCourse(int studentId, int courseId) {
        return studentCourseDAO.read(studentId, courseId);
    }

    public StudentCourse deleteStudentFromCourse(int studentId, int courseId) {
        StudentCourse studentCourse = new StudentCourse(studentId, courseId);
        studentCourseDAO.delete(studentId, courseId);
        return studentCourse;
    }
}
