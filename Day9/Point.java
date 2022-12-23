package Day9;

public class Point {
    private int x;
    private int y;

    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Point p = (Point) o;
        return this.getX() == p.getX() && this.getY() == p.getY();
    }

    @Override
    public int hashCode() {
        return (53 + this.getX()) * 53 + this.getY();
    }
}
