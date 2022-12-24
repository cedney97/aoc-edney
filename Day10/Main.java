package Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final boolean GIVENS = false;
    private static final List<Integer> STRENGTHS = List.of(20, 60, 100, 140, 180, 220);

    public static void main (String[] args) throws FileNotFoundException {
        System.out.println(problem1());
        problem2();
    }

    public static Scanner setUpScanner() throws FileNotFoundException {
        return GIVENS ? new Scanner(new File("Day10/given.txt")) : new Scanner(new File("Day10/input.txt"));
    }

    public static int problem1() throws FileNotFoundException {
        Scanner cmdReader = setUpScanner();
        int x = 1;
        int signalTotal = 0;
        int cycle = 0;

        while(cmdReader.hasNextLine()) {
            Scanner cmd = new Scanner(cmdReader.nextLine());
            String next = cmd.next();
            if (next.equals("noop")) {
                ++cycle;
                if (checkCycle(cycle)) {
                    signalTotal += x * cycle;
                }
            } else if (next.equals("addx")) {
                ++cycle;
                if (checkCycle(cycle)) {
                    signalTotal += x * cycle;
                }
                ++cycle;
                if (checkCycle(cycle)) {
                    signalTotal += x * cycle;
                }
                x += cmd.nextInt();
            } else {
                System.out.println(next);
            }
        }

        return signalTotal;
    }

    public static boolean checkCycle(int cycle) {
        return STRENGTHS.contains(cycle);
    }

    public static void problem2() throws FileNotFoundException {
        Scanner cmdReader = setUpScanner();
        int x = 1;
        int cycle = 0;
        char[] row = new char[40];
        Arrays.fill(row, '.');
        row[0] = '#';
        row[1] = '#';
        row[2] = '#';

        while(cmdReader.hasNextLine()) {
            Scanner cmd = new Scanner(cmdReader.nextLine());
            String next = cmd.next();
            if (next.equals("noop")) {
                getCharacter(cycle, row);
                cycle++;
            } else if (next.equals("addx")) {
                getCharacter(cycle, row);
                cycle++;
                getCharacter(cycle, row);
                cycle++;
                Arrays.fill(row, '.');
                int move = cmd.nextInt();
                x += move;
                if (x - 1 >= 0 && x - 1 <= 39) {
                    row[x - 1] = '#';
                }
                if (x + 1 >= 0 && x + 1 <= 39) {
                    row[x + 1] = '#';
                }
                if (x >= 0 && x <= 39) {
                    row[x] = '#';
                }
            } else {
                System.out.println(next);
            }
        }
    }

    public static void getCharacter(int cycle, char[] row) {
        System.out.print(row[cycle % 40]);
        if ((cycle + 1) % 40 == 0) {
            System.out.println();
        }
    }
}
