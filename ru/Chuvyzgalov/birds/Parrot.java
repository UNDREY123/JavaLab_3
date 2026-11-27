package ru.Chuvyzgalov.birds;

import java.util.Random;

public class Parrot extends Bird {
    private static final Random random = new Random();

    private final String text;

    public Parrot(String text) {
        super("Попугай");
        if (text == null || text.trim().isEmpty()) throw new IllegalArgumentException("Попугаю нужно указать текст для пения");
        this.text = text;
    }

    @Override
    public void sing() {
        int n = 1 + random.nextInt(text.length());
        System.out.println(text.substring(0, n));
    }

    public String getText() { return text; }

    @Override
    public String toString() {
        return super.toString() + " (знает текст: " + text + ")";
    }
}