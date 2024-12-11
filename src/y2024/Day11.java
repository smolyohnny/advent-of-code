package y2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11 {
    private static final String input;
    static {
        try {
            input = Files.readString(Path.of("src\\y2024\\inputs\\day11.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final List<Long> stones = new ArrayList<>();

    public static void main(String[] args) {
        String[] chopped = input.split(" ");
        for (String str : chopped) {
            stones.add(Long.parseLong(str));
        }
        System.out.println(Arrays.toString(stones.toArray()));
        for (int i = 0; i < 75; i++) {
            blink();
            //System.out.println(Arrays.toString(stones.toArray()));
            System.out.println(i);
        }
        System.out.println(stones.size());


    }
    
    private static void blink() {
        int index = 0;
        List<Long> tempStones = new ArrayList<>(stones);
        for (Long num : stones) {
            if (num == 0) {
                tempStones.remove(index);
                tempStones.add(index, 1L);
                index++;
                continue;
            }
            int length = String.valueOf(num).length();
            if (length % 2 == 0) {
                int index2 = 1;
                StringBuilder num1 = new StringBuilder();
                StringBuilder num2 = new StringBuilder();
                for (char ch : String.valueOf(num).toCharArray()) {
                    if (index2 <= length/2) {
                        num1.append(ch);
                    } else num2.append(ch);
                    index2++;
                }
                tempStones.remove(index);
                tempStones.add(index, Long.parseLong(num2.toString()));
                tempStones.add(index, Long.parseLong(num1.toString()));
                index += 2;
                continue;
            }
            else {
                Long aLong = tempStones.get(index);
                tempStones.remove(index);
                tempStones.add(index, aLong*2024);
            }
            index++;    
        }
        stones.clear();
        stones.addAll(tempStones);
    }



}
