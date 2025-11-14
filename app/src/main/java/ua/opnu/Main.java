package ua.opnu;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.reflect.Array;
import java.util.function.Predicate;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n--------- Тестування Завдання 1 ---------");

        // 1. Порожнє значення (наприклад, у користувача немає по-батькові)
        MyOptional<String> middleName = new MyOptional<>();
        System.out.println(middleName); // MyOptional[empty]
        System.out.println("isPresent: " + middleName.isPresent()); // false
        System.out.println("orElse: " + middleName.orElse("немає")); // "немає"

        // 2. Заповнене значення (наприклад, логін користувача)
        MyOptional<String> username = new MyOptional<>("admin");
        System.out.println(username); // MyOptional[value=admin]
        System.out.println("isPresent: " + username.isPresent()); // true
        System.out.println("get(): " + username.get()); // "admin"
        System.out.println("orElse: " + username.orElse("guest")); // "admin"

        // 3. Перевіримо, що get() на порожньому об'єкті кидає помилку
        try {
            String test = middleName.get(); // має кинути IllegalStateException
            System.out.println("unexpected: " + test);
        } catch (IllegalStateException ex) {
            System.out.println("Очікуваний виняток: " + ex.getMessage());
        }

        // 4. Перевіримо, що конструктор не приймає null
        try {
            MyOptional<String> broken = new MyOptional<>(null);
            System.out.println("unexpected: " + broken);
        } catch (IllegalArgumentException ex) {
            System.out.println("Правильно не дозволив null: " + ex.getMessage());
        }

        System.out.println("\n--------- Тестування Завдання 2 ---------");

        List<BookData> books = new ArrayList<>();
        books.add(new BookData("Java: A Beginner's Guide", "Herbert Schildt", 150, 600));
        books.add(new BookData("Effective Java", "Joshua Bloch", 300, 1425));
        books.add(new BookData("Clean Code", "Robert Martin", 500, 2375));
        books.add(new BookData("Head First Java", "Kathy Sierra", 250, 1000));
        books.add(new BookData("The C++ Programming Language", "Bjarne Stroustrup", 100, 350));

        System.out.println("Список до сортування:");
        books.forEach(System.out::println);

        Collections.sort(books);

        System.out.println("\nСписок після сортування (вищий рейтинг перший):");
        books.forEach(System.out::println);

        System.out.println("\n--------- Тестування Завдання 3 ---------");

        Printer myPrinter = new Printer();
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};
        myPrinter.printArray(intArray);
        myPrinter.printArray(stringArray);

        System.out.println("\n--------- Тестування Завдання 4 ---------");

        Main mainInstance = new Main();

        String[] words = {"Apple", "Banana", "Avocado", "Cherry"};
        Predicate<String> startsWithA = (s) -> s.startsWith("A");
        String[] aWords = mainInstance.filter(words, startsWithA);
        System.out.println("Слова на 'A': " + Arrays.toString(aWords));

        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};
        Predicate<Integer> isEven = (n) -> n % 2 == 0;
        Integer[] evenNumbers = mainInstance.filter(numbers, isEven);
        System.out.println("Парні числа: " + Arrays.toString(evenNumbers));

        System.out.println("\n--------- Тестування Завдання 5 ---------");

        String searchStr = "Hello";
        System.out.println("Масив рядків " + Arrays.toString(stringArray) + " містить 'Hello': " + contains(stringArray, searchStr)); // true
        System.out.println("Масив рядків " + Arrays.toString(stringArray) + " містить 'Ruby': " + contains(stringArray, "Ruby")); // false

        Integer searchInt = 3;
        System.out.println("Масив чисел " + Arrays.toString(intArray) + " містить 3: " + contains(intArray, searchInt)); // true
        System.out.println("Масив чисел " + Arrays.toString(intArray) + " містить 999: " + contains(intArray, 999)); // false

        System.out.println("\n--------- Тестування Завдання 6 ---------");

        System.out.println("Тестуємо GenericTwoTuple:");

        GenericTwoTuple<String, Integer> studentGrade = new GenericTwoTuple<>("Петро Іванов", 12);
        System.out.println("Кортеж (2): " + studentGrade);
        System.out.println("Студент: " + studentGrade.first);
        System.out.println("Оцінка: " + studentGrade.second);

        System.out.println("\nТестуємо GenericThreeTuple:");

        GenericThreeTuple<String, String, Integer> bookInfo =
                new GenericThreeTuple<>("1984", "Джордж Орвелл", 1949);

        System.out.println("Кортеж (3): " + bookInfo);
        System.out.println("Назва: " + bookInfo.getFirst());
        System.out.println("Автор: " + bookInfo.getSecond());
        System.out.println("Рік: " + bookInfo.three);
    }

    public <T> T[] filter(T[] input, Predicate<T> p) {

        T[] result = (T[]) Array.newInstance(input.getClass().getComponentType(), input.length);

        int counter = 0;
        for (T item : input) {
            if (p.test(item)) {
                result[counter] = item;
                counter++;
            }
        }

        return Arrays.copyOfRange(result, 0, counter);
    }

    public static <T extends Comparable<T>, V extends T> boolean contains(T[] array, V element) {
        if (array == null) {
            return false;
        }

        for (T item : array) {
            if (Objects.equals(item, element)) {
                return true;
            }
        }

        return false;
    }
}
