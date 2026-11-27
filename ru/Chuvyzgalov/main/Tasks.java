package ru.Chuvyzgalov.main;

import java.util.ArrayList;
import java.util.Scanner;

import ru.Chuvyzgalov.birds.Bird;
import ru.Chuvyzgalov.birds.Cuckoo;
import ru.Chuvyzgalov.birds.Parrot;
import ru.Chuvyzgalov.birds.Sparrow;
import ru.Chuvyzgalov.geometry.Point;
import ru.Chuvyzgalov.geometry.Point3D;
import ru.Chuvyzgalov.humans.Human;
import ru.Chuvyzgalov.humans.Name;
import ru.Chuvyzgalov.another.Robot;
import ru.Chuvyzgalov.another.Secret;
import ru.Chuvyzgalov.another.Getter;
import ru.Chuvyzgalov.another.Cat;
import ru.Chuvyzgalov.another.City;

public class Tasks {
    public static void meowAll(Meowable... meowers) {
        for (Meowable meower : meowers) {
            meower.meow();
        }
    }

    private final Scanner scanner = new Scanner(System.in);
    private final Getter getter = new Getter(scanner);
    private final ArrayList<Name> namesList = new ArrayList<>();
    private final ArrayList<Human> humansList = new ArrayList<>();
    private final ArrayList<City> citiesList = new ArrayList<>();
    private final ArrayList<Cat> catsList = new ArrayList<>();
    private final ArrayList<Robot> robotsList = new ArrayList<>();
    private final ArrayList<Point> pointsList = new ArrayList<>();
    private final ArrayList<Secret> secretsList = new ArrayList<>();
    private final ArrayList<Bird> birdsList = new ArrayList<>();

    public void start() {
        mainMenu();
    }

    private void mainMenu() {
        while (true) {
            System.out.println("Категории:");
            System.out.println("1) Люди и имена");
            System.out.println("2) Города");
            System.out.println("3) Коты");
            System.out.println("4) Точки");
            System.out.println("5) Секреты");
            System.out.println("6) Птицы");
            System.out.println("Введите 0 для выхода\n");

            int category = getter.getInt(0, 6, "Введите номер категории: ");
            System.out.println();

            if (category == 0) {
                System.out.println("Работа завершена.");
                break;
            }

            switch (category) {
                case 1 -> humanMenu();
                case 2 -> cityMenu();
                case 3 -> catMenu();
                case 4 -> pointMenu();
                case 5 -> secretMenu();
                case 6 -> birdMenu();
            }
        }
    }

    private void humanMenu() {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1) Добавить имя");
            System.out.println("2) Добавить человека");
            System.out.println("3) Вывести имена");
            System.out.println("4) Вывести людей");
            System.out.println("5) Изменить рост человека");
            System.out.println("6) Вывести данные человека");
            System.out.println("Введите 0 для возвращения в меню\n");

            int action = getter.getInt(0, 6, "Выберите действие: ");
            System.out.println();

            if (action == 0) break;

            switch (action) {
                case 1 -> inputAndAddName();
                case 2 -> addHuman();
                case 3 -> printNames();
                case 4 -> printHumans();
                case 5 -> changeHumanHeight();
                case 6 -> printHumanPedigree();
            }
        }
    }

    private void inputAndAddName() {
        Name nameObj = requestNameFields();
        if (nameObj != null) {
            namesList.add(nameObj);
            System.out.println("Имя успешно добавлено");
        }
    }

    private Name requestNameFields() {
        while (true) {
            System.out.print("Введите фамилию (введите \"--\", чтобы не указывать): ");
            String surname = scanner.nextLine().trim();
            System.out.print("Введите имя (введите \"--\", чтобы не указывать): ");
            String name = scanner.nextLine().trim();
            System.out.print("Введите отчество (введите \"--\", чтобы не указывать): ");
            String patronymic = scanner.nextLine().trim();

            String s = surname.equals("--") ? null : surname;
            String n = name.equals("--") ? null : name;
            String p = patronymic.equals("--") ? null : patronymic;

            try {
                return new Name(s, n, p);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка! " + e.getMessage());
            }
        }
    }

    private void addHuman() {
        System.out.println("Выберите способ задания имени:");
        System.out.println("1 - создать новое");
        System.out.println("2 - выбрать существующее");
        int method = getter.getInt(1, 2, "Выберите способ: ");

        Name humanName = null;
        if (method == 1) {
            humanName = requestNameFields();
        } else {
            if (namesList.isEmpty()) {
                System.out.println("Ошибка! Список имен пуст. Сначала создайте имя");
                return;
            }
            printNames();
            int nameIndex = getter.getInt(1, namesList.size(), "Введите номер имени: ") - 1;
            humanName = namesList.get(nameIndex);
        }

        int height = getter.getInt("Введите рост: ");

        Human father = null;
        if (!humansList.isEmpty()) {
            printHumans();
            System.out.print("Введите номер человека-отца (введите \"--\", чтобы не указывать): ");
            String fatherInput = scanner.nextLine().trim();
            if (!fatherInput.equals("--")) {
                try {
                    int fatherIndex = Integer.parseInt(fatherInput) - 1;
                    if (fatherIndex >= 0 && fatherIndex < humansList.size()) {
                        father = humansList.get(fatherIndex);
                    } else {
                        System.out.println("Ошибка! Такого человека не существует");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка! Введено некорректное значение");
                    return;
                }
            }
        }

        try {
            humansList.add(new Human(humanName, height, father));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка! " + e.getMessage());
            return;
        }
        System.out.println("Человек успешно добавлен");
    }

    private void printNames() {
        if (namesList.isEmpty()) {
            System.out.println("Список имён пуст");
            return;
        }
        for (int i = 0; i < namesList.size(); i++) {
            System.out.println((i + 1) + " - " + namesList.get(i) + (i == namesList.size() - 1 ? "" : ","));
        }
        System.out.println();
    }

    private void printHumans() {
        if (humansList.isEmpty()) {
            System.out.println("Список людей пуст");
            return;
        }
        for (int i = 0; i < humansList.size(); i++) {
            System.out.println((i + 1) + " - " + humansList.get(i) + (i == humansList.size() - 1 ? "" : ","));
        }
        System.out.println();
    }

    private void changeHumanHeight() {
        if (humansList.isEmpty()) {
            System.out.println("Ошибка! Список людей пуст");
            return;
        }
        printHumans();
        int childIndex = getter.getInt(1, humansList.size(), "Введите номер человека, которому хотите изменить рост: ") - 1;

        int height = getter.getInt("Введите новый рост: ");

        try {
            humansList.get(childIndex).setHeight(height);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка! " + e.getMessage());
            return;
        }
        System.out.println("Рост успешно изменен");
    }

    private void printHumanPedigree() {
        if (humansList.isEmpty()) {
            System.out.println("Ошибка! Список людей пуст");
            return;
        }
        printHumans();
        int index = getter.getInt(1, humansList.size(), "Введите номер человека: ") - 1;

        Human current = humansList.get(index);
        StringBuilder tabs = new StringBuilder();
        System.out.println(current);

        while (current.getFather() != null) {
            tabs.append("\t");
            System.out.println(tabs + "Отец: " + current.getFather());
            current = current.getFather();
        }
    }

    private void cityMenu() {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1) Добавить город");
            System.out.println("2) Добавить дорогу");
            System.out.println("3) Вывести города");
            System.out.println("4) Вывести города и дороги");
            System.out.println("5) Удалить город");
            System.out.println("6) Удалить дорогу");
            System.out.println("Введите 0 для возвращения в меню\n");

            int action = getter.getInt(0, 6, "Выберите действие: ");
            System.out.println();

            if (action == 0) break;

            switch (action) {
                case 1 -> addCity();
                case 2 -> addRoadBetweenCities();
                case 3 -> printCitiesOnly();
                case 4 -> printCitiesAndRoads();
                case 5 -> removeCityByName();
                case 6 -> removeRoadBetweenCities();
            }
        }
    }

    private City findCityByName(String cityName) {
        for (City c : citiesList) {
            if (c.name.equalsIgnoreCase(cityName)) return c;
        }
        return null;
    }

    private void addCity() {
        System.out.print("Введите имя города: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Ошибка! Имя города не может быть пустым");
            return;
        }
        if (findCityByName(name) != null) {
            System.out.println("Ошибка! Такой город уже существует");
            return;
        }

        City newCity = new City(name);
        int roadsCount = getter.getInt(0, Integer.MAX_VALUE, "Введите количество дорог: ");

        for (int i = 1; i <= roadsCount; i++) {
            System.out.print("Введите дорогу " + i + " в формате: <имя_города> <стоимость>: ");
            String roadLine = scanner.nextLine().trim();
            String[] parts = roadLine.split("\\s+");

            if (parts.length < 2) {
                System.out.println("Предупреждение! Некорректный формат ввода дороги " + i);
                continue;
            }
            String targetName = parts[0];
            int cost;
            try {
                cost = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Предупреждение! Некорректная стоимость в дороге " + i);
                continue;
            }
            City targetCity = findCityByName(targetName);
            if (targetCity == null) {
                System.out.println("Предупреждение! Невозможно создать дорогу " + i + ", отсутствует город " + targetName);
            } else {
                newCity.addRoad(targetCity, cost);
            }
        }
        citiesList.add(newCity);
        System.out.println("Город успешно добавлен");
    }

    private void addRoadBetweenCities() {
        System.out.print("Введите дорогу в формате: <имя_нач_города> <имя_конеч_города> <стоимость>: ");
        String line = scanner.nextLine().trim();
        String[] parts = line.split("\s+");
        if (parts.length < 3) {
            System.out.println("Ошибка! Некорректный формат ввода");
            return;
        }
        String startName = parts[0];
        String endName = parts[1];
        int cost;
        try {
            cost = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка! Некорректная стоимость дороги");
            return;
        }
        City startCity = findCityByName(startName);
        City endCity = findCityByName(endName);
        if (startCity == null) {
            System.out.println("Ошибка! Невозможно создать дорогу, отсутствует город " + startName);
            return;
        }
        if (endCity == null) {
            System.out.println("Ошибка! Невозможно создать дорогу, отсутствует город " + endName);
            return;
        }
        if (startCity.getRoads().containsKey(endCity)) {
            System.out.println("Ошибка! Такая дорога уже существует");
            return;
        }
        startCity.addRoad(endCity, cost);
        System.out.println("Дорога успешно создана");
    }

    private void printCitiesOnly() {
        if (citiesList.isEmpty()) {
            System.out.println("Список городов пуст");
            return;
        }
        for (int i = 0; i < citiesList.size(); i++) {
            System.out.println(" - " + citiesList.get(i).name + (i == citiesList.size() - 1 ? "" : ","));
        }
        System.out.println();
    }

    private void printCitiesAndRoads() {
        if (citiesList.isEmpty()) {
            System.out.println("Список городов пуст");
            return;
        }
        for (City city : citiesList) {
            System.out.println(city.toString() + "\n");
        }
    }

    private void removeCityByName() {
        System.out.print("Введите имя города для удаления: ");
        String name = scanner.nextLine().trim();
        City toRemove = findCityByName(name);
        if (toRemove == null) {
            System.out.println("Ошибка! Такого города не существует");
            return;
        }
        citiesList.remove(toRemove);
        for (City c : citiesList) {
            c.removeRoad(toRemove);
        }
        System.out.println("Город " + name + " и все связанные с ним дороги удалены");
    }

    private void removeRoadBetweenCities() {
        System.out.print("Введите дорогу для удаления в формате: <имя_нач_города> <имя_конеч_города>: ");
        String line = scanner.nextLine().trim();
        String[] parts = line.split("\s+");
        if (parts.length < 2) {
            System.out.println("Ошибка! Некорректный формат ввода");
            return;
        }
        String startName = parts[0];
        String endName = parts[1];
        City startCity = findCityByName(startName);
        City endCity = findCityByName(endName);
        if (startCity == null || endCity == null || !startCity.getRoads().containsKey(endCity)) {
            System.out.println("Ошибка! Дороги между городами " + startName + " и " + endName + " не существует");
            return;
        }
        startCity.removeRoad(endCity);
        System.out.println("Дорога успешно удалена");
    }

    private void catMenu() {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1) Добавить кота");
            System.out.println("2) Добавить робота");
            System.out.println("3) Помяукать");
            System.out.println("4) Заставить мяукать всех");
            System.out.println("5) Вывести котов и роботов");
            System.out.println("6) Удалить кота");
            System.out.println("7) Удалить робота");
            System.out.println("Введите 0 для возвращения в меню\n");
            int action = getter.getInt(0, 7, "Выберите действие: ");
            System.out.println();
            if (action == 0) break;
            switch (action) {
                case 1 -> addCat();
                case 2 -> addRobot();
                case 3 -> makeCatMeow();
                case 4 -> makeAllMeow();
                case 5 -> printCatsAndRobots();
                case 6 -> removeCatByName();
                case 7 -> removeRobotByName();
            }
        }
    }

    private Cat findCatByName(String catName) {
        for (Cat cat : catsList) {
            if (cat.name.equalsIgnoreCase(catName)) return cat;
        }
        return null;
    }

    private Robot findRobotByName(String robotName) {
        for (Robot robot : robotsList) {
            if (robot.getName().equalsIgnoreCase(robotName)) return robot;
        }
        return null;
    }

    private void addCat() {
        System.out.print("Введите имя кота: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Ошибка! Имя кота не может быть пустым");
            return;
        }
        if (findCatByName(name) != null) {
            System.out.println("Ошибка! Такой кот уже существует");
            return;
        }
        catsList.add(new Cat(name));
        System.out.println("Кот успешно добавлен");
    }

    private void addRobot() {
        System.out.print("Введите имя робота: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Ошибка! Имя робота не может быть пустым");
            return;
        }
        if (findRobotByName(name) != null) {
            System.out.println("Ошибка! Такой робот уже существует");
            return;
        }
        robotsList.add(new Robot(name));
        System.out.println("Робот успешно добавлен");
    }

    private void makeCatMeow() {
        System.out.print("Введите имя кота: ");
        String name = scanner.nextLine().trim();
        Cat cat = findCatByName(name);
        if (cat == null) {
            System.out.println("Ошибка! Такого кота не существует");
            return;
        }
        int count = getter.getInt(0, Integer.MAX_VALUE, "Введите количество мяуканий: ");
        cat.meow(count);
    }

    private void makeAllMeow() {
        if (catsList.isEmpty() && robotsList.isEmpty()) {
            System.out.println("Ошибка! Нет ни котов, ни роботов");
            return;
        }
        ArrayList<Meowable> meowers = new ArrayList<>();
        meowers.addAll(catsList);
        meowers.addAll(robotsList);
        meowAll(meowers.toArray(new Meowable[0]));
    }

    private void printCatsAndRobots() {
        if (catsList.isEmpty() && robotsList.isEmpty()) {
            System.out.println("Котов и роботов пока нет");
            return;
        }
        if (!catsList.isEmpty()) {
            System.out.println("Коты:");
            for (int i = 0; i < catsList.size(); i++) {
                System.out.println(" - " + catsList.get(i).name + (i == catsList.size() - 1 ? "" : ","));
            }
        }
        if (!robotsList.isEmpty()) {
            System.out.println("Роботы:");
            for (int i = 0; i < robotsList.size(); i++) {
                System.out.println(" - " + robotsList.get(i).getName() + (i == robotsList.size() - 1 ? "" : ","));
            }
        }
        System.out.println();
    }

    private void removeCatByName() {
        System.out.print("Введите имя кота для удаления: ");
        String name = scanner.nextLine().trim();
        Cat cat = findCatByName(name);
        if (cat == null) {
            System.out.println("Ошибка! Такого кота не существует");
            return;
        }
        catsList.remove(cat);
        System.out.println("Кот " + name + " успешно удален. Зачем ты это сделал? :(");
    }

    private void removeRobotByName() {
        System.out.print("Введите имя робота для удаления: ");
        String name = scanner.nextLine().trim();
        Robot robot = findRobotByName(name);
        if (robot == null) {
            System.out.println("Ошибка! Такого робота не существует");
            return;
        }
        robotsList.remove(robot);
        System.out.println("Робот " + name + " успешно удален");
    }

    private void pointMenu() {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1) Добавить точку");
            System.out.println("2) Добавить трехмерную точку");
            System.out.println("3) Вывести точки");
            System.out.println("4) Сравнить две точки");
            System.out.println("5) Клонировать точку");
            System.out.println("6) Удалить точку");
            System.out.println("Введите 0 для возвращения в меню\n");

            int action = getter.getInt(0, 6, "Выберите действие: ");
            System.out.println();

            if (action == 0) break;

            switch (action) {
                case 1 -> addPoint();
                case 2 -> addPoint3D();
                case 3 -> printPoints();
                case 4 -> comparePoints();
                case 5 -> clonePoint();
                case 6 -> removePoint();
            }
        }
    }

    private void addPoint() {
        int x = getter.getInt("Введите координату X: ");
        int y = getter.getInt("Введите координату Y: ");
        pointsList.add(new Point(x, y));
        System.out.println("Точка успешно добавлена");
    }

    private void addPoint3D() {
        int x = getter.getInt("Введите координату X: ");
        int y = getter.getInt("Введите координату Y: ");
        int z = getter.getInt("Введите координату Z: ");
        pointsList.add(new Point3D(x, y, z));
        System.out.println("Трехмерная точка успешно добавлена");
    }

    private void printPoints() {
        if (pointsList.isEmpty()) {
            System.out.println("Список точек пуст");
            return;
        }
        for (int i = 0; i < pointsList.size(); i++) {
            System.out.println((i + 1) + " - " + pointsList.get(i) + (i == pointsList.size() - 1 ? "" : ","));
        }
        System.out.println();
    }

    private void comparePoints() {
        if (pointsList.size() < 2) {
            System.out.println("Ошибка! Нужно хотя бы две точки");
            return;
        }
        printPoints();
        int firstIndex = getter.getInt(1, pointsList.size(), "Введите номер первой точки: ") - 1;
        int secondIndex = getter.getInt(1, pointsList.size(), "Введите номер второй точки: ") - 1;

        Point first = pointsList.get(firstIndex);
        Point second = pointsList.get(secondIndex);
        if (first.equals(second)) {
            System.out.println("Точки " + first + " и " + second + " одинаковые");
        } else {
            System.out.println("Точки " + first + " и " + second + " разные");
        }
    }

    private void clonePoint() {
        if (pointsList.isEmpty()) {
            System.out.println("Ошибка! Список точек пуст");
            return;
        }
        printPoints();
        int index = getter.getInt(1, pointsList.size(), "Введите номер точки: ") - 1;

        Point original = pointsList.get(index);
        Point clone = original.clone();
        pointsList.add(clone);
        System.out.println("Точка " + original + " клонирована");
        System.out.println("Клон: " + clone);
        System.out.println("Это тот же самый объект: " + (clone == original ? "да" : "нет"));
        System.out.println("Координаты совпадают: " + (clone.equals(original) ? "да" : "нет"));
    }

    private void removePoint() {
        if (pointsList.isEmpty()) {
            System.out.println("Ошибка! Список точек пуст");
            return;
        }
        printPoints();
        int index = getter.getInt(1, pointsList.size(), "Введите номер точки для удаления: ") - 1;
        Point point = pointsList.remove(index);
        System.out.println("Точка " + point + " успешно удалена");
    }

    private void secretMenu() {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1) Создать секрет");
            System.out.println("2) Рассказать секрет");
            System.out.println("3) Вывести хранителей секрета");
            System.out.println("4) Информация о хранителе");
            System.out.println("5) Узнать имя соседнего хранителя");
            System.out.println("6) Узнать разницу в длине текста секрета");
            System.out.println("7) Удалить секрет");
            System.out.println("Введите 0 для возвращения в меню\n");

            int action = getter.getInt(0, 7, "Выберите действие: ");
            System.out.println();

            if (action == 0) break;

            switch (action) {
                case 1 -> addSecret();
                case 2 -> tellSecret();
                case 3 -> printSecrets();
                case 4 -> printSecretOwnerInfo();
                case 5 -> printNeighborName();
                case 6 -> printLengthDifference();
                case 7 -> removeSecret();
            }
        }
    }

    private void addSecret() {
        System.out.print("Введите имя хранителя секрета: ");
        String ownerName = scanner.nextLine().trim();
        System.out.print("Введите текст секрета: ");
        String text = scanner.nextLine().trim();

        try {
            secretsList.add(new Secret(ownerName, text));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка! " + e.getMessage());
            return;
        }
        System.out.println("Секрет успешно создан");
    }

    private void tellSecret() {
        if (secretsList.isEmpty()) {
            System.out.println("Ошибка! Список секретов пуст");
            return;
        }
        printSecrets();
        int index = getter.getInt(1, secretsList.size(), "Введите номер хранителя, который рассказывает секрет: ") - 1;
        System.out.print("Введите имя нового хранителя: ");
        String ownerName = scanner.nextLine().trim();

        try {
            secretsList.add(new Secret(secretsList.get(index), ownerName));
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Ошибка! " + e.getMessage());
            return;
        }
        System.out.println("Секрет успешно рассказан");
    }

    private void printSecrets() {
        if (secretsList.isEmpty()) {
            System.out.println("Список секретов пуст");
            return;
        }
        for (int i = 0; i < secretsList.size(); i++) {
            Secret secret = secretsList.get(i);
            System.out.println((i + 1) + " - " + secret + " (хранитель №" + secret.getOrderNumber() + ")"
                    + (i == secretsList.size() - 1 ? "" : ","));
        }
        System.out.println();
    }

    private void printSecretOwnerInfo() {
        if (secretsList.isEmpty()) {
            System.out.println("Ошибка! Список секретов пуст");
            return;
        }
        printSecrets();
        int index = getter.getInt(1, secretsList.size(), "Введите номер хранителя: ") - 1;

        Secret secret = secretsList.get(index);
        System.out.println("Каким по очереди он узнал секрет: " + secret.getOrderNumber());
        System.out.println("Сколько еще человек узнали секрет после него: " + secret.getFollowersCount());
    }

    private void printNeighborName() {
        if (secretsList.isEmpty()) {
            System.out.println("Ошибка! Список секретов пуст");
            return;
        }
        printSecrets();
        int index = getter.getInt(1, secretsList.size(), "Введите номер хранителя: ") - 1;
        Secret secret = secretsList.get(index);

        int n = getter.getInt("Введите номер человека (положительное число - следующий хранитель, отрицательное - предыдущий): ");
        try {
            System.out.println("Имя: " + secret.getKeeperName(n));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
    }

    private void printLengthDifference() {
        if (secretsList.isEmpty()) {
            System.out.println("Ошибка! Список секретов пуст");
            return;
        }
        printSecrets();
        int index = getter.getInt(1, secretsList.size(), "Введите номер хранителя: ") - 1;
        Secret secret = secretsList.get(index);

        int n = getter.getInt("Введите номер человека (положительное число - следующий хранитель, отрицательное - предыдущий): ");
        try {
            int difference = secret.getLengthDifference(n);
            System.out.println("Разница в количестве символов текста секрета: " + difference);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
    }

    private void removeSecret() {
        if (secretsList.isEmpty()) {
            System.out.println("Ошибка! Список секретов пуст");
            return;
        }
        printSecrets();
        int index = getter.getInt(1, secretsList.size(), "Введите номер секрета для удаления: ") - 1;

        Secret current = secretsList.get(index);
        while (current.getPrevious() != null) {
            current = current.getPrevious();
        }
        while (current != null) {
            secretsList.remove(current);
            current = current.getNext();
        }
        System.out.println("Секрет успешно удален");
    }

    private void birdMenu() {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1) Добавить птицу");
            System.out.println("2) Птицы поют");
            System.out.println("3) Вывести птиц");
            System.out.println("4) Удалить птицу");
            System.out.println("Введите 0 для возвращения в меню\n");
            int action = getter.getInt(0, 4, "Выберите действие: ");
            System.out.println();
            if (action == 0) break;
            switch (action) {
                case 1 -> addBird();
                case 2 -> makeBirdsSing();
                case 3 -> printBirds();
                case 4 -> removeBird();
            }
        }
    }

    private void addBird() {
        System.out.println("Выберите птицу:");
        System.out.println("1 - Воробей");
        System.out.println("2 - Кукушка");
        System.out.println("3 - Попугай");
        int type = getter.getInt(1, 3, "Выберите птицу: ");

        Bird bird;
        if (type == 1) {
            bird = new Sparrow();
        } else if (type == 2) {
            bird = new Cuckoo();
        } else {
            System.out.print("Введите текст, который будет петь попугай: ");
            String text = scanner.nextLine().trim();
            try {
                bird = new Parrot(text);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка! " + e.getMessage());
                return;
            }
        }

        birdsList.add(bird);
        System.out.println("Птица успешно добавлена");
    }

    private void makeBirdsSing() {
        if (birdsList.isEmpty()) {
            System.out.println("Список птиц пуст");
            return;
        }
        for (Bird bird : birdsList) {
            System.out.print(bird.getName() + ": ");
            bird.sing();
        }
    }

    private void printBirds() {
        if (birdsList.isEmpty()) {
            System.out.println("Список птиц пуст");
            return;
        }
        for (int i = 0; i < birdsList.size(); i++) {
            System.out.println((i + 1) + " - " + birdsList.get(i) + (i == birdsList.size() - 1 ? "" : ","));
        }
        System.out.println();
    }

    private void removeBird() {
        if (birdsList.isEmpty()) {
            System.out.println("Список птиц пуст");
            return;
        }
        printBirds();
        int index = getter.getInt(1, birdsList.size(), "Введите номер птицы для удаления: ") - 1;
        Bird bird = birdsList.remove(index);
        System.out.println("Птица " + bird.getName() + " успешно удалена");
    }
}