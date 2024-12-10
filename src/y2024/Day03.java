package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {

    private static final Path path = Path.of("src\\y2024\\inputs\\day3.txt");
    private static final Pattern mulPattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
    private static final Pattern doPattern = Pattern.compile("do\\(\\)");
    private static final Pattern dontPattern = Pattern.compile("don't\\(\\)");
    private static int sum1 = 0;
    private static int sum2 = 0;

    public static void main(String[] args) {
        try {
            String input = Files.readString(path);

            solvePart1(input);
            solvePart2(input);

            //silly(input);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void solvePart1(String input) {
        Matcher matcher = mulPattern.matcher(input);

        while (matcher.find()) sum1 += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        System.out.println(sum1);
    }

    private static void solvePart2(String input) {
        Matcher mulMatcher = mulPattern.matcher(input);
        Matcher doMatcher = doPattern.matcher(input);
        Matcher dontMatcher = dontPattern.matcher(input);

        int position = 0;
        boolean Do = true;
        while (position < input.length()) {
            if (Do && mulMatcher.find(position)) {
                if (mulMatcher.start() == position) {
                    sum2 += Integer.parseInt(mulMatcher.group(1)) * Integer.parseInt(mulMatcher.group(2));
                }
            }

            if (Do && dontMatcher.find(position)) {
                if (dontMatcher.start() == position) Do = false;
            }

            if (doMatcher.find(position)) {
                if (doMatcher.start() == position) {
                    Do = true;
                }
            }
            position++;
        }
        System.out.println(sum2);
    }

    // My first solution :D
    private static void silly(String input) {
        int num1 = 0;
        int num2 = 0;
        char[] chars = input.toCharArray();
        boolean Do = true;
        for (int i = 0; i < input.length() - 5; i++) {
            if (chars[i] == 'm' && Do) {
                if (chars[i + 1] == 'u') {
                    if (chars[i + 2] == 'l') {
                        if (chars[i + 3] == '(') {
                            for (int j = 1; j < 5; j++) {
                                if (Character.isDigit(chars[i + 3 + j])) {
                                    num1 = num1 * 10 + Character.getNumericValue(chars[i + 3 + j]);
                                } else if (chars[i + 3 + j] == ',' && num1 > 0) {
                                    for (int k = 1; k < 5; k++) {
                                        int position = i + 4 + String.valueOf(num1).length() + k;
                                        if (Character.isDigit(chars[position])) {
                                            int temp = Character.getNumericValue(chars[position]);
                                            num2 = num2 * 10 + temp;
                                        } else if (chars[position] == ')' && num2 > 0) {
                                            sum2 += num1 * num2;
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                    break;
                                } else {
                                    break;
                                }
                            }
                            num1 = 0;
                            num2 = 0;
                        }
                    }
                }
            }
            if (chars[i] == 'd') {
                if (chars[i + 1] == 'o') {
                    if (chars[i + 2] == 'n') {
                        if (chars[i + 3] == '\'') {
                            if (chars[i + 4] == 't') {
                                if (chars[i + 5] == '(')
                                    if (chars[i + 6] == ')') Do = false;
                            }
                        }
                    } else if (chars[i + 2] == '(') {
                        if (chars[i + 3] == ')') Do = true;
                    }
                }
            }
        }
        System.out.println(sum2);
    }
}

