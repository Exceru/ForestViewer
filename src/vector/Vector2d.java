package vector;

/**
 * Class of the double-version of the {@link Vector2} class.
 */
public class Vector2d extends Vector2 {
    /**
     * Sets the x and y components given double values.
     * @param x X component
     * @param y Y component
     */
    public Vector2d(double x, double y) {
        super(x, y);
    }

    /**
     * Returns the x component of the vector.
     * @return x component as double value.
     */
    public double getX() {
        return x.doubleValue();
    }


    /**
     * Sets the x component of the vector.
     * @param x Double value to be set as x component.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Returns the y component of the vector.
     * @return y component as double value.
     */
    public double getY() {
        return y.doubleValue();
    }

    /**
     * Sets the y component of the vector.
     * @param y Double value to be set as y component.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Will compare the equality of two double vectors.
     * @param v Another vector.
     * @return True if equal, false if unequal.
     */
    public boolean equals(Vector2d v) {
        return (this.x.equals(v.getX()) && this.y.equals(v.getY()));
    }
}
