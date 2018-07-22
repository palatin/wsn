package model;

public class Point {

    public int x,y,z;

    public Point() {}

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
        this.z = point.z;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;
        Point p = (Point) obj;
        return x == p.x && y == p.y && z == p.z;
    }

    public Point add(float value) {
        this.x += value;
        this.y += value;
        this.z += value;
        return this;
    }

    public Point add(Point point) {
        this.x += point.x;
        this.y += point.y;
        this.z += point.z;
        return this;
    }

    public Point divide(float value) {
        this.x /= value;
        this.y /= value;
        this.z /= value;
        return this;
    }

    public Point divide(Point point) {
        this.x /= point.x;
        this.y /= point.y;
        this.z /= point.z;
        return this;
    }


    public Point copy() {
        return new Point(x, y, z);
    }
}
