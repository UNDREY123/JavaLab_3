package ru.Chuvyzgalov.humans;

public final class Name {
    private final String surname;
    private final String name;
    private final String patronymic;

    public Name(String name) {
        this(null, name, null);
    }

    public Name(String name, String surname) {
        this(surname, name, null);
    }

    public Name(String surname, String name, String patronymic) {
        if (isEmpty(surname) && isEmpty(name) && isEmpty(patronymic)) {
            throw new IllegalArgumentException("Хотя бы один из параметров имени должен быть задан");
        }
        if (!checkString(name)) {
            throw new IllegalArgumentException("Некорректное личное имя");
        }
        if (!checkString(surname)) {
            throw new IllegalArgumentException("Некорректная фамилия");
        }
        if (!checkString(patronymic)) {
            throw new IllegalArgumentException("Некорректное отчество");
        }

        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    private boolean isEmpty(String val) {
        return val == null || val.trim().isEmpty();
    }

    private boolean checkString(String val) {
        if (val == null) return true;
        return !val.trim().isEmpty() && val.matches("^[А-ЯЁ][а-яё]*$");
    }

    public String getSurname() { return surname; }
    public String getName() { return name; }
    public String getPatronymic() { return patronymic; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (surname != null && !surname.trim().isEmpty()) sb.append(surname).append(" ");
        if (name != null && !name.trim().isEmpty()) sb.append(name).append(" ");
        if (patronymic != null && !patronymic.trim().isEmpty()) sb.append(patronymic);
        return sb.toString().trim();
    }
}