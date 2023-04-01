package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.foxminded.nikasgig.sqljdbcschool.exception.DataProcessingException;
import ua.foxminded.nikasgig.sqljdbcschool.model.Group;

public class GroupDAO {

    public void create(Group group) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO groups (name) VALUES (?)")) {
            statement.setString(1, group.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Data processing error occurred", e);
        }
    }

    public Group read(int groupId) {
        Group group = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM groups WHERE id = ?")) {
            statement.setInt(1, groupId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                group = new Group(result.getInt("id"), result.getString("name"));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Data processing error occurred", e);
        }
        return group;
    }

    public List<Group> readList(int number) {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM groups WHERE group_id IN (SELECT group_id FROM students "
                                + "GROUP BY group_id HAVING COUNT(*) <= ?)")) {
            statement.setInt(1, number);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("group_id");
                String name = resultSet.getString("group_name");
                Group group = new Group(id, name);
                groups.add(group);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Data processing error occurred", e);
        }
        return groups;
    }

    public void update(Group group) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE groups SET name = ? WHERE id = ?")) {
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Data processing error occurred", e);
        }
    }

    public void delete(int groupId) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM groups WHERE id = ?")) {
            statement.setInt(1, groupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Data processing error occurred", e);
        }
    }
}
