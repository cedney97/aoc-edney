package Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static Map<List<Integer>, Set<List<Integer>>> nbs;
    static List<Integer> end = null;

    public static void main(String[] args) throws FileNotFoundException {
        Map<List<Integer>, Integer> map = new HashMap<>();
        nbs = new HashMap<>();
        List<Integer> start = null;
        List<List<Integer>> starts = new ArrayList<>();
        Scanner scanner = setUpScanner();

        int x, y = 0;
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for(x = 0; x < line.length(); ++x) {
                char c = line.charAt(x);
                if(c == 'S') {
                    c = 'a';
                    start = List.of(x, y);
                }
                else if(c == 'E') {
                    c = 'z';
                    end = List.of(x, y);
                }
                if(c == 'a') {
                    starts.add(List.of(x, y));
                }
                map.put(List.of(x, y), c - 'a');
                nbs.put(List.of(x, y), new HashSet<>());
            }
            ++y;
        }
        for(var node : map.keySet()) {
            x = node.get(0);
            y = node.get(1);
            for(var coords : List.of(List.of(0, 1), List.of(1, 0), List.of(0, -1), List.of(-1, 0))) {
                int xx = x + coords.get(0);
                int yy = y + coords.get(1);
                var newCoords = List.of(xx, yy);
                if(map.containsKey(newCoords) && map.get(newCoords) <= map.get(node) + 1)
                    nbs.get(node).add(newCoords);
            }
        }

        System.out.println(bfs(List.of(start)));
        System.out.println(bfs(starts));
    }

    static int bfs(List<List<Integer>> starts) {
        Queue<List<Integer>> q = new LinkedList<>(starts);
        Map<List<Integer>, Integer> dist = new HashMap<>();
        for(var start : starts) {
            dist.put(start, 0);
        }

        while(!q.isEmpty()) {
            var node = q.poll();
            if(node.equals(end)) {
                return dist.get(node);
            }
            for(var nb : nbs.get(node)) {
                if(!dist.containsKey(nb)) {
                    dist.put(nb, dist.get(node) + 1);
                    q.add(nb);
                }
            }
        }

        return -1;
    }

    static String example = """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi
            """;
    
    private static final boolean GIVENS = false;
    // private static int startRow, startCol, endRow, endCol;

    public static Scanner setUpScanner() throws FileNotFoundException {
        Scanner s;
        if (GIVENS) {
            s = new Scanner(new File("Day12/given.txt"));
        } else {
            s = new Scanner(new File("Day12/input.txt"));
        }
        return s;
    }

    // public static void main (String[] args) throws FileNotFoundException {
    //     System.out.println(problem1());
    //     System.out.println(problem2());
    // }

    // public static int problem1() throws FileNotFoundException {
    //     Scanner rowScanner = setUpScanner();
    //     Scanner colScanner = setUpScanner();
    //     Scanner gridScanner = setUpScanner();

    //     int rows = getRows(rowScanner);
    //     int cols = getCols(colScanner);

    //     char[][] grid = new char[rows][cols];
    //     boolean[][] walkedGrid = new boolean[rows][cols];

    //     int r = 0;
    //     while (gridScanner.hasNextLine()) {
    //         String line = gridScanner.nextLine();
    //         for (int i = 0; i < line.length(); ++i) {
    //             char c = line.charAt(i);
    //             if (c == 'S') {
    //                 grid[r][i] = 'a';
    //                 startRow = r;
    //                 startCol = i;
    //             } else if (c == 'E') {
    //                 grid[r][i] = 'z';
    //                 endRow = r;
    //                 endCol = i;
    //             } else {
    //                 grid[r][i] = c;
    //             }
    //         }
    //         ++r;
    //     }

    //     for (char[] row : grid) {
    //         for (char col : row) {
    //             System.out.print(col + " ");
    //         }
    //         System.out.println();
    //     }
    //     System.out.println("Start @ " + startRow + ", " + startCol);
    //     System.out.println("End @ " + endRow + ", " + endCol);

    //     Optional minPath = minPath(startRow, startCol, grid, walkedGrid, 'a');
    //     if (minPath.hasValue()) {
    //         return minPath.getValue();
    //     } else {
    //         return 0;
    //     }
    // }

    // public static int getRows(Scanner s) {
    //     int rows = 0;
    //     while(s.hasNextLine()) {
    //         s.nextLine();
    //         ++rows;
    //     }
    //     return rows;
    // }

    // public static int getCols(Scanner s) {
    //     return s.nextLine().length();
    // }

    // public static Optional minPath(int r, int c, char[][] grid, boolean[][] walkedGrid, char prev) {
    //     // Check bounds or already stepped
    //     if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || walkedGrid[r][c]) {
    //         return new Optional();
    //     }
    //     // Check if at the end
    //     if (r == endRow && c == endCol) {
    //         return new Optional(0);
    //     }
    //     // Check height constraint
    //     if (prev + 1 < grid[r][c]) {
    //         return new Optional();
    //     }
    //     walkedGrid[r][c] = true;
    //     Optional up = minPath(r - 1, c, grid, walkedGrid, grid[r][c]);
    //     Optional down = minPath(r + 1, c, grid, walkedGrid, grid[r][c]);
    //     Optional left = minPath(r, c - 1, grid, walkedGrid, grid[r][c]);
    //     Optional right = minPath(r, c + 1, grid, walkedGrid, grid[r][c]);
    //     walkedGrid[r][c] = false;

    //     Optional min = Optional.min(up, down, left, right);
    //     if (min.hasValue()) {
    //         System.out.println(r + ", " + c);
    //         return new Optional(min.getValue() + 1);
    //     } else {  
    //         return new Optional();
    //     }

    // }

    // public static int problem2() throws FileNotFoundException {
    //     return 0;
    // }

}
