package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day8 {
    private static final List<String> input;
    static {
        try {
            input = Files.readAllLines(Path.of("src\\y2024\\inputs\\day8.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final char[][] charArray = new char[input.size()][input.getFirst().length()];
    private static final List<Integer[]> positionList = new ArrayList<>();

    public static void main(String[] args) {
        solvePart1();
        solvePart2();
    }

    private static void solvePart1() {
        fill2DArray();
        findAntennas();

        int position = 0;
        for (Integer[] antenna : positionList) {
            for (int i = 1; i < positionList.subList(position, positionList.size()).size(); i++) {
                Integer[] nextAntenna = positionList.subList(position, positionList.size()).get(i);

                if (nextAntenna[0].equals(antenna[0])) {
                    int mirroredX1 = antenna[1]*2 - nextAntenna[1];
                    int mirroredY1 = antenna[2]*2 - nextAntenna[2];
                    int mirroredX2 = nextAntenna[1]*2 - antenna[1];
                    int mirroredY2 = nextAntenna[2]*2 - antenna[2];

                    if (isInBounds(mirroredX1, mirroredY1)) {
                        charArray[mirroredX1][mirroredY1] = '#';
                    }
                    if (isInBounds(mirroredX2, mirroredY2)) {
                        charArray[mirroredX2][mirroredY2] = '#';
                    }
                }
            }
            position++;
        }
        System.out.println("Part 1: " + countAntinodes());
    }

    private static void solvePart2() {

        fill2DArray();
        findAntennas();

        int position = 0;
        for (Integer[] antenna : positionList) {
            for (int i = 1; i < positionList.subList(position, positionList.size()).size(); i++) {
                Integer[] nextAntenna = positionList.subList(position, positionList.size()).get(i);

                if (nextAntenna[0].equals(antenna[0])) {
                    int mirroredX1 = antenna[1];
                    int mirroredY1 = antenna[2];
                    int mirroredX2 = nextAntenna[1];
                    int mirroredY2 = nextAntenna[2];

                    while (isInBounds(mirroredX1, mirroredY1)) {
                        charArray[mirroredX1][mirroredY1] = '#';
                        mirroredX1 += antenna[1] - nextAntenna[1];
                        mirroredY1 += antenna[2] - nextAntenna[2];
                    }

                    while (isInBounds(mirroredX2, mirroredY2)) {
                        charArray[mirroredX2][mirroredY2] = '#';
                        mirroredX2 += nextAntenna[1] - antenna[1];
                        mirroredY2 += nextAntenna[2] - antenna[2];
                    }
                }
            }
            position++;
        }
        System.out.println("Part 2: " + countAntinodes());
    }


    private static int countAntinodes() {
        int count = 0;
        for (char[] x : charArray) {
            for (char y : x) {
                if (y == '#') count++;
            }
        }
        return count;
    }

    private static boolean isInBounds(int x, int y) {
        return x < charArray.length && x >= 0 && y < charArray[0].length && y >= 0;
    }

    private static void findAntennas() {
        positionList.clear();
        for (int row = 0; row < charArray.length; row++) {
            for (int index = 0; index < charArray[0].length; index++) {
                if (charArray[row][index] != '.') {
                    positionList.add(new Integer[]{(int) charArray[row][index], row, index});
                }
            }
        }
    }

    private static void fill2DArray() {
        int row = 0;
        for (String line : input) {
            int index = 0;
            for (char ch : line.toCharArray()) {
                charArray[row][index] = ch;
                index++;
            }
            row++;
        }
    }
}
