package ru.Chuvyzgalov.another;

import java.util.Random;

public class Secret {
    private static final Random random = new Random();

    private final String text;
    private final String ownerName;
    private final int orderNumber;
    private Secret previous;
    private Secret next;

    public Secret(String ownerName, String text) {
        if (isEmpty(ownerName)) throw new IllegalArgumentException("Имя хранителя не может быть пустым");
        if (isEmpty(text)) throw new IllegalArgumentException("Текст секрета не может быть пустым");
        this.ownerName = ownerName;
        this.text = text;
        this.orderNumber = 1;
        System.out.println(ownerName + " узнал секрет: " + text);
    }

    public Secret(Secret secret, String ownerName) {
        if (secret == null) throw new IllegalArgumentException("Секрет не задан");
        if (isEmpty(ownerName)) throw new IllegalArgumentException("Имя хранителя не может быть пустым");
        if (secret.next != null) throw new IllegalStateException("Этот секрет уже рассказан другому человеку");

        System.out.println(secret.ownerName + " сказал что " + secret.text);
        this.text = addRandomSymbols(secret.text);
        this.ownerName = ownerName;
        this.orderNumber = secret.orderNumber + 1;
        this.previous = secret;
        secret.next = this;
    }

    private boolean isEmpty(String val) {
        return val == null || val.trim().isEmpty();
    }

    private String addRandomSymbols(String oldText) {
        StringBuilder sb = new StringBuilder(oldText);
        int n = oldText.length() / 10;
        int count = random.nextInt(n + 1);
        for (int i = 0; i < count; i++) {
            int position = random.nextInt(sb.length() + 1);
            char symbol = (char) ('а' + random.nextInt(32));
            sb.insert(position, symbol);
        }
        return sb.toString();
    }

    public int getOrderNumber() { return orderNumber; }

    public int getFollowersCount() {
        int count = 0;
        Secret current = next;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    private Secret getRelative(int n) {
        if (n == 0) throw new IllegalArgumentException("Номер не может быть равен нулю");
        Secret current = this;
        for (int i = 0; i < Math.abs(n); i++) {
            current = (n > 0) ? current.next : current.previous;
            if (current == null) throw new IllegalArgumentException("Такого хранителя секрета не существует");
        }
        return current;
    }

    public String getKeeperName(int n) { return getRelative(n).ownerName; }

    public int getLengthDifference(int n) { return text.length() - getRelative(n).text.length(); }

    public String getOwnerName() { return ownerName; }
    public Secret getPrevious() { return previous; }
    public Secret getNext() { return next; }

    @Override
    public String toString() {
        return ownerName + ": это секрет!";
    }
}