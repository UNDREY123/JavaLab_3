package ru.Chuvyzgalov.birds;

import java.util.Random;

public class Cuckoo extends Bird {
    private static final Random random = new Random();

    public Cuckoo() {
        super("Кукушка");
    }

    @Override
    public void sing() {
        int count = 1 + random.nextInt(10);
        for (int i = 0; i < count; i++) {
            System.out.println("ку-ку");
        }
    }
}