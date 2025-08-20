package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyThreadPool {

  private final LinkedList<Runnable> tasks = new LinkedList<>();
  private final List<Thread> threads = new ArrayList<>();

  private final Object monitor = new Object();
  private volatile boolean isShutdown;

  public MyThreadPool(int numThreads) {
    for (int i = 1; i <= numThreads; i++) {
      var thread = new Thread(new MyRunnable());
      thread.start();
      System.out.println(
          "Поток " + thread.getName() + " был инициализирован и запущен.");
      threads.add(thread);
    }
  }

  public void execute(Runnable task) {
    if (isShutdown) {
      throw new IllegalStateException(
          "Пул потоков завершается. Невозможно запустить новые задачи.");
    }

    synchronized (monitor) {
      System.out.println(
          "В очереди " + tasks.size() + " задач. Добавляется новая задача.");
      tasks.add(task);
      monitor.notifyAll();
    }
  }

  public void shutdown() {
    System.out.println("Пул потоков завершается.");
    isShutdown = true;
    synchronized (monitor) {
      monitor.notifyAll();
    }
  }

  private class MyRunnable implements Runnable {

    @Override
    public void run() {
      while (true) {
        Runnable task = null;
        synchronized (monitor) {
          if (!tasks.isEmpty()) {
            System.out.println(
                "В очереди " + tasks.size() + " задач. Поток " + Thread.currentThread().getName()
                    + " берет задачу в работу.");

            task = tasks.remove();
          } else {
            if (isShutdown) {
              System.out.println(
                  "Поток " + Thread.currentThread().getName() + " завершает свою работу.");
              return;
            }

            System.out.println(
                "В очереди нет задач. Поток " + Thread.currentThread().getName()
                    + " ожидает задач.");
            try {
              monitor.wait();
            } catch (InterruptedException e) {
            }
          }
        }

        if (task != null) {
          task.run();
          System.out.println("Поток "
              + Thread.currentThread().getName() + " завершил задачу.");
        }
      }
    }
  }

  public void printThreadsState() {
    for (Thread thread : threads) {
      System.out.println("Состояние потока " + thread.getName() + " : " + thread.getState());
    }
  }
}
