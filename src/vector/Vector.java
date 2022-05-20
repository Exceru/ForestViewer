package vector;

import java.util.Comparator;

abstract public class Vector<T extends Number> {
    protected T x;
    protected T y;

    public Vector(T x, T y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
