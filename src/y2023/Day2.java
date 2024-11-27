package y2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        Path path = Path.of("src\\y2023\\inputs\\day2.txt");
        try{
            List<String> strings = Files.readAllLines(path);
            int finalResult = 0;
            int powerResult = 0;
            for (String line : strings) {
                String replaced = line.replace("Game ", "");
                String[] noIDs = replaced.split(": ");
                String[] noSemi = noIDs[1].split("; ");
                int r = 0;
                int g = 0;
                int b = 0;
                for (String string : noSemi) {
                    String[] ind = string.split(", ");
                    for (String s : ind) {
                        if (s.contains("red") && r < Integer.parseInt(s.replace(" red", "")))
                            r = Integer.parseInt(s.replace(" red", ""));
                        if (s.contains("green") && g < Integer.parseInt(s.replace(" green", "")))
                            g = Integer.parseInt(s.replace(" green", ""));
                        if (s.contains("blue") && b < Integer.parseInt(s.replace(" blue", "")))
                            b = Integer.parseInt(s.replace(" blue", ""));
                    }
                }
                int linePower = r * g * b;
                powerResult += linePower;
                if (r <= 12 && g <= 13 && b <= 14) finalResult += Integer.parseInt(noIDs[0]);
                //System.out.println(noIDs[0] + " " + finalResult);
            }
            System.out.println(finalResult);
            System.out.println(powerResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
