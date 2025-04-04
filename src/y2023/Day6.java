package y2023;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day6 {
    public static void main(String[] args) {
        Path path = Path.of("src\\y2023\\inputs\\day6.txt");
        try {

            List<String> input = Files.readAllLines(path);
            String[] timesStr = String.valueOf(input.get(0))
                    .trim()
                    .replaceAll(" +", " ")
                    .replace("Time: ", "")
                    .split(" ");
            String[] distancesStr = String.valueOf(input.get(1))
                    .trim()
                    .replaceAll(" +", " ")
                    .replace("Distance: ", "")
                    .split(" ");


            int[] times = new int[timesStr.length];
            int[] distances = new int[distancesStr.length];

            for (int i = 0; i < times.length; i++) {
                times[i] = Integer.parseInt(timesStr[i]);
                distances[i] = Integer.parseInt(distancesStr[i]);
            }
            System.out.println(Arrays.toString(times));
            System.out.println(Arrays.toString(distances));

            int totalTimesBeaten = 1;
            int i = 0;
            for (int record : distances) {
                int timesBeaten = 0;
                for (int j = 1; j < times[i]; j++) {
                    if (((times[i] - j) * j) > record) {
                        timesBeaten++;
                    }
                }
                totalTimesBeaten *= timesBeaten;
                i++;
            }
            System.out.println(totalTimesBeaten);

            //part 2

            String time = String.valueOf(input.get(0))
                    .trim()
                    .replaceAll(" +", "")
                    .replace("Time:", "");
            String distance = String.valueOf(input.get(1))
                    .trim()
                    .replaceAll(" +", "")
                    .replace("Distance:", "");

            int timesBeaten = 0;
            for (long j = 1; j < Integer.parseInt(time); j++) {
                if (((Long.parseLong(time) - j) * j) > Long.parseLong(distance)) {
                    timesBeaten++;
                }
            }
            System.out.println(timesBeaten);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
