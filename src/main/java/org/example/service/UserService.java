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

  private final UserRepository userRepository;

  public User createUserWithName(String username) {
    var user = userRepository.save(User.builder().userName(username).build());
    log.info("Сохранен пользователь {}", user);

    return user;
  }

  public User getUser(Long id) {
    var user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    log.info("Получен пользователь {}", user);

    return user;
  }

  public User getUserByName(String name) {
    var user = userRepository.findByUserName(name).orElseThrow(EntityNotFoundException::new);
    log.info("Получен пользователь {}", user);

    return user;
  }

  public List<User> getAllUsers() {
    var users = userRepository.findAll();
    log.info("Получены все пользователи: {}", users);

    return users;
  }

  public User updateUser(User user) {
    var updatedUser = userRepository.save(user);
    log.info("Обновлен пользователь: {}", updatedUser);

    return updatedUser;
  }

  public void deleteUser(Long id) {
    log.info("Удаляется пользователь с id: {}", id);
    userRepository.deleteById(id);
  }

  public void deleteAll() {
    log.info("Удаляется все пользователи");
    userRepository.deleteAll();
  }

  public void getProductsByUser(Long userId) {
    log.info("Получение всех продуктов для пользователя {}", userId);
    userRepository.deleteAll();
  }
}
