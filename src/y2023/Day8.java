package y2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day8 {

    private static String lastStep = "AAA";
    private static String directions = "";
    private static int stepCount = 0;


    public static void main(String[] args) {
        Path path = Path.of("src\\y2023\\inputs\\day8.txt");
        List<String[]> strArrList = new ArrayList<>();

        fillList(path, strArrList);

        System.out.println(countSteps(directions, strArrList));
    }

    private static void fillList(Path path, List<String[]> strArrList) {
        try {
            List<String> strings = Files.readAllLines(path);
            int lineCount = 0;
            for (String line : strings) {
                if (lineCount == 0) {
                    directions = line;
                } else if (lineCount > 1) {
                    String[] combination = getStrings(line);
                    strArrList.add(combination);
                }
                lineCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] getStrings(String line) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            if (i < 3) sb1.append(line.charAt(i));
            else if (i > 6 && i < 10) sb2.append(line.charAt(i));
            else if (i > 11 && i < 15) sb3.append(line.charAt(i));
        }
        return new String[]{sb1.toString(), sb2.toString(), sb3.toString()};
    }

    private static int countSteps(String directions, List<String[]> strArrList) {
        boolean IsFinished = false;
        for (char step : directions.toCharArray()) {
            if (!lastStep.equals("ZZZ")) {
                stepCount++;
                if (step == 'L') {
                    lastStep = nextStep(lastStep,true, strArrList);
                } else if (step == 'R') {
                    lastStep = nextStep(lastStep,false, strArrList);
                }
            } else {
                IsFinished = true;
            }
        }
        if(!IsFinished) countSteps(directions,strArrList);
        return stepCount;
    }

    private static String nextStep(String step, boolean IsLeft, List<String[]> strArrList) {
        String nextStep = "";
            for (String[] strings : strArrList) {
               if (Objects.equals(strings[0], step)){
                    if (IsLeft) {
                        nextStep = strings[1];
                        return nextStep;
                    }else {
                        nextStep = strings[2];
                        return nextStep;
                    }
               }
            }
        return nextStep;
    }
}
