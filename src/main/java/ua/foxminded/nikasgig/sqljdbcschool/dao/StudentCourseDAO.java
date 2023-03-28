package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.foxminded.nikasgig.sqljdbcschool.model.StudentCourse;

public class StudentCourseDAO {

    private Connection connection;

    public StudentCourseDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(StudentCourse studentCourse) throws SQLException {
        String query = "INSERT INTO student_course (student_id, course_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentCourse.getStudentId());
            statement.setInt(2, studentCourse.getCourseId());
            statement.executeUpdate();
        }
    }

    public List<StudentCourse> read(int studentId, int courseId) throws SQLException {
        List<StudentCourse> result = new ArrayList<>();
        String query = "SELECT * FROM student_course WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new StudentCourse(resultSet.getInt("student_id"), resultSet.getInt("course_id")));
                }
            }
        }
        return result;
    }

    public void update(StudentCourse studentCourse) throws SQLException {
        String query = "UPDATE student_course SET student_id = ?, course_id = ? WHERE student_id = ? AND course_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentCourse.getStudentId());
            statement.setInt(2, studentCourse.getCourseId());
            statement.setInt(3, studentCourse.getStudentId());
            statement.setInt(4, studentCourse.getCourseId());
            statement.executeUpdate();
        }
    }

    public void delete(int studentId, int courseId) throws SQLException {
        String query = "DELETE FROM student_course WHERE student_id = ? AND course_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.executeUpdate();
        }
    }

    public List<StudentCourse> getAll() throws SQLException {
        List<StudentCourse> studentCourses = new ArrayList<>();
        String query = "SELECT * FROM student_course";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                studentCourses.add(new StudentCourse(resultSet.getInt("student_id"), resultSet.getInt("course_id")));
            }
        }
        return studentCourses;
    }
}
