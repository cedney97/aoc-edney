package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static final int WIN = 6;
    public static final int DRAW = 3;
    public static final int LOSE = 0;

    public static void main (String[] args) throws FileNotFoundException {
        System.out.println(problem1());
        System.out.println(problem2());
    }

    public static int problem1 () throws FileNotFoundException {
        Scanner stratReader = new Scanner(new File("Day2/input.txt"));
        int sum = 0;
        while(stratReader.hasNextLine()) {
            // String line = stratReader.nextLine();
            // Scanner lineScanner = new Scanner(line);
            String oppMove = stratReader.next();
            String myMove = stratReader.next();

            int oppInt = oppMove.charAt(0) - 'A';
            int myInt = myMove.charAt(0) - 'X';

            sum += myInt + 1;

            if ((oppInt + 1) % 3 == (myInt % 3)) {
                // System.out.println("WIN -\t" + oppMove.charAt(0) + " vs " + myMove.charAt(0));
                sum += WIN;
            } else if ((myInt + 1 % 3) == (oppInt % 3)) {
                // System.out.println("LOSE -\t" + oppMove.charAt(0) + " vs " + myMove.charAt(0));
                sum += LOSE;
            } else if (myInt == oppInt) {
                // System.out.println("DRAW -\t" + oppMove.charAt(0) + " vs " + myMove.charAt(0));
                sum += DRAW;
            }
        }
        return sum;
    }

    public static int problem2 () throws FileNotFoundException {
        int sum = 0;
        Scanner stratReader = new Scanner(new File("Day2/input.txt"));

        while (stratReader.hasNextLine()) {
            String oppMove = stratReader.next();
            String ending = stratReader.next();

            int oppInt = oppMove.charAt(0) - 'A' + 1;
            int endInt = ending.charAt(0) - 'X';

            if (endInt == 0) {
                sum += LOSE;
                if (oppInt == 1) {
                    sum += 3;
                } else if (oppInt == 2) {
                    sum += 1;
                } else if (oppInt == 3) {
                    sum += 2;
                }
            } else if (endInt == 1) {
                sum += DRAW + oppInt;
            } else if (endInt == 2) {
                sum += WIN;
                if (oppInt == 1) {
                    sum += 2;
                } else if (oppInt == 2) {
                    sum += 3;
                } else if (oppInt == 3) {
                    sum += 1;
                }
            }

        }

        return sum;
    }
}
