package vector;

/**
 * Class of the integer-version of the {@link Vector} class.
 */
public class Vector2i extends Vector {
    /**
     * Sets the x and y components given integer values.
     * @param x X component
     * @param y Y component
     */
    public Vector2i(int x, int y) {
        super(x, y);
    }

    /**
     * Returns the x component of the vector.
     * @return x component as integer value.
     */
    public int getX() {
        return x.intValue();
    }

    /**
     * Sets the x component of the vector.
     * @param x Integer value to be set as x component.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y component of the vector.
     * @return y component as integer value.
     */
    public int getY() {
        return y.intValue();
    }

    /**
     * Sets the y component of the vector.
     * @param y Integer value to be set as y component.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Will compare the equality of two integer vectors.
     * @param v Another vector.
     * @return True if equal, false if unequal.
     */
    public boolean equals(Vector2i v) {
        return (this.x.equals(v.getX()) && this.y.equals(v.getY()));
    }
}
