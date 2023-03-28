package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.foxminded.nikasgig.sqljdbcschool.model.Student;

public class StudentDAO {

    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Student student) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO students (student_id, first_name, last_name) VALUES (?, ?, ?)");
            statement.setInt(1, student.getId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student read(int studentId) {
        Student student = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students WHERE id = ?");
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = new Student(resultSet.getInt("id"), resultSet.getInt("group_id"),
                        resultSet.getString("first_name"), resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void update(Student student) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE students SET group_id = ?, first_name = ?, last_name = ? WHERE id = ?");
            statement.setInt(1, student.getGroupId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setInt(4, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int studentId) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE student_id = ?");
            statement.setInt(1, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
