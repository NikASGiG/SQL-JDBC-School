package ua.foxminded.nikasgig.sqljdbcschool.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.foxminded.nikasgig.sqljdbcschool.dao.DatabaseChecker;
import ua.foxminded.nikasgig.sqljdbcschool.dao.DatabaseManager;
import ua.foxminded.nikasgig.sqljdbcschool.dao.InsertTestData;
import ua.foxminded.nikasgig.sqljdbcschool.dao.PostgreSQLConnection;
import ua.foxminded.nikasgig.sqljdbcschool.dao.PostgreSQLConnectionCloser;
import ua.foxminded.nikasgig.sqljdbcschool.dao.StudentCourseDAO;
import ua.foxminded.nikasgig.sqljdbcschool.dao.StudentDAO;
import ua.foxminded.nikasgig.sqljdbcschool.model.Group;
import ua.foxminded.nikasgig.sqljdbcschool.model.Student;
import ua.foxminded.nikasgig.sqljdbcschool.model.StudentCourse;

public class DataService {

    private Connection connection = PostgreSQLConnection.getConnection();
    private DatabaseManager databaseManager = new DatabaseManager(connection);
    private StudentDAO studentDAO = new StudentDAO(connection);
    private StudentCourseDAO studentCourseDAO = new StudentCourseDAO(connection);

    public void complexCheck() {
        DatabaseChecker.complexCheck(connection);
    }

    public void generateTestData() {
        InsertTestData insertTestData = new InsertTestData(databaseManager);
        insertTestData.commandGenerateTheTestData();
    }

    public List<Group> findGroupsWithLessOrEqualStudents(int number) {
        List<Group> groups = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM groups WHERE group_id IN (SELECT group_id FROM students GROUP BY group_id HAVING COUNT(*) <= ?)")) {
            statement.setInt(1, number);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("group_id");
                String name = resultSet.getString("group_name");
                Group group = new Group(id, name);
                groups.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public List<Student> findStudentsByCourseName(String courseName) {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT students.* FROM students "
                + "JOIN student_course ON students.student_id = student_course.student_id "
                + "JOIN courses ON student_course.course_id = courses.course_id WHERE courses.course_name = ?")) {
            statement.setString(1, courseName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(resultSet.getInt("student_id"), resultSet.getInt("group_id"),
                        resultSet.getString("first_name"), resultSet.getString("last_name"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void createNewStudent(String firstName, String lastName) {
        int newId;
        try (PreparedStatement statement = connection.prepareStatement("(SELECT MAX(student_id) FROM students)")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                newId = resultSet.getInt(1) + 1;
                studentDAO.create(new Student(newId, 0, firstName, lastName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentId) {
        try (PreparedStatement statement = connection
                .prepareStatement("DELETE FROM student_course WHERE student_id = " + studentId)) {
            statement.execute();
            studentDAO.delete(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudentToCourse(int studentId, int courseId) {
        try {
            studentCourseDAO.create(new StudentCourse(studentId, courseId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<StudentCourse> readStudentCourse(int studentId, int courseId) throws SQLException {
        return studentCourseDAO.read(studentId, courseId);
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        try {
            studentCourseDAO.delete(studentId, courseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        PostgreSQLConnectionCloser.close(connection);
    }
}
