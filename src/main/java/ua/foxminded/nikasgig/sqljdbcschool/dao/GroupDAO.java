package ua.foxminded.nikasgig.sqljdbcschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.foxminded.nikasgig.sqljdbcschool.model.Group;

public class GroupDAO {
    
    private Connection connection;

    public GroupDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Group group) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO groups (name) VALUES (?)")) {
            statement.setString(1, group.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Group read(int groupId) {
        Group group = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM groups WHERE id = ?")) {
            statement.setInt(1, groupId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                group = new Group(result.getInt("id"), result.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public void update(Group group) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE groups SET name = ? WHERE id = ?")) {
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int groupId) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM groups WHERE id = ?")) {
            statement.setInt(1, groupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
