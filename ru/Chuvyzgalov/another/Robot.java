package ru.Chuvyzgalov.another;

import ru.Chuvyzgalov.main.Meowable;

public class Robot implements Meowable {
    private final String name;

    public Robot(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Имя робота не может быть пустым");
        this.name = name;
    }

    @Override
    public void meow() {
        System.out.println("Робот " + name + ": мяу-мяу!");
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return "Робот: " + name;
    }
}