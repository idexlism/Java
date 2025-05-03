import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MyLabWork {
    private static Scanner scanner;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: не указан номер задачи.");
            return;
        }

        scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        String selectedTask = args[0];

        try {
            switch (selectedTask) {
                case "1" -> runTaskCollatz();
                case "2" -> runTaskAlternatingSum();
                case "3" -> runTaskTreasurePath();
                case "4" -> runTaskMaxSafeHeight();
                case "5" -> runTaskEvenSumAndProduct();
                default -> System.out.println("Нет такой задачи");
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка во время выполнения программы.");
        } finally {
            scanner.close();
        }
    }

    // Задача 1: Последовательность Коллатца
    public static void runTaskCollatz() {
        System.out.print("Введите начальное число для последовательности Коллатца: ");
        int number = scanner.nextInt();
        int steps = 0;

        while (number != 1) {
            if (number % 2 == 0) {
                number /= 2;
            } else {
                number = 3 * number + 1;
            }
            steps++;
        }

        System.out.println("Количество шагов до единицы: " + steps);
    }

    // Задача 2: Чередующаяся сумма
    public static void runTaskAlternatingSum() {
        System.out.print("Сколько чисел будет введено? ");
        int count = scanner.nextInt();
        int result = 0;

        for (int i = 0; i < count; i++) {
            System.out.print("Введите число: ");
            int value = scanner.nextInt();
            result += ((i % 2 == 0) ? 1 : -1) * value;
        }

        System.out.println("Результат чередующейся суммы: " + result);
    }

    // Задача 3: Путь к сокровищу
    public static void runTaskTreasurePath() {
        System.out.println("Введите координаты сокровища:");
        int targetX = scanner.nextInt();
        int targetY = scanner.nextInt();

        int currentX = 0, currentY = 0;
        int movesCount = 0;

        System.out.println("Введите направление (например, север/юг/запад/восток) или 'стоп':");
        String direction = scanner.next();

        while (!direction.equals("стоп")) {
            int steps = scanner.nextInt();

            if (!(currentX == targetX && currentY == targetY)) {
                movesCount++;

                switch (direction) {
                    case "север" -> currentY += steps;
                    case "юг" -> currentY -= steps;
                    case "запад" -> currentX -= steps;
                    case "восток" -> currentX += steps;
                }
            }

            System.out.println("Введите следующее направление или 'стоп':");
            direction = scanner.next();
        }

        System.out.println("Число корректных передвижений до достижения цели: " + movesCount);
    }

    // Задача 4: Самый безопасный маршрут
    public static void runTaskMaxSafeHeight() {
        System.out.print("Сколько дорог доступно? ");
        int roadCount = scanner.nextInt();
        int bestRoadIndex = 0;
        int maxHeight = Integer.MIN_VALUE;

        for (int i = 1; i <= roadCount; i++) {
            System.out.print("Сколько препятствий на дороге №" + i + "? ");
            int obstacleCount = scanner.nextInt();
            int minObstacleHeight = Integer.MAX_VALUE;

            for (int j = 0; j < obstacleCount; j++) {
                System.out.print("Введите высоту препятствия: ");
                int height = scanner.nextInt();
                if (height < minObstacleHeight) {
                    minObstacleHeight = height;
                }
            }

            if (minObstacleHeight > maxHeight) {
                maxHeight = minObstacleHeight;
                bestRoadIndex = i;
            }
        }

        System.out.printf("Лучшая дорога: %d, максимальная проходимая высота: %d\n", bestRoadIndex, maxHeight);
    }

    // Задача 5: Чётные сумма и произведение
    public static void runTaskEvenSumAndProduct() {
        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        int sumDigits = 0;
        int productDigits = 1;

        while (number > 0) {
            int digit = number % 10;
            sumDigits += digit;
            productDigits *= digit;
            number /= 10;
        }

        boolean isSumEven = sumDigits % 2 == 0;
        boolean isProductEven = productDigits % 2 == 0;

        System.out.println((isSumEven && isProductEven) ? "ДА" : "НЕТ");
    }
}