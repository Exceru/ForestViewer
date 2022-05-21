package vector;

/**
 * Class for useful general purpose math regarding vectors.
 */
public class VectorMath {
    /**
     * Implements the mathematical lerp function.
     * @param start starting value
     * @param end end value
     * @param t alpha, the amount to interpolate by
     * @return value: start <= value <= end.
     */
    public static double lerp(double start, double end, double t) {
        return start + t * (end - start);
    }

    /**
     * Implements the lerp function for two vectors.
     * @param start starting vector
     * @param end end vector
     * @param t alpha, the amount to interpolate by
     * @return the interpolated vector between start and end
     */
    public static Vector2d lerp(Vector2d start, Vector2d end, double t){
        Vector2d result = new Vector2d(0.0,0.0);
        result.setX(lerp(start.getX(), end.getX(), t));
        result.setY(lerp(start.getY(), end.getY(), t));

        return result;
    }

    /**
     * Adds two integer vectors and returns a new vector.
     * @param v1 First vector
     * @param v2 Second vector
     * @return result vector
     */
    public static Vector2i add(Vector2i v1, Vector2i v2){
        return new Vector2i(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    /**
     * Adds two double vectors and returns a new vector.
     * @param v1 First vector
     * @param v2 Second vector
     * @return result vector
     */
    public static Vector2d add(Vector2d v1, Vector2d v2){
        return new Vector2d(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    /**
     * Subtracts two integer vectors and returns a new vector.
     * @param v1 First vector
     * @param v2 Second vector
     * @return result vector
     */
    public static Vector2i sub(Vector2i v1, Vector2i v2){
        return new Vector2i(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    /**
     * Subtracts two double vectors and returns a new vector.
     * @param v1 First vector
     * @param v2 Second vector
     * @return result vector
     */
    public static Vector2d sub(Vector2d v1, Vector2d v2){
        return new Vector2d(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    /**
     * Will convert a vector of type integer to a type of double.
     * @param v Vector of type integer to convert
     * @return returns a vector of type double.
     */
    public static Vector2d convertTo2d(Vector2i v) {
        return new Vector2d(v.getX(), v.getY());
    }

    /**
     * Will round a vector using {@link Math#round(float)}.
     * @param v Vector to round.
     * @return Rounded vector of type double.
     */
    public static Vector2d round(Vector2d v){
        return new Vector2d(Math.round(v.getX()), Math.round(v.getY()));
    }

    /**
     * Will generate a random vector between (inclusive) two vectors.
     * @param min The minimum of the random vector.
     * @param max The maximum of the random vector.
     * @return Random vector between min and max.
     */
    public static Vector2i getRandomVector2i(Vector2i min, Vector2i max){
        int randomX = (int) ((Math.random() * (max.getX() - min.getX())) + min.getX());
        int randomY = (int) ((Math.random() * (max.getY() - min.getY())) + min.getY());

        return new Vector2i(randomX,randomY);
    }

}
