package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day7 {

    private static final List<String> input;
    static {
        try {
            input = Files.readAllLines(Path.of("src\\y2024\\inputs\\day7.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static long sum = 0;
    private static long sum2 = 0;
    private static List<Long> numList = new ArrayList<>();

    public static void main(String[] args) {
        solvePart1();
        solvePart2();
    }

    private static void solvePart1() {
        for (String line : input) {
            long equation = getEquation(line);
            boolean Valid = sumOfArrayList(numList) == equation;

            if (isValid(numList, equation, false)) Valid = true;

            if (Valid) sum += equation;
        }
        System.out.println("Part 1: " + sum);
    }


    private static void solvePart2() {
        for (String line : input) {
            long equation = getEquation(line);

            boolean Valid = sumOfArrayList(numList) == equation;
            if (isValid(numList, equation, true)) Valid = true;

            if (Valid) sum2 += equation;
        }
        System.out.println("Part 2: " + sum2);
    }

    private static boolean isValid(List<Long> numbers, long equation, boolean isPart2) {
        if (numbers.size() == 1) return numbers.getFirst() == equation;
        else {
            List<Long> plusList = new ArrayList<>();
            List<Long> multiList = new ArrayList<>();
            List<Long> cockList = new ArrayList<>();

            String tempFinal = numbers.get(0) + String.valueOf(numbers.get(1));

            multiList.add(numbers.get(0) * numbers.get(1));
            plusList.add(numbers.get(0) + numbers.get(1));
            cockList.add(Long.parseLong(tempFinal));

            numbers.removeFirst();
            numbers.removeFirst();
            for (long number : numbers) {
                plusList.add(number);
                multiList.add(number);
                cockList.add(number);
            }
            if (isValid(plusList, equation, isPart2)) return true;
            if (isValid(multiList, equation, isPart2)) return true;
            if (isValid(cockList, equation, isPart2) && isPart2) return true;
        }
        return false;
    }

    private static long getEquation(String line) {
        String[] temp = line.split(": ");
        String strEquation = temp[0];
        long equation = Long.parseLong(strEquation);
        numList = new ArrayList<>();
        for (String strNum : temp[1].split(" ")) numList.add(Long.parseLong(strNum));
        return equation;
    }

    private static long sumOfArrayList(List<Long> list) {
        long sum = 0;
        for (long i : list) sum += i;
        return sum;
    }
}