package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {

    private static final Path path = Path.of("src\\y2024\\inputs\\day1.txt");
    private static List<String> input = new ArrayList<>();
    private static final List<Integer> col1 = new ArrayList<>();
    private static final List<Integer> col2 = new ArrayList<>();
    private static int sum1 = 0;
    private static int sum2 = 0;

    public static void main(String[] args) throws IOException {
        input = Files.readAllLines(path);

        fillLists();
        solvePart1();
        solvePart2();
    }

    //Counts the distances between each pair
    private static void solvePart1() {
        for (int i = 0; i < col1.size(); i++) {
            //Math.abs() makes sure there are no negative values as a distance
            sum1 += Math.abs(col1.get(i) - col2.get(i));
        }
        System.out.println(sum1);
    }

    //Counts how often each number from col1 appears in col2
    private static void solvePart2() {
        for (int i : col1) {
            int appearances = 0;
            for (int j : col2) {
                if (i == j) appearances++;
            }
            sum2 += (i * appearances);
        }
        System.out.println(sum2);
    }

    private static void fillLists() {
        for (String line : input) {
            String[] arr = line.split(" {3}", 0);
            col1.add(Integer.parseInt(arr[0]));
            col2.add(Integer.parseInt(arr[1]));
        }
        Collections.sort(col1);
        Collections.sort(col2);
    }

}
