package org.example;

import java.util.Random;

public class Main {

  public static void main(String[] args) {
    var myThreadPool = new MyThreadPool(4);
    myThreadPool.printThreadsState();
    for (var i = 0; i < 10; i++) {
      int finalI = i;
      myThreadPool.execute(() -> {
        try {
          var sleepTime = new Random().nextInt(10);
          System.out.println(
              "Задача " + finalI + " выполняется в течении " + sleepTime + " секунд.");
          Thread.sleep(sleepTime * 1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    }
    myThreadPool.printThreadsState();

    myThreadPool.shutdown();
    try {
      myThreadPool.execute(() -> {
        try {
          var sleepTime = new Random().nextInt(15) + 1;
          Thread.sleep(sleepTime * 1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    myThreadPool.printThreadsState();

    try {
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    myThreadPool.printThreadsState();
  }

}
