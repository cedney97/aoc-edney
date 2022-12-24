package Day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Monkey {
    private List<Integer> items;
    private List<BigInteger> bigItems;
    private boolean big;
    private List<Monkey> others;
    private int inspections;
    private String op;
    private int num;
    private boolean square;
    private int testDiv;
    private int trueCase;
    private int falseCase;

    public Monkey(List<Integer> startingItems, List<BigInteger> bigItems, String op, int test, int trueCase,
            int falseCase, boolean big) {
        this.big = big;
        if (this.big) {
            this.bigItems = bigItems;
        } else {
            this.items = startingItems;
        }
        this.inspections = 0;
        this.testDiv = test;
        this.trueCase = trueCase;
        this.falseCase = falseCase;
        this.others = new ArrayList<>();

        Scanner operation = new Scanner(op);
        operation.next();
        this.op = operation.next();
        if (operation.hasNextInt()) {
            this.square = false;
            this.num = operation.nextInt();
        } else {
            this.square = true;
        }
        operation.close();
    }

    public void round() {
        if (!this.big) {
            for (int item : items) {
                int newWorry = doOperation(item);
                newWorry /= 3;
                if (newWorry % testDiv == 0) {
                    others.get(trueCase).addItem(newWorry);
                } else {
                    others.get(falseCase).addItem(newWorry);
                }
                ++this.inspections;
            }
            this.items = new ArrayList<>();
        } else {
            for (BigInteger item : bigItems) {
                BigInteger newWorry = doOperation(item);
                if (newWorry.mod(new BigInteger(testDiv + "")).equals(BigInteger.ZERO)) {
                    others.get(trueCase).addItem(newWorry);
                } else {
                    others.get(falseCase).addItem(newWorry);
                }
                ++this.inspections;
            }
            this.bigItems = new ArrayList<>();
        }
    }

    public int doOperation(int item) {
        if (this.square) {
            switch (op) {
                case "+":
                    return item + item;
                case "*":
                    return item * item;
                case "-":
                    return item * item;
                case "/":
                    return item / item;
                default:
                    System.out.println("Uh oh");
                    return 0;
            }
        } else {
            switch (op) {
                case "+":
                    return item + num;
                case "*":
                    return item * num;
                case "-":
                    return item * num;
                case "/":
                    return item / num;
                default:
                    System.out.println("Uh oh");
                    return 0;
            }
        }
    }

    public BigInteger doOperation(BigInteger item) {
        if (this.square) {
            switch (op) {
                case "+":
                    return item.add(item);
                case "*":
                    return item.multiply(item);
                case "-":
                    return item.subtract(item);
                case "/":
                    return item.divide(item);
                default:
                    System.out.println("Uh oh");
                    return new BigInteger("0");
            }
        } else {
            switch (op) {
                case "+":
                    return item.add(new BigInteger(num + ""));
                case "*":
                    return item.multiply(new BigInteger(num + ""));
                case "-":
                    return item.subtract(new BigInteger(num + ""));
                case "/":
                    return item.divide(new BigInteger(num + ""));
                default:
                    System.out.println("Uh oh");
                    return new BigInteger("0");
            }
        }
    }

    public void addItem(int item) {
        this.items.add(item);
    }

    public void addItem(BigInteger item) {
        this.bigItems.add(item);
    }

    public int getInspections() {
        return this.inspections;
    }

    public void addOthers(List<Monkey> others) {
        this.others = others;
    }

    public void printItems() {
        for (int item : items) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        String toString = "Monkey " + this.others.indexOf(this) + " has ";
        toString += this.inspections + " inspections with ";
        if (this.big) {
            for (BigInteger item : bigItems) {
                toString += item + " ";
            }
        } else {
            for (int item : items) {
                toString += item + " ";
            }
        }
        return toString;
    }
}
