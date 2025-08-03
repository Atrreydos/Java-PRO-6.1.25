package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.example.annotations.AfterSuite;
import org.example.annotations.BeforeSuite;
import org.example.annotations.Test;

public class TestRunner {

  public static void runTests(Class<TestClass> testClass) throws Exception {
    System.out.println("Запуск runTests для класса " + testClass.getName());
    var methods = testClass.getDeclaredMethods();
    List<Method> beforeSuiteMethods = new ArrayList<>();
    List<Method> afterSuiteMethods = new ArrayList<>();
    List<Method> testMethods = new ArrayList<>();

    for (Method method : methods) {
      if (method.isAnnotationPresent(BeforeSuite.class)) {
        beforeSuiteMethods.add(method);
      }
      if (method.isAnnotationPresent(AfterSuite.class)) {
        afterSuiteMethods.add(method);
      }
      if (method.isAnnotationPresent(Test.class)) {
        testMethods.add(method);
      }
    }

    checkMethods(beforeSuiteMethods, BeforeSuite.class.getName());
    checkMethods(afterSuiteMethods, AfterSuite.class.getName());

    Constructor<TestClass> constructor = testClass.getConstructor();
    TestClass testClassObject = constructor.newInstance();

    invokeMethods(testClassObject, beforeSuiteMethods);
    invokeTestMethods(testClassObject, testMethods);
    invokeMethods(testClassObject, afterSuiteMethods);
  }

  private static void checkMethods(List<Method> methods, String annotationName) {
    System.out.printf("Проверка методов с аннотацией %s. Количество методов = %d.\n",
        annotationName,
        methods.size());
    if (methods.size() > 1) {
      throw new RuntimeException(
          String.format("Недопустимое количество: %d методов с аннотацией %s.", methods.size(),
              annotationName));
    }

    methods.forEach(method -> {
      if (Modifier.isStatic(method.getModifiers())) {
        throw new RuntimeException(
            String.format("Аннотация %s проставлена над статическим методом %s.", annotationName,
                method.getName()));
      }
    });

    System.out.printf("Проверка методов с аннотацией %s прошла успешно.\n", annotationName);
  }

  private static void invokeMethods(TestClass testClassObject,
      List<Method> methods) {
    methods.forEach(method -> {
      try {
        method.invoke(testClassObject);
      } catch (IllegalAccessException | InvocationTargetException e) {
        throw new RuntimeException(e);
      }
    });
  }

  private static void invokeTestMethods(TestClass testClassObject, List<Method> testMethods) {
    testMethods.stream()
        .sorted((m1, m2) ->
            m2.getAnnotation(Test.class).priority() - m1.getAnnotation(Test.class).priority())
        .forEach(method -> {
          try {
            method.invoke(testClassObject);
          } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
          }
        });
  }
}
