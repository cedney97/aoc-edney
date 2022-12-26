package Day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private static final boolean GIVENS = true;

    public static Scanner setUpScanner() throws FileNotFoundException {
        Scanner s;
        if (GIVENS) {
            s = new Scanner(new File("Day13/given.txt"));
        } else {
            s = new Scanner(new File("Day13/input.txt"));
        }
        return s;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(problem1());
        System.out.println(problem2());
    }

    public static int problem1() throws FileNotFoundException {
        Scanner pairReader = setUpScanner();
        int pairIndex = 0;
        while (pairReader.hasNextLine()) {
            String leftFull = pairReader.nextLine();
            String rightFull = pairReader.nextLine();

            checkPair(leftFull, rightFull);

            ++pairIndex;
            if (pairReader.hasNextLine()) {
                pairReader.nextLine();
            }
        }
        return 0;
    }

    public static boolean checkPair(String leftFull, String rightFull) {
        String leftList = leftFull.substring(1, leftFull.length() - 1);
        String rightList = rightFull.substring(1, rightFull.length() - 1);
        int longestLength = leftList.length() > rightList.length() ? leftList.length() : rightList.length();
        Scanner leftReader = new Scanner(leftList);
        leftReader.useDelimiter(",");
        Scanner rightReader = new Scanner(rightList);
        rightReader.useDelimiter(",");
        if (leftReader.hasNextInt() && rightReader.hasNextInt()) {
            if (checkInt(leftReader.nextInt(), rightReader.nextInt())) {
                return true;
            } else {
                return checkPair(getRest(leftReader), getRest(rightReader));
            }
        }
        
        return false;
    }

    public static boolean checkInt(int left, int right) {
        if (left < right) {
            return true;
        } else if (left == right) {
            return false;
        }
        return left <= right;
    }

    public static String getRest(Scanner s) {
        s.useDelimiter("\\A");
        return s.next();
    }

    public static int problem2() throws FileNotFoundException {
        return 0;
    }
}