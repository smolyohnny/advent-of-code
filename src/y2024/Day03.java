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

    public static void main(String[] args) {
        try {
            String input = Files.readString(path);

            solvePart1(input);
            solvePart2(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void solvePart1(String input) {
        int sum1 = 0;
        Matcher matcher = mulPattern.matcher(input);

        while (matcher.find()) sum1 += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));

        System.out.println(sum1);
    }

    private static void solvePart2(String input) {
        int sum2 = 0;
        Matcher mulMatcher = mulPattern.matcher(input);
        Matcher doMatcher = doPattern.matcher(input);
        Matcher dontMatcher = dontPattern.matcher(input);

        int position = 0;
        boolean Do = true;
        while (position < input.length()) {
            if (Do && mulMatcher.find(position) && mulMatcher.start() == position) {
                sum2 += Integer.parseInt(mulMatcher.group(1)) * Integer.parseInt(mulMatcher.group(2));
            }

            if (Do && dontMatcher.find(position) && dontMatcher.start() == position) Do = false;
            if (doMatcher.find(position) && doMatcher.start() == position) Do = true;

            position++;
        }

        System.out.println(sum2);
    }

}

