package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static final boolean GIVENS = false;

    public static int total1 = 0;
    public static int freeSpace = 0;
    public static final int GOAL = 30_000_000;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(problem1());
        System.out.println(problem2());
    }

    public static int problem1() throws FileNotFoundException {
        Scanner cmdReader;
        if (GIVENS) {
            cmdReader = new Scanner(new File("Day7/given.txt"));
        } else {
            cmdReader = new Scanner(new File("Day7/input.txt"));
        }

        Directory head = populate(cmdReader);

        getSizes(head);

        return total1;
    }

    public static int problem2() throws FileNotFoundException {
        Scanner cmdReader;
        if (GIVENS) {
            cmdReader = new Scanner(new File("Day7/given.txt"));
        } else {
            cmdReader = new Scanner(new File("Day7/input.txt"));
        }

        Directory head = populate(cmdReader);
        getFreeSpace(head);
        int smallest = findSmallestDir(head);

        return smallest;
    }

    public static void getFreeSpace(Directory head) {
        freeSpace = 70_000_000 - head.getTotalSize();
    }

    public static int findSmallestDir(Directory head) {
        Map<String, Directory> dirs = head.getDirectories();
        int minSize = 70_000_000;
        for (String name : dirs.keySet()) {
            Directory d = dirs.get(name);
            int thisSize = d.getTotalSize();
            int smallSize = findSmallestDir(d);
            int test1 = thisSize + freeSpace;
            int test2 = smallSize + freeSpace;
            if (test1 > GOAL && (test1 < test2 || smallSize == -1)) {
                return thisSize;
            } else if (test2 > GOAL && (test2 < test1 || smallSize == -1)) {
                return smallSize;
            }
        }
        return -1;
    }

    public static Directory populate(Scanner cmdReader) {
        Directory head = new Directory(null, "/");
        cmdReader.nextLine();
        cmdReader.nextLine();
        while (cmdReader.hasNextLine()) {
            Scanner output = new Scanner(cmdReader.nextLine());
            String cmdOrSize = output.next();
            if (cmdOrSize.equals("$")) {
                cmdReader.useDelimiter("\\A");
                output.useDelimiter("\\A");
                goThroughCommands(new Scanner(cmdOrSize + output.next() + "\n" + cmdReader.next()), head);
            } else {
                String name = output.next();
                if (cmdOrSize.equals("dir")) {
                    head.addDirectory(name);
                } else {
                    head.addFile(name, Integer.parseInt(cmdOrSize));
                }
            }
        }
        return head;
    }

    public static void goThroughCommands(Scanner cmds, Directory curr) {
        cmds.useDelimiter("\\s");
        while (cmds.hasNextLine()) {
            Scanner line = new Scanner(cmds.nextLine());
            String cmdOrSize = line.next();
            if (cmdOrSize.equals("$")) {
                // line is a command

                String cmd = line.next();
                if (cmd.equals("cd")) {
                    // cd command

                    String dir = line.next();
                    if (dir.equals("..")) {
                        // .. go back a directory and continue

                        goThroughCommands(getRestOfContent(cmds), curr.getParent());
                    } else {
                        // go into the given directory

                        goThroughCommands(getRestOfContent(cmds), curr.getChild(dir));
                    }
                } else if (cmd.equals("ls")) {
                    // ls command

                    while (cmds.hasNextLine()) {
                        String ls = cmds.nextLine();
                        if (ls.charAt(0) == '$') {
                            // go until next command, then continue through

                            goThroughCommands(getRestOfContent(cmds, ls), curr);
                        } else {
                            // directory or file

                            Scanner lsReader = new Scanner(ls);
                            if (lsReader.hasNextInt()) {
                                // line is a file

                                int size = lsReader.nextInt();
                                String name = lsReader.next();
                                curr.addFile(name, size);
                            } else {
                                // line is a directory

                                lsReader.next();
                                String name = lsReader.next();
                                curr.addDirectory(name);
                            }
                        }
                    }
                }
            } else {
                System.out.println("Whoops shouldn't be here");
            }
        }
    }

    public static Scanner getRestOfContent(Scanner s) {
        s.useDelimiter("\\A");
        return new Scanner(s.next());
    }

    public static Scanner getRestOfContent(Scanner s, String prev) {
        s.useDelimiter("\\A");
        return new Scanner(prev + "\n" + s.next());
    }

    public static void getSizes(Directory head) {
        int total = 0;
        Map<String, Directory> dirs = head.getDirectories();
        Map<String, Integer> files = head.getFiles();
        for (String name : dirs.keySet()) {
            Directory d = dirs.get(name);
            getSizes(d);
            // if (d.getTotalSize() <= 100000) {
            //     System.out.println("Adding " + d.getName() + " to " + total1 + " = " + (total1 + total));
            //     total1 += d.getTotalSize();
            // }
            total += d.getTotalSize();
        }
        
        for (String name : files.keySet()) {
            total += files.get(name);
        }
        // System.out.println("Size of " + head.getName() + " = " + total);

        if (total <= 100000) {
            // System.out.println("Adding " + head.getName() + " to " + total1 + " = " + (total1 + total));
            total1 += total;
        }
    }
}
