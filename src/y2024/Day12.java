package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day12 {
    private static final List<String> input;
    static {
        try {
            input = Files.readAllLines(Path.of("src\\y2024\\inputs\\day12.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final char[][] array = new char[input.size()][input.getFirst().length()];
    private static final List<List<Integer>> visited = new ArrayList<>();
    private static int perimeter = 0;
    private static int count = 0;

    public static void main(String[] args) {
        fill2DArray();
        solvePart1();
    }

    private static void solvePart1() {
        visited.clear();
        int sum = 0;

        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                findPerimeter(row, col, array[row][col]);
                sum += perimeter * count;
                perimeter = 0;
                count = 0;
            }
        }
        System.out.println(sum);
    }

    private static void findPerimeter(int row, int index, int cropType) {
        if (array[row][index] == cropType) {
            if (visited.contains(List.of(cropType, row, index))) return;
            else {
                count++;
                visited.add(List.of(cropType, row, index));
            }
            if (row != 0 && array[row - 1][index] == cropType) {
                findPerimeter(row - 1, index, cropType);
            } else perimeter++;
            if (row != array.length - 1 && array[row + 1][index] == cropType) {
                findPerimeter(row + 1, index, cropType);
            } else perimeter++;
            if (index != 0 && array[row][index - 1] == cropType) {
                findPerimeter(row, index - 1, cropType);
            } else perimeter++;
            if (index != array[0].length - 1 && array[row][index + 1] == cropType) {
                findPerimeter(row, index + 1, cropType);
            } else perimeter++;
        }

    }

    private static void fill2DArray() {
        int row = 0;
        for (String line : input) {
            int index = 0;
            for (char ch : line.toCharArray()) {
                array[row][index] = ch;
                index++;
            }
            row++;
        }
    }
}