package Day7;

import java.util.HashMap;
import java.util.Map;

public class Directory {
    private Map<String, Directory> dirs;
    private Map<String, Integer> files;
    private Directory parent;
    private String name;

    public Directory(Directory parent, String name) {
        this.name = name;
        this.parent = parent;
        dirs = new HashMap<>();
        files = new HashMap<>();
    }

    public void addDirectory(String name) {
        dirs.put(name, new Directory(this, name));
    } 

    public void addFile(String name, int size) {
        files.put(name, size);
    }

    public Directory getParent() {
        return parent;
    }

    public Directory getChild(String name) {
        return dirs.get(name);
    }

    public int getTotalSize() {
        int sum = 0;
        for (String name : dirs.keySet()) {
            sum += dirs.get(name).getTotalSize();
        }
        for (String name : files.keySet()) {
            sum += files.get(name);
        }

        return sum;
    }

    public Map<String, Directory> getDirectories() {
        return this.dirs;
    }

    public Map<String, Integer> getFiles() {
        return this.files;
    }

    public String getName() {
        return this.name;
    }

}