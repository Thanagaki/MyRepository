package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Statement statement = Util.getMySQLConnection().createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(45) NOT NULL, " +
                "lastname VARCHAR(45) NOT NULL, " +
                "age TINYINT NOT NULL, " +
                "PRIMARY KEY (id))");
        statement.close();

    }

    public void dropUsersTable() throws SQLException {
        Statement statement = Util.getMySQLConnection().createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS Users");
        statement.close();

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = Util.getMySQLConnection().prepareStatement
                ("INSERT INTO users (name, lastname, age) Values (?, ?, ?)");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setInt(3, age);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = Util.getMySQLConnection().
                prepareStatement("DELETE FROM users WHERE id = ?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();


    }

    public List<User> getAllUsers() throws SQLException {
        List<User> usersList = new ArrayList<>();
        PreparedStatement preparedStatement = Util.getMySQLConnection().
                prepareStatement("SELECT * FROM users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usersList.add(new User(resultSet.getString("name"),
                    resultSet.getString("lastname"),
                    resultSet.getByte("age")));
        }
        preparedStatement.close();
        System.out.println(usersList);
        return usersList;
    }

    public void cleanUsersTable() throws SQLException {
        PreparedStatement preparedStatement = Util.getMySQLConnection()
                .prepareStatement("DELETE FROM users");
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}




