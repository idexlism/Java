import java.util.Scanner;

public class MyLabWork {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер задачи (1-8):");
        String input = scanner.nextLine();
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Программа завершена.");
            scanner.close();
            return;
        }

        switch (taskNumber) {
            case 1 -> runTask1(scanner);
            case 2 -> runTask2(scanner);
            case 3 -> runTask3(scanner);
            case 4 -> runTask4(scanner);
            case 5 -> runTask5(scanner);
            case 6 -> runTask6(scanner);
            case 7 -> runTask7(scanner);
            case 8 -> runTask8(scanner);
            default -> System.out.println("Задача с таким номером не существует.");
        }

        scanner.close();
    }

    private static void runTask1(Scanner sc) {
        String example = "abrkaabcdefghijjxxx";
        System.out.println("Задача 1: Поиск самой длинной подстроки без повторов.");
        System.out.println("Введите строку или нажмите Enter для использования примера:");
        String input = sc.nextLine().trim();

        if (input.isEmpty()) {
            input = example;
            System.out.println("Используется пример: " + input);
        }

        String result = findLongestUniqueSubstring(input);
        System.out.println("Результат: " + result);
    }

    private static void runTask2(Scanner sc) {
        String arr1Default = "1 3 5 7";
        String arr2Default = "2 4 6 8";

        System.out.println("Задача 2: Слияние двух отсортированных массивов.");
        System.out.println("Введите элементы первого массива (по умолчанию: " + arr1Default + ")");
        String arr1Str = sc.nextLine().trim();
        if (arr1Str.isEmpty()) arr1Str = arr1Default;

        System.out.println("Введите элементы второго массива (по умолчанию: " + arr2Default + ")");
        String arr2Str = sc.nextLine().trim();
        if (arr2Str.isEmpty()) arr2Str = arr2Default;

        int[] arr1 = parseNumbersFromString(arr1Str);
        int[] arr2 = parseNumbersFromString(arr2Str);
        int[] merged = mergeSortedArrays(arr1, arr2);

        System.out.print("Результат слияния: ");
        for (int num : merged) System.out.print(num + " ");
        System.out.println();
    }

    private static void runTask3(Scanner sc) {
        String defaultArr = "-2 1 -3 4 -1 2 1 -5 4";
        System.out.println("Задача 3: Найти максимальную сумму подмассива.");
        System.out.println("Введите элементы массива или нажмите Enter для примера:");
        String input = sc.nextLine().trim();
        if (input.isEmpty()) input = defaultArr;

        int[] arr = parseNumbersFromString(input);
        int maxSum = findMaxSubarraySum(arr);
        System.out.println("Максимальная сумма подмассива: " + maxSum);
    }

    private static void runTask4(Scanner sc) {
        String defaultMatrix = "1,2,3;4,5,6;7,8,9";
        System.out.println("Задача 4: Повернуть матрицу по часовой стрелке.");
        System.out.println("Введите матрицу в формате row1;row2;row3 или нажмите Enter для примера:");
        String input = sc.nextLine().trim();
        if (input.isEmpty()) input = defaultMatrix;

        int[][] matrix = parse2DIntArray(input);
        int[][] rotated = rotateClockwise(matrix);

        System.out.println("Повернутая матрица:");
        printMatrix(rotated);
    }

    private static void runTask5(Scanner sc) {
        String defaultArr = "10 15 3 7";
        String defaultTarget = "17";

        System.out.println("Задача 5: Найти пару чисел с заданной суммой.");
        System.out.println("Введите массив чисел или нажмите Enter для примера:");
        String arrInput = sc.nextLine().trim();
        if (arrInput.isEmpty()) arrInput = defaultArr;

        System.out.println("Введите целевую сумму или нажмите Enter для примера:");
        String targetInput = sc.nextLine().trim();
        int target = targetInput.isEmpty() ? Integer.parseInt(defaultTarget) : Integer.parseInt(targetInput);

        int[] numbers = parseNumbersFromString(arrInput);
        int[] pair = findPairWithSum(numbers, target);

        if (pair != null) {
            System.out.printf("Найдена пара: %d и %d%n", pair[0], pair[1]);
        } else {
            System.out.println("Пара с такой суммой не найдена.");
        }
    }

    private static void runTask6(Scanner sc) {
        String defaultMatrix = "1,2,3;4,5,6;7,8,9";
        System.out.println("Задача 6: Посчитать сумму всех элементов матрицы.");
        System.out.println("Введите матрицу или нажмите Enter для примера:");
        String input = sc.nextLine().trim();
        if (input.isEmpty()) input = defaultMatrix;

        int[][] matrix = parse2DIntArray(input);
        int total = calculateTotalSum(matrix);
        System.out.println("Сумма всех элементов матрицы: " + total);
    }

    private static void runTask7(Scanner sc) {
        String defaultMatrix = "1,2,3;4,5,6;7,8,9";
        System.out.println("Задача 7: Найти максимальный элемент в каждой строке.");
        System.out.println("Введите матрицу или нажмите Enter для примера:");
        String input = sc.nextLine().trim();
        if (input.isEmpty()) input = defaultMatrix;

        int[][] matrix = parse2DIntArray(input);
        int[] maxInRows = findMaxInEachRow(matrix);

        System.out.print("Максимальные элементы в строках: ");
        for (int num : maxInRows) System.out.print(num + " ");
        System.out.println();
    }

    private static void runTask8(Scanner sc) {
        String defaultMatrix = "1,2,3;4,5,6;7,8,9";
        System.out.println("Задача 8: Повернуть матрицу против часовой стрелки.");
        System.out.println("Введите матрицу или нажмите Enter для примера:");
        String input = sc.nextLine().trim();
        if (input.isEmpty()) input = defaultMatrix;

        int[][] matrix = parse2DIntArray(input);
        int[][] rotated = rotateCounterClockwise(matrix);

        System.out.println("Повернутая матрица:");
        printMatrix(rotated);
    }

    // === Реализации задач ===

    public static String findLongestUniqueSubstring(String s) {
        if (s == null || s.isEmpty()) return "";
        int[] lastPos = new int[256];
        for (int i = 0; i < 256; i++) lastPos[i] = -1;

        int start = 0, maxLength = 0, maxStart = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (lastPos[ch] >= start) {
                start = lastPos[ch] + 1;
            }
            lastPos[ch] = i;
            if (i - start + 1 > maxLength) {
                maxLength = i - start + 1;
                maxStart = start;
            }
        }
        return s.substring(maxStart, maxStart + maxLength);
    }

    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            res[k++] = a[i] <= b[j] ? a[i++] : b[j++];
        }
        while (i < a.length) res[k++] = a[i++];
        while (j < b.length) res[k++] = b[j++];
        return res;
    }

    public static int findMaxSubarraySum(int[] arr) {
        int curr = arr[0], max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            curr = Math.max(arr[i], curr + arr[i]);
            max = Math.max(max, curr);
        }
        return max;
    }

    public static int[][] rotateClockwise(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                res[j][m - 1 - i] = matrix[i][j];
        return res;
    }

    public static int[] findPairWithSum(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                if (arr[i] + arr[j] == target)
                    return new int[]{arr[i], arr[j]};
        return null;
    }

    public static int calculateTotalSum(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix) for (int val : row) sum += val;
        return sum;
    }

    public static int[] findMaxInEachRow(int[][] matrix) {
        int[] maxValues = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int max = matrix[i][0];
            for (int val : matrix[i]) if (val > max) max = val;
            maxValues[i] = max;
        }
        return maxValues;
    }

    public static int[][] rotateCounterClockwise(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                res[n - 1 - j][i] = matrix[i][j];
        return res;
    }

    // === Вспомогательные методы ===

    public static int[] parseNumbersFromString(String input) {
        if (input.isEmpty()) return new int[0];
        String[] parts = input.trim().split("[\\s,]+");
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++) nums[i] = Integer.parseInt(parts[i]);
        return nums;
    }

    public static int[][] parse2DIntArray(String input) {
        String[] rows = input.split(";");
        int[][] matrix = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            matrix[i] = parseNumbersFromString(rows[i]);
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) System.out.print(val + " ");
            System.out.println();
        }
    }
}