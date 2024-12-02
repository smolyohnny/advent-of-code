package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day2 {

    private static final Path path = Path.of("src\\y2024\\inputs\\day2.txt");
    private static int sum = 0;
    private static int sum2 = 0;

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
        for (String line : strings) {
            boolean safe = false;
            String[] strArr = line.split(" ");
            int[] numArr = new int[strArr.length];

            for (int i = 0; i < numArr.length; i++) {
                numArr[i] = Integer.parseInt(strArr[i]);
            }
            for (int i = 0; i < numArr.length - 1; i++) {
                if (numArr[i] > numArr[i + 1] && (numArr[i] - numArr[i + 1] <= 3)) {
                    if (i == numArr.length - 2) {
                        safe = true;
                    }
                } else break;
            }
            for (int i = 0; i < numArr.length - 1; i++) {
                if (numArr[i] < numArr[i + 1] && (numArr[i + 1] - numArr[i] <= 3)) {
                    if (i == numArr.length - 2) {
                        safe = true;
                    }
                } else break;
            }
            if (safe) sum++;
        }
        System.out.println(sum);
    }

    private static void solvePart2(List<String> strings) {
        for (String line : strings) {
            boolean safe = false;
            String[] strArr = line.split(" ");
            int[] numArr = new int[strArr.length];
            //Parses string array into integer one
            for (int i = 0; i < numArr.length; i++) {
                numArr[i] = Integer.parseInt(strArr[i]);
            }
            //Loop checks validity for all variants of missing number
            for (int l = 0; l < numArr.length; l++) {
                int[] altArr = removeElement(numArr, l);

                for (int i = 0; i < altArr.length-1; i++) {
                    if (altArr[i] > altArr[i+1] && (altArr[i]-altArr[i+1] <= 3)) {
                        if (i == altArr.length-2) {
                            safe = true;
                        }
                    } else break;
                }
                for (int i = 0; i < altArr.length-1; i++) {
                    if (altArr[i] < altArr[i+1] && (altArr[i+1]-altArr[i] <= 3)) {
                        if (i == altArr.length-2) {
                            safe = true;
                        }
                    } else break;
                }
            }
            if (safe) sum2++;
        }
        System.out.println(sum2);
    }
    public static int[] removeElement(int[] arr, int index){
        List<Integer> tempList = IntStream.of(arr).boxed().collect(Collectors.toList());

        tempList.remove(index);

        return tempList.stream().mapToInt(Integer::intValue).toArray();
    }
}