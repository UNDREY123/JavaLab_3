package ru.Chuvyzgalov.birds;

public abstract class Bird {
    private final String name;

    protected Bird(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public abstract void sing();

    @Override
    public String toString() {
        return "Птица: " + name;
    }
}