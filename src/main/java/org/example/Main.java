package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    var testList = Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13);
    System.out.println(findThirdLargest(testList));
    System.out.println(findThirdLargestUnique(testList));

    var director = new Employee("Директор", 70, Position.DIRECTOR);
    var engineer1 = new Employee("Инженер 1", 30, Position.ENGINEER);
    var engineer2 = new Employee("Инженер 2", 54, Position.ENGINEER);
    var engineer3 = new Employee("Инженер 3", 18, Position.ENGINEER);
    var engineer4 = new Employee("Инженер 4", 35, Position.ENGINEER);
    var employees = Arrays.asList(director, engineer1, engineer2, engineer3, engineer4);
    findThreeOldestEngineers(employees).forEach(System.out::println);
    System.out.println(getAverageEngineersAge(employees));

    System.out.println(findMostBigString(Arrays.asList("слово", "огромное_слово", "просто_слово")));
    System.out.println(countStrings("это просто текст текст не просто текст да да да не нет"));

    printWordsSortedByLength(Arrays.asList("а", "вбв", "ук", "ививи", "цукцук", "абв", "ббв"));

    String[] lines = {
        "раз два три самоедлинноеслово пять",
        "пам пам пурум пурум пам",
        "раз самоедлинноеслово пу пу пу"
    };
    System.out.println(findMostBigString(lines));
  }

  /*Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)*/
  public static Integer findThirdLargest(List<Integer> testList) {
    return testList.stream()
        .sorted(Comparator.reverseOrder())
        .skip(2)
        .findFirst()
        .orElse(null);
  }

  /*Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9
  , в отличие от прошлой задачи здесь разные 10 считает за одно число)*/
  public static Integer findThirdLargestUnique(List<Integer> testList) {
    return testList.stream()
        .distinct()
        .sorted(Comparator.reverseOrder())
        .skip(2)
        .findFirst()
        .orElse(null);
  }

  /*Имеется список объектов типа Сотрудник (имя, возраст, должность),
  необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста*/
  public static List<String> findThreeOldestEngineers(List<Employee> employees) {
    return employees.stream()
        .filter(employee -> Position.ENGINEER.equals(employee.position()))
        .sorted(Comparator.comparingInt(Employee::age).reversed())
        .map(Employee::name)
        .limit(3)
        .toList();
  }

  /*Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»*/
  public static double getAverageEngineersAge(List<Employee> employees) {
    return employees.stream()
        .filter(employee -> Position.ENGINEER.equals(employee.position()))
        .mapToDouble(Employee::age)
        .average()
        .orElse(0);
  }

  /*Найдите в списке слов самое длинное*/
  public static String findMostBigString(List<String> strings) {
    return strings.stream()
        .max(Comparator.comparingInt(String::length))
        .orElse(null);
  }

  /*Имеется строка с набором слов в нижнем регистре, разделенных пробелом.
  Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке*/
  public static Map<String, Long> countStrings(String inputString) {
    return Arrays.stream(inputString.split(" "))
        .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
  }

  /*Отпечатайте в консоль строки из списка в порядке увеличения длины слова,
  если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок*/
  public static void printWordsSortedByLength(List<String> strings) {
    strings.stream()
        .sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()))
        .forEach(System.out::println);
  }

  /*Имеется массив строк, в каждой из которых лежит набор из 5 слов, разделенных пробелом,
  найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них*/
  public static String findMostBigString(String[] lines) {
    return Arrays.stream(lines)
        .flatMap(line -> Arrays.stream(line.split(" ")))
        .max(Comparator.comparingInt(String::length))
        .orElse(null);
  }
}
