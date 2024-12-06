package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day6 {

    private static final List<String> input;
    static {
        try {
            input = Files.readAllLines(Path.of("src\\y2024\\inputs\\day6.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final char[][] charArray = new char[input.size()][input.getFirst().length()];
    private static final List<List<Integer>> collisionList = new ArrayList<>();
    private static int collision = 0;
    private static int sum = 0;
    private static int sum2 = 0;
    private static int startX;
    private static int startY;
    private static char startChar;

    public static void main(String[] args) {
        create2DArray();
        solvePart1(startChar, startX, startY);
        solvePart2(startChar, startX, startY);
    }

    private static void solvePart1(char startChar, int startX, int startY) {
        logic(startChar, startX, startY);
        countX();
        System.out.println("Part 1: " + sum);
    }

    private static void solvePart2(char startChar, int startX, int startY) {
        for (int x = 0; x < charArray.length; x++) {
            for (int y = 0; y < charArray[0].length; y++) {
                collision = 0;
                placeObstacle(x, y);
                logic(startChar, startX, startY);
            }
        }
        System.out.println("Part 2: " + sum2);
    }

    private static void create2DArray() {
        fill2DArray();
        for (int x = 0; x < charArray[0].length; x++) {
            for (int y = 0; y < charArray.length; y++) {
                if (charArray[x][y] != '.' && charArray[x][y] != '#') {
                    startX = x;
                    startY = y;
                    startChar = charArray[x][y];
                }
            }
        }
    }

    private static void logic(char character, int firstX, int firstY) {
        char ch = character;
        int x = firstX;
        int y = firstY;
        while (collision < 180) {
            switch (ch) {
                case '^':
                    charArray[x][y] = 'X';
                    if (x != 0) {
                        if (charArray[x - 1][y] != '#' && charArray[x - 1][y] != 'O') {
                            charArray[x - 1][y] = ch;
                            x = x - 1;
                        } else {
                            if (collisionList.contains(Arrays.asList(x, y))) collision++;
                            else collisionList.add(Arrays.asList(x, y));
                            ch = '>';
                        }
                    } else return;

                    break;
                case '>':
                    charArray[x][y] = 'X';
                    if (y != charArray[0].length - 1) {
                        if (charArray[x][y + 1] != '#' && charArray[x][y + 1] != 'O') {
                            charArray[x][y + 1] = ch;
                            y = y + 1;
                        } else {
                            if (collisionList.contains(Arrays.asList(x, y))) collision++;
                            else collisionList.add(Arrays.asList(x, y));
                            ch = 'v';
                        }
                    } else return;

                    break;
                case 'v':
                    charArray[x][y] = 'X';
                    if (x != charArray.length - 1) {
                        if (charArray[x + 1][y] != '#' && charArray[x + 1][y] != 'O') {
                            charArray[x + 1][y] = ch;
                            x = x + 1;
                        } else {
                            if (collisionList.contains(Arrays.asList(x, y))) collision++;
                            else collisionList.add(Arrays.asList(x, y));
                            ch = '<';
                        }
                    } else return;
                    break;
                case '<':
                    charArray[x][y] = 'X';
                    if (y != 0) {
                        if (charArray[x][y - 1] != '#' && charArray[x][y - 1] != 'O') {
                            charArray[x][y - 1] = ch;
                            y = y - 1;
                        } else {
                            if (collisionList.contains(Arrays.asList(x, y))) collision++;
                            else collisionList.add(Arrays.asList(x, y));
                            ch = '^';
                        }
                    } else return;
                    break;
            }
        }
        sum2++;
    }

    private static void placeObstacle(int x, int y) {
        fill2DArray(); //clears Xs and Os
        if (charArray[x][y] == '.') {
            charArray[x][y] = 'O';
        }
    }

    private static void countX() {
        for (char[] x : charArray) {
            for (char y : x) {
                if (y == 'X') sum++;
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

//    private static void print2DArray() {
//        for (char[] x : charArray) {
//            for (char y : x) {
//                System.out.print(y + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
}

