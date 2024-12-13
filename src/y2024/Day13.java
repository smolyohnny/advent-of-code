package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day13 {
    private static final List<String> input;

    static {
        try {
            input = Files.readAllLines(Path.of("src\\y2024\\inputs\\day13.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final List<Integer[]> machines = new ArrayList<>();
    private static int sum = 0;

    public static void main(String[] args) {
        fillMachines();
        solvePart1();
    }

    private static void solvePart1() {
        for (Integer[] arr : machines) {
            countTokens(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
        }
        System.out.println(sum);
    }

    public static void countTokens(int x1, int y1, int x2, int y2, int xGoal, int yGoal) {
        int cheapestPrice = 0;
        for (int a = 0; a <= 100; a++) {
            if ((xGoal - (x1 * a)) % x2 == 0) {
                int b = (xGoal - (x1 * a)) / x2;
                if ((yGoal - (y1 * a)) % y2 == 0 && (yGoal - (y1 * a)) / y2 == b) {
                    if (b <= 100) {
                        int temp = (3 * a) + (b);
                        if (cheapestPrice == 0) cheapestPrice = temp;
                        else if (temp < cheapestPrice) cheapestPrice = temp;
                    }
                }
            }
        }
        sum += cheapestPrice;
    }

    private static void fillMachines() {
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0, xGoal = 0, yGoal = 0;
        int counter = 0;
        int lineNum = 0;
        for (String line : input) {
            if (counter == 0) {
                x1 = Integer.parseInt(line.substring(12, line.indexOf(",", 12)));
                y1 = Integer.parseInt(line.substring(line.indexOf(",", 12) + 4));
            } else if (counter == 1) {
                x2 = Integer.parseInt(line.substring(12, line.indexOf(",", 12)));
                y2 = Integer.parseInt(line.substring(line.indexOf(",", 12) + 4));
            }
            if (counter == 2) {
                xGoal = Integer.parseInt(line.substring(9, line.indexOf(",", 9)));
                yGoal = Integer.parseInt(line.substring(line.indexOf(",", 9) + 4));
            }
            counter++;
            lineNum++;
            if (line.isBlank() || lineNum == 1279) {
                machines.add(new Integer[]{x1, y1, x2, y2, xGoal, yGoal});
                counter = 0;
            }
        }
    }
}
