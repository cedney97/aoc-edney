package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final boolean GIVENS = false;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(problem1());
        System.out.println(problem2());
    }

    public static int problem1() throws FileNotFoundException {
        Scanner gridReader;
        if (GIVENS) {
            gridReader = new Scanner(new File("Day8/given.txt"));
        } else {
            gridReader = new Scanner(new File("Day8/input.txt"));
        }

        int totalTrees = 0;
        List<List<Integer>> grid = new ArrayList<>();
        while (gridReader.hasNextLine()) {
            List<Integer> row = new ArrayList<>();
            String line = gridReader.nextLine();
            for (char c : line.toCharArray()) {
                row.add((int) (c - 48));
            }
            grid.add(row);
        }

        // printGrid(grid);

        // Add edges
        totalTrees += grid.size() * 2 + (grid.get(0).size() - 2) * 2;

        for (int i = 1; i < grid.size() - 1; ++i) {
            for (int j = 1; j < grid.get(0).size() - 1; ++j) {
                int height = grid.get(i).get(j);
                boolean isVisible = true;
                for (int up = 0; up < i && isVisible; ++up) {
                    isVisible = grid.get(up).get(j) < height;
                }
                if (isVisible) {
                    // System.out.println(i + ", " + j + " is visible");
                    ++totalTrees;
                } else {
                    isVisible = true;
                    for (int down = i + 1; down < grid.size() && isVisible; ++down) {
                        isVisible = grid.get(down).get(j) < height;
                    }
                    if (isVisible) {
                        // System.out.println(i + ", " + j + " is visible");
                        ++totalTrees;
                    } else {
                        isVisible = true;
                        for (int left = 0; left < j && isVisible; ++left) {
                            isVisible = grid.get(i).get(left) < height;
                        }
                        if (isVisible) {
                            // System.out.println(i + ", " + j + " is visible");
                            ++totalTrees;
                        } else {
                            isVisible = true;
                            for (int right = j + 1; right < grid.get(0).size() && isVisible; ++right) {
                                isVisible = grid.get(i).get(right) < height;
                            }
                            if (isVisible) {
                                // System.out.println(i + ", " + j + " is visible");
                                ++totalTrees;
                            }
                        }
                    }
                }
            }
        }

        return totalTrees;
    }

    public static int problem2() throws FileNotFoundException {
        Scanner gridReader;
        if (GIVENS) {
            gridReader = new Scanner(new File("Day8/given.txt"));
        } else {
            gridReader = new Scanner(new File("Day8/input.txt"));
        }

        int maxScenic = -1;
        List<List<Integer>> grid = new ArrayList<>();
        while (gridReader.hasNextLine()) {
            List<Integer> row = new ArrayList<>();
            String line = gridReader.nextLine();
            for (char c : line.toCharArray()) {
                row.add((int) (c - 48));
            }
            grid.add(row);
        }

        for (int i = 0; i < grid.size(); ++i) {
            for (int j = 0; j < grid.get(0).size(); ++j) {
                int height = grid.get(i).get(j);
                int up = 0;
                int down = 0;
                int left = 0;
                int right = 0;

                boolean blocked = false;
                for (int k = i - 1; k >= 0 && !blocked; --k) {
                    if (grid.get(k).get(j) < height) {
                        ++up;
                    } else {
                        blocked = true;
                    }
                }
                if (blocked) {
                    ++up;
                }
                blocked = false;
                for (int k = i + 1; k < grid.size() && !blocked; ++k) {
                    if (grid.get(k).get(j) < height) {
                        ++down;
                    } else {
                        blocked = true;
                    }
                }
                if (blocked) {
                    ++down;
                }
                blocked = false;
                for (int k = j - 1; k >= 0 && !blocked; --k) {
                    if (grid.get(i).get(k) < height) {
                        ++left;
                    } else {
                        blocked = true;
                    }
                }
                if (blocked) {
                    ++left;
                }
                blocked = false;
                for (int k = j + 1; k < grid.get(0).size() && !blocked; ++k) {
                    if (grid.get(i).get(k) < height) {
                        ++right;
                    } else {
                        blocked = true;
                    }
                }
                if (blocked) {
                    ++right;
                }
                if (up * down * left * right >= maxScenic) {
                    // System.out.println("max at " + i + " " + j);
                    maxScenic = up * down * left * right;
                }
            }
        }

        return maxScenic;
    }

    public static void printGrid(List<List<Integer>> grid) {

        for (List<Integer> row : grid) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }
}
