package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day04 {

    private static final List<String> input;
    static {
        try {
            input = Files.readAllLines(Path.of("src\\y2024\\inputs\\day4.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static int sum = 0;
    private static int sum2 = 0;
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        char[][] charArrays = new char[input.getFirst().length()][];
        for (int i = 0; i < charArrays.length; i++) charArrays[i] = input.get(i).toCharArray();

        solvePart1(charArrays);
        solvePart2(charArrays);
    }

    private static void solvePart1(char[][] charArrays) {
        checkColumn(charArrays);
        checkDiagonal(charArrays);
        checkRow(charArrays);
        System.out.println(sum);
    }

    private static void checkDiagonal(char[][] charArrays) {
        StringBuilder normal = new StringBuilder();

        for (int row = 0; row < charArrays.length; row++) {
            if (row == 0) {
                for (int index = 0; index < charArrays[row].length; index++) {
                    for (int i = 0; i < charArrays[row].length-index; i++) {
                        normal.append(charArrays[row + i][index + i]);
                    }
                    normal.append(".");
                }
            }
            else {
                for (int i = 0; i < charArrays[row].length-row; i++) {
                    normal.append(charArrays[row + i][i]);
                }
                normal.append(".");
            }
        }

        for (int row = 0; row < charArrays.length; row++) {
            if (row == 0) {
                for (int index = 0; index < charArrays[row].length; index++) {
                    for (int i = 0; i < charArrays[row].length-index; i++) {
                        normal.append(charArrays[row + i][charArrays[row].length - 1 - index - i]);
                    }
                    normal.append(".");
                }
            }
            else {
                for (int i = 0; i < charArrays[row].length-row; i++) {
                    normal.append(charArrays[row + i][charArrays[row].length - 1 - i]);
                }
                normal.append(".");
            }
        }

        stringBuilder = new StringBuilder();
        String diagonal = normal.toString();
        stringBuilder.append(diagonal);
        String reverseDiagonal = stringBuilder.reverse().toString();

        while(diagonal.contains("XMAS")) {
            diagonal = diagonal.replaceFirst("XMAS","-");
            sum++;
        }

        while(reverseDiagonal.contains("XMAS")) {
            reverseDiagonal = reverseDiagonal.replaceFirst("XMAS","-");
            sum++;
        }
    }

    private static void checkColumn(char[][] charArrays) {
        StringBuilder normal = new StringBuilder();

        for (int i = 0; i < charArrays[0].length; i++) {
            for (char[] charArray : charArrays) {
                normal.append(charArray[i]);
            }
            normal.append(".");
        }

        String column = normal.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(column);
        String reverseColumn = stringBuilder.reverse().toString();

        while(column.contains("XMAS")) {
            column = column.replaceFirst("XMAS","-");
            sum++;
        }

        while(reverseColumn.contains("XMAS")) {
            reverseColumn = reverseColumn.replaceFirst("XMAS","-");
            sum++;
        }
    }

    private static void checkRow(char[][] charArrays) {
        StringBuilder normal = new StringBuilder();
        for (char[] charArray : charArrays) {
            for (char c : charArray) {
                normal.append(c);
            }
            normal.append(".");
        }

        String row = normal.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(row);
        String reverseRow = stringBuilder.reverse().toString();


        while(row.contains("XMAS")) {
            row = row.replaceFirst("XMAS","-");
            sum++;
        }

        while(reverseRow.contains("XMAS")) {
            reverseRow = reverseRow.replaceFirst("XMAS","-");
            sum++;
        }
    }

    private static void solvePart2(char[][] charArrays){
        checkX(charArrays);
        System.out.println(sum2);
    }

    private static void checkX(char[][] charArrays) {
        for (int row = 1; row < charArrays.length-1; row++) {
            for (int index = 1; index < charArrays[row].length-1; index++) {
                if (charArrays[row][index] == 'A') {
                    if (isX(charArrays, row, index)) sum2++;
                }
            }
        }
    }

    private static Boolean isX(char[][] charArrays, int row, int index) {
        int i = 0;
        if (charArrays[row-1][index-1] == 'M') {
            if (charArrays[row+1][index+1] == 'S') {
                i++;
            }
        }
        if (charArrays[row-1][index-1] == 'S') {
            if (charArrays[row+1][index+1] == 'M') {
                i++;
            }
        }
        if (charArrays[row-1][index+1] == 'M') {
            if (charArrays[row+1][index-1] == 'S') {
                i++;
            }
        }
        if (charArrays[row-1][index+1] == 'S') {
            if (charArrays[row+1][index-1] == 'M') {
                i++;
            }
        }
        return i > 1;
    }
}