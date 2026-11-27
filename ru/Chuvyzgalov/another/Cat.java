package ru.Chuvyzgalov.another;

import ru.Chuvyzgalov.main.Meowable;

public class Cat implements Meowable {
    public String name;

    public Cat() {
        name = "";
    }

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void meow() {
        System.out.println(name + ": мяу!");
    }

    public void meow(int count) {
        System.out.print(name + ": ");
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                if (i == 0) System.out.print("Мяу");
                else System.out.print("-мяу");
            }
            System.out.println("!");
        } else System.out.println("...");
    }

    @Override
    public String toString() {
        return "Кот: " + name;
    }

}