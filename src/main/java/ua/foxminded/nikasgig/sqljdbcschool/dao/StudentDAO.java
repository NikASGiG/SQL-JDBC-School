package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.foxminded.nikasgig.sqljdbcschool.model.Student;

public class StudentDAO {

    public int getMax() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("(SELECT MAX(student_id) FROM students)")) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1) + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Student> findStudentsByCourseName(String courseName) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void create(Student student) {
        try (Connection connection = ConnectionUtil.getConnection()) {
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
        try (Connection connection = ConnectionUtil.getConnection()) {
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
        try (Connection connection = ConnectionUtil.getConnection()) {
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

    public void deleteRelations(int studentId) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("DELETE FROM student_course WHERE student_id = " + studentId)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int studentId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE student_id = ?");
            statement.setInt(1, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
