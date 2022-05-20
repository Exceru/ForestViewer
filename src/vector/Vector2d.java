package vector;

public class Vector2d extends Vector {
    public Vector2d(double x, double y) {
        super(x, y);
    }

    public double getX() {
        return x.doubleValue();
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y.doubleValue();
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean equals(Vector2d v) {
        return (this.x.equals(v.getX()) && this.y.equals(v.getY()));
    }
}
