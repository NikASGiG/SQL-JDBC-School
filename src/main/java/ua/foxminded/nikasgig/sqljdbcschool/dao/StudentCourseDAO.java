package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.foxminded.nikasgig.sqljdbcschool.exception.DataProcessingException;
import ua.foxminded.nikasgig.sqljdbcschool.model.StudentCourse;

public class StudentCourseDAO {

    public void create(StudentCourse studentCourse) {
        String query = "INSERT INTO student_course (student_id, course_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentCourse.getStudentId());
            statement.setInt(2, studentCourse.getCourseId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Data processing error occurred", e);
        }
    }

    public List<StudentCourse> read(int studentId, int courseId) {
        List<StudentCourse> result = new ArrayList<>();
        String query = "SELECT * FROM student_course WHERE student_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new StudentCourse(resultSet.getInt("student_id"), resultSet.getInt("course_id")));
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Data processing error occurred", e);
        }
        return result;
    }

    public void delete(int studentId, int courseId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM student_course WHERE student_id = ? AND course_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, studentId);
                statement.setInt(2, courseId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Data processing error occurred", e);
        }
    }

    public List<StudentCourse> getAll() {
        List<StudentCourse> studentCourses = new ArrayList<>();
        String query = "SELECT * FROM student_course";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                studentCourses.add(new StudentCourse(resultSet.getInt("student_id"), resultSet.getInt("course_id")));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Data processing error occurred", e);
        }
        return studentCourses;
    }
}
