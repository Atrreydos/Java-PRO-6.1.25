package org.example;

import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Main {

  public static void main(String[] args) {
    var context = new AnnotationConfigApplicationContext(Main.class);
    var userService = context.getBean(UserService.class);

    var user1Id = userService.createUser("Тестовое имя");
    userService.createUser("Тестовое имя 2");
    userService.createUser("Тестовое имя 3");

    var user1 = userService.getUser(user1Id);
    System.out.println(user1);

    System.out.println(userService.getAllUsers());

    user1.setUserName("Новое имя");
    userService.updateUser(user1);
    System.out.println(userService.getUser(user1Id));

    userService.deleteUser(user1Id);
    System.out.println(userService.getUser(user1Id));
  }
}
