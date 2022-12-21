package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws FileNotFoundException {
        Scanner flReader = new Scanner(new File("Problem 1/input.txt"));
        boolean newElf = true;
        int currElf = 0;
        int maxElf1 = -1;
        int maxElf2 = -1;
        int maxElf3 = -1;
        while (flReader.hasNextLine()) {
            String s = flReader.nextLine();
            if (!s.equals("")) {
                if (newElf) {
                    currElf = Integer.parseInt(s);
                    newElf = false;
                } else {
                    currElf += Integer.parseInt(s);
                }
            } else {
                if (currElf > maxElf1) {
                    int temp1 = maxElf1;
                    int temp2 = maxElf2;
                    maxElf1 = currElf;
                    maxElf2 = temp1;
                    maxElf3 = temp2;
                } else if (currElf > maxElf2) {
                    int temp = maxElf2;
                    maxElf2 = currElf;
                    maxElf3 = temp;
                } else if (currElf > maxElf3) {
                    maxElf3 = currElf;
                }
                currElf = 0;
                newElf = true;
            }
        }
        System.out.println(maxElf1 + maxElf2 + maxElf3);
    }
}