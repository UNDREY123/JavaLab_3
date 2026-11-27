package ru.Chuvyzgalov.another;

import java.util.Scanner;

public class Getter {
    private final Scanner scanner;

    public Getter(Scanner scanner) {
        this.scanner = scanner;
    }

    private int readInt() {
        int num = scanner.nextInt();
        if (scanner.hasNextLine()) scanner.nextLine();
        return num;
    }

    public int getInt() {
        System.out.print("Введите целое число: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка! Ожидалось целое число");
            scanner.next();
            System.out.print("Введите целое число: ");
        }
        return readInt();
    }

    public int getInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка! Ожидалось целое число");
            scanner.next();
            System.out.print(message);
        }
        return readInt();
    }

    public int getInt(int min_val, int max_val) {
        while (true) {
            int num;
            System.out.print("Введите целое число: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка! Ожидалось целое число");
                scanner.next();
            } else {
                num = readInt();
                if (num < min_val)
                    System.out.println("Ошибка! Ожидалось число, большее или равное " + min_val);
                else if (num > max_val)
                    System.out.println("Ошибка! Ожидалось число, меньшее или равное " + max_val);
                else return num;
            }
        }
    }

    public int getInt(int min_val, int max_val, String message) {
        while (true) {
            int num;
            System.out.print(message);
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка! Ожидалось целое число");
                scanner.next();
            } else {
                num = readInt();
                if (num < min_val)
                    System.out.println("Ошибка! Ожидалось число, большее или равное " + min_val);
                else if (num > max_val)
                    System.out.println("Ошибка! Ожидалось число, меньшее или равное " + max_val);
                else return num;
            }
        }
    }

    public int getInt(int min_val, int max_val, boolean have_min, boolean have_max) {
        while (true) {
            int num;
            System.out.print("Введите целое число: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка! Ожидалось целое число");
                scanner.next();
            } else {
                num = readInt();
                if (num < min_val && have_min)
                    System.out.println("Ошибка! Ожидалось число, большее или равное " + min_val);
                else if (num > max_val && have_max)
                    System.out.println("Ошибка! Ожидалось число, меньшее или равное " + max_val);
                else return num;
            }
        }
    }

    public int getInt(int min_val, int max_val, boolean have_min, boolean have_max, String message) {
        while (true) {
            int num;
            System.out.print(message);
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка! Ожидалось целое число");
                scanner.next();
            } else {
                num = readInt();
                if (num < min_val && have_min)
                    System.out.println("Ошибка! Ожидалось число, большее или равное " + min_val);
                else if (num > max_val && have_max)
                    System.out.println("Ошибка! Ожидалось число, меньшее или равное " + max_val);
                else return num;
            }
        }
    }

    public float getFloat() {
        System.out.print("Введите число: ");
        while (!scanner.hasNextFloat()) {
            System.out.println("Ошибка! Ожидалось число");
            scanner.next();
            System.out.print("Введите число: ");
        }
        return scanner.nextFloat();
    }

    public float getFloat(String message) {
        System.out.print(message);
        while (!scanner.hasNextFloat()) {
            System.out.println("Ошибка! Ожидалось число");
            scanner.next();
            System.out.print(message);
        }
        return scanner.nextFloat();
    }

    public double getDouble() {
        System.out.print("Введите число: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Ошибка! Ожидалось число");
            scanner.next();
            System.out.print("Введите число: ");
        }
        return scanner.nextDouble();
    }

    public double getDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.println("Ошибка! Ожидалось число");
            scanner.next();
            System.out.print(message);
        }
        return scanner.nextDouble();
    }

    public long getLong() {
        System.out.print("Введите число: ");
        while (!scanner.hasNextLong()) {
            System.out.println("Ошибка! Ожидалось число");
            scanner.next();
            System.out.print("Введите число: ");
        }
        return scanner.nextLong();
    }

    public long getLong(String message) {
        System.out.print(message);
        while (!scanner.hasNextLong()) {
            System.out.println("Ошибка! Ожидалось число");
            scanner.next();
            System.out.print(message);
        }
        return scanner.nextLong();
    }
}