package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  public User createUserWithName(String username) {
    var user = repository.save(User.builder().userName(username).build());
    log.info("Сохранен пользователь {}", user);

    return user;
  }

  public User getUser(Long id) {
    var user = repository.findById(id).orElseThrow(EntityNotFoundException::new);
    log.info("Получен пользователь {}", user);

    return user;
  }

  public User getUserByName(String name) {
    var user = repository.findByUserName(name).orElseThrow(EntityNotFoundException::new);
    log.info("Получен пользователь {}", user);

    return user;
  }

  public List<User> getAllUsers() {
    var users = repository.findAll();
    log.info("Получены все пользователи: {}", users);

    return users;
  }

  public User updateUser(User user) {
    var updatedUser = repository.save(user);
    log.info("Обновлен пользователь: {}", updatedUser);

    return updatedUser;
  }

  public void deleteUser(Long id) {
    log.info("Удаляется пользователь с id: {}", id);
    repository.deleteById(id);
  }

  public void deleteAll() {
    log.info("Удаляется все пользователи");
    repository.deleteAll();
  }
}
