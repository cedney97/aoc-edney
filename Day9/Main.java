package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final boolean GIVENS = false;
    private static final boolean GIVENS_2 = false;
    private static final int X = 0;
    private static final int Y = 1;
    private static final int HEAD = 0;
    private static final int TAIL = 9;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(problem1());
        System.out.println(problem2());
    }

    public static int problem1() throws FileNotFoundException {
        Scanner moveReader;
        if (GIVENS) {
            moveReader = new Scanner(new File("Day9/given.txt"));
        } else {
            moveReader = new Scanner(new File("Day9/input.txt"));
        }

        Set<Point> tailPoints = new HashSet<>();

        int hx = 0;
        int hy = 0;
        int tx = 0;
        int ty = 0;
        tailPoints.add(new Point(tx, ty));

        while (moveReader.hasNextLine()) {
            String dir = moveReader.next();
            int mag = moveReader.nextInt();
            for (int i = 0; i < mag; ++i) {
                switch (dir) {
                    case "U":
                        ++hy;
                        Point tpu = moveTail(hx, hy, tx, ty);
                        tx = tpu.getX();
                        ty = tpu.getY();
                        tailPoints.add(tpu);
                        break;
                    case "D":
                        --hy;
                        Point tpd = moveTail(hx, hy, tx, ty);
                        tx = tpd.getX();
                        ty = tpd.getY();
                        tailPoints.add(tpd);
                        break;
                    case "L":
                        --hx;
                        Point tpl = moveTail(hx, hy, tx, ty);
                        tx = tpl.getX();
                        ty = tpl.getY();
                        tailPoints.add(tpl);
                        break;
                    case "R":
                        ++hx;
                        Point tpr = moveTail(hx, hy, tx, ty);
                        tx = tpr.getX();
                        ty = tpr.getY();
                        tailPoints.add(tpr);
                        break;
                    default:
                        System.out.println("Uh Oh");
                        break;
                }
            }
        }

        return tailPoints.size();
    }

    public static Point moveTail(int hx, int hy, int tx, int ty) {
        int dx = tx - hx;
        int dy = ty - hy;

        if (dx == -2) {
            if (dy == 0) {
                ++tx;
            } else if (dy == -1) {
                ++tx;
                ++ty;
            } else if (dy == 1) {
                ++tx;
                --ty;
            }
        } else if (dx == 2) {
            if (dy == 0) {
                --tx;
            } else if (dy == -1) {
                --tx;
                ++ty;
            } else if (dy == 1) {
                --tx;
                --ty;
            }
        }

        if (dy == -2) {
            if (dx == 0) {
                ++ty;
            } else if (dx == 1) {
                ++ty;
                --tx;
            } else if (dx == -1) {
                ++ty;
                ++tx;
            }
        } else if (dy == 2) {
            if (dx == 0) {
                --ty;
            } else if (dx == 1) {
                --ty;
                --tx;
            } else if (dx == -1) {
                --ty;
                ++tx;
            }
        }

        return new Point(tx, ty);
    }

    public static int problem2() throws FileNotFoundException {
        Scanner moveReader;
        if (GIVENS_2) {
            moveReader = new Scanner(new File("Day9/given2.txt"));
        } else {
            moveReader = new Scanner(new File("Day9/input.txt"));
        }

        int[][] knotPoints = new int[10][2];
        Set<Point> tailPoints = new HashSet<>();
        tailPoints.add(new Point(0, 0));

        while (moveReader.hasNextLine()) {
            String dir = moveReader.next();
            int mag = moveReader.nextInt();
            for (int i = 0; i < mag; ++i) {
                switch (dir) {
                    case "U":
                        ++knotPoints[HEAD][Y];
                        moveTail(knotPoints);
                        break;
                    case "D":
                        --knotPoints[HEAD][Y];
                        moveTail(knotPoints);
                        break;
                    case "L":
                        --knotPoints[HEAD][X];
                        moveTail(knotPoints);
                        break;
                    case "R":
                        ++knotPoints[HEAD][X];
                        moveTail(knotPoints);
                        break;
                    default:
                        break;
                }
                tailPoints.add(new Point(knotPoints[TAIL][X], knotPoints[TAIL][Y]));
            }
        }
        return tailPoints.size();
    }

    public static void moveTail(int[][] knotPoints) {
        int currHead = HEAD;
        for (int i = currHead + 1; i < knotPoints.length; ++i) {
            int hx = knotPoints[currHead][X];
            int hy = knotPoints[currHead][Y];
            int nx = knotPoints[currHead + 1][X];
            int ny = knotPoints[currHead + 1][Y];

            int dx = nx - hx;
            int dy = ny - hy;

            if (dx <= -2) {
                if (dy == 0) {
                    ++nx;
                } else if (dy <= -1) {
                    ++nx;
                    ++ny;
                } else if (dy >= 1) {
                    ++nx;
                    --ny;
                }
            } else if (dx >= 2) {
                if (dy == 0) {
                    --nx;
                } else if (dy <= -1) {
                    --nx;
                    ++ny;
                } else if (dy >= 1) {
                    --nx;
                    --ny;
                }
            } else if (dy <= -2) {
                if (dx == 0) {
                    ++ny;
                } else if (dx >= 1) {
                    ++ny;
                    --nx;
                } else if (dx <= -1) {
                    ++ny;
                    ++nx;
                }
            } else if (dy >= 2) {
                if (dx == 0) {
                    --ny;
                } else if (dx >= 1) {
                    --ny;
                    --nx;
                } else if (dx <= -1) {
                    --ny;
                    ++nx;
                }
            }
            knotPoints[currHead + 1][X] = nx;
            knotPoints[currHead + 1][Y] = ny;
            ++currHead;
        }
    }
}
