package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import ua.foxminded.nikasgig.sqljdbcschool.model.Course;

public class CourseDAO {

    public void create(Course course) throws SQLException {
        String sql = "INSERT INTO course (name, description) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Course> read(int courseId) {
        String sql = "SELECT * FROM course WHERE id=?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    return Optional.of(new Course(courseId, name, description));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error reading course with id " + courseId + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public void update(Course course) throws SQLException {
        String sql = "UPDATE course SET name=?, description=? WHERE id=?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int courseId) throws SQLException {
        String sql = "DELETE FROM course WHERE id=?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
