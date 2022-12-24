package Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final boolean GIVENS = true;
    private static boolean BIG = false;

    public static Scanner setUpScanner() throws FileNotFoundException {
        Scanner s;
        if (GIVENS) {
            s = new Scanner(new File("Day11/given.txt"));
        } else {
            s = new Scanner(new File("Day11/input.txt"));
        }
        return s;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(problem1());
        System.out.println(problem2());
    }

    public static List<Monkey> parseData() throws FileNotFoundException {
        Scanner monkeyReader = setUpScanner();
        monkeyReader.useDelimiter("\n\n");
        List<Monkey> monkeys = new ArrayList<>();

        while (monkeyReader.hasNext()) {
            Scanner monkeyData = new Scanner(monkeyReader.next());
            // Skip "Monkey N:"
            monkeyData.nextLine();
            // Skip "Starting items:"
            monkeyData.next();
            monkeyData.next();

            // Get starting items
            String itemString = monkeyData.nextLine();
            itemString = itemString.replaceAll(",", " ");
            Scanner items = new Scanner(itemString);
            List<Integer> startingItems = new LinkedList<>();
            List<BigInteger> bigStartingItems = new LinkedList<>();
            while (items.hasNextInt()) {
                if (BIG) {
                    bigStartingItems.add(new BigInteger(items.next()));
                } else {
                    startingItems.add(items.nextInt());
                }
            }
            items.close();

            // Skip "Operation: new ="
            monkeyData.next();
            monkeyData.next();
            monkeyData.next();
            // Get equation
            String op = monkeyData.nextLine();

            // Skip "Test: divisible by"
            monkeyData.next();
            monkeyData.next();
            monkeyData.next();
            // Get predicate number
            int div = monkeyData.nextInt();
            // Skip to next line
            monkeyData.next();

            // Skip "true: throw to monkey"
            monkeyData.next();
            monkeyData.next();
            monkeyData.next();
            monkeyData.next();
            // Get true throw
            int trueCase = monkeyData.nextInt();
            // Skip to next line
            monkeyData.next();

            // Skip "false: throw to monkey"
            monkeyData.next();
            monkeyData.next();
            monkeyData.next();
            monkeyData.next();
            // Get false throw
            int falseCase = monkeyData.nextInt();

            if (BIG) {
                monkeys.add(new Monkey(null, bigStartingItems, op, div, trueCase, falseCase, BIG));
            } else {
                monkeys.add(new Monkey(startingItems, null, op, div, trueCase, falseCase, BIG));
            }

            monkeyData.close();
        }

        for (Monkey m : monkeys) {
            m.addOthers(monkeys);
        }

        return monkeys;
    }

    public static int problem1() throws FileNotFoundException {
        BIG = false;
        List<Monkey> monkeys = parseData();

        int max = -1;
        int nextMax = -1;

        for (int i = 0; i < 20; ++i) {
            for (Monkey m : monkeys) {
                m.round();
            }
        }

        for (Monkey m : monkeys) {
            int inspections = m.getInspections();
            if (inspections > max) {
                nextMax = max;
                max = inspections;
            } else if (inspections > nextMax) {
                nextMax = inspections;
            }
        }

        return max * nextMax;
    }

    public static BigInteger problem2() throws FileNotFoundException {
        BIG = true;
        List<Monkey> monkeys = parseData();

        int max = -1;
        int nextMax = -1;

        for (int i = 0; i < 10000; ++i) {
            for (Monkey m : monkeys) {
                m.round();
            }
            if (i == 0 || i == 1 || i == 19 || i == 999 || i == 1999 || i == 2999 || i == 3999 || i == 4999 || i == 5999 || i == 6999 || i == 7999 || i == 8999 || i == 9999 ) {
                for (Monkey m : monkeys) {
                    System.out.println(m);
                }
                System.out.println("--------");
            }
        }

        for (Monkey m : monkeys) {
            int inspections = m.getInspections();
            if (inspections > max) {
                nextMax = max;
                max = inspections;
            } else if (inspections > nextMax) {
                nextMax = inspections;
            }
        }

        BigInteger m1 = new BigInteger(max + "");
        BigInteger m2 = new BigInteger(nextMax + "");
        System.out.println(max + ", " + nextMax);

        return m1.multiply(m2);
    }
}
