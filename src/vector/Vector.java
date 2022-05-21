package vector;

/**
 * Generic, abstract class on which a 2-dimensional vector can be based on.
 * @param <T> Generic type which must be a {@link Number}
 */
abstract public class Vector<T extends Number> {
    /**
     * X component of the vector.
     */
    protected T x;
    /**
     * Y component of the vector.
     */
    protected T y;

    /**
     * Sets the value of the vector.
     * @param x X component
     * @param y Y component
     */
    public Vector(T x, T y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Will print the value of a vector in the form of (x, y).
     * @return Value of the vector.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
