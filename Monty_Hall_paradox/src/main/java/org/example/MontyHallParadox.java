package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


public class MontyHallParadox {
    private static final int NUM_TRIALS = 1000;

    public static void main(String[] args) {
        // Мап для хранения результатов (шаг теста -> результат)
        Map<Integer, String> results = new HashMap<>();

        // Проводим серию тестов
        for (int i = 1; i <= NUM_TRIALS; i++) {
            // Выполняем одну итерацию игры Монти Холла
            boolean win = playMontyHallGame();
            results.put(i, win ? "Победа" : "Поражение");
        }
        // Выводим статистику
        printStatistics(results);
    }

    private static boolean playMontyHallGame() {
        // Инициализируем генератор случайных чисел
        Random random = new Random();
        // Задаем случайную позицию автомобиля и начальный выбор игрока
        int carPosition = random.nextInt(3);
        int initialChoice = random.nextInt(3);

        int doorOpenedByMonty = openDoorByMonty(carPosition, initialChoice);
        boolean switchDoor = random.nextBoolean();
        // Итоговый выбор игрока
        int finalChoice = switchDoor ? switchDoor(initialChoice, doorOpenedByMonty) : initialChoice;
        // Проверяем, выиграл ли игрок
        return finalChoice == carPosition;
    }

    private static int openDoorByMonty(int carPosition, int initialChoice) {
        Random random = new Random();
        int doorOpenedByMonty;

        do {
            doorOpenedByMonty = random.nextInt(3);
        } while (doorOpenedByMonty == carPosition || doorOpenedByMonty == initialChoice);

        return doorOpenedByMonty;
    }

    private static int switchDoor(int initialChoice, int doorOpenedByMonty) {
        return 3 - initialChoice - doorOpenedByMonty;
    }

    private static void printStatistics(Map<Integer, String> results) {
        // Подсчитываем частоту вхождений каждого результата
        Map<String, Long> resultFrequency = results.values().stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        System.out.println("Результаты симуляции парадокса Монти Холла:");
        System.out.println("Всего испытаний: " + NUM_TRIALS);
        System.out.println("Побед: " + resultFrequency.getOrDefault("Победа", 0L));
        System.out.println("Поражений: " + resultFrequency.getOrDefault("Поражение", 0L));
    }
}