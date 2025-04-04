package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day02 {

    private static final Path path = Path.of("src\\y2024\\inputs\\day2.txt");

    public static void main(String[] args) {
        try {
            List<String> strings = Files.readAllLines(path);
            solvePart1(strings);
            solvePart2(strings);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void solvePart1(List<String> strings) {
        int sum = 0;

        for (String line : strings) {
            int[] numArr = parseArray(line.split(" "));
            if (isAscending(numArr) || isDescending(numArr)) sum++;
        }

        System.out.println(sum);
    }

    private static void solvePart2(List<String> strings) {
        int sum2 = 0;

        for (String line : strings) {
            boolean safe = false;
            int[] numArr = parseArray(line.split(" "));

            for (int i = 0; i < numArr.length; i++) {
                int[] altArr = removeElement(numArr, i);
                if (isAscending(altArr) || isDescending(altArr)) safe = true;
            }

            if (safe) sum2++;
        }

        System.out.println(sum2);
    }

    private static int[] parseArray(String[] strArr) {
        int[] numArr = new int[strArr.length];

        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = Integer.parseInt(strArr[i]);
        }

        return numArr;
    }

    private static boolean isAscending(int[] numArr) {

        for (int i = 0; i < numArr.length - 1; i++) {
            if (numArr[i] >= numArr[i + 1] || (numArr[i + 1] - numArr[i] > 3)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDescending(int[] numArr) {
        for (int i = 0; i < numArr.length - 1; i++) {
            if (numArr[i] <= numArr[i + 1] || (numArr[i] - numArr[i + 1] > 3)) {
                return false;
            }
        }
        return true;
    }


    private static int[] removeElement(int[] arr, int index) {

        List<Integer> tempList = new ArrayList<>();
        for (int i : arr) {
            tempList.add(i);
        }

        tempList.remove(index);

        int[] result = new int[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            result[i] = tempList.get(i);
        }

        return result;
    }
}