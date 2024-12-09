package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9 {
    private static final char[] input;

    static {
        try {
            input = Files.readString(Path.of("src\\y2024\\inputs\\day9.txt")).toCharArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final List<String> stringList = new ArrayList<>();
    private static long sum = 0;
    private static long sum2 = 0;

    public static void main(String[] args) {
        solvePart1();
        solvePart2();
    }

    private static void solvePart1() {
        fillStringList();
        sortStringList1();

        for (int i = 0; i < stringList.size(); i++) {
            if (stringList.get(i).equals(".")) break;
            sum += (Long.parseLong(String.valueOf(stringList.get(i))) * i);
        }
        System.out.println("Part 1: " + sum);
    }

    private static void sortStringList1() {
        for (int i = stringList.size() - 1; i >= 0; i--) {
            int dotPosition = 0;
            for (int j = 0; j <= i; j++) {
                if (stringList.get(j).equals(".")) {
                    dotPosition = j;
                    break;
                }
            }
            if (dotPosition != 0) {
                Collections.swap(stringList, i, dotPosition);
            }
        }
    }

    private static void solvePart2() {
        fillStringList();
        sortStringList2();
        for (int i = 0; i < stringList.size(); i++) {
            if (!stringList.get(i).equals(".")) {
                sum2 += (Long.parseLong(String.valueOf(stringList.get(i))) * i);
            }
        }
        System.out.println("Part 2: " + sum2);
    }

    private static void sortStringList2() {
        String number = "";
        for (int i = stringList.size() - 1; i >= 0; i--) {
            if (!stringList.get(i).equals(".") && !stringList.get(i).equals(number)) {
                number = stringList.get(i);

                int start = i;
                while (start >= 0 && stringList.get(start).equals(number)) {
                    start--;
                }
                int numberCount = i - start;

                int dot = findDot(start, numberCount);

                if (dot > 0) {
                    int swapPostition = start + 1;
                    for (int j = dot; j < dot + numberCount; j++) {
                        Collections.swap(stringList, j, swapPostition);
                        swapPostition++;
                    }
                }
            }
        }
    }

    private static int findDot(int limit, int numberCount) {
        int dotPosition = 0;
        int dotCount = 0;

        for (int i = 0; i <= limit; i++) {
            if (stringList.get(i).equals(".")) {
                if (dotPosition == 0) dotPosition = i;
                dotCount++;
            } else {
                dotCount = 0;
                dotPosition = 0;
            }

            if (dotCount >= numberCount) {
                return dotPosition;
            }
        }
        return 0;
    }

    private static void fillStringList() {
        stringList.clear();
        long loops = 0;
        for (int i = 0; i < input.length; i++) {
            int value = Character.getNumericValue(input[i]);
            if (i % 2 == 0) {
                for (int j = 0; j < value; j++) {
                    stringList.add(String.valueOf(loops));
                }
                loops++;
            } else {
                for (int j = 0; j < value; j++) {
                    stringList.add(".");
                }
            }
        }
    }
}