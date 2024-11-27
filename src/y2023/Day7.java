package y2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day7 {

    static final private int fiveOfAKind = 7;
    static final private int fourOfAKind = 6;
    static final private int fullHouse = 5;
    static final private int threeOfAKind = 4;
    static final private int twoPair = 3;
    static final private int onePair = 2;
    static final private int highCard = 1;

    public static void main(String[] args) {
        Path path = Path.of("src\\y2023\\inputs\\day7.txt");
        List<String[]> stringArrList = new ArrayList<>();

        try {
            List<String> strings = Files.readAllLines(path);
            for (String list : strings) {
                stringArrList.add(list.split(" "));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stringArrList.sort(com);
        for (String[] strArr : stringArrList) {
            System.out.println(strArr[0]);
        }
        System.out.println();

        int result = 0;
        for (int i = 1; i <= stringArrList.size(); i++) {
            String[] tempStr = stringArrList.get(i-1);
            result += (Integer.parseInt(tempStr[1])*i);
        }
        System.out.println(result);

    }

    //    https://youtu.be/ZA2oNhtNk3w
    static Comparator<String[]> com = new Comparator<String[]>() {
        public int compare(String[] s1, String[] s2) {
            if (checkCombination(s1[0]) > checkCombination(s2[0])) return 1;
            else if (checkCombination(s1[0]) < checkCombination(s2[0])) return -1;
            else if(IsGreater(s1[0], s2[0])) return 1;
            else return -1;
        }
    };

    private static boolean IsGreater(String s1, String s2) {
        int[] arr1 = new int[5];
        int[] arr2 = new int[5];
        for (int i = 0; i < 5; i++) {
            if(!Character.isDigit(s1.toCharArray()[i])) {
                arr1[i] = letterReplace(s1.toCharArray()[i]);
            } else {
                arr1[i] = Integer.parseInt(String.valueOf(s1.charAt(i)));
            }
            if(!Character.isDigit(s2.toCharArray()[i])) {
                arr2[i] = letterReplace(s2.toCharArray()[i]);
            }
            else {
                arr2[i] = Integer.parseInt(String.valueOf(s2.charAt(i)));
            }
        }

        for (int i = 0; i < 5; i++) {
            if (arr1[i] > arr2[i]){
                return true;
            }
            else if (arr1[i] < arr2[i]) {
                return false;
            }
        }
        return false;
    }

    private static int checkCombination(String str) {
        int pentos = 0;
        int quatros = 0;
        int trios = 0;
        int pairs = 0;
        char[] usedCharArray = new char[5];
        boolean IsDuplicateEntry = false;
        int charArrPosition = 0;
        for (char ch : str.toCharArray()) {
            for (char arrCh : usedCharArray) {
                if (arrCh == ch) {
                    IsDuplicateEntry = true;
                    break;
                }
            }
            if (!IsDuplicateEntry) {
                int charCounter = 0;
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == ch) charCounter++;
                }
                if (charCounter == 5) pentos++;
                else if (charCounter == 4) quatros++;
                else if (charCounter == 3) trios++;
                else if (charCounter == 2) pairs++;
            }
            IsDuplicateEntry = false;
            usedCharArray[charArrPosition] = ch;
            charArrPosition++;
        }
        if (pentos == 1) return fiveOfAKind;
        else if (quatros == 1) return fourOfAKind;
        else if (trios == 1 && pairs == 1) return fullHouse;
        else if (trios == 1) return threeOfAKind;
        else if (pairs == 2) return twoPair;
        else if (pairs == 1) return onePair;
        else return highCard;
    }

    private static int letterReplace(char ch) {
        return switch (ch) {
            case 'A' -> 14;
            case 'K' -> 13;
            case 'Q' -> 12;
            case 'J' -> 11;
            case 'T' -> 10;
            default -> 0;
        };
    }
}

