package org.example.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.example.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

  private final DataSource dataSource;

  public UserDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Long createUser(String username) {
    Long createdUserId = null;
    var sql = "INSERT INTO users (username) VALUES (?) RETURNING id";
    try (var connection = dataSource.getConnection();
        var statement = connection.prepareStatement(sql)) {
      statement.setString(1, username);
      var resultSet = statement.executeQuery();
      if (resultSet.next()) {
        System.out.println("Пользователь с именем " + username + " создан.");
        createdUserId = resultSet.getLong("id");
      } else {
        System.out.println("Пользователь с именем " + username + " не создан.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return createdUserId;
  }

  public User getUser(Long id) {
    User user = null;
    var sql = "SELECT * FROM users WHERE id = ?";
    try (var connection = dataSource.getConnection();
        var statement = connection.prepareStatement(sql)) {
      statement.setLong(1, id);
      try (var resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          user = new User(resultSet.getLong("id"), resultSet.getString("username"));
        } else {
          System.out.println("Пользователь с ид " + id + " не найден.");
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return user;
  }

  public List<User> getAllUsers() {
    var users = new ArrayList<User>();

    var sql = "SELECT * FROM users";
    try (var connection = dataSource.getConnection();
        var statement = connection.prepareStatement(sql)) {
      try (var resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          users.add(new User(resultSet.getLong("id"), resultSet.getString("username")));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return users;
  }

  public void updateUser(User user) {
    var sql = "UPDATE users set username = ? WHERE id = ?";
    try (var connection = dataSource.getConnection();
        var statement = connection.prepareStatement(sql)) {
      statement.setString(1, user.getUserName());
      statement.setLong(2, user.getId());
      statement.executeUpdate();
      System.out.println("Пользователь с id " + user.getId() + " обновлен.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteUser(Long id) {
    var sql = "DELETE FROM users WHERE id = ?";
    try (var connection = dataSource.getConnection();
        var statement = connection.prepareStatement(sql)) {
      statement.setLong(1, id);
      statement.executeUpdate();
      System.out.println("Пользователь с id " + id + " удален.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
