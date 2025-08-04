package org.example;

import org.example.annotations.AfterSuite;
import org.example.annotations.BeforeSuite;
import org.example.annotations.Test;

public class TestClass {

  @Test(priority = 1)
  public void method1() {
    System.out.println("Метод с приоритетом 1");
  }

  @Test(priority = 9)
  public void method9() {
    System.out.println("Метод с приоритетом 9");
  }

  @Test
  public void method5() {
    System.out.println("Метод с приоритетом 5 по умолчанию");
  }

  @Test(priority = 3)
  public void method3() {
    System.out.println("Метод с приоритетом 3");
  }

  @Test(priority = 4)
  public void method4() {
    System.out.println("Метод с приоритетом 4");
  }

  @BeforeSuite
  public static void methodBefore() {
    System.out.println("method before");
  }

//  @BeforeSuite
//  public void methodBefore2() {
//    System.out.println("method before 2");
//  }

  @AfterSuite
  public static void methodAfter() {
    System.out.println("method after");
  }

//  @AfterSuite
//  public void methodAfter2() {
//    System.out.println("method after2");
//  }

  public void methodWithoutAnnotation() {
    System.out.println("method without annotation");
  }
}
