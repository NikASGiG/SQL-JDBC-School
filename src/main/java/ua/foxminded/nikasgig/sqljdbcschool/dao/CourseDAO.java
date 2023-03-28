package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.foxminded.nikasgig.sqljdbcschool.model.Course;

public class CourseDAO {

    private Connection connection;

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Course course) throws SQLException {
        String sql = "INSERT INTO course (name, description) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.executeUpdate();
        }
    }

    public Course read(int courseId) throws SQLException {
        String sql = "SELECT * FROM course WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    return new Course(courseId, name, description);
                } else {
                    return null;
                }
            }
        }
    }

    public void update(Course course) throws SQLException {
        String sql = "UPDATE course SET name=?, description=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int courseId) throws SQLException {
        String sql = "DELETE FROM course WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.executeUpdate();
        }
    }
}
