package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final int NUM_STACKS = 9;
    public static final int HEIGHT = 8;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(problem1());
        System.out.println(problem2());
    }

    public static String problem1() throws FileNotFoundException {
        Scanner boxAndMoveReader = new Scanner(new File("Day5/input.txt"));
        List<List<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < NUM_STACKS; i++) {
            stacks.add(new ArrayList<>());
        }

        for (int i = 0; i < HEIGHT; ++i) {
            String line = boxAndMoveReader.nextLine();
            for (int j = 1; j < line.length(); j += 4) {
                if (Character.isAlphabetic(line.charAt(j))) {
                    stacks.get(j / 4).add(line.charAt(j));
                }
            }
        }

        // for (List<Character> l : stacks) {
        // for (char c : l) {
        // System.out.print(c + " ");
        // }
        // System.out.println();
        // }

        boxAndMoveReader.nextLine();
        boxAndMoveReader.nextLine();
        while (boxAndMoveReader.hasNextLine()) {
            String line = boxAndMoveReader.nextLine();
            Scanner moveReader = new Scanner(line);
            moveReader.useDelimiter("move | from | to ");

            // System.out.println(moveReader.nextInt() + "," + moveReader.nextInt() + "," +
            // moveReader.nextInt());

            int move = moveReader.nextInt();
            int from = moveReader.nextInt() - 1;
            int to = moveReader.nextInt() - 1;

            for (int i = 0; i < move; ++i) {
                stacks.get(to).add(0, stacks.get(from).get(0));
                stacks.get(from).remove(0);
            }
            moveReader.close();
        }

        // System.out.println("----------");

        // for (List<Character> l : stacks) {
        // for (char c : l) {
        // System.out.print(c + " ");
        // }
        // System.out.println();
        // }

        String tops = "";
        for (List<Character> l : stacks) {
            tops += l.get(0);
        }

        return tops;
    }

    public static String problem2() throws FileNotFoundException {
        Scanner boxAndMoveReader = new Scanner(new File("Day5/input.txt"));
        List<List<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < NUM_STACKS; i++) {
            stacks.add(new ArrayList<>());
        }

        for (int i = 0; i < HEIGHT; ++i) {
            String line = boxAndMoveReader.nextLine();
            for (int j = 1; j < line.length(); j += 4) {
                if (Character.isAlphabetic(line.charAt(j))) {
                    stacks.get(j / 4).add(line.charAt(j));
                }
            }
        }

        boxAndMoveReader.nextLine();
        boxAndMoveReader.nextLine();
        while (boxAndMoveReader.hasNextLine()) {
            String line = boxAndMoveReader.nextLine();
            Scanner moveReader = new Scanner(line);
            moveReader.useDelimiter("move | from | to ");

            // System.out.println(moveReader.nextInt() + "," + moveReader.nextInt() + "," +
            // moveReader.nextInt());

            int move = moveReader.nextInt();
            int from = moveReader.nextInt() - 1;
            int to = moveReader.nextInt() - 1;

            List<Character> toMove = stacks.get(from).subList(0, move);
            for (int j = toMove.size() - 1; j >= 0; --j) {
                stacks.get(to).add(0, toMove.get(j));
            }
            for (int j = 0; j < move; ++j) {
                stacks.get(from).remove(0);
            }
            moveReader.close();
        }

        String tops = "";
        for (List<Character> l : stacks) {
            tops += l.get(0);
        }

        return tops;
    }
}
