package vector;

public class Vector2i extends Vector {
    public Vector2i(int x, int y) {
        super(x, y);
    }

    public int getX() {
        return x.intValue();
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y.intValue();
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Vector2i v) {
        return (this.x.equals(v.getX()) && this.y.equals(v.getY()));
    }
}
