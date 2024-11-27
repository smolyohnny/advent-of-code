package y2023;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;

public class Day1 {

    public static void main(String[] args) {
            Path path = Path.of("src\\y2023\\inputs\\day1.txt");
            System.out.println(part1(path));
            System.out.println(part2(path));
    }

    private static int part1(Path path) {
            int finalResult = 0;
            try {
                List<String> strings = Files.readAllLines(path);
                    for (String line : strings) {
                        String leftNum = "";
                        int index = 0;
                        while (leftNum.isEmpty()) {
                            if (Character.isDigit(line.charAt(index))) leftNum = String.valueOf(line.charAt(index));
                            index++;
                        }
                        String rightNum = "";
                        index = line.length()-1;
                        while (rightNum.isEmpty()) {
                            if (Character.isDigit(line.charAt(index))) rightNum = String.valueOf(line.charAt(index));
                            index--;
                        }
                        int result = Integer.parseInt(leftNum + rightNum);
                        finalResult += result;
                    }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return finalResult;
        }

        private static int part2(Path path) {
            int finalResult = 0;
            try {
                List<String> strings = Files.readAllLines(path);
                for (String line : strings) {
                    String leftNum = "";
                    int index = 0;
                    while (leftNum.isEmpty()) {
                        leftNum = findNum(line.substring(0, index));
                        index++;
                    }
                    String rightNum = "";
                    index = 0;
                    while (rightNum.isEmpty()) {
                        rightNum = findNum(line.substring(line.length() - index));
                        index++;
                    }
                    int result = Integer.parseInt(leftNum + rightNum);
                    finalResult += result;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return finalResult;
        }

        private static String findNum(String subString) {
            subString = subString.replace("one", "1")
                    .replace("two", "2")
                    .replace("three", "3")
                    .replace("four", "4")
                    .replace("five", "5")
                    .replace("six", "6")
                    .replace("seven", "7")
                    .replace("eight", "8")
                    .replace("nine", "9")
                    .replaceAll("[^\\d.]", "");
            return subString;
    }
}
