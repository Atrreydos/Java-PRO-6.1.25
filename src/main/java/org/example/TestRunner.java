package org.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.example.annotations.AfterSuite;
import org.example.annotations.BeforeSuite;
import org.example.annotations.Test;

public class TestRunner {

  public static void runTests(Class<TestClass> testClass) throws Exception {
    System.out.println("Запуск runTests для класса " + testClass.getName());
    var declaredMethods = testClass.getDeclaredMethods();
    checkMethods(declaredMethods);

    Constructor<TestClass> constructor = testClass.getConstructor();
    TestClass testClassObject = constructor.newInstance();

    invokeMethodWithAnnotation(testClassObject, BeforeSuite.class);
    invokeMethodsWithTestAnnotation(testClassObject);
    invokeMethodWithAnnotation(testClassObject, AfterSuite.class);
  }

  private static void checkMethods(Method[] methods) {
    System.out.println("Проверка методов.");
    int beforeSuiteCount = 0;
    int afterSuiteCount = 0;
    for (Method method : methods) {
      if (method.isAnnotationPresent(BeforeSuite.class)) {
        beforeSuiteCount++;
      }
      if (method.isAnnotationPresent(AfterSuite.class)) {
        afterSuiteCount++;
      }
    }

    System.out.printf("beforeSuiteCount = %d, afterSuiteCount = %d\n", beforeSuiteCount,
        afterSuiteCount);
    if (beforeSuiteCount > 1) {
      throw new RuntimeException(
          "Недопустимое количество методов с аннотацией BeforeSuite = " + beforeSuiteCount);
    }
    if (afterSuiteCount > 1) {
      throw new RuntimeException(
          "Недопустимое количество методов с аннотацией AfterSuite = " + afterSuiteCount);
    }
    System.out.println("Проверка методов прошла успешно.");
  }

  private static void invokeMethodWithAnnotation(TestClass testClassObject,
      Class<? extends Annotation> annotationClass)
      throws Exception {
    for (Method method : testClassObject.getClass().getDeclaredMethods()) {
      if (method.isAnnotationPresent(annotationClass)) {
        method.invoke(testClassObject);
      }
    }
  }

  private static void invokeMethodsWithTestAnnotation(TestClass testClassObject) {
    var testMethods = Arrays.stream(testClassObject.getClass().getDeclaredMethods())
        .filter(testMethod -> testMethod.isAnnotationPresent(Test.class))
        .sorted((m1, m2) ->
            m2.getAnnotation(Test.class).priority() - m1.getAnnotation(Test.class).priority())
        .toList();

    testMethods.forEach(method -> {
      try {
        method.invoke(testClassObject);
      } catch (IllegalAccessException | InvocationTargetException e) {
        throw new RuntimeException(e);
      }
    });
  }
}
