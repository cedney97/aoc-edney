package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(problem1());
        System.out.println(problem2());
    }

    public static int problem1() throws FileNotFoundException {
        int sum = 0;
        Scanner compReader = new Scanner(new File("Day3/input.txt"));

        while (compReader.hasNextLine()) {
            String rucksack = compReader.nextLine();
            char[] firstComp = rucksack.substring(0, rucksack.length() / 2).toCharArray();
            List<Character> secondComp = new LinkedList<>();
            for (char c : rucksack.substring(rucksack.length() / 2).toCharArray()) {
                secondComp.add(c);
            }
            // System.out.println(rucksack.substring(0, rucksack.length() / 2).length() + " "
            //         + rucksack.substring(rucksack.length() / 2).length());
            boolean checked = false;

            for (char c : firstComp) {
                if (secondComp.contains(c) && !checked) {
                    if (c >= 'a' && c <= 'z') {
                        sum += c - 'a' + 1;
                    } else if (c >= 'A' && c <= 'Z') {
                        sum += c - 'A' + 27;
                    }
                    checked = true;
                }
            }

        }

        return sum;
    }

    public static int problem2() throws FileNotFoundException {
        int sum = 0;
        Scanner compReader = new Scanner(new File("Day3/input.txt"));

        while (compReader.hasNextLine()) {
            char[] sack1 = compReader.nextLine().toCharArray();
            char[] sack2a = compReader.nextLine().toCharArray();
            char[] sack3a = compReader.nextLine().toCharArray();

            List<Character> sack2 = new LinkedList<>();
            List<Character> sack3 = new LinkedList<>();


            for (char c : sack2a) {
                sack2.add(c);
            }
            for (char c : sack3a) {
                sack3.add(c);
            }

            for (char c : sack1) {
                if (sack2.contains(c) && sack3.contains(c)) {
                    if (c >= 'a' && c <= 'z') {
                        sum += c - 'a' + 1;
                    } else if (c >= 'A' && c <= 'Z') {
                        sum += c - 'A' + 27;
                    }
                    break;
                }
            }
        }

        return sum;
    }
}
