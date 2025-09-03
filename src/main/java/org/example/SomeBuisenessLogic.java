package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SomeBuisenessLogic implements CommandLineRunner {

  private final UserService userService;

  @Override
  public void run(String... args) {
    userService.getUserByName("Тестовое имя 1");
    userService.getAllUsers();

    var user1Id = userService.createUserWithName("Тестовое имя 4").getId();
    var user1 = userService.getUser(user1Id);

    user1.setUserName("Новое имя");
    userService.updateUser(user1);
    userService.getUser(user1Id);

    userService.deleteUser(user1Id);
  }
}
