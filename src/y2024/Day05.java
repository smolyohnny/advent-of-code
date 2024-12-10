package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day05 {

    private static final List<String> input;
    static {
        try {
            input = Files.readAllLines(Path.of("src\\y2024\\inputs\\day5.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final List<String> wrong = new ArrayList<>();
    private static int sum = 0;
    private static int sum2 = 0;

    public static void main(String[] args) throws IOException {
        solvePart1();
        solvePart2();
    }

    private static void solvePart1() {
        for (String line : input.subList(input.indexOf("") + 1, input.size())) {
            if (IsSorted(strArrToIntArr(line))) sum += getMiddleInt(strArrToIntArr(line));
        }
        System.out.println(sum);
    }

    private static void solvePart2() {
        for (String line : input.subList(input.indexOf("") + 1, input.size())) {
            if (!IsSorted(strArrToIntArr(line))) wrong.add(line);
        }

        for (String line : wrong) {
            List<Integer> sortList = new ArrayList<>();
            int [] temp = strArrToIntArr(line);
            for (int i : temp) sortList.add(i);
            sortList.sort(customCom);
            sum2 += sortList.get(sortList.size()/2);
        }
        System.out.println(sum2);
    }

    static Comparator<Integer> customCom = new Comparator<Integer>() {
        public int compare(Integer o1, Integer o2) {
            if (isBefore(o1, o2)) return -1;
            else return +1;
        }
    };

    private static Boolean IsSorted(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (String line : input.subList(0, input.indexOf(""))) {
                String[] string = line.split("\\|");
                int[] temp = new int[string.length];
                for (int s = 0; s < string.length; s++) temp[s] = Integer.parseInt(string[s]);
                if (nums[i] == temp[0]) {
                    if (i != 0) {
                        for (int j = 0; j < i; j++) {
                            if (nums[j] == temp[1]) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean isBefore(int x, int y) {
        for (String line : input.subList(0,input.indexOf(""))) {
            String[] temp = line.split("\\|");
            if (Integer.parseInt(temp[0]) == x) {
                if (Integer.parseInt(temp[1]) == y) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int getMiddleInt(int[] nums) {
        return nums[nums.length / 2];
    }

    private static int[] strArrToIntArr(String string) {
        String[] temp = string.split(",");
        int[] nums = new int[temp.length];

        for (int i = 0; i < temp.length; i++) nums[i] = Integer.parseInt(temp[i]);
        return nums;
    }
}