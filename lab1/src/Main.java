import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MyLabWork {
    private static Scanner scanner;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("������: �� ������ ����� ������.");
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
                default -> System.out.println("��� ����� ������");
            }
        } catch (Exception e) {
            System.out.println("��������� ������ �� ����� ���������� ���������.");
        } finally {
            scanner.close();
        }
    }

    // ������ 1: ������������������ ��������
    public static void runTaskCollatz() {
        System.out.print("������� ��������� ����� ��� ������������������ ��������: ");
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

        System.out.println("���������� ����� �� �������: " + steps);
    }

    // ������ 2: ������������ �����
    public static void runTaskAlternatingSum() {
        System.out.print("������� ����� ����� �������? ");
        int count = scanner.nextInt();
        int result = 0;

        for (int i = 0; i < count; i++) {
            System.out.print("������� �����: ");
            int value = scanner.nextInt();
            result += ((i % 2 == 0) ? 1 : -1) * value;
        }

        System.out.println("��������� ������������ �����: " + result);
    }

    // ������ 3: ���� � ���������
    public static void runTaskTreasurePath() {
        System.out.println("������� ���������� ���������:");
        int targetX = scanner.nextInt();
        int targetY = scanner.nextInt();

        int currentX = 0, currentY = 0;
        int movesCount = 0;

        System.out.println("������� ����������� (��������, �����/��/�����/������) ��� '����':");
        String direction = scanner.next();

        while (!direction.equals("����")) {
            int steps = scanner.nextInt();

            if (!(currentX == targetX && currentY == targetY)) {
                movesCount++;

                switch (direction) {
                    case "�����" -> currentY += steps;
                    case "��" -> currentY -= steps;
                    case "�����" -> currentX -= steps;
                    case "������" -> currentX += steps;
                }
            }

            System.out.println("������� ��������� ����������� ��� '����':");
            direction = scanner.next();
        }

        System.out.println("����� ���������� ������������ �� ���������� ����: " + movesCount);
    }

    // ������ 4: ����� ���������� �������
    public static void runTaskMaxSafeHeight() {
        System.out.print("������� ����� ��������? ");
        int roadCount = scanner.nextInt();
        int bestRoadIndex = 0;
        int maxHeight = Integer.MIN_VALUE;

        for (int i = 1; i <= roadCount; i++) {
            System.out.print("������� ����������� �� ������ �" + i + "? ");
            int obstacleCount = scanner.nextInt();
            int minObstacleHeight = Integer.MAX_VALUE;

            for (int j = 0; j < obstacleCount; j++) {
                System.out.print("������� ������ �����������: ");
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

        System.out.printf("������ ������: %d, ������������ ���������� ������: %d\n", bestRoadIndex, maxHeight);
    }

    // ������ 5: ׸���� ����� � ������������
    public static void runTaskEvenSumAndProduct() {
        System.out.print("������� �����: ");
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

        System.out.println((isSumEven && isProductEven) ? "��" : "���");
    }
}