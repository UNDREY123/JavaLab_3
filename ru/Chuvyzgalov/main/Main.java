package ru.Chuvyzgalov.main;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Возведение в степень: чтобы его выполнить, запустите программу с двумя целыми числами X и Y");
        } else {
            try {
                double result = Power.power(args[0], args[1]);
                System.out.println(args[0] + " в степени " + args[1] + " = " + result);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Оба параметра должны быть целыми числами");
            }
        }
        System.out.println();

        Tasks task = new Tasks();
        task.start();
    }
}