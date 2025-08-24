package org.example.service;

import java.util.List;
import org.example.entity.User;
import org.example.repository.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserDao repository;

  public UserService(UserDao repository) {
    this.repository = repository;
  }

  public Long createUser(String username) {
    return repository.createUser(username);
  }

  public User getUser(Long id) {
    return repository.getUser(id);
  }

  public List<User> getAllUsers() {
    return repository.getAllUsers();
  }

  public void updateUser(User user) {
    repository.updateUser(user);
  }

  public void deleteUser(Long id) {
    repository.deleteUser(id);
  }
}
