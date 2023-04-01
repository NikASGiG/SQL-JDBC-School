package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ua.foxminded.nikasgig.sqljdbcschool.exception.DataProcessingException;
import ua.foxminded.nikasgig.sqljdbcschool.model.Course;

public class CourseDAOImpl implements CourseDAO {

    public Course create(Course course) {
        String sql = "INSERT INTO course (name, description) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Data processing error occurred", e);
        }
        return course;
    }

    public Optional<Course> findById(Long courseId) {
        String sql = "SELECT * FROM course WHERE id=?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    return Optional.of(new Course(courseId.intValue(), name, description));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Error reading course with id " + courseId+ ": ", e);
        }
    }

    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("course_id");
                String name = resultSet.getString("course_name");
                String description = resultSet.getString("course_description");
                courses.add(new Course(id, name, description));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Data processing error occurred", e);
        }
        return courses;
    }

    public Course update(Course course) {
        String sql = "UPDATE course SET name=?, description=? WHERE id=?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Data processing error occurred", e);
        }
        return course;
    }

    public boolean delete(Long courseId) {
        String sql = "DELETE FROM course WHERE id=?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, courseId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Data processing error occurred", e);
        }
    }
}
