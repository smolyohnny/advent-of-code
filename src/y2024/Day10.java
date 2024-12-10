package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day10 {
    private static final List<String> input;
    static {
        try {
            input = Files.readAllLines(Path.of("src\\y2024\\inputs\\day10.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final int[][] array = new int[input.size()][input.getFirst().length()];
    private static final List<List<Integer>> positions = new ArrayList<>();

    public static void main(String[] args) {
        fill2DArray();
        solvePart1();
    }

    private static void solvePart1() {
        int sum = 0;
        int row = 0;
        for (int[] line : array) {
            int index = 0;
            for (int element : line) {
                if (element == 0) {
                    findValidPath(row, index, 0);
                    sum += positions.size();
                    positions.clear();
                }
                index++;
            }
            row++;
        }
        System.out.println(sum);
    }

    private static void findValidPath(int row, int index, int value) {
        if (value == 9) {
            if (array[row][index] == 9) {
                if (!positions.contains(Arrays.asList(row, index))) {
                    positions.add(Arrays.asList(row, index));
                }
            }
            else return;
        }
        if (row != 0 && array[row-1][index] == value +1) findValidPath(row-1,index, value +1);
        if (row != array.length-1 && array[row+1][index] == value +1) findValidPath(row+1,index, value +1);
        if (index != 0 && array[row][index-1] == value +1) findValidPath(row,index-1, value +1);
        if (index != array[0].length-1 && array[row][index+1] == value +1) findValidPath(row,index+1, value +1);
    }

    private static void fill2DArray() {
        int row = 0;
        for (String line : input) {
            int index = 0;
            for (char ch : line.toCharArray()) {
                array[row][index] = Character.getNumericValue(ch);
                index++;
            }
            row++;
        }
    }
}
