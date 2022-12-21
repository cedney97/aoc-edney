package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static final boolean GIVENS = false;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(problem1());
        System.out.println(problem2());
    }

    public static int problem1() throws FileNotFoundException {
        if (GIVENS) {
            Scanner stream = new Scanner(new File("Day6/givens.txt"));
            while (stream.hasNextLine()) {
                char[] message = stream.nextLine().toCharArray();
                for (int i = 3; i < message.length; ++i) {
                    Set<Character> code = new HashSet<>();
                    code.add(message[i]);
                    code.add(message[i - 1]);
                    code.add(message[i - 2]);
                    code.add(message[i - 3]);
                    if (code.size() == 4) {
                        System.out.println(i + 1);
                        break;
                    }
                }
            }
        } else {
            Scanner stream = new Scanner(new File("Day6/input.txt"));
            char[] message = stream.nextLine().toCharArray();
            for (int i = 3; i < message.length; ++i) {
                Set<Character> code = new HashSet<>();
                code.add(message[i]);
                code.add(message[i - 1]);
                code.add(message[i - 2]);
                code.add(message[i - 3]);
                if (code.size() == 4) {
                    return i + 1;
                }
            }
        }

        return 0;
    }

    public static int problem2() throws FileNotFoundException {

        if (GIVENS) {
            Scanner stream = new Scanner(new File("Day6/givens.txt"));
            while (stream.hasNextLine()) {
                char[] message = stream.nextLine().toCharArray();
                for (int i = 13; i < message.length; ++i) {
                    Set<Character> code = new HashSet<>();
                    for (int j = 0; j <= 13; ++j) {
                        code.add(message[i - j]);
                    }
                    if (code.size() == 14) {
                        System.out.print(i + 1);
                        System.out.println();
                        break;
                    }
                }
            }
        } else {
            Scanner stream = new Scanner(new File("Day6/input.txt"));
            char[] message = stream.nextLine().toCharArray();
            for (int i = 13; i < message.length; ++i) {
                Set<Character> code = new HashSet<>();
                for (int j = 0; j <= 13; ++j) {
                    code.add(message[i - j]);
                }
                if (code.size() == 14) {
                    return i + 1;
                }
            }
        }

        // Scanner stream = new Scanner(new File("Day6/input.txt"));
        // char[] message = stream.nextLine().toCharArray();
        // for (int i = 13; i < message.length; ++i) {
        // Set<Character> code = new HashSet<>();
        // for (int j = 0; j <= i; ++j) {
        // code.add(message[i - j]);
        // }
        // if (code.size() == 14) {
        // return i + 1;
        // }
        // }
        return 0;
    }
}
