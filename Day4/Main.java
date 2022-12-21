package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(problem1());
        System.out.println(problem2());
    }

    public static int problem1() throws FileNotFoundException {
        int totalOverlap = 0;
        Scanner pairReader = new Scanner(new File("Day4/input.txt"));
        while (pairReader.hasNextLine()) {
            Scanner lineReader = new Scanner(pairReader.nextLine());
            lineReader.useDelimiter("-|,");
            int elf1Low = Integer.parseInt(lineReader.next());
            int elf1High = Integer.parseInt(lineReader.next());
            int elf2Low = Integer.parseInt(lineReader.next());
            int elf2High = Integer.parseInt(lineReader.next());
            if (elf2High - elf2Low > elf1High - elf1Low) {
                if (elf1Low >= elf2Low && elf1High <= elf2High) {
                    ++totalOverlap;
                }
            } else {
                if (elf2Low >= elf1Low && elf2High <= elf1High) {
                    ++totalOverlap;
                }
            }
        }

        return totalOverlap;
    }

    public static int problem2() throws FileNotFoundException {
        int totalOverlap = 0;
        Scanner pairReader = new Scanner(new File("Day4/input.txt"));

        while (pairReader.hasNextLine()) {
            Scanner lineReader = new Scanner(pairReader.nextLine());
            lineReader.useDelimiter("-|,");
            int elf1Low = Integer.parseInt(lineReader.next());
            int elf1High = Integer.parseInt(lineReader.next());
            int elf2Low = Integer.parseInt(lineReader.next());
            int elf2High = Integer.parseInt(lineReader.next());
            Set<Integer> elf1 = new HashSet<>();
            Set<Integer> elf2 = new HashSet<>();

            for (int i = elf1Low; i <= elf1High; i++) {
                elf1.add(i);
            }
            for (int i = elf2Low; i <= elf2High; i++) {
                elf2.add(i);
            }
            elf1.retainAll(elf2);
            if (elf1.size() > 0) {
                totalOverlap++;
            }
        }

        return totalOverlap;
    }
}
